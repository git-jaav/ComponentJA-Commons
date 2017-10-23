package pe.jaav.common.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.util.JSONPObject;


public class UtilesJSON  {		
	
	private static final String RQ_MET_GET="GET";
	private static final String RQ_MET_POST="POST";
//	private static final String RQ_MET_PUT="PUT";
//	private static final String RQ_MET_DELETE="DELETE";
	
	private static final String RQ_PROP_APPJSON="application/json";
	
	
	public static final String TAG_CODE_RESPONSE_ERROR="FAILED-CODE:";
	
	/**
	 * @param urlParam
	 * @return
	 */
	public static String getJson(String urlParam) {
		return getJson(urlParam,true);
	}
	
	/**
	 * @param urlParam
	 * @param claseObjeto
	 * @return
	 */
	public static String getJson(String urlParam,boolean evitarResponseCode) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlParam);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(RQ_MET_GET);
			conn.setRequestProperty("Accept", RQ_PROP_APPJSON);

			if (conn.getResponseCode() !=HttpURLConnection.HTTP_OK) {
				if(evitarResponseCode){
					return null;
				}else{
					throw new RuntimeException(TAG_CODE_RESPONSE_ERROR+ conn.getResponseCode());
				}																		
			}
			
			//para obtener el Texto en el ENCODING correcto (UTF-8)     	    			
			String json = getJSON_Encoding(conn, "UTF-8");		
			
			if(json!=null){						
				conn.disconnect();	
				return json; 									 		
			}		
		} catch (MalformedURLException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		} finally{
			if(conn !=null){
				conn.disconnect();	
			}
		}
		return null;
	}
	
	/**
	 * @param urlParam
	 * @param claseObjeto
	 * @return
	 */
	public static Object getObjectJson(String urlParam,  Class<?> claseObjeto) {
		return getObjectJson(urlParam, claseObjeto,true);
	}
	
	/**
	 * @param urlParam
	 * @param claseObjeto
	 * @return
	 */
	public static Object getObjectJson(String urlParam,  Class<?> claseObjeto,boolean evitarResponseCode) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlParam);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(RQ_MET_GET);
			conn.setRequestProperty("Accept", RQ_PROP_APPJSON);

			if (conn.getResponseCode() !=HttpURLConnection.HTTP_OK) {
				if(evitarResponseCode){
					return null;
				}else{
					throw new RuntimeException(TAG_CODE_RESPONSE_ERROR+ conn.getResponseCode());
				}																		
			}							
			
			//para obtener el Texto en el ENCODING correcto (UTF-8)
			InputStream in = conn.getInputStream();
	        String encoding = conn.getContentEncoding();	        
	        encoding = encoding == null ? "UTF-8" : encoding; 
	        String json = IOUtils.toString(in, encoding);  	        	    
			
			if(json!=null){
				//MAPPER JASON TO OBJECT 
				ObjectMapper mapper = new ObjectMapper();				
				Object objeto =  mapper.readValue(json,claseObjeto);			
				conn.disconnect();	
				return objeto; 				
			}
		} catch (MalformedURLException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		} finally{
			if(conn !=null){
				conn.disconnect();	
			}
		}
		return null;
	}

	/**
	 * @param urlParam
	 * @param objData
	 * @param claseObjeto
	 * @return
	 */
	public static Object getObjectJsonFiltro(String urlParam,Object objData,  Class<?> claseObjeto) {
		return getObjectJsonFiltro(urlParam, objData, claseObjeto, true);
	}
	
	/**
	 * @param urlParam
	 * @param objData
	 * @param claseObjeto
	 * @return
	 */
	public static Object getObjectJsonFiltro(String urlParam,Object objData,  Class<?> claseObjeto,boolean evitarResponseCode) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlParam);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(RQ_MET_POST);
			conn.setRequestProperty("Content-Type", RQ_PROP_APPJSON);

			
			//MAPPER OBJECT TO JASON
			ObjectMapper mapper = new ObjectMapper();
			byte[] jsonInBytes = mapper.writeValueAsBytes(objData);
			/////////////////////////
			
			OutputStream os = conn.getOutputStream();
			os.write(jsonInBytes);
			os.flush();
			
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK
					&& conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED
					) {
				if(evitarResponseCode){
					return null;
				}else{
					throw new RuntimeException(TAG_CODE_RESPONSE_ERROR+ conn.getResponseCode());
				}										
			}

			//para obtener el Texto en el ENCODING correcto (UTF-8)
			InputStream in = conn.getInputStream();
	        String encoding = conn.getContentEncoding();	        
	        encoding = encoding == null ? "UTF-8" : encoding; 
	        String json = IOUtils.toString(in, encoding);  	       
			
			if(json!=null){			
				//json = json.toLowerCase();//AUX
				//MAPPER JASON TO OBJECT 
				ObjectMapper mapperObj = new ObjectMapper();				
				Object objeto =  mapperObj.readValue(json,claseObjeto);			
				conn.disconnect();	
				return objeto; 									 		
			}
		} catch (MalformedURLException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		} finally{
			if(conn !=null){
				conn.disconnect();	
			}
		}
		return null;
	}
	
	/**
	 * @param urlParam
	 * @param dataJson
	 * @return
	 */
	public static String getJsonFiltro(String urlParam,String dataJson) {
		return getJsonFiltro(urlParam, dataJson, true);
	}
	
	/**
	 * @param urlParam
	 * @param dataJson
	 * @return
	 */
	public static String getJsonFiltro(String urlParam,String dataJson,boolean evitarResponseCode) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlParam);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(RQ_MET_POST);
			conn.setRequestProperty("Content-Type", RQ_PROP_APPJSON);

			
			//MAPPER OBJECT TO JASON
			//ObjectMapper mapper = new ObjectMapper();
			//JSONObject json = new JSONObject(objData);						
			
			//byte[] jsonInBytes = mapper.writeValueAsBytes(objData);
			byte[] jsonInBytes = dataJson.getBytes();
			/////////////////////////
			
			OutputStream os = conn.getOutputStream();
			os.write(jsonInBytes);
			os.flush();			
			

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK
					&& conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED
					) {
				if(evitarResponseCode){
					return null;
				}else{
					throw new RuntimeException(TAG_CODE_RESPONSE_ERROR+ conn.getResponseCode());
				}										
			}

			//para obtener el Texto en el ENCODING correcto (UTF-8)
			String json = getJSON_Encoding(conn, "UTF-8");		
			
			if(json!=null){						
				conn.disconnect();	
				return json; 									 		
			}
		} catch (MalformedURLException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		} finally{
			if(conn !=null){
				conn.disconnect();	
			}
		}
		return null;
	}
	
	
	/** ...Evita por DEFAULT el response CODE
	 * @param urlParam
	 * @param typeReference
	 * @return
	 */
	public static List<?> getListJson(String urlParam,TypeReference<?> typeReference){
		return getListJson(urlParam, typeReference,true);
	}
			
	
	/**
	 * @param urlParam
	 * @param typeReference
	 * @return
	 */
	public static List<?> getListJson(String urlParam,TypeReference<?> typeReference
			,boolean evitarResponseCode) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlParam);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(RQ_MET_GET);
			conn.setRequestProperty("Accept", RQ_PROP_APPJSON);

			if (conn.getResponseCode() !=HttpURLConnection.HTTP_OK) {
				if(evitarResponseCode){
					return null;
				}else{
					throw new RuntimeException(TAG_CODE_RESPONSE_ERROR+ conn.getResponseCode());
				}																		
			}				

		
			
			//para obtener el Texto en el ENCODING correcto (UTF-8)
			InputStream in = conn.getInputStream();
	        String encoding = conn.getContentEncoding();	        
	        encoding = encoding == null ? "UTF-8" : encoding; 
	        String json = IOUtils.toString(in, encoding);  	        	    

			//BufferedReader br = new BufferedReader(new InputStreamReader(
				//(conn.getInputStream())));																
			
			if(json!=null){
				//MAPPER JASON TO OBJECT 
				ObjectMapper mapper = new ObjectMapper();				
				List<?> lista = mapper.readValue(json,typeReference);				
				conn.disconnect();	
				return lista; 				
			}
		} catch (MalformedURLException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		} finally{
			if(conn !=null){
				conn.disconnect();	
			}
		}
		return null;
	}
	
	
	/** Abstraccion
	 * @param urlParam
	 * @param objData
	 * @param claseObjeto
	 * @return
	 */
	public static List<?> getListJsonFiltro(String urlParam,Object objData,Class<?> claseObjeto,boolean evitarResponseCode) {
		return getListJsonFiltro(urlParam, objData, new TypeReference<List<?>>() {},evitarResponseCode);
	}
	

	/** Obtiene una lISTA JSON modo POST para enviar UN PARAM ...Por defecto evita el RESPONSE CODE
	 * @param urlParam
	 * @param objData
	 * @param typeReference
	 * @return
	 */
	public static List<?> getListJsonFiltro(String urlParam,Object objData,TypeReference<?> typeReference
			) {
		return getListJsonFiltro(urlParam, objData, new TypeReference<List<?>>() {},true);
	}
	
	
	/** Obtiene una lISTA JSON modo POST para enviar UN PARAM ... Permite evitar o no el RESPONSE CODE
	 * @param urlParam
	 * @param objData
	 * @param typeReference
	 * @param evitarResponseCode
	 * @return
	 */
	public static List<?> getListJsonFiltro(String urlParam,Object objData,TypeReference<?> typeReference, 
			boolean evitarResponseCode) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlParam);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(RQ_MET_POST);
			conn.setRequestProperty("Content-Type", RQ_PROP_APPJSON);

			
			//MAPPER OBJECT TO JASON
			ObjectMapper mapper = new ObjectMapper();
			byte[] jsonInBytes = mapper.writeValueAsBytes(objData);
			/////////////////////////
			
			OutputStream os = conn.getOutputStream();
			os.write(jsonInBytes);
			os.flush();
			
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK
					&& conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED
					) {
				if(evitarResponseCode){
					return null;
				}else{
					throw new RuntimeException(TAG_CODE_RESPONSE_ERROR+ conn.getResponseCode());
				}										
			}

			//para obtener el Texto en el ENCODING correcto (UTF-8)
			InputStream in = conn.getInputStream();
	        String encoding = conn.getContentEncoding();	        
	        encoding = encoding == null ? "UTF-8" : encoding; 
	        String json = IOUtils.toString(in, encoding);  	       
			
			if(json!=null){
				//MAPPER JASON TO OBJECT
				
				ObjectMapper mapperJsonToObject = new ObjectMapper();								
//				Object obj = mapperJsonToObject.readValue(json,typeReference.getClass());
//				if(obj instanceof List<?>){
//					lista = mapperJsonToObject.readValue(json,typeReference);
//				}else{
//					lista.add(obj);
//				}
				List<?> lista = mapperJsonToObject.readValue(json,typeReference);				
				conn.disconnect();	
				return lista; 				
			}
		} catch (MalformedURLException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		} finally{
			if(conn !=null){
				conn.disconnect();	
			}
		}
		return null;
	}
	
	/**
	 * @param urlParam
	 * @param objData
	 * @param claseObjeto
	 * @return
	 */
	public static Object postObjectJson(String urlParam, Object objData, Class<?> claseObjeto) {		
		try {
			URL url = new URL(urlParam);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(RQ_MET_POST);
			conn.setRequestProperty("Content-Type", RQ_PROP_APPJSON);

			
			//MAPPER OBJECT TO JASON
			ObjectMapper mapper = new ObjectMapper();
			byte[] jsonInBytes = mapper.writeValueAsBytes(objData);
			/////////////////////////
			
			OutputStream os = conn.getOutputStream();
			os.write(jsonInBytes);
			os.flush();
			
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED
					&& conn.getResponseCode() != HttpURLConnection.HTTP_OK
					&& conn.getResponseCode() != HttpURLConnection.HTTP_ACCEPTED
					) {
					throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}

			//para obtener el Texto en el ENCODING correcto (UTF-8)
			InputStream in = conn.getInputStream();
	        String encoding = conn.getContentEncoding();	        
	        encoding = encoding == null ? "UTF-8" : encoding; 
	        String json = IOUtils.toString(in, encoding);  	  
	        
			//BufferedReader br = new BufferedReader(new InputStreamReader(
				//		(conn.getInputStream())));

			//MAPPER JASON TO OBJECT 
			if(json!=null){
				ObjectMapper mapperJsonToObject = new ObjectMapper();			
				Object objetoResult  = mapperJsonToObject.readValue(json,claseObjeto);			
				conn.disconnect();				
				return objetoResult;
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    /**Servicio para obtener un OBJETO JSON*/
    public JSONObject getJSONObject(String urlStr,String indica){
    	JSONObject objeto = null;
    	try {
    		URL url = new URL(urlStr);
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod(RQ_MET_GET);
    		conn.setRequestProperty("Accept",RQ_PROP_APPJSON);

    		if (conn.getResponseCode() != 200) {
    			throw new RuntimeException("Failed : HTTP error code : "
    					+ conn.getResponseCode());
    		}
    		
    		InputStream in = conn.getInputStream(); 
	        String encoding = conn.getContentEncoding();	        
	        encoding = encoding == null ? "UTF-8" : encoding; 
	        String json = IOUtils.toString(in, encoding); 
	        objeto = new JSONObject(json); 	        	     
	        
    		conn.disconnect();
    	} catch (MalformedURLException e) {

    		e.printStackTrace();
    	  } catch (IOException e) {

    		e.printStackTrace();

    	  }
    	return objeto;
    }

    /*** GEENERALES
     * @throws IOException ***/
    
    public static String getJSON_Encoding(HttpURLConnection conn,String encode) throws IOException{
    	
		//para obtener el Texto en el ENCODING correcto (UTF-8)
		InputStream in = conn.getInputStream();
        String encoding = conn.getContentEncoding();	        
        encoding = encoding == null ? encode : encoding; 
        String json = IOUtils.toString(in, encoding);
        
        return json;
    }
    
    /**
     * @param jsonList
     * @return
     */
    public static boolean isJsonList(String jsonList){
    	boolean result = false;
    	/***Primer metodo empirico: Si posee CORCHETES iniciales '[' es una lista de Objetos
    	 * si posee  '{' es una estructura
    	 **/
    	StringTokenizer stToken = new StringTokenizer(jsonList);
    	if(stToken.hasMoreElements()){
    		String token = stToken.nextToken();
    		//logica , siempre en cuando el SOURCE JSON sea válido ....
    		if((""+token.charAt(0)).equals("[")){
    			result = true;
    		}
    	}    	    	
    	return result;
    }
    
    /** Devuelve el elemento (indexNode)  JSON de una lista JSON (jsonList) enviada
     * @param json
     * @param indexNode
     * @return
     */
    public static String getNodeJson(String jsonList, int indexNode){
    	String result = "";    	
    	try {
    		ObjectMapper mapper = new ObjectMapper();    		    		
			JsonNode elemNode = mapper.readTree(jsonList);			
			if(elemNode.size() > indexNode){
				JsonNode node = elemNode.get(indexNode);				
				result = node.toString();				
				//node.as
			}									
			return result;    	
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
    	
    	return result;
    }
    
    public static Map<String,Object> getMapJson(String json){
    	Map<String,Object> mapJson = new HashMap<String,Object>();
    	try {
    		Iterator<String> fields =null;
    		ObjectMapper mapper = new ObjectMapper();
			JsonNode nodeJson = mapper.readTree(json);
			if(nodeJson!=null){
				fields = nodeJson.fieldNames();
				while(fields.hasNext()){
					String field = fields.next();
					if(nodeJson.get(field).isNumber()){
						mapJson.put(field, nodeJson.get(field).decimalValue());
					}else{
						mapJson.put(field, nodeJson.get(field).textValue());	
					}				
				}
			}					
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}       	    	
    	return mapJson;
    }
    
    
    /** Convertir una cadena JSON en un Objeto de una determinada clase: claseObjeto
     * @param json
     * @param claseObjeto
     * @return
     */
    public static Object getParseObjectJson(String json,  Class<?> claseObjeto) {
		if(json!=null){
			try {
				//MAPPER JASON TO OBJECT 
				ObjectMapper mapper = new ObjectMapper();				
				Object objeto;				
				objeto = mapper.readValue(json,claseObjeto);
				return objeto; 
			} catch (JsonParseException e) {			
				e.printStackTrace();
			} catch (JsonMappingException e) {				
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}								
		}    	
		return null;
    }
    public static Iterator<String> getFieldsJson(String json){    	
    	try {
    		ObjectMapper mapper = new ObjectMapper();
			JsonNode nodeJson = mapper.readTree(json);
			if(nodeJson!=null){
				return nodeJson.fieldNames();
			}						
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}   
    	return null;
    }    
    
}
