package pe.jaav.common.crypto;

public class CryptoMain {
	
	

	public static void main(String[] args){
	
		CryptoMotorService crypto = new CryptoMotorService();
		String original = "mi_clave";
		String cifrado = crypto.getCifrado(original);								
		System.out.println("[TEST::cifrado:]" + cifrado);		
		boolean esDecifrado = crypto.isMatchDescifrado(cifrado, original);
		System.out.println("[TEST::len:]" + cifrado.length());
		System.out.println("[TEST::descifrado:]" + esDecifrado);
	}
}
