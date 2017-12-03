package pe.jaav.common.crypto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.rsa.crypto.RsaSecretEncryptor;

/**
 * @author JAAV
 *
 */
public class CryptoMotorService {

	String tipoEncriptacion ;
	BCryptPasswordEncoder passwordEncoder;	
	TextEncryptor encryptorRSA;
	
	/** activa el modo RSA
	 * Important Prerequisites: to use Spring Security RSA you need the full-strength JCE installed in your JVM 
	 * (it's not there by default). You can download the "Java Cryptography Extension (JCE) 
	 * Unlimited Strength Jurisdiction Policy Files" from Oracle, and follow instructions 
	 * for installation (essentially replace the 2 policy files in the JRE lib/security 
	 * directory with the ones that you downloaded).	 
	 * @link https://github.com/dsyer/spring-security-rsa
	 * @param indicaRSA
	 */
	public CryptoMotorService(boolean indicaRSA) {
		super();
		if(indicaRSA){
			this.encryptorRSA = new RsaSecretEncryptor();
		}else{
			this.passwordEncoder = new BCryptPasswordEncoder();	
		}							
	}

	
	public CryptoMotorService() {
		super();
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	/** Generar la Clave/Token codificados. Basados en el algoritmo bcrypt. 
	 * @param token
	 * @return
	 */
	public String getCifrado(String token){		
		String hashedToken = passwordEncoder.encode(token);		
		return hashedToken;
	}
	
	
	/** Verificar que los dos token entregados se correspondan, luego del encriptamiento
	 * @param tokenCifrado (Cifrado bajo el mismo algoritmo)
	 * @param token (Original)
	 * @return
	 */
	public boolean isMatchDescifrado(String tokenCifrado,String token){		
		boolean matchTokens  = passwordEncoder.matches(token, tokenCifrado);		
		return matchTokens;
	}	
	
	
	/** Generar la Clave/Token codificados. Basados en el algoritmo bcrypt.
	 * Note that bcrypt logrounds is not the same as iterations. 
	 * Number of iterations = 2log_rounds. 
	 * Example: 12 logrounds = 4096 (212) iterations.
	 * @param token
	 * @param log_rounds (deber de estar entre 4 y 31)
	 * @return
	 */
	public  String getCifrado(String token, int log_rounds){	
		BCryptPasswordEncoder passwordEncoderLogRounds = new BCryptPasswordEncoder(log_rounds);
		String hashedToken = passwordEncoderLogRounds.encode(token);		
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
	public boolean isMatchDescifrado(String tokenCifrado,String token, int log_rounds){		
		BCryptPasswordEncoder passwordEncoderLogRounds = new BCryptPasswordEncoder(log_rounds);
		boolean matchTokens  = passwordEncoderLogRounds.matches(token, tokenCifrado);
		
		return matchTokens;
	}

	
	/**
	 * NOTA::
	 * Important Prerequisites: to use Spring Security RSA you need the full-strength JCE installed in your JVM 
	 * (it's not there by default). You can download the "Java Cryptography Extension (JCE) 
	 * Unlimited Strength Jurisdiction Policy Files" from Oracle, and follow instructions 
	 * for installation (essentially replace the 2 policy files in the JRE lib/security 
	 * directory with the ones that you downloaded).
	 * @link https://github.com/dsyer/spring-security-rsa
	 * @param tokenCifrado
	 * @return
	 */
	public String getDescifradoRSA(String tokenCifrado){						
		String message = encryptorRSA.decrypt(tokenCifrado);		
		return message;
	}

	/**NOTA::
	 * Important Prerequisites: to use Spring Security RSA you need the full-strength JCE installed in your JVM 
	 * (it's not there by default). You can download the "Java Cryptography Extension (JCE) 
	 * Unlimited Strength Jurisdiction Policy Files" from Oracle, and follow instructions 
	 * for installation (essentially replace the 2 policy files in the JRE lib/security 
	 * directory with the ones that you downloaded).
	 * @link https://github.com/dsyer/spring-security-rsa
	 * @param token
	 * @return
	 */
	public String getCifradoRSA(String token){				
		String cipher = encryptorRSA.encrypt(token);
		//String message = encryptor.decrypt(cipher);		
		return cipher;			
	}
}
