package pe.jaav.common.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/** Funciones utiles
 * @author JAAV
 *
 */
public class UtilesCommons {

	
	/**VARIABLES DEL SISTEMA*/
	public static ResourceBundle constanPropiedades = ResourceBundle.getBundle("mensajes",Locale.forLanguageTag("es"));

	/** retornar valor de Property
	 * @param prop
	 * @return
	 */
	public static String getConstantProperty(String prop){				
		//constanPropiedades.getLocale().setDefault(Locale.forLanguageTag("es"));
		if(constanPropiedades.containsKey(prop)){
			return constanPropiedades.getString(prop);	
		}else{
			return "[Definir mensaje]";
		}				
	}
	
	
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

	public static final String SI_desc = getConstantProperty("CONST_SI_DESC_CAP");
	public static final String NO_desc = getConstantProperty("CONST_SI_NO_DESC_CAP");
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

	/** Convert Date to calendar **/
	public static Calendar dateToCalendar(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} else {
			return null;
		}
	}

	/** Convert Calendar to Date **/
	public static Date calendarToDate(Calendar calendar) {
		if (calendar != null) {
			return calendar.getTime();
		} else {
			return null;
		}
	}

	public static Date getFechaHoy() {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		return now;
	}

	public static Timestamp obtenerTimestamp() {
		try {
			// 1) create a java calendar instance
			Calendar calendar = Calendar.getInstance();

			// 2) get a java.util.Date from the calendar instance.
			// this date will represent the current instant, or "now".
			Date now = calendar.getTime();

			// 3) a java current time (now) instance
			Timestamp currentTimestamp = new Timestamp(now.getTime());

			return currentTimestamp;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static String formatoFechaString(Date fecha, String formato) {
		String fechaConFormato = "";
		try {
			if (FORMA_DATECOMPLETA.equals(formato)) {
				if (fecha != null) {
					SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
					SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
					SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
					String day = sdfDay.format(fecha);
					String month = sdfMonth.format(fecha);
					String year = sdfYear.format(fecha);
					int monthNum = Integer.parseInt(month);
					fechaConFormato = nombreDiaSemana(fecha) + " " + day + " de " + getDescMes(monthNum) + " del "
							+ year;
				}
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat(formato);
				fechaConFormato = sdf.format(fecha);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fechaConFormato;
	}

	public static Object getNuevoObjetoGenerico(Object objetoOrigen) {
		try {
			Object objAntiguo = null;
			if (objetoOrigen != null) {
				String type = objetoOrigen.getClass().getName();
				objAntiguo = Class.forName(type).newInstance();// sgAgenteSeleccionado;
				Field[] fields = objAntiguo.getClass().getDeclaredFields();
				for (Field field : fields) {
					try {
						if ((field.getModifiers()
								& java.lang.reflect.Modifier.FINAL) == java.lang.reflect.Modifier.FINAL) {
							// ES FINAL
						} else {
							field.setAccessible(true);
							Object fieldValue = field.get(objetoOrigen);
							// Object fieldValue = field.get(objAntiguo);
							field.set(objAntiguo, fieldValue);
						}
					} catch (IllegalArgumentException e) {
						// System.out.println("TEST EXC ADD FIELD OBJ OLD::");
						e.printStackTrace();
					}
				}
			}
			return objAntiguo;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// @SuppressWarnings("unused")
	public static void showObjeto(Object objShow) {
		try {

			if (objShow != null) {
				System.out.println("[INFO SYS ] INICIO SHOW CLASS::" + objShow.getClass().getSimpleName());
				Field[] fields = objShow.getClass().getDeclaredFields();
				for (Field field : fields) {
					try {
						String fieldName = field.getName();
						field.setAccessible(true);
						Object fieldValue = field.get(objShow);
						System.out.println("[INFO SYS ]  ATRIBUTO::" + fieldName + " || VALOR::" + fieldValue);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				}
				System.out.println("[INFO SYS ]  FIN SHOW CLASS::" + objShow.getClass().getSimpleName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**********************************/
	public static final String DAY_LUNES = "Lunes";
	public static final String DAY_MART = "Martes";
	public static final String DAY_MIERC = "Miércoles";
	public static final String DAY_JUEV = "Jueves";
	public static final String DAY_VIER = "Viernes";
	public static final String DAY_SAB = "Sábado";
	public static final String DAY_DOM = "Domingo";

	public static final int DIA_LUNES = DateTimeConstants.MONDAY;
	public static final int DIA_MARTES = DateTimeConstants.TUESDAY;
	public static final int DIA_MIERCOLES = DateTimeConstants.WEDNESDAY;
	public static final int DIA_JUEVES = DateTimeConstants.THURSDAY;
	public static final int DIA_VIERNES = DateTimeConstants.FRIDAY;
	public static final int DIA_SABADO = DateTimeConstants.SATURDAY;
	public static final int DIA_DOMINGO = DateTimeConstants.SUNDAY;

	public static final String MONTH_ENERO = "enero";
	public static final String MONTH_FEB = "febrero";
	public static final String MONTH_MARZ = "marzo";
	public static final String MONTH_ABR = "abril";
	public static final String MONTH_MAY = "mayo";
	public static final String MONTH_JUN = "junio";
	public static final String MONTH_JUL = "julio";
	public static final String MONTH_AGO = "agosto";
	public static final String MONTH_SET = "setiembre";
	public static final String MONTH_OCT = "octubre";
	public static final String MONTH_NOV = "noviembre";
	public static final String MONTH_DIC = "diciembre";

	public static String getDescMes(int numOrden) {
		switch (numOrden) {
		case 1:
			return MONTH_ENERO;
		case 2:
			return MONTH_FEB;
		case 3:
			return MONTH_MARZ;
		case 4:
			return MONTH_ABR;
		case 5:
			return MONTH_MAY;
		case 6:
			return MONTH_JUN;
		case 7:
			return MONTH_JUL;
		case 8:
			return MONTH_AGO;
		case 9:
			return MONTH_SET;
		case 10:
			return MONTH_OCT;
		case 11:
			return MONTH_NOV;
		case 12:
			return MONTH_DIC;
		default:
			return "";
		}
	}

	public static String nombreDiaSemana(Date fecha) {
		try {
			if (fechaEsLunes(fecha)) {
				return DAY_LUNES;
			} else if (fechaEsMartes(fecha)) {
				return DAY_MART;
			} else if (fechaEsMiercoles(fecha)) {
				return DAY_MIERC;
			} else if (fechaEsjueves(fecha)) {
				return DAY_JUEV;
			} else if (fechaEsViernes(fecha)) {
				return DAY_VIER;
			} else if (fechaEsSabado(fecha)) {
				return DAY_SAB;
			} else if (fechaEsDomingo(fecha)) {
				return DAY_DOM;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String obtenerTipoRegistroMatricula(int tipoRegistro) {
		switch (tipoRegistro) {
		case 1:
			return "Presencial";
		default:
			return "";
		}
	}

	public static String nombreDiaSemana(int dia) {
		if (dia == 1) {
			return DAY_LUNES;
		} else if (dia == 2) {
			return DAY_MART;
		} else if (dia == 3) {
			return DAY_MIERC;
		} else if (dia == 4) {
			return DAY_JUEV;
		} else if (dia == 5) {
			return DAY_VIER;
		} else if (dia == 6) {
			return DAY_SAB;
		} else if (dia == 7) {
			return DAY_DOM;
		} else {
			return "";
		}
	}

	public static String obtenerTurno(int turno) {
		if (turno == 1) {
			return "Ma-ana";
		} else if (turno == 2) {
			return "Tarde";
		} else if (turno == 3) {
			return "Noche";
		} else {
			return "";
		}
	}

	/***/
	public static boolean fechaEsLunes(Date fecha) {
		return fechaEsDiaSemana(fecha, 1);
	}

	/***/
	public static boolean fechaEsMartes(Date fecha) {
		return fechaEsDiaSemana(fecha, 2);
	}

	/***/
	public static boolean fechaEsMiercoles(Date fecha) {
		return fechaEsDiaSemana(fecha, 3);
	}

	/***/
	public static boolean fechaEsjueves(Date fecha) {
		return fechaEsDiaSemana(fecha, 4);
	}

	/***/
	public static boolean fechaEsViernes(Date fecha) {
		return fechaEsDiaSemana(fecha, 5);
	}

	/***/
	public static boolean fechaEsSabado(Date fecha) {
		return fechaEsDiaSemana(fecha, 6);
	}

	/***/
	public static boolean fechaEsDomingo(Date fecha) {
		return fechaEsDiaSemana(fecha, 0); /// Domingo = 0 y lunes = 1
	}

	/***/
	@SuppressWarnings("deprecation")
	public static boolean fechaEsDiaSemana(Date fecha, int dia) {
		try {
			if (fecha != null) {
				if (fecha.getDay() == dia) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/***/
	public static long fechaDiferenciaEnDias_Con(Date fecha, Date fechaDif) {
		try {
			if (fecha != null && fechaDif != null) {
				Date fechaAux = getDateFormat("yyyyMMdd", printDate("yyyyMMdd", fecha));
				Date fechaAuxDif = getDateFormat("yyyyMMdd", printDate("yyyyMMdd", fechaDif));
				long time = fechaAux.getTime();
				long timeDif = fechaAuxDif.getTime();
				long dias = Math.abs((time - timeDif) / 86400000);

				return dias;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/***/
	public static String printDate(String pattern, Date valor) {
		if (valor != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(valor);
		} else {
			return null;
		}
	}

	public static Date getDateFormat(String pattern, String valor) {
		try {
			if (valor != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				return sdf.parse(valor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** Devuelve la fecha desplazada tantos dias (Solo dias, no Horas...) */
	public static Date fechaMasNumeroDias(Date fecha, int dias) {
		// fecha.getDay()
		try {
			/// FALLA CUANDO SE LE AÑADE MáS DE 24 Días de golpe (Tener
			/// cuidado)
			if (fecha != null) {
				int signo = 1;
				if (dias < 0) {
					signo = -1;
				}
				int diasAux = Math.abs(dias);
				while (diasAux >= 24) {
					Date fechaAux = getDateFormat("yyyyMMdd", printDate("yyyyMMdd", fecha));
					long time = fechaAux.getTime();
					time = time + 86400000 * 20 * signo; //// equivalente a un
															//// dia = 86400000
															//// ms
					Date fechaAux2 = new Date(time);
					// return fechaAux2;
					diasAux = diasAux - 20;
					fecha = fechaAux2;
					dias = diasAux;
				}
				// long fecha1 = Integer.parseInt( printDate("yyyyMMdd",
				// fecha));
				Date fechaAux = getDateFormat("yyyyMMdd", printDate("yyyyMMdd", fecha));
				long time = fechaAux.getTime();
				time = time + 86400000 * diasAux * signo; //// equivalente a un
															//// dia = 86400000
															//// ms
				Date fechaAux2 = new Date(time);
				return fechaAux2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** Obtener MAC del cliente **/
	public static String getMacCliente() {
		InetAddress ip;
		byte[] mac = null;
		StringBuilder sb = new StringBuilder();
		try {
			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			mac = network.getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static <E> List<E> union(final List<? extends E> list1, final List<? extends E> list2) {
		final ArrayList<E> result = new ArrayList<E>(list1);
		result.addAll(list2);
		return result;
	}

	/***/
	public static String formatoCerosIzqNum(int dimensionTotal, String numero) {
		String codigo = "";
		int dimNum = numero.length();
		if (dimensionTotal - dimNum >= 0) {
			for (int i = 0; i < dimensionTotal - dimNum; i++) {
				codigo = codigo + "0";
			}
		}
		codigo = codigo + numero;
		return codigo;
	}

	public static String formatoCerosIzqNum(int dimensionTotal, Number numeroIn) {
		String codigo = "";
		String numero = "";
		if (numeroIn != null) {
			numero = "" + numeroIn;
		}
		int dimNum = numero.length();
		if (dimensionTotal - dimNum >= 0) {
			for (int i = 0; i < dimensionTotal - dimNum; i++) {
				codigo = codigo + "0";
			}
		}
		codigo = codigo + numero;
		return codigo;
	}

	/**
	 * DEVUELVE EL VALOR NUMERICO (unidad de tiempo:indica) en un formato de
	 * CRONOMETRO
	 */
	public static String tiempoFormato(int unidades, String indica) {
		String tiempo = null;
		int horas = 0;
		int segundosSob = 0;
		int minutos = 0;
		int segundos = 0;

		if (UNID_SEGUNDOS.equals(indica)) {
			horas = unidades / 3600;
			segundosSob = unidades % 3600;
			minutos = segundosSob / 60;
			segundos = unidades - (minutos * 60);
			// Date time = new Date(unidades);
			tiempo = formatoCerosIzqNum(2, "" + horas) + ": " + formatoCerosIzqNum(2, "" + minutos) + ": "
					+ formatoCerosIzqNum(2, "" + segundos);
		}
		return tiempo;
	}

	/*** Para Horario **/
	public static Date contatenarFechaHora(Date fecha, Date hora) {
		DateTime _fecha = new DateTime(fecha);
		DateTime _hora = new DateTime(hora);
		return new DateTime().withYear(_fecha.getYear()).withMonthOfYear(_fecha.getMonthOfYear())
				.withDayOfMonth(_fecha.getDayOfMonth()).withHourOfDay(_hora.getHourOfDay())
				.withMinuteOfHour(_hora.getMinuteOfHour()).withSecondOfMinute(_hora.getSecondOfMinute()).toDate();
	}

	public static int obtenerEnteroNativo(Integer value) {
		return (value == null ? 0 : value.intValue());
	}

	public static float obtenerFloatNativo(Float value) {
		return (value == null ? 0 : value.floatValue());
	}

	public static float obtenerFloatPositivo(Float value) {
		return (value == null ? 1 : value.floatValue());
	}

	public static double obtenerDoubleNativo(Double value) {
		return (value == null ? 0 : value.doubleValue());
	}

	public static int obtenerEnteroNativo(BigInteger value) {
		return (value == null ? 0 : value.intValue());
	}

	public static int obtenerEnteroNativo(BigDecimal value) {
		return (value == null ? 0 : value.intValue());
	}

	public static double obtenerDoubleNativo(BigDecimal value) {
		return (value == null ? 0 : value.doubleValue());
	}

	public static BigDecimal obtenerBigDecimal(BigDecimal value) {
		return (esNulo(value) ? BigDecimal.ZERO : value);
	}

	public static BigDecimal obtenerBigDecimalPositivo(BigDecimal value) {
		return (esNulo(value) ? BigDecimal.ONE : value.abs());
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

	public static <E> List<E> transformarMapaALista(final Map<?, E> map) {
		if (map == null || map.isEmpty()) {
			new ArrayList<E>();
		}
		return new ArrayList<E>(map.values());
	}

	public static <K, V> List<K> getListKeyOfMap(Map<K, V> map) {
		if (map == null || map.isEmpty()) {
			new ArrayList<K>();
		}
		return new ArrayList<K>(map.keySet());
	}

	public static <K, V> List<V> getListValueOfMap(Map<K, V> map) {
		if (map == null || map.isEmpty()) {
			new ArrayList<V>();
		}
		return new ArrayList<V>(map.values());
	}

	public static <E> List<E> getCleanList(List<E> list) {
		if (list == null) {
			list = new ArrayList<E>();
		} else {
			list.clear();
		}
		return list;
	}

	public static <E> List<E> getList(List<E> list) {
		if (list == null) {
			list = new ArrayList<E>();
		}
		return list;
	}

	public static <E> List<E> getNewList() {
		return new ArrayList<E>();
	}

	public static <E> E obtenerPrimerElementoLista(List<E> list) {
		if (esVacio(list)) {
			return null;
		}
		return list.get(0);
	}

	public static <E> E obtenerUltimoElementoLista(List<E> list) {
		if (esVacio(list)) {
			return null;
		}
		return list.get(list.size() - 1);
	}

	@SafeVarargs
	public static <E> List<E> combinarListas(List<E>... lists) {
		List<E> newList = getNewList();
		if (noEsVacio(lists)) {
			for (List<E> list : lists) {
				newList.addAll(list);
			}
		}
		return newList;
	}

	public static <K, V> Map<K, V> getNewLinkedHashMap() {
		return new LinkedHashMap<K, V>();
	}

	public static <K, V> Map<K, V> getNewHashMap() {
		return new HashMap<K, V>();
	}

	public static <K, V> Map<K, V> getCleanLinkedHashMap(Map<K, V> map) {
		if (map == null) {
			return getNewLinkedHashMap();
		} else {
			map.clear();
		}
		return map;
	}

	public static <K, V> Map<K, V> getCleanHashMap(Map<K, V> map) {
		if (map == null) {
			return getNewHashMap();
		} else {
			map.clear();
		}
		return map;
	}

	public static boolean esNulo(Object obj) {
		return obj == null ? true : false;
	}

	public static boolean noEsNulo(Object obj) {
		return !esNulo(obj);
	}

	public static <T> T castearObjeto(Object obj, Class<T> clazz) {
		if (obj == null) {
			return null;
		} else {
			try {
				return clazz.cast(obj);
			} catch (ClassCastException cce) {
				cce.printStackTrace();
				return null;
			}
		}
	}

	public static <T, E> boolean esInstancia(E instancia, Class<T> clazz) {
		if (instancia == null) {
			return false;
		} else {
			if (clazz.isInstance(instancia)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static <T, E> List<T> castearLista(List<E> list, Class<T> clazz) {
		List<T> newList = getNewList();
		for (E item : list) {
			if (esInstancia(item, clazz)) {
				newList.add(castearObjeto(item, clazz));
			}
		}
		return newList;
	}

	public static boolean esPositivo(Number number) {
		if (esNulo(number)) {
			return false;
		}
		if (number instanceof Integer) {
			return esIntegerPositivo((Integer) number);
		} else if (number instanceof Float) {
			return esFloatPositivo((Float) number);
		} else if (number instanceof Double) {
			return esDoublePositivo((Double) number);
		} else if (number instanceof Long) {
			return esLongPositivo((Long) number);
		} else if (number instanceof Short) {
			return esShortPositivo((Short) number);
		} else {
			return false;
		}
	}

	public static boolean esIntegerPositivo(Integer value) {
		if (esNulo(value)) {
			return false;
		}
		return (value.intValue() > 0 ? true : false);
	}

	public static boolean esFloatPositivo(Float value) {
		if (esNulo(value)) {
			return false;
		}
		return (value.floatValue() > 0 ? true : false);
	}

	public static boolean esDoublePositivo(Double value) {
		if (esNulo(value)) {
			return false;
		}
		return (value.doubleValue() > 0 ? true : false);
	}

	public static boolean esLongPositivo(Long value) {
		if (esNulo(value)) {
			return false;
		}
		return (value.longValue() > 0 ? true : false);
	}

	public static boolean esShortPositivo(Short value) {
		if (esNulo(value)) {
			return false;
		}
		return (value.shortValue() > 0 ? true : false);
	}

	public static boolean esBigIntegerPositivo(BigInteger big) {
		if (esNulo(big)) {
			return false;
		}
		return (big.compareTo(BigInteger.ZERO) == 1 ? true : false);
	}

	public static boolean esBigDecimalPositivo(BigDecimal big) {
		if (esNulo(big)) {
			return false;
		}
		return (big.compareTo(BigDecimal.ZERO) == 1 ? true : false);
	}

	public static String trim(String value) {
		return (value == null ? null : value.trim());
	}

	public static boolean compareToZero(BigDecimal big) {
		if (big.compareTo(BigDecimal.ZERO) <= 0) {
			return true;
		}
		return false;
	}

	public static <E> boolean esUnicoValor(List<E> list) {				
		if (list.size() == 1) {
			return true;
		}		
		return false;
	}
	
	public static <K, V> boolean esUnicoValor(Map<K, V> map) {				
		if(noEsVacio(map)){
			if (map.size() == 1) {
				return true;
			}
		}
				
		return false;
	}

	
	public static String correrCaractersIzq(String cadenaOriginal, char caracter, int dimensionCadenaFinal) {
		if (esVacio(cadenaOriginal)) {
			return cadenaOriginal;
		}
		int i = cadenaOriginal.length();
		while (i < dimensionCadenaFinal) {
			cadenaOriginal = caracter + cadenaOriginal;
			i = i + 1;
		}
		return cadenaOriginal;
	}

	public static String correrCaractersDer(String cadenaOriginal, char caracter, int dimensionCadenaFinal) {
		if (esVacio(cadenaOriginal)) {
			return cadenaOriginal;
		}
		int i = cadenaOriginal.length();
		while (i < dimensionCadenaFinal) {
			cadenaOriginal = cadenaOriginal + caracter;
			i = i + 1;
		}
		return cadenaOriginal;
	}

	// Obtenemos el valor boolean segun valorString
	public static boolean obtenerValorBooleano(String valor, String parametroTrue, String parametroFalse) {
		boolean valorBooleano = true;
		if (valor == null || "".equals((("" + valor).trim()))) {
			valorBooleano = false;
		} else {
			if (valor.equals(parametroTrue)) {
				valorBooleano = true;
			} else if (valor.equals(parametroFalse)) {
				valorBooleano = false;
			}
		}
		return valorBooleano;
	}

	public static boolean obtenerValorBooleanoSiNo(String valor) {
		boolean valorBooleano = true;
		if (esNulo(valor) ||  "".equals((("" + valor).trim()))) {
			valorBooleano = false;
		} else {
			if (sonCadenasIguales(valor,SI_db)|| sonCadenasIguales(valor, YES_db)) {
				valorBooleano = true;
			} else if (sonCadenasIguales(valor, NO_db)) {
				valorBooleano = false;
			}
		}
		return valorBooleano;
	}

	public static boolean obtenerValorBooleanoActivoInactivo(String valor) {
		boolean valorBooleano = true;
		if (esNulo(valor) || "".equals((("" + valor).trim()))) {
			valorBooleano = false;
		} else {
			if (sonCadenasIguales(valor,ACTIVO_db)) {
				valorBooleano = true;
			} else if (sonCadenasIguales(valor, INACTIVO_db)) {
				valorBooleano = false;
			}
		}
		return valorBooleano;
	}

	// Obtenemos el valor string segun valor Booleano
	public static String obtenerValorString(boolean valor, String parametroTrue, String parametroFalse) {
		String valorString = "";
		if (valor) {
			valorString = parametroTrue;
		} else {
			valorString = parametroFalse;
		}
		return valorString;
	}

	public static String obtenerValorStringSiNo(boolean valor) {
		String valorString = "";
		if (valor) {
			valorString = SI_db;
		} else {
			valorString = NO_db;
		}
		return valorString;
	}

	public static String obtenerValorStringActivoInactivo(boolean valor) {
		String valorString = "";
		if (valor) {
			valorString = ACTIVO_db;
		} else {
			valorString = INACTIVO_db;
		}
		return valorString;
	}

	/**
	 * Get valor de un par-metro WEB
	 * 
	 * @param valor
	 * @return
	 */
	public static String getValorGenString(String valor) {
		String valorString = null;
		if (esVacio(valor)) {
			valorString = null;
		} else {
			if (TYPE_COD_NULL.equals(valor.toLowerCase())) {
				valorString = null;
			} else {
				valorString = valor;
			}
		}
		return valorString;
	}

	/**
	 * Get valor de un par-metro WEB
	 * 
	 * @param valor
	 * @return
	 */
	public static Integer getValorIntString(String valor) {
		Integer valorString = null;
		if (esVacio(valor)) {
			valorString = null;
		} else {
			if (TYPE_COD_NULL.equals(valor.toLowerCase())) {
				valorString = null;
			} else {
				valorString = Integer.parseInt(valor);
			}
		}
		return valorString;
	}

	public static BigDecimal convertirEnBigDecimal(Number value) {
		return (value == null ? BigDecimal.ZERO : new BigDecimal(value.doubleValue()));
	}

	/** Redonde un valor DOUBLE a un determinado numero de decimales */
	public static Double redondearDecimales(Double value, int decimales) {
		if (decimales < 0)
			throw new IllegalArgumentException();
		if (!value.isNaN()) {
			BigDecimal bd = new BigDecimal(value != null ? value.doubleValue() : 0.0);
			bd = bd.setScale(decimales, RoundingMode.HALF_UP);
			return bd.doubleValue();
		} else {
			return 0.0;
		}
	}

	public static boolean sonCadenasIguales(String str1, String str2) {
		if (esVacio(str1) && esVacio(str2)) {
			return true;
		} else if (esVacio(str1) && noEsVacio(str2)) {
			return false;
		} else if (noEsVacio(str1) && esVacio(str2)) {
			return false;
		} else {
			if (str1.equalsIgnoreCase(str2)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static String convertirNumeroToTexto(Integer numero) {
		String resultado = "";
		try {
			NumerosHaciaTexto numeroConv = new NumerosHaciaTexto(numero);
			resultado = numeroConv.convertirLetras(numero);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultado;
	}

	public static String fileNameUtf8(String fileName) {
		try {
			return new String(fileName.getBytes(Charset.defaultCharset()), UTF_8);
		} catch (UnsupportedEncodingException e) {
			return fileName;
		}
	}

	public static <T> boolean esNumero(T value) {
		if (esNulo(value)) {
			return false;
		}

		if (value instanceof String) {
			String number = (String) value;
			return esNumeroString(number);
		} else if (value instanceof Integer || value instanceof Long || value instanceof Float
				|| value instanceof Double || value instanceof BigInteger || value instanceof BigDecimal) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean esNumeroString(String number) {
		try {
			Double.parseDouble(number);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static boolean cadenaEnListIgnoreCase(String cadena, String... cadenas) {
		if (esVacio(cadenas) || esVacio(cadena)) {
			return false;
		}
		for (int i = 0; i < cadenas.length; i++) {
			if (cadena.equalsIgnoreCase(cadenas[i])) {
				return true;
			}
		}
		return false;
	}

	public static Object getNewInstance(Class<?> type) {
		try {
			return type.newInstance();
		} catch (Exception ex) {
			return null;
		}
	}

	public static Date obtenerFechaSemana(int diaSemana) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, diaSemana + 1);
		c.add(Calendar.WEEK_OF_YEAR, 0);
		return c.getTime();
	}

	public static Date obtenerHoraDia(int horaDia) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, horaDia);
		return c.getTime();
	}

	public static boolean sonPositivos(Number ... value){
		if(value == null || value.length == 0){
			return false;
		}
		for(int i = 0;i < value.length;i++){
			if(noEsPositivo(value[i])){
				return false;
			}
		}
		return true;
	}
	
	public static boolean noEsPositivo(Number value){
		return !esPositivo(value);
	}
	
	
	

	/** Genera un Numero Entero aleatoriamente, con una LONGITUD DETERMINADA
	 * EN caso de Longitud = 1, no devuelve el valor 0 
	 * @param longitud
	 * @return
	 */
	public static int getIntRandomLongitud(int longitud){
		int randomNum = -1;
		if(longitud>0){
			Double minimo = Math.pow(10, longitud-1) ;//10*(longitud-1);
			Double maximo = Math.pow(10, longitud)-1 ; //10*(longitud) - 1;			    
			randomNum = getIntRandomRango(minimo.intValue(), maximo.intValue());
		}		
		return randomNum;
	}
	
	/** Genera un Numero Entero aleatoriamente, desde un valor minimo hasta un valor maximo 
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	public static int getIntRandomRango(int minimo, int maximo){
		int randomNum = -1;
		if(maximo > minimo && minimo >= 0){
			 Random rand = new Random();
			 randomNum = minimo + rand.nextInt((maximo - minimo) + 1);			    
			 System.out.println("TEST:A:"+randomNum);					
		}		
		return randomNum;
	}
}
