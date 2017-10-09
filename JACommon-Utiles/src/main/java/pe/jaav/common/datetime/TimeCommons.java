package pe.jaav.common.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pe.jaav.common.util.UtilesCommons;

/**
 * @author JAAV
 *
 */
public class TimeCommons {
	
	/** Constantes **/
	
	public static final int NUMERO_DIAS_SEMANA = DateTimeConstants.DAYS_PER_WEEK;
	
	public static final int MINUTOS_POR_HORA = DateTimeConstants.MINUTES_PER_HOUR;
	
	public static final int DIA_LUNES_JODA = DateTimeConstants.MONDAY;
	public static final int DIA_MARTES_JODA = DateTimeConstants.TUESDAY;
	public static final int DIA_MIERCOLES_JODA = DateTimeConstants.WEDNESDAY;
	public static final int DIA_JUEVES_JODA = DateTimeConstants.THURSDAY;
	public static final int DIA_VIERNES_JODA = DateTimeConstants.FRIDAY;
	public static final int DIA_SABADO_JODA = DateTimeConstants.SATURDAY;
	public static final int DIA_DOMINGO_JODA = DateTimeConstants.SUNDAY;
	
	public static final int MES_ENERO_JODA = DateTimeConstants.JANUARY;
	public static final int MES_FEBRERO_JODA = DateTimeConstants.FEBRUARY;
	public static final int MES_MARZO_JODA = DateTimeConstants.MARCH;
	public static final int MES_ABRIL_JODA = DateTimeConstants.APRIL;
	public static final int MES_MAYO_JODA = DateTimeConstants.MAY;
	public static final int MES_JUNIO_JODA = DateTimeConstants.JUNE;
	public static final int MES_JULIO_JODA = DateTimeConstants.JULY;
	public static final int MES_AGOSTO_JODA = DateTimeConstants.AUGUST;
	public static final int MES_SEPTIEMBRE_JODA = DateTimeConstants.SEPTEMBER;
	public static final int MES_OCTUBRE_JODA = DateTimeConstants.OCTOBER;
	public static final int MES_NOVIEMBRE_JODA = DateTimeConstants.NOVEMBER;
	public static final int MES_DICIEMBRE_JODA = DateTimeConstants.DECEMBER;
	
	
	public static final int HORA_INICIO = 0;
	public static final int MINUTO_INICIO = 0;
	public static final int SEGUNDO_INICIO = 0;
	public static final int MILISEGUNDO_INICIO = 0;
	
	public static final int HORA_FIN = 23;
	public static final int MINUTO_FIN = 59;
	public static final int SEGUNDO_FIN = 59;
	public static final int MILISEGUNDO_FIN = 59;
	
	public static final int DIA_DEFAULT = 1;
	public static final int MES_DEFAULT = 1;
	public static final int YEAR_DEFAULT = 2000;
	
	public static final String PATTERN_ANIO = "yyyy";
	public static final String PATTERN_ANIO_MES = "yyyyMM";
	public static final String PATTERN_ANIO_MES_DIA = "yyyyMMdd";
	public static final String PATTERN_ANIO_MES_SEPARATOR = "yyyy-mm";
	public static final String PATTERN_FECHA = "dd/MM/yyyy";
	public static final String PATTERN_HORA = "HH:mm";
	public static final String PATTERN_12_HORA = "hh:mm";
	public static final String PATTERN_FECHA_HORA = PATTERN_FECHA+" "+PATTERN_HORA;
	public static final String PATTERN_AM_PM = "a";
	public static final String PATTERN_HORA_AM_PM = PATTERN_HORA+" "+ PATTERN_AM_PM;
	public static final String PATTERN_SEGUNDOS = "ss";
	public static final String PATTERN_HORA_SEGUNDOS = PATTERN_HORA + ":"+PATTERN_SEGUNDOS;
	public static final String PATTERN_12_HORA_AM_PM = PATTERN_12_HORA + " " + PATTERN_AM_PM;
	
	public static final String DEFAULT_MIN_HORA_DIA = "00:00:00";
	public static final String DEFAULT_MAX_HORA_DIA = "23:59:59";
	
	private static final ResourceBundle properties = ResourceBundle.getBundle("parametrosTimeCommons");

	
	public static DateTime crearDateTime(int anio, int mesAnio, int diaMes, int horaDia, int minutoHora, int segundoMinuto){
		return new DateTime(anio, mesAnio, diaMes, horaDia, minutoHora, segundoMinuto);
	}
	
	public static DateTime crearSoloHora(int horaDia, int minutoHora, int segundoMinuto){
		return crearDateTime(YEAR_DEFAULT, MES_DEFAULT, DIA_DEFAULT, horaDia, minutoHora, segundoMinuto);
	}
	
	/**
	 * Convierte un java.util.Date en un or.joda.time.DateTime
	 * @param date
	 * @return
	 */
	public static DateTime convertirEnDateTime(Date date){
		return new DateTime(date);
	}
	
	public static Date obtenerFechaHoraActual(){
		return DateTime.now().toDate();
	}
	
	public static DateTime obtenerFechaHoraActualDateTime(){
		return DateTime.now();
	}
	
	public static Date obtenerFechaActual(){
		return getSoloFecha(DateTime.now()).toDate();
	}
	
	public static DateTime obtenerFechaActualDateTime(){
		return getSoloFecha(DateTime.now());
	}
	
	public static int getAnio(DateTime dateTime){
		return dateTime.getYear();
	}
	
	public static int getAnio(Date date){
		return getAnio(convertirEnDateTime(date));
	}
	
	public static int getMes(DateTime dateTime){
		return dateTime.getMonthOfYear();
	}
	
	public static int getMes(Date date){
		return getMes(convertirEnDateTime(date));
	}
	
	public static int getDia(DateTime dateTime){
		return dateTime.getDayOfMonth();
	}
	
	public static int getDia(Date date){
		return getDia(convertirEnDateTime(date));
	}
	
	public static int getDiaSemana(DateTime dateTime){
		return dateTime.getDayOfWeek();
	}
	
	public static int getDiaSemana(Date date){
		return getDiaSemana(convertirEnDateTime(date));
	}
	
	public static int getSemanaAnio(DateTime dateTime){
		return dateTime.getWeekOfWeekyear();
	}
	
	public static int getSemanaAnio(Date date){
		return getSemanaAnio(convertirEnDateTime(date));
	}
	
	public static DateTime getSoloFecha(DateTime dateTime){
		return getSoloFechaInicial(dateTime);
	}
	
	public static DateTime getSoloFecha(Date date){
		return getSoloFechaInicial(date);
	}
	
	public static DateTime getSoloFechaInicial(DateTime dateTime){
		return dateTime.withHourOfDay(HORA_INICIO).withMinuteOfHour(MINUTO_INICIO).withSecondOfMinute(SEGUNDO_INICIO).withMillisOfSecond(MILISEGUNDO_INICIO);
	}
	
	public static DateTime getSoloFechaInicial(Date date){
		return getSoloFechaInicial(convertirEnDateTime(date));
	}
	
	public static DateTime getSoloFechaFinal(DateTime dateTime){
		return dateTime.withHourOfDay(HORA_FIN).withMinuteOfHour(MINUTO_FIN).withSecondOfMinute(SEGUNDO_FIN).withMillisOfSecond(MILISEGUNDO_FIN);
	}
	
	public static DateTime getSoloFechaFinal(Date date){
		return getSoloFechaFinal(convertirEnDateTime(date));
	}
	
	public static DateTime getSoloHora(DateTime dateTime){
		return dateTime.withDayOfMonth(DIA_DEFAULT).withMonthOfYear(MES_DEFAULT).withYear(YEAR_DEFAULT);
	}
	
	public static DateTime getSoloHora(Date date){
		return getSoloHora(convertirEnDateTime(date));
	}
	
	public static String imprimirDateTime(DateTime dateTime, String pattern){
		return dateTime.toString(pattern);
	}
	
	public static String imprimirDateTime(Date date, String pattern){
		return imprimirDateTime(convertirEnDateTime(date), pattern);
	}
	
	public static String imprimirSoloFecha(DateTime dateTime){
		return imprimirDateTime(dateTime, PATTERN_FECHA);
	}
	
	public static String imprimirSoloFecha(Date date){
		return imprimirDateTime(date, PATTERN_FECHA);
	}
	
	public static String imprimirSoloHora(DateTime dateTime){
		return imprimirDateTime(dateTime, PATTERN_HORA);
	}
	
	public static String imprimirSoloHora(Date date){
		return imprimirDateTime(date, PATTERN_HORA);
	}
	
	public static String imprimirSoloFechaHora(DateTime dateTime){
		return imprimirDateTime(dateTime, PATTERN_FECHA_HORA);
	}
	
	public static String imprimirSoloFechaHora(Date date){
		return imprimirDateTime(date, PATTERN_FECHA_HORA);
	}
	
	public static String imprimirAhora(String pattern){
		return DateTime.now().toString(pattern);
	}
	
	public static boolean dateTimeContenidoEnRango(DateTime dateTimeInicial, DateTime dateTimeFinal, DateTime dateTime){
		if((dateTimeInicial.isBefore(dateTime) || dateTimeInicial.isEqual(dateTime)) && (dateTimeFinal.isAfter(dateTime) || dateTimeFinal.isEqual(dateTime))){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean dateTimeRangoContenidoEnRango(DateTime rangoInicioSup, DateTime rangoFinSup, DateTime rangoInicio, DateTime rangoFin){
		if((rangoInicioSup.isBefore(rangoInicio) || rangoInicioSup.isEqual(rangoInicio)) && (rangoFinSup.isAfter(rangoFin) || rangoFinSup.isEqual(rangoFin))){
			return true;
		}else{
			return false;
		}
	}

	public static boolean fechaContenidaEnRango(DateTime fechaInicial, DateTime fechaFinal, DateTime fecha){
		DateTime newFechaInicial = getSoloFecha(fechaInicial);
		DateTime newFechaFinal = getSoloFecha(fechaFinal);
		DateTime newFecha = getSoloFecha(fecha);
		return dateTimeContenidoEnRango(newFechaInicial, newFechaFinal, newFecha);
	}
	
	public static boolean fechaContenidaEnRango(Date fechaInicial, Date fechaFinal, Date fecha){
		DateTime fechaI = getSoloFecha(fechaInicial);
		DateTime fechaF = getSoloFecha(fechaFinal);
		DateTime fechaD = getSoloFecha(fecha);
		return dateTimeContenidoEnRango(fechaI, fechaF, fechaD);
	}

	public static boolean horaContenidaEnRango(DateTime horaInicial, DateTime horaFinal, DateTime hora){
		DateTime newHoraInicial = getSoloHora(horaInicial);
		DateTime newHoraFinal = getSoloHora(horaFinal);
		DateTime newHora = getSoloHora(hora);
		return dateTimeContenidoEnRango(newHoraInicial, newHoraFinal, newHora);
	}
	
	public static boolean horaContenidaEnRango(Date horaInicial, Date horaFinal, Date hora){
		return dateTimeContenidoEnRango(getSoloHora(horaInicial), getSoloHora(horaFinal), getSoloHora(hora));
	}
	
	public static boolean fechaRangoContenidaEnRango(DateTime rangoFISup, DateTime rangoFFSup, DateTime rangoFI, DateTime rangoFF){
		DateTime newRangoFISup = getSoloFecha(rangoFISup);
		DateTime newRangoFFSup = getSoloFecha(rangoFFSup);
		DateTime newRangoFI = getSoloFecha(rangoFI);
		DateTime newRangoFF = getSoloFecha(rangoFF);
		return dateTimeRangoContenidoEnRango(newRangoFISup, newRangoFFSup, newRangoFI, newRangoFF);
	}
	
	public static boolean fechaRangoContenidaEnRango(Date rangoFISup, Date rangoFFSup, Date rangoFI, Date rangoFF){
		DateTime newRangoFISup = getSoloFecha(rangoFISup);
		DateTime newRangoFFSup = getSoloFecha(rangoFFSup);
		DateTime newRangoFI = getSoloFecha(rangoFI);
		DateTime newRangoFF = getSoloFecha(rangoFF);
		return dateTimeRangoContenidoEnRango(newRangoFISup, newRangoFFSup, newRangoFI, newRangoFF);
	}
	
	public static boolean cruceDateTimeConDateTime(DateTime inicio1, DateTime fin1, DateTime inicio2, DateTime fin2){
		if(inicio1.isEqual(fin2) || fin1.isEqual(inicio2)){
			return false;
		}
		if(inicio1.isBefore(inicio2) || inicio1.isEqual(inicio2)){
			return true;
		}
		if(fin1.isAfter(fin2) || fin1.isEqual(fin2)){
			return true;
		}
		return false;
	}
	
	public static boolean cruceSoloFecha(DateTime fechaInicio1, DateTime fechaFin1, DateTime fechaInicio2, DateTime fechaFin2){
		return cruceDateTimeConDateTime(getSoloFecha(fechaInicio1), getSoloFecha(fechaFin1), getSoloFecha(fechaInicio2), getSoloFecha(fechaFin2));
	}
	
	public static boolean cruceSoloHora(DateTime horaInicio1, DateTime horaFin1, DateTime horaInicio2, DateTime horaFin2){
		return cruceDateTimeConDateTime(getSoloHora(horaInicio1), getSoloHora(horaFin1), getSoloHora(horaInicio2), getSoloHora(horaFin2));
	}
	
	public static boolean cruceDateConDate(Date inicio1, Date fin1, Date inicio2, Date fin2){
		return cruceDateTimeConDateTime(convertirEnDateTime(inicio1), convertirEnDateTime(fin1), convertirEnDateTime(inicio2), convertirEnDateTime(fin2));
	}
	
	public static boolean cruceSoloFecha(Date fechaInicio1, Date fechaFin1, Date fechaInicio2, Date fechaFin2){
		return cruceDateTimeConDateTime(getSoloFecha(fechaInicio1), getSoloFecha(fechaFin1), getSoloFecha(fechaInicio2), getSoloFecha(fechaFin2));
	}
	
	public static boolean cruceSoloHora(Date horaInicio1, Date horaFin1, Date horaInicio2, Date horaFin2){
		return cruceDateTimeConDateTime(getSoloHora(horaInicio1), getSoloHora(horaFin1), getSoloHora(horaInicio2), getSoloHora(horaFin2));
	}
	
	
	
	public static boolean sonHorasIguales(DateTime hora1, DateTime hora2){
		String str1 = imprimirSoloHora(hora1);
		String str2 = imprimirSoloHora(hora2);
		if(str1.equalsIgnoreCase(str2)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean sonHorasIguales(Date hora1, Date hora2){
		String str1 = imprimirSoloHora(hora1);
		String str2 = imprimirSoloHora(hora2);
		if(str1.equalsIgnoreCase(str2)){
			return true;
		}else{
			return false;
		}
	}
	
	public static int getUltimoDiaDelMes(DateTime fecha){
		LocalDate local = LocalDate.fromDateFields(fecha.toDate());
		return local.dayOfMonth().withMaximumValue().getDayOfMonth();
	}
	
	public static int getPrimerDiaDelMes(DateTime fecha){
		LocalDate local = LocalDate.fromDateFields(fecha.toDate());
		return local.dayOfMonth().withMinimumValue().getDayOfMonth();
	}
	
	public static Date getPrimerDiaDelMes() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getUltimoDiaDelMes() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	public static DateTime obtenerHoraMinimaDia(){
		DateTime dateTime = DateTime.now().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
		return dateTime;
	}
	
	public static DateTime obtenerHoraMaximaDia(){
		DateTime dateTime = DateTime.now().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(59);
		return dateTime;
	}
	
	public static DateTime fechaMasHoras(DateTime fecha,Integer horas){
		DateTime dateTime = fecha.plusHours(horas);
		return dateTime;
	}
	
	public static Date fechaMasHoras(Date fecha,Integer horas){
		Date dateResult =  new Date();
		if(fecha!=null){
			DateTime dateTime = convertirEnDateTime(fecha).plusHours(horas);
			dateResult =dateTime.toDate();
		}		
		return dateResult;
	}	
	
	public static DateTime fechaMasMinutos(DateTime fecha,Integer min){
		DateTime dateTime = fecha.plusMinutes(min);
		return dateTime;
	}
	
	public static Date fechaMasMinutos(Date fecha,Integer min){
		Date dateResult =  new Date();
		if(fecha!=null){
			DateTime dateTime = convertirEnDateTime(fecha).plusMinutes(min);
			dateResult =dateTime.toDate();
		}		
		return dateResult;
	}	
	
	public static Integer diferenciaHoras(Date fechaA, Date fechaB){
		Integer result =  null;
		if(fechaA!=null && fechaB !=null){
			DateTime dateTimeA = convertirEnDateTime(fechaA);
			DateTime dateTimeB = convertirEnDateTime(fechaB);
			Hours obj=  Hours.hoursBetween(dateTimeA, dateTimeB);
			result = obj.getHours();
			
		}		
		return result;
	}
	
	public static String getProperty(String property){
		if(properties.containsKey(property)){
			return properties.getString(property);
		}else{
			return "";
		}
	}
	
	public static DateTime transformarEnDateTime(String fechaText, String pattern){
		DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		DateTime dt = formatter.parseDateTime(fechaText);
		return dt;
	}
	
	public static Date transformarEnDate(String fechaText, String pattern){
		DateTime dt = transformarEnDateTime(fechaText, pattern);
		return dt.toDate();
	}
	
	
	/**
	 * @param valor
	 * @return
	 */
	public static String formatoPeriodo(String valor) {
		String valorString = "";		
		if (UtilesCommons.esVacio(valor)) {
			valorString = null;
		} else {
			if(valor.length()>=6){
				Date valorFecha= TimeCommons.transformarEnDate(valor, PATTERN_ANIO_MES);
				valorString = TimeCommons.imprimirDateTime(valorFecha, PATTERN_ANIO_MES_SEPARATOR);
			}else{
				valorString = valor;
			}
		}
		return valorString;
	}
	
	public static String obtenerNombreMes(int mes){
		String nombreMes = "";
		switch(mes){
			case MES_ENERO_JODA 		: nombreMes = getProperty("MONTH_JANUARY");break;
			case MES_FEBRERO_JODA 		: nombreMes = getProperty("MONTH_FEBRUARY");break;
			case MES_MARZO_JODA 		: nombreMes = getProperty("MONTH_MARCH");break;
			case MES_ABRIL_JODA 		: nombreMes = getProperty("MONTH_APRIL");break;
			case MES_MAYO_JODA 			: nombreMes = getProperty("MONTH_MAY");break;
			case MES_JUNIO_JODA 		: nombreMes = getProperty("MONTH_JUNE");break;
			case MES_JULIO_JODA 		: nombreMes = getProperty("MONTH_JULY");break;
			case MES_AGOSTO_JODA 		: nombreMes = getProperty("MONTH_AUGUST");break;
			case MES_SEPTIEMBRE_JODA	: nombreMes = getProperty("MONTH_SEPTEMBER");break;
			case MES_OCTUBRE_JODA 		: nombreMes = getProperty("MONTH_OCTOBER");break;
			case MES_NOVIEMBRE_JODA 	: nombreMes = getProperty("MONTH_NOVEMBER");break;
			case MES_DICIEMBRE_JODA 	: nombreMes = getProperty("MONTH_DECEMBER");break;
		}
		return nombreMes;
	}
	
	public static String obtenerNombreDiaSemana(int semana){
		String nombreSemana = "";
		switch(semana){
			case DIA_LUNES_JODA 	: nombreSemana = getProperty("DAY_MONDAY");break;
			case DIA_MARTES_JODA 	: nombreSemana = getProperty("DAY_TUESDAY");break;
			case DIA_MIERCOLES_JODA : nombreSemana = getProperty("DAY_WEDNESDAY");break;
			case DIA_JUEVES_JODA 	: nombreSemana = getProperty("DAY_THURSDAY");break;
			case DIA_VIERNES_JODA 	: nombreSemana = getProperty("DAY_FRIDAY");break;
			case DIA_SABADO_JODA 	: nombreSemana = getProperty("DAY_SATURDAY");break;
			case DIA_DOMINGO_JODA 	: nombreSemana = getProperty("DAY_SUNDAY");break;
		}
		return nombreSemana;
	}
	
	public static DateTime sumarDiasSinFinSemana(DateTime fecha, int dias){
		for(int i = 1; i <= dias; i++){
			int cantidadSumar = 1;
			if(fecha.getDayOfWeek() == DIA_VIERNES_JODA){
				cantidadSumar = 3;
			}
			fecha = fecha.plusDays(cantidadSumar);
		}
		return fecha;
	}
	
	public static DateTime concatenarFechaHora(DateTime fecha, DateTime hora){
		return new DateTime(fecha.getYear(), fecha.getMonthOfYear(), fecha.getDayOfMonth(), hora.getHourOfDay(), hora.getMinuteOfHour(), hora.getSecondOfMinute(), hora.getMillisOfSecond());
	}
	
	public static DateTime concatenarFechaHora(Date fecha, Date hora){
		return concatenarFechaHora(getSoloFecha(fecha), getSoloHora(hora));
	}
	
	public static DateTime aproximarHoraFinPorMinutos(DateTime horaInicio, DateTime horaFin, int minutos){
		horaInicio = getSoloHora(horaInicio);
		horaFin = getSoloHora(horaFin);
		DateTime index = horaInicio;
		while(index.isBefore(horaFin)){
			index = index.plusMinutes(minutos);
		}
		return index;
	}
	
	public static Date aproximarHoraFinPorMinutos(Date horaInicio, Date horaFin, int minutos){
		Date hora = aproximarHoraFinPorMinutos(convertirEnDateTime(horaInicio), convertirEnDateTime(horaFin), minutos).toDate();
		return hora;
	}
	
	/**
	 * <p>Compara dos dateTime
	 * <ul>
	 *	<li> -1: dt1 < dt2
	 *  <li>  0: dt1 = dt2
	 *  <li>  1: dt1 > dt2
	 * </ul>
	 * @param dt1 primer DateTime
	 * @param dt2 segundo DateTime
	 * @return
	 */
	public static int compararDateTime(DateTime dt1, DateTime dt2){
		if(dt1.isBefore(dt2)){
			return -1;
		}else if(dt1.isEqual(dt2)){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * <ul>
	 *	<li> -1: fechaHora1 < fechaHora2
	 *  <li>  0: fechaHora1 = fechaHora2
	 *  <li>  1: fechaHora1 > fechaHora2
	 * </ul>
	 * @param fechaHora1
	 * @param fechaHora2
	 * @return
	 */
	public static int compararFechaHora(DateTime fechaHora1, DateTime fechaHora2){
		return compararDateTime(fechaHora1, fechaHora2);
	}
	
	/**
	 * <ul>
	 *	<li> -1: fechaHora1 < fechaHora2
	 *  <li>  0: fechaHora1 = fechaHora2
	 *  <li>  1: fechaHora1 > fechaHora2
	 * </ul>
	 * @param fechaHora1
	 * @param fechaHora2
	 * @return
	 */
	public static int comprarFechaHora(Date fechaHora1, Date fechaHora2){
		return compararDateTime(convertirEnDateTime(fechaHora1), convertirEnDateTime(fechaHora2));
	}
	
	/**
	 * <ul>
	 *	<li> -1: fecha1 < fecha2
	 *  <li>  0: fecha1 = fecha2
	 *  <li>  1: fecha1 > fecha2
	 * </ul>
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	public static int compararSoloFecha(DateTime fecha1, DateTime fecha2){
		return compararDateTime(getSoloFecha(fecha1), getSoloFecha(fecha2));
	}
	
	/**
	 * <ul>
	 *	<li> -1: fecha1 < fecha2
	 *  <li>  0: fecha1 = fecha2
	 *  <li>  1: fecha1 > fecha2
	 * </ul>
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	public static int compararSoloFecha(Date fecha1, Date fecha2){
		return compararDateTime(getSoloFecha(fecha1), getSoloFecha(fecha2));
	}
	
	/**
	 * <ul>
	 *	<li> -1: hora1 < hora2
	 *  <li>  0: hora1 = hora2
	 *  <li>  1: hora1 > hora2
	 * </ul>
	 * @param hora1
	 * @param hora2
	 * @return
	 */
	public static int compararSoloHora(DateTime hora1, DateTime hora2){
		return compararDateTime(getSoloHora(hora1), getSoloHora(hora2));
	}
	
	/**
	 * <ul>
	 *	<li> -1: hora1 < hora2
	 *  <li>  0: hora1 = hora2
	 *  <li>  1: hora1 > hora2
	 * </ul>
	 * @param hora1
	 * @param hora2
	 * @return
	 */
	public static int compararSoloHora(Date hora1, Date hora2){
		return compararDateTime(getSoloHora(hora1), getSoloHora(hora2));
	}
}	
