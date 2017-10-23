package pe.jaav.common.util.model;

public class ResultTx <T>{

	private T result;
	private boolean ok;
	private String modo;
	
	
	// Control para Warnings
	private boolean warningResult;
	private Throwable warning;
	private String warningCodigo;
	private String warningMsj;
	
	// Control de Errores
	private boolean errorResult;
	private Throwable error;
	private String errorCodigo;
	private String errorMsj;
	
	private Object[] paramErrorMsj;
	
	public ResultTx(){
		
	}
	
	public static <T> ResultTx<T> ok(T result){
		ResultTx<T> obj = new ResultTx<>();
		obj.setResult(result);
		obj.setOk(true);
		return obj;
	}
	
	/** METODOS PARA RECUPERAR LOS ERRORES DENTRO DE UNA TRANSACCION O SERVICIO QUE NO FUNCIONARON**/
	
	public static <T> ResultTx<T> error(T result, String errorCodigo){
		return error(result, null, errorCodigo, "");
	}
	
	public static <T> ResultTx<T> error(T result, Throwable error){
		return error(result, error, "", "");
	}
	
	public static <T> ResultTx<T> error(T result, Throwable error, String errorCodigo){
		return error(result, error, errorCodigo, "");
	}
	
	public static <T> ResultTx<T> error(T result, String errorCodigo, String errorMsj){
		return error(result, null, errorCodigo, errorMsj);
	}
	
	public static <T> ResultTx<T> error(T result, Throwable error, String errorCodigo, String errorMsj){
		ResultTx<T> obj = new ResultTx<>();
		obj.setResult(result);
		obj.setOk(false);
		obj.setWarningResult(false);
		obj.setErrorResult(true);
		obj.setError(error);
		obj.setErrorMsj(errorMsj);
		obj.setErrorCodigo(errorCodigo);
		return obj;
	}
	
	/** METODOS PARA RECUPERAR LOS ERRORES DENTRO DE UNA TRANSACCION Y/O SERVICIO QUE FUNCIONO CORRECTAMENTE**/
	
	public static <T> ResultTx<T> warning(T result, String errorCodigo){
		return warning(result, null, errorCodigo, "");
	}
	
	public static <T> ResultTx<T> warning(T result, Throwable error){
		return warning(result, error, "", "");
	}
	
	public static <T> ResultTx<T> warning(T result, Throwable error, String errorCodigo){
		return warning(result, error, errorCodigo, "");
	}
	
	public static <T> ResultTx<T> warning(T result, String errorCodigo, String errorMsj){
		return warning(result, null, errorCodigo, errorMsj);
	}
	
	public static <T> ResultTx<T> warning(T result, Throwable warning, String warningCodigo, String warningMsj){
		ResultTx<T> obj = new ResultTx<>();
		obj.setResult(result);
		obj.setOk(true);
		obj.setWarningResult(true);
		obj.setErrorResult(false);
		obj.setWarning(warning);
		obj.setWarningCodigo(warningCodigo);
		obj.setWarningMsj(warningMsj);
		return obj;
	}
	
	public void addParameterErrorMsj(Object ... params){
		this.paramErrorMsj = params;
	}
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
	public void setModo(String modo) {
		this.modo = modo;
	}
	
	public String getModo() {
		return modo;
	}
	
	public boolean isWarningResult() {
		return warningResult;
	}
	
	public void setWarningResult(boolean warningResult) {
		this.warningResult = warningResult;
	}
	
	public Throwable getWarning() {
		return warning;
	}
	
	public void setWarning(Throwable warning) {
		this.warning = warning;
	}
	
	public String getWarningCodigo() {
		return warningCodigo;
	}
	
	public void setWarningCodigo(String warningCodigo) {
		this.warningCodigo = warningCodigo;
	}
	
	public String getWarningMsj() {
		return warningMsj;
	}
	
	public void setWarningMsj(String warningMsj) {
		this.warningMsj = warningMsj;
	}
	
	public boolean isErrorResult() {
		return errorResult;
	}
	
	public void setErrorResult(boolean errorResult) {
		this.errorResult = errorResult;
	}

	public Throwable getError() {
		return error;
	}

	public void setError(Throwable error) {
		this.error = error;
	}

	public String getErrorCodigo() {
		return errorCodigo;
	}

	public void setErrorCodigo(String errorCodigo) {
		this.errorCodigo = errorCodigo;
	}
	
	public String getErrorMsj() {
		return errorMsj;
	}
	
	public void setErrorMsj(String errorMsj) {
		this.errorMsj = errorMsj;
	}
	
	public Object[] getParamErrorMsj() {
		return paramErrorMsj;
	}
	
	public void setParamErrorMsj(Object[] paramErrorMsj) {
		this.paramErrorMsj = paramErrorMsj;
	}
}
