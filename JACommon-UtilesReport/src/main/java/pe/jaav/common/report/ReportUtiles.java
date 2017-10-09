package pe.jaav.common.report;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportUtiles {

	/**
	 * @param params
	 * @param root_path
	 * @return
	 */
	public static void setParametrosDefault(Map<String, Object> params,String root_path){
		//Map<String, Object> params;
		if(params==null){
			params = new HashMap<String, Object>();	
		}				
		params.put(JRParameter.REPORT_LOCALE,  new Locale("en", "US"));
		/////////PATH para imagen		
		root_path = root_path.replace('\\', '/');
		params.put("imagePath", root_path);		
	}
	
	
	/**
	 * @param file
	 * @param params
	 * @param lista
	 * @return
	 * @throws Exception
	 */
	public static JasperReport buildReportTemplate(Object file) throws Exception{
		JasperReport jasperReport = null;
		if (file instanceof File) {
			jasperReport = JasperCompileManager.compileReport(((File) file).getAbsolutePath());
		} else if (file instanceof InputStream) {
			jasperReport = JasperCompileManager.compileReport((InputStream) file);
		}else if (file instanceof JasperReport) {
			jasperReport = (JasperReport)file;
		} else {			
			throw new Exception("GenericAction :: reportPDF :: Object type is not support.");
		}	
		return jasperReport;
	}
	
	/**
	 * @param jasperReport
	 * @param params
	 * @param lista
	 * @return
	 * @throws JRException
	 */
	public static byte[] runReport(JasperReport jasperReport, Map<String, Object> params, 
			JRDataSource jrdsObj) throws JRException{
		byte[] bytes = null;
		if(jasperReport!=null){
			if (jrdsObj !=null) {				
				bytes = JasperRunManager.runReportToPdf(jasperReport,params,jrdsObj);	
			} else {
				bytes = JasperRunManager.runReportToPdf(jasperReport,params);
			}		
		}
		
		return bytes;
	}
	
	/**
	 * @param jasperReport
	 * @param params
	 * @param lista
	 * @return
	 * @throws JRException
	 */
	public static byte[] runReport(JasperReport jasperReport, Map<String, Object> params, Collection<?> lista) throws JRException{
		byte[] bytes = null;
		if(jasperReport!=null){
			if (lista !=null) {		
				JRDataSource dataSource = new JRBeanCollectionDataSource(lista);
				bytes = JasperRunManager.runReportToPdf(jasperReport, params,dataSource);
			} else {
				bytes = JasperRunManager.runReportToPdf(jasperReport, params);
			}			
		}
		
		return bytes;
	}	
	
}
