package pe.jaav.common.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

public class GenericReportServlet extends  HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4107380739407005806L;
	
	
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
	/** Genera el REPORTE para visualizarlo, a partir de una lista de elementos  y par-metros enviados
	 * @param file
	 * @param params
	 * @param lista
	 */
	public void reportPDF(Object file, Map<String, Object> params, Collection<?> lista) {
		ServletOutputStream outputStream = null;
		byte[] bytes = null;
		JasperReport jasperReport = null;
		try {
			String root_path = this.getServletContext().getRealPath("/");			
			ReportUtiles.setParametrosDefault(params, root_path);
			//construir JASPER FILE
			jasperReport = ReportUtiles.buildReportTemplate(file);
			//correr JASPER FILE
			bytes = ReportUtiles.runReport(jasperReport, params, lista);

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			outputStream = response.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
			System.gc();
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(e.getMessage());
		} finally {
			try {
				if (file instanceof InputStream) {
					((InputStream) file).close();
				}
			} catch (Exception e) {
				//logger.error(e);
			}
		}
		//logger.debug("FIN REPORTE PDF");
	}
	
	/** Genera el REPORTE para visualizarlo, a partir de una lista de elementos  y par-metros enviados
	 * @param file
	 * @param params
	 * @param lista
	 * @param filePDF
	 */
	public void reportPDFtoFile(Object file, Map<String, Object> params, Collection<?> lista,String filePDF) {		
		byte[] bytes = null;
		JasperReport jasperReport = null;
		Log.debug("[SYS:]:INICIO REPORTE TO PDF");
		try {
			bytes = getReportPDFtoFile(file, params, lista);
			if (bytes !=null && filePDF!=null) {			
			    FileOutputStream fos = new FileOutputStream(new File(filePDF));
			    fos.write(bytes);
			    fos.close();
			} else {
				bytes = JasperRunManager.runReportToPdf(jasperReport,params);
			}		
		} catch (Exception e) {
			e.printStackTrace();			
			Log.error(e.getMessage(), e);
		} finally {
			try {
				System.gc();
			} catch (Exception e) {
				Log.error(e.getMessage(), e);				
			}
		}
		Log.debug("[SYS:]:FIN REPORTE TO PDF");
	}
	
	/** Genera el REPORTE para visualizarlo, a partir de una lista de elementos  y par-metros enviados
	 * y retorna los BYTES
	 * @param file
	 * @param params
	 * @param lista
	 * @param filePDF
	 */
	public byte[] getShownReportPDFtoFile(Object file, Map<String, Object> params, Collection<?> lista) {		
		byte[] bytes = null;
		ServletOutputStream outputStream = null;		
		Log.debug("[SYS:]:INICIO REPORTE TO PDF");
		try {
			bytes = getReportPDFtoFile(file, params, lista);
			if (bytes !=null) {			
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				outputStream = response.getOutputStream();
				outputStream.write(bytes, 0, bytes.length);
				outputStream.flush();
				outputStream.close();
			} 	
		} catch (Exception e) {
			e.printStackTrace();			
			Log.error(e.getMessage(), e);
		} finally {
			try {
				System.gc();
			} catch (Exception e) {
				Log.error(e.getMessage(), e);				
			}
		}
		Log.debug("[SYS:]:FIN REPORTE TO PDF");
		return bytes;
	}	
	
	/** Genera el REPORTE para visualizarlo, a partir de una lista de elementos  y par-metros enviados
	 * y retorna los BYTES
	 * @param file
	 * @param params
	 * @param lista	
	 */
	public byte[] getShownReportPDFtoFile(Object file, Map<String, Object> params,  JRDataSource jrdsObj) {		
		byte[] bytes = null;
		ServletOutputStream outputStream = null;		
		Log.debug("[SYS:]:INICIO REPORTE TO PDF");
		try {
			bytes = getReportPDFDataSourceToFile(file, null, params, jrdsObj);
			//bytes = getReportPDFtoFile(file, params, lista);
			if (bytes !=null) {			
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				outputStream = response.getOutputStream();
				outputStream.write(bytes, 0, bytes.length);
				outputStream.flush();
				outputStream.close();
			} 	
		} catch (Exception e) {
			e.printStackTrace();			
			Log.error(e.getMessage(), e);
		} finally {
			try {
				System.gc();
			} catch (Exception e) {
				Log.error(e.getMessage(), e);				
			}
		}
		Log.debug("[SYS:]:FIN REPORTE TO PDF");
		return bytes;
	}	
	
	/**
	 * @param fileJrxml
	 * @param fileJasper
	 * @param params
	 * @param jrdsObj
	 * @param filePDF
	 */
	public byte[] getReportPDFDataSourceToFile(Object fileJrxml,Object fileJasper, Map<String, Object> params, JRDataSource jrdsObj) {		
		byte[] bytes = null;
		JasperReport jasperReport = null;
		try {
			if(params==null){
				params = new HashMap<String, Object>();	
			}				
			params.put(JRParameter.REPORT_LOCALE,  new Locale("en", "US"));
			/////////PATH para imagen
						
			String root_path = this.getServletContext().getRealPath("/");
			root_path = root_path.replace('\\', '/');
			params.put("imagePath", root_path);
			/////END PATH para imagen
			Log.debug("INICIO REPORTE PDF");
			if (fileJrxml instanceof File) {
				jasperReport = JasperCompileManager.compileReport(((File) fileJrxml)
						.getAbsolutePath());
			} else if (fileJrxml instanceof InputStream) {
				jasperReport = JasperCompileManager
						.compileReport((InputStream) fileJrxml);
			} else {
				Log.error("Object type is not support.");				
				Log.error("Class Name : " + fileJrxml.getClass());				
				throw new Exception("GenericAction :: reportPDF :: Object type is not support.");
			}

			if (jrdsObj !=null) {
				//JRDataSource dataSource = new JRBeanCollectionDataSource(lista);
				bytes = JasperRunManager.runReportToPdf(jasperReport,params,
						jrdsObj);
				
			} else {
				bytes = JasperRunManager.runReportToPdf(jasperReport,params);
			}
	 
			System.gc();
		} catch (Exception e) {
			e.printStackTrace();			
			Log.error(e.getMessage(), e);
		} 
		return bytes;
	}	
	
	/** Genera el REPORTE para visualizarlo, a partir de una lista de elementos  y par-metros enviados
	 * @param file
	 * @param params
	 * @param lista
	 * @param filePDF
	 */
	public byte[]  getReportPDFtoFile(Object file, Map<String, Object> params, Collection<?> lista) {		
		byte[] bytes = null;
		JasperReport jasperReport = null;		
		try {
			String root_path = this.getServletContext().getRealPath("/");			
			ReportUtiles.setParametrosDefault(params, root_path);
			//construir JASPER FILE
			jasperReport = ReportUtiles.buildReportTemplate(file);
			//correr JASPER FILE
			bytes = ReportUtiles.runReport(jasperReport, params, lista);
			
			System.gc();
		} catch (Exception e) {
			e.printStackTrace();			
			Log.error(e.getMessage(), e);
		}
		Log.debug("[SYS:]FIN GET REPORTE TO PDF");
		return bytes;
		
	}
	
	/**
	 * @param fileJrxml
	 * @param fileJasper
	 * @param params
	 * @param jrdsObj
	 * @param filePDF
	 */
	public void reportPDFDataSourceToFile(Object fileJrxml,Object fileJasper, Map<String, Object> params, JRDataSource jrdsObj, String filePDF) {
		//ServletOutputStream outputStream = null;
		byte[] bytes = null;
		JasperReport jasperReport = null;
		try {
			String root_path = this.getServletContext().getRealPath("/");			
			ReportUtiles.setParametrosDefault(params, root_path);
			//construir JASPER FILE
			jasperReport = ReportUtiles.buildReportTemplate(fileJrxml);
			//correr JASPER FILE
			bytes = ReportUtiles.runReport(jasperReport, params, jrdsObj);
	
			if (bytes !=null) {
			    FileOutputStream fos = new FileOutputStream(new File(filePDF));
			    fos.write(bytes);
			    fos.close();
			}
	 
			System.gc();
		} catch (Exception e) {
			e.printStackTrace();			
			Log.error(e.getMessage(), e);
		} finally {
			try {
				if (fileJrxml instanceof InputStream) {
					((InputStream) fileJrxml).close();
				}
			} catch (Exception e) {
				Log.error(e.getMessage(), e);				
			}
		}		
	}	
	
	
	/**
	 * @param fileJrxml
	 * @param fileJasper
	 * @param params
	 * @param jrdsObj
	 */
	public void reportPDFDataSource(Object fileJrxml,Object fileJasper, Map<String, Object> params, JRDataSource jrdsObj) {
		ServletOutputStream outputStream = null;
		byte[] bytes = null;
		JasperReport jasperReport = null;

		try {
			String root_path = this.getServletContext().getRealPath("/");			
			ReportUtiles.setParametrosDefault(params, root_path);
			//construir JASPER FILE
			jasperReport = ReportUtiles.buildReportTemplate(fileJrxml);
			//correr JASPER FILE
			bytes = ReportUtiles.runReport(jasperReport, params, jrdsObj);		
			
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			outputStream = response.getOutputStream();
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
			System.gc();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
		} finally {
			try {
				if (fileJrxml instanceof InputStream) {
					((InputStream) fileJrxml).close();
				}
			} catch (Exception e) {
				Log.error(e);
			}
		}
		Log.debug("REPORTE_SERVLET stream_bytes.length:"+bytes.length);
		Log.debug("REPORTE_SERVLET stream:"+outputStream);
		Log.debug("FIN REPORTE PDF");
	}
}
