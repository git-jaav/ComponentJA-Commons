package pe.jaav.common.crypto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptoMotorUtiles {


	/** Generar la Clave/Token codificados. Basados en el algoritmo bcrypt. 
	 * @param token
	 * @return
	 */
	public static String getCifrado(String token){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedToken = passwordEncoder.encode(token);
		
		return hashedToken;
	}
	
	/** Generar la Clave/Token codificados. Basados en el algoritmo bcrypt.
	 * Note that bcrypt logrounds is not the same as iterations. 
	 * Number of iterations = 2log_rounds. 
	 * Example: 12 logrounds = 4096 (212) iterations.
	 * @param token
	 * @param log_rounds (deber de estar entre 4 y 31)
	 * @return
	 */
	public  static String getCifrado(String token, int log_rounds){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(log_rounds);
		String hashedToken = passwordEncoder.encode(token);
		
		return hashedToken;
	}
	

	/**Note that bcrypt logrounds is not the same as iterations. 
	 * Number of iterations = 2log_rounds. 
	 * Example: 12 logrounds = 4096 (212) iterations.
	 * @param tokenCifrado
	 * @param token
	 * @param log_rounds
	 * @return
	 */
	public static boolean isMatchDescifrado(String tokenCifrado,String token, int log_rounds){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean matchTokens  = passwordEncoder.matches(token, tokenCifrado);
		
		return matchTokens;
	}
	
	/** Verificar que los dos token entregados se correspondan, luego del encriptamiento
	 * @param tokenCifrado (Cifrado bajo el mismo algoritmo)
	 * @param token (Original)
	 * @return
	 */
	public static boolean isMatchDescifrado(String tokenCifrado,String token){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean matchTokens  = passwordEncoder.matches(token, tokenCifrado);
		
		return matchTokens;
	}	
	
	
}
