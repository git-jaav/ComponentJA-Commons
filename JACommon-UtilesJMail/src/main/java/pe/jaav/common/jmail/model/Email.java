package pe.jaav.common.jmail.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Email {

	

	public String CORREO_USAURIO;
	public String CORREO_CLAVE;
	public String CORREO_HOST ;
	public String CORREO_PORT ;
	public String CORREO_SOCKETPORT;
	public String CORREO_ACTIVO_TTL;
	public String CORREO_ACCOUNT;
	
	private Map<String, String> mapvariablesValores;
	
	private String subject;
	private String body;
	
	private String correoDestinos[];
	private String correoDestino;	
	private List<String> listCorreoDestinos;
	
	private Date correoFecha;
	private Date remitente;
	private String pathFileAttached;
	private String pathFilesAttached[];
	private List<String> listpathFilesDestinos;
	
	private boolean parametrosConexionSet;
	private boolean esBodyPlantilla;
	
	public Email() {		
		super();
		this.parametrosConexionSet=false;
		this.esBodyPlantilla=false;
	}


	public void addListCorreoDestinos(String correoDestino) {
		if(listCorreoDestinos==null){
			listCorreoDestinos = new ArrayList<String>();
		}
		listCorreoDestinos.add(correoDestino);		
	}
	
	public String[]  getTotalCorreoDestinos() {
		String correoDestinosTemp[] = null;		
		if(listCorreoDestinos==null){
			listCorreoDestinos = new ArrayList<String>();
		}				
		if(correoDestino!=null){			
			listCorreoDestinos.add(correoDestino);
		}
		if(correoDestinos!=null){
			for(int i=0;i< correoDestinos.length;i++){
				listCorreoDestinos.add(correoDestinos[i]);
			}			
		}
		if(listCorreoDestinos.size()>0){
			correoDestinosTemp = new String [listCorreoDestinos.size()];
			for(int i=0; i<listCorreoDestinos.size();i++){
				correoDestinosTemp[i] =  listCorreoDestinos.get(i);
			}
		}
		return correoDestinosTemp;
	}



	public String[] getCorreoDestinos() {
		return correoDestinos;
	}



	public void setCorreoDestinos(String[] correoDestinos) {
		this.correoDestinos = correoDestinos;
	}



	public String getCorreoDestino() {
		return correoDestino;
	}



	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}



	public List<String> getListCorreoDestinos() {
		return listCorreoDestinos;
	}



	public void setListCorreoDestinos(List<String> listCorreoDestinos) {		
		this.listCorreoDestinos = listCorreoDestinos;
	}



	public Date getCorreoFecha() {
		return correoFecha;
	}



	public void setCorreoFecha(Date correoFecha) {
		this.correoFecha = correoFecha;
	}



	public Date getRemitente() {
		return remitente;
	}



	public void setRemitente(Date remitente) {
		this.remitente = remitente;
	}



	public String getPathFileAttached() {
		return pathFileAttached;
	}



	public void setPathFileAttached(String pathFileAttached) {
		this.pathFileAttached = pathFileAttached;
	}



	public String[] getPathFilesAttached() {
		return pathFilesAttached;
	}



	public void setPathFilesAttached(String[] pathFilesAttached) {
		
		this.pathFilesAttached = pathFilesAttached;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public List<String> getListpathFilesDestinos() {
		return listpathFilesDestinos;
	}


	public void setListpathFilesDestinos(List<String> listpathFilesDestinos) {
		this.listpathFilesDestinos = listpathFilesDestinos;
	}


	public String getCORREO_USAURIO() {
		return CORREO_USAURIO;
	}


	public void setCORREO_USAURIO(String cORREO_USAURIO) {
		CORREO_USAURIO = cORREO_USAURIO;
	}


	public String getCORREO_CLAVE() {
		return CORREO_CLAVE;
	}


	public void setCORREO_CLAVE(String cORREO_CLAVE) {
		CORREO_CLAVE = cORREO_CLAVE;
	}


	public String getCORREO_HOST() {
		return CORREO_HOST;
	}


	public void setCORREO_HOST(String cORREO_HOST) {
		CORREO_HOST = cORREO_HOST;
	}


	public String getCORREO_PORT() {
		return CORREO_PORT;
	}


	public void setCORREO_PORT(String cORREO_PORT) {
		CORREO_PORT = cORREO_PORT;
	}


	public String getCORREO_SOCKETPORT() {
		return CORREO_SOCKETPORT;
	}


	public void setCORREO_SOCKETPORT(String cORREO_SOCKETPORT) {
		CORREO_SOCKETPORT = cORREO_SOCKETPORT;
	}


	public String getCORREO_ACTIVO_TTL() {
		return CORREO_ACTIVO_TTL;
	}


	public void setCORREO_ACTIVO_TTL(String cORREO_ACTIVO_TTL) {
		CORREO_ACTIVO_TTL = cORREO_ACTIVO_TTL;
	}


	public String getCORREO_ACCOUNT() {
		return CORREO_ACCOUNT;
	}


	public void setCORREO_ACCOUNT(String cORREO_ACCOUNT) {
		CORREO_ACCOUNT = cORREO_ACCOUNT;
	}


	public boolean isParametrosConexionSet() {
		return parametrosConexionSet;
	}

	public void setParametrosConexionSet(boolean parametrosConexionSet) {
		this.parametrosConexionSet = parametrosConexionSet;
	}


	public boolean isEsBodyPlantilla() {
		return esBodyPlantilla;
	}


	public void setEsBodyPlantilla(boolean esBodyPlantilla) {
		this.esBodyPlantilla = esBodyPlantilla;
	}


	public Map<String, String> getMapvariablesValores() {
		return mapvariablesValores;
	}


	public void setMapvariablesValores(Map<String, String> mapvariablesValores) {
		this.mapvariablesValores = mapvariablesValores;
	}

	
	
}
