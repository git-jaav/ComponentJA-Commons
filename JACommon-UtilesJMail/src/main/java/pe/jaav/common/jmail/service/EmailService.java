package pe.jaav.common.jmail.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import pe.jaav.common.jmail.model.Email;
import pe.jaav.common.jmail.util.Utiles;

public class EmailService {

	/**Envía el correo electrónico de acuerdo a los parámetros Stnadar  de correo, un Map de las Variables y sus
	 * respectivos valores y todo el esquema del mensaje de correo (PLANTILLA)
	 * DEVUELVE:
	 * 1:	si el envío fu exitos
	 * 0:	si hubo una excepción
	 * -1:	Si no se recibió una plantilla válida
	 * -2:	No se encontraron correos destino
	 * -3:	No se encontró un motivo del correo (SUBJECT)
	 * -4:	No se construyó el objeto de Correo. (Algún parámetro erróneo)
	 * -5:	No se pudo enviar el correo. Sucedió un error en el envío.
	 * */
	public static int enviarEmailPlantilla(String bodyPlantilla,List<String> correoDestinos,String subject, 
			List<String>  pathFilesAtached, Date correoFecha , Map<String,String> mapvariablesValores){
		int result =0;
		try{
			if(bodyPlantilla!=null && !"".equals(bodyPlantilla+"")){
				if(correoDestinos!=null){
					if(correoDestinos.size()>0){
						if(subject!=null && !"".equals(subject+"")){
							Email email = construirMail(correoDestinos, subject, bodyPlantilla, pathFilesAtached, correoFecha, mapvariablesValores);
							email = EmailService.setEmailVariablesGenerales(email);
							if(email!=null){
								if(EmailService.enviarEmail(email)){
									result =1;	
								}else{
									result =-5;	
								}
							}else{
								result =-4;
							}			
						}else{
							result =-3;
						}
					}else{
						result =-2;
					}
				}else{
					result =-2;
				}
			}else{
				result =-1;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			result =0;
		}
		return result;
	}
		

	/**Envía el correo electrónico de acuerdo a los parámetros estandar  de correo
	 * @param email: el objeto del Email
	 * @param contienePlantilla: si posee o no plantilla de correo
	 * @return
	 * 1:	si el envío fu exitos
	 * 0:	si hubo una excepción
	 * -1:	Si no se recibió una plantilla válida
	 * -2:	No se encontraron correos destino
	 * -3:	No se encontró un motivo del correo (SUBJECT)
	 * -4:	No se recibió el objeto de Correo. (Algún parámetro erróneo)
	 * -5:	No se pudo enviar el correo. Sucedió un error en el envío.	 * 
	 */
	public static int enviarEmail(Email email,boolean contienePlantilla){
		return enviarEmail(email, contienePlantilla,false);
	}

	/**Envía el correo electrónico de acuerdo a los parámetros estandar  de correo
	 * Con la implementacion de SES de AWS
	 * @param email: el objeto del Email
	 * @param contienePlantilla: si posee o no plantilla de correo
	 * @return
	 * 1:	si el envío fu exitos
	 * 0:	si hubo una excepción
	 * -1:	Si no se recibió una plantilla válida
	 * -2:	No se encontraron correos destino
	 * -3:	No se encontró un motivo del correo (SUBJECT)
	 * -4:	No se recibió el objeto de Correo. (Algún parámetro erróneo)
	 * -5:	No se pudo enviar el correo. Sucedió un error en el envío.	 * 
	 */
	public static int enviarEmailAWS(Email email,boolean contienePlantilla){
		return enviarEmail(email, contienePlantilla,true);
	}
	
	/**Envía el correo electrónico de acuerdo a los parámetros estandar  de correo
	 * @param email: el objeto del Email
	 * @param contienePlantilla: si posee o no plantilla de correo
	 * @return
	 * 1:	si el envío fu exitos
	 * 0:	si hubo una excepción
	 * -1:	Si no se recibió una plantilla válida
	 * -2:	No se encontraron correos destino
	 * -3:	No se encontró un motivo del correo (SUBJECT)
	 * -4:	No se recibió el objeto de Correo. (Algún parámetro erróneo)
	 * -5:	No se pudo enviar el correo. Sucedió un error en el envío.	 * 
	 */
	public static int enviarEmail(Email email,boolean contienePlantilla, boolean implemntarAwsMail){
		int result =0;
		try{			
			if(email!=null){				
				if(contienePlantilla){
					//Tratamiento si hubiese
					email = EmailService.construirMail(email, email.getMapvariablesValores());
				}else{
				}
				
				if(email.getBody()!=null && !"".equals(email.getBody()+"")){
					if(email.getListCorreoDestinos()!=null){
						if(email.getListCorreoDestinos().size()>0){
							if(email.getSubject()!=null && !"".equals(email.getSubject()+"")){
								/* VERIFICAR TIPO DE IMPLEMENTACION*/
								if(implemntarAwsMail) {
									//AWS - SES
									if(EmailService.enviarEmailAWS(email)){
										result =1;	
									}else{
										result =-5;	
									}
								}else {
									//DEFAULT
									if(EmailService.enviarEmail(email)){
										result =1;	
									}else{
										result =-5;	
									}
								}														
							}else{
								result =-3;
							}
						}else{
							result =-2;
						}
					}else{
						result =-2;
					}
				}else{
					result =-1;
				}
			}else{
				result =-4;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			result =0;
		}
		return result;
	}	
	
	
	/** Implementacion MAIL SES
	 * @param email
	 * @return
	 */
	public static  boolean enviarEmailAWS(Email email) {
		EmailServiceAWS sesEmail = new EmailServiceAWS();
		sesEmail.setParametrosEmail(email);
		try {
			return sesEmail.sendEmail();
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		return false;
	}
	
	public static Email construirMail(Email objEmail, Map<String,String> mapvariablesValores){		
		/***VARIABLES PROPIOS DE LA TAREA:*/
		if(objEmail!=null){
			String bodyPlantilla=objEmail.getBody();
			if(mapvariablesValores!=null && bodyPlantilla!=null){
				Collection<String> collKey =  mapvariablesValores.keySet();
				for(String key :collKey){	
					String valor = mapvariablesValores.get(key);
					if(valor!=null){
						bodyPlantilla = bodyPlantilla.replaceAll(key, valor);	
					}else{
						bodyPlantilla = bodyPlantilla.replaceAll(key,"");
					}
						
				}
			}
			objEmail.setBody(bodyPlantilla);
		}
		return objEmail;
	}
	
	public static Email setEmailVariablesGenerales(Email objEmail){		
		/***VARIABLES PROPIOS DE LA TAREA:*/
		if(objEmail!=null){
			String bodyPlantilla=objEmail.getBody();					
			//mapvariablesGeneralesValores.put(FacesUtil.getPropertyCorreo("PAR_USUARIO"),"");
			objEmail.setBody(bodyPlantilla);
		}
		return objEmail;
	}
	
	/**construir EMAIL CON VALORES DE PLANTILLA*/
	public static Email construirMail(List<String> listCorreoDestinos,String subject, String bodyPlantilla, 
			List<String>  listPathFileAtached, Date correoFecha , Map<String,String> mapvariablesValores){
		Email email = null;
		try{
			//System.out.println("TEST LLEGA EVENTO USUARIO 2222");
			/******************************/			
			email= new Email();			
			email.setListCorreoDestinos(listCorreoDestinos);
			email.setSubject(subject);			
			//armar CUERPO: reemplazamos las variables que existiesen por los valores:						
			/***VARIABLES GENERALES*/
			//PlantillaCorreo = PlantillaCorreo.replaceAll(FacesUtil.getPropertyCorreo("PAR_FECHA_ACTUAL"),Utiles.printDate("dd/MM/yyyy",Utiles.getFechaHoy()));						
			email.setBody(bodyPlantilla);			
			email.setListpathFilesDestinos(listPathFileAtached);
			email.setCorreoFecha(Utiles.getFechaHoy());
			email = construirMail(email, mapvariablesValores);
			
			/******************************/
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return email;
	}
	/**construir EMAIL SIN VALORES DE PLANTILLA*/
	public static Email construirMail(List<String> listCorreoDestinos,String subject, String body, 
			List<String>  listPathFileAtached, Date correoFecha){
	Email email = null;
	try{
		//System.out.println("TEST LLEGA EVENTO USUARIO 2222");
		/******************************/			
		email= new Email();			
		email.setListCorreoDestinos(listCorreoDestinos);
		email.setSubject(subject);			
		//armar CUERPO: reemplazamos las variables que existiesen por los valores:						
		/***VARIABLES GENERALES*/
		//PlantillaCorreo = PlantillaCorreo.replaceAll(FacesUtil.getPropertyCorreo("PAR_FECHA_ACTUAL"),Utiles.printDate("dd/MM/yyyy",Utiles.getFechaHoy()));						
		email.setBody(body);			
		email.setListpathFilesDestinos(listPathFileAtached);
		email.setCorreoFecha(Utiles.getFechaHoy());
		
		
		/******************************/
	}catch(Exception ex){
		ex.printStackTrace();
	}		
	return email;
}

	
	
	public static Email setEmailParametrosConexion(Email email,
			String correoRemitente,String correoClave,String correoHost,String correoPort
			,String correoSocketPort,String activoTTL,String correoCuenta){
		if(email!=null){
			email.setCORREO_USAURIO(correoRemitente);
			email.setCORREO_CLAVE(correoClave);
			email.setCORREO_HOST(correoHost);
			email.setCORREO_PORT(correoPort);
			email.setCORREO_SOCKETPORT(correoSocketPort);
			//email.setCORREO_ACTIVO_TTL(isActivoTTL?UtilesCommons.SI_db:UtilesCommons.NO_db);
			email.setCORREO_ACTIVO_TTL(activoTTL);
			email.setCORREO_ACCOUNT(correoCuenta);
			email.setParametrosConexionSet(true);
		}
		return email;
	}
			
	
	/**llamada al Servicio de correo con la configuración propia del JavaMail */
	public static boolean enviarEmail(Email email){
		/***********************
		 * prueba envío correo
		***************************/
		try{
			if(email!=null){
				String correoUser= email.getCORREO_USAURIO();
				String correoPw= email.getCORREO_CLAVE();
				String correoHost= email.getCORREO_HOST();
				String correoPort= email.getCORREO_PORT();
				String correoSPort= email.getCORREO_SOCKETPORT();
				String correoTTL = email.getCORREO_ACTIVO_TTL();
				String correoAccount = email.getCORREO_ACCOUNT();
				
				EmailServiceGen objMail = new EmailServiceGen(correoUser,correoPw);
				objMail.setParametrosMail(correoHost, correoPort, correoSPort,correoTTL);
				//objMail.setUserpassw();
				if(email.getListCorreoDestinos()!=null){
					String[] toArray = new String[email.getListCorreoDestinos().size()];					
					int i=0;
					for(String correo: email.getListCorreoDestinos()){
						toArray[i] = correo;
						i++;
					}					
					objMail.setTo(toArray);
				}else if(email.getTotalCorreoDestinos()!=null){
					objMail.setTo(email.getTotalCorreoDestinos());	
				}
				
								
				objMail.setFrom(correoUser);										
				objMail.setAccount(correoAccount);
				objMail.setSubject(email.getSubject());				
				objMail.setBody(email.getBody());
				if(email.getPathFileAttached()!=null){
					objMail.addAttachment(email.getPathFileAttached());
				}
				if(email.getListpathFilesDestinos()!=null){
					objMail.addAttachment(email.getListpathFilesDestinos());
				}				
				objMail.send();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			//logger.error(e.getMessage());
		}
		return false;
	}
}
