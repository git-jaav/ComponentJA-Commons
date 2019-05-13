package pe.jaav.common.jmail.util;

import java.util.*;

/** Funciones utiles
 * @author JAAV
 *
 */
public class Utiles {

	
	/**VARIABLES DEL SISTEMA*/
	//public static ResourceBundle constanPropiedades = ResourceBundle.getBundle("mensajes",Locale.forLanguageTag("es"));

	/** retornar valor de Property
	 * @param prop
	 * @return
	 */
	//public static String getConstantProperty(String prop){				
		//constanPropiedades.getLocale().setDefault(Locale.forLanguageTag("es"));
	//	if(constanPropiedades.containsKey(prop)){
			//return constanPropiedades.getString(prop);	
		//}else{
			//return "[Definir mensaje]";
		//}				
	//}
	
	
	/*** PARAMETROS GENERALES ***/
	public static String HOST_APP = "HOST_UNDEF";

	public static final String CODIGO_PERFIL = "PE";
	public static final String CODIGO_USUARIO = "US";
	
	public static final String ACTIVO_db = "A";
	public static final String INACTIVO_db = "I";
	
	public static final Integer ACTIVO_NUM_db = 1;
	public static final Integer INACTIVO_NUM_db = 2;

	//Afirmativo
	public static final String SI_db = "S";
	public static final String YES_db = "Y";
	
	//Negativo
	public static final String NO_db = "N";

	//public static final String SI_desc = getConstantProperty("CONST_SI_DESC_CAP");
	//public static final String NO_desc = getConstantProperty("CONST_SI_NO_DESC_CAP");
	public static final String DELETE_db = "D";
	public static final String UPDATE_db = "U";
	public static final String INSERT_db = "I";

	public static final String TYPE_MSG_WARN = "warn";
	public static final String TYPE_MSG_INFO = "info";
	public static final String TYPE_MSG_ERROR = "error";

	public static final String TYPE_COD_NULL = "null";

	public static final String FORMA_DATECOMPLETA = "FECHACOMPLETA";
	public static final String UNID_SEGUNDOS = "segundos";
	
	public static final String ESTADO_ELIMINADO_db = "E";
	public static final Integer ESTADO_ELIMINADO_NUM_db = 3;
	

	public static final String UTF_8 = "UTF-8";

	public static final Integer INTEGER_ZERO = new Integer(0);
	public static final Long LONG_ZERO = new Long(0);
	
	
	public static Date getFechaHoy() {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		return now;
	}
	

	/******/
	public static boolean esVacio(String str) {
		if (str == null || str.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean noEsVacio(String str) {
		return !esVacio(str);
	}

	public static <E> boolean esVacio(E[] array) {
		if (array == null || array.length == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean esVacio(Date date) {
		if (date == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean noEsVacio(Date date) {
		return !esVacio(date);
	}
	
	public static boolean noEsVacio(Integer integer) {
		return !esVacio(integer);
	}

	public static boolean esVacio(Integer integer) {
		if (integer == null || integer.compareTo(INTEGER_ZERO) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean esVacio(Long longValue) {
		if (longValue == null || longValue.compareTo(LONG_ZERO) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean noEsVacio(Long longValue) {
		return !esVacio(longValue);
	}

	public static <E> boolean noEsVacio(E[] array) {
		return !esVacio(array);
	}

	public static <E> boolean esVacio(List<E> list) {
		if (list == null || list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static <K, V> boolean esVacio(Map<K, V> map) {
		if (map == null || map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static <E> boolean noEsVacio(List<E> list) {
		return !esVacio(list);
	}

	public static <K, V> boolean noEsVacio(Map<K, V> map) {
		return !esVacio(map);
	}
	
}
