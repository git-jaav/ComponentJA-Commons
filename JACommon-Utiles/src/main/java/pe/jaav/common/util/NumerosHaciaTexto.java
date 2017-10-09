package pe.jaav.common.util;

/**
 * @author araucoj
 * Clase que permite obtener en TEXTO un n-mero entero
 * RECOME: EXTENDER EL VALOR DE ALCANCE ....
 */
public class NumerosHaciaTexto {
		
	private int flag;
	public int numero;
	public String importe_parcial;
	public String num;
	public String num_letra;
	public String num_letras;
	public String num_letram;
	public String num_letradm;
	public String num_letracm;
	public String num_letramm;
	public String num_letradmm;
	
	private Integer VALOR_MAX_ALCANCE=100000000;
	public static final String NUM_MILLONES="";
	
	public static final String 	NUM_CON_Y_	=	"y ";
	
	public static final String 	NUM_NUEVE	=	"nueve";
	public static final String 	NUM_OCHO	=	"ocho";
	public static final String 	NUM_SIETE	=	"siete";
	public static final String 	NUM_SEIS	=	"seis";
	public static final String 	NUM_CINCO	=	"cinco";
	public static final String 	NUM_CUATRO	=	"cuatro";
	public static final String 	NUM_TRES	=	"tres";
	public static final String 	NUM_DOS	=	"dos";
	public static final String 	NUM_UNO	=	"uno";
	public static final String 	NUM_UN	=	"un";
	public static final String 	NUM_NOVENTA_	=	"noventa ";
	public static final String 	NUM_OCHENTA_	=	"ochenta ";
	public static final String 	NUM_SETENTA_	=	"setenta ";
	public static final String 	NUM_SESENTA_	=	"sesenta ";
	public static final String 	NUM_CINCUENTA_	=	"cincuenta ";
	public static final String 	NUM_CUARENTA_	=	"cuarenta ";
	public static final String 	NUM_TREINTA_	=	"treinta ";
	public static final String 	NUM_VEINTE_	=	"veinte ";
	public static final String 	NUM_VEINTI	=	"veinti";
	public static final String 	NUM_DIEZ_	=	"diez ";
	public static final String 	NUM_ONCE_	=	"once ";
	public static final String 	NUM_DOCE_	=	"doce ";
	public static final String 	NUM_TRECE_	=	"trece ";
	public static final String 	NUM_CATORCE_	=	"catorce ";
	public static final String 	NUM_QUINCE_	=	"quince ";
	public static final String 	NUM_DIECISEIS_	=	"dieciseis ";
	public static final String 	NUM_DIECISIETE_	=	"diecisiete ";
	public static final String 	NUM_DIECIOCHO_	=	"dieciocho ";
	public static final String 	NUM_DIECINUEVE_	=	"diecinueve ";
	public static final String 	NUM_NOVECIENTOS_	=	"novecientos ";
	public static final String 	NUM_OCHOCIENTOS_	=	"ochocientos ";
	public static final String 	NUM_SETECIENTOS_	=	"setecientos ";
	public static final String 	NUM_SEISCIENTOS_	=	"seiscientos ";
	public static final String 	NUM_QUINIENTOS_	=	"quinientos ";
	public static final String 	NUM_CUATROCIENTOS_	=	"cuatrocientos ";
	public static final String 	NUM_TRESCIENTOS_	=	"trescientos ";
	public static final String 	NUM_DOSCIENTOS_	=	"doscientos ";
	public static final String 	NUM_CIEN_	=	"cien ";
	public static final String 	NUM_CIENTO_	=	"ciento ";
	public static final String 	NUM_DIEZ_MIL	=	"diez mil";
	public static final String 	NUM_MIL_	=	"mil ";
	public static final String 	NUM__MIL_	=	" mil ";
	public static final String 	NUM_CIEN_MIL	=	"cien mil";	
	public static final String 	NUM_UN_MILLON_	=	"Un millon ";
	public static final String 	NUM__MILLONES_	=	" millones ";
	public static final String 	NUM_DIEZ_MILLONES	=	"diez millones";
	public static final String 	NUM_MILLONES_	=	"millones ";
	public static final String 	NUM__MILLLONES_	=	" milllones ";

	
	
	public NumerosHaciaTexto(){
		num_letradmm = "";
		numero = 0;
		flag=0;
	}
	public NumerosHaciaTexto(int n){
		numero = n;
		flag=0;
	}

	
	private String unidad(int numero){
		
		switch (numero){
		case 9:
				num = NUM_NUEVE;
				break;
		case 8:
				num = NUM_OCHO;
				break;
		case 7:
				num = NUM_SIETE;
				break;
		case 6:
				num = NUM_SEIS;
				break;
		case 5:
				num = NUM_CINCO;
				break;
		case 4:
				num = NUM_CUATRO;
				break;
		case 3:
				num = NUM_TRES;
				break;
		case 2:
				num = NUM_DOS;
				break;
		case 1:
				if (flag == 0)
					num = NUM_UNO;
				else 
					num = NUM_UN;
				break;
		case 0:
				num = "";
				break;
		}
		return num;
	}
	
	private String decena(int numero){
	
		if (numero >= 90 && numero <= 99)
		{
			num_letra = NUM_NOVENTA_;
			if (numero > 90)
				num_letra = num_letra.concat(NUM_CON_Y_).concat(unidad(numero - 90));
		}
		else if (numero >= 80 && numero <= 89)
		{
			num_letra = NUM_OCHENTA_;
			if (numero > 80)
				num_letra = num_letra.concat(NUM_CON_Y_).concat(unidad(numero - 80));
		}
		else if (numero >= 70 && numero <= 79)
		{
			num_letra = NUM_SETENTA_;
			if (numero > 70)
				num_letra = num_letra.concat(NUM_CON_Y_).concat(unidad(numero - 70));
		}
		else if (numero >= 60 && numero <= 69)
		{
			num_letra = NUM_SESENTA_;
			if (numero > 60)
				num_letra = num_letra.concat(NUM_CON_Y_).concat(unidad(numero - 60));
		}
		else if (numero >= 50 && numero <= 59)
		{
			num_letra = NUM_CINCUENTA_;
			if (numero > 50)
				num_letra = num_letra.concat(NUM_CON_Y_).concat(unidad(numero - 50));
		}
		else if (numero >= 40 && numero <= 49)
		{
			num_letra = NUM_CUARENTA_;
			if (numero > 40)
				num_letra = num_letra.concat(NUM_CON_Y_).concat(unidad(numero - 40));
		}
		else if (numero >= 30 && numero <= 39)
		{
			num_letra = NUM_TREINTA_;
			if (numero > 30)
				num_letra = num_letra.concat(NUM_CON_Y_).concat(unidad(numero - 30));
		}
		else if (numero >= 20 && numero <= 29)
		{
			if (numero == 20)
				num_letra = NUM_VEINTE_;
			else
				num_letra = NUM_VEINTI.concat(unidad(numero - 20));
		}
		else if (numero >= 10 && numero <= 19)
		{
			switch (numero){
			case 10:

				num_letra = NUM_DIEZ_;
				break;

			case 11:

				num_letra = NUM_ONCE_;
				break;

			case 12:

				num_letra = NUM_DOCE_;
				break;

			case 13:

				num_letra = NUM_TRECE_;
				break;

			case 14:

				num_letra = NUM_CATORCE_;
				break;

			case 15:
			
				num_letra = NUM_QUINCE_;
				break;
			
			case 16:
			
				num_letra = NUM_DIECISEIS_;
				break;
			
			case 17:
			
				num_letra = NUM_DIECISIETE_;
				break;
			
			case 18:
			
				num_letra = NUM_DIECIOCHO_;
				break;
			
			case 19:
			
				num_letra = NUM_DIECINUEVE_;
				break;
			
			}	
		}
		else
			num_letra = unidad(numero);

	return num_letra;
	}	

	private String centena(int numero){
		if (numero >= 100)
		{
			if (numero >= 900 && numero <= 999)
			{
				num_letra = NUM_NOVECIENTOS_;
				if (numero > 900)
					num_letra = num_letra.concat(decena(numero - 900));
			}
			else if (numero >= 800 && numero <= 899)
			{
				num_letra = NUM_OCHOCIENTOS_;
				if (numero > 800)
					num_letra = num_letra.concat(decena(numero - 800));
			}
			else if (numero >= 700 && numero <= 799)
			{
				num_letra = NUM_SETECIENTOS_;
				if (numero > 700)
					num_letra = num_letra.concat(decena(numero - 700));
			}
			else if (numero >= 600 && numero <= 699)
			{
				num_letra = NUM_SEISCIENTOS_;
				if (numero > 600)
					num_letra = num_letra.concat(decena(numero - 600));
			}
			else if (numero >= 500 && numero <= 599)
			{
				num_letra = NUM_QUINIENTOS_;
				if (numero > 500)
					num_letra = num_letra.concat(decena(numero - 500));
			}
			else if (numero >= 400 && numero <= 499)
			{
				num_letra = NUM_CUATROCIENTOS_;
				if (numero > 400)
					num_letra = num_letra.concat(decena(numero - 400));
			}
			else if (numero >= 300 && numero <= 399)
			{
				num_letra = NUM_TRESCIENTOS_;
				if (numero > 300)
					num_letra = num_letra.concat(decena(numero - 300));
			}
			else if (numero >= 200 && numero <= 299)
			{
				num_letra = NUM_DOSCIENTOS_;
				if (numero > 200)
					num_letra = num_letra.concat(decena(numero - 200));
			}
			else if (numero >= 100 && numero <= 199)
			{
				if (numero == 100)
					num_letra = NUM_CIEN_;
				else
					num_letra = NUM_CIENTO_.concat(decena(numero - 100));
			}
		}
		else
			num_letra = decena(numero);
		
		return num_letra;	
	}	

	private String miles(int numero){
		if (numero >= 1000 && numero <2000){
			num_letram = (NUM_MIL_).concat(centena(numero%1000));
		}
		if (numero >= 2000 && numero <10000){
			flag=1;
			num_letram = unidad(numero/1000).concat(NUM__MIL_).concat(centena(numero%1000));
		}
		if (numero < 1000)
			num_letram = centena(numero);
		
		return num_letram;
	}		

	private String decmiles(int numero){
		if (numero == 10000)
			num_letradm = NUM_DIEZ_MIL;
		if (numero > 10000 && numero <20000){
			flag=1;
			num_letradm = decena(numero/1000).concat(NUM_MIL_).concat(centena(numero%1000));		
		}
		if (numero >= 20000 && numero <100000){
			flag=1;
			num_letradm = decena(numero/1000).concat(NUM__MIL_).concat(miles(numero%1000));		
		}
		
		
		if (numero < 10000)
			num_letradm = miles(numero);
		
		return num_letradm;
	}		

	private String cienmiles(int numero){
		if (numero == 100000)
			num_letracm = NUM_CIEN_MIL;
		if (numero >= 100000 && numero <1000000){
			flag=1;
			num_letracm = centena(numero/1000).concat(NUM__MIL_).concat(centena(numero%1000));		
		}
		if (numero < 100000)
			num_letracm = decmiles(numero);
		return num_letracm;
	}		

	private String millon(int numero){
		if (numero >= 1000000 && numero <2000000){
			flag=1;
			num_letramm = (NUM_UN_MILLON_).concat(cienmiles(numero%1000000));
		}
		if (numero >= 2000000 && numero <10000000){
			flag=1;
			num_letramm = unidad(numero/1000000).concat(NUM__MILLONES_).concat(cienmiles(numero%1000000));
		}
		if (numero < 1000000)
			num_letramm = cienmiles(numero);
		
		return num_letramm;
	}		
	
	/** Recibe un entero y empieza el procedimietno de conversi-n (valida el m-ximo n-mero permitisdo)
	 * @param numero
	 * @return
	 */
	private String decmillon(int numero){
		if (numero == 10000000)
			num_letradmm = NUM_DIEZ_MILLONES;
		if (numero > 10000000 && numero <20000000){
			flag=1;
			num_letradmm = decena(numero/1000000).concat(NUM_MILLONES_).concat(cienmiles(numero%1000000));		
		}
		if (numero >= 20000000 && numero <VALOR_MAX_ALCANCE){
			flag=1;
			num_letradmm = decena(numero/1000000).concat(NUM__MILLLONES_).concat(millon(numero%1000000));		
		}
		
		
		if (numero < 10000000)
			num_letradmm = millon(numero);
		
		return num_letradmm;
	}		

	
	public String convertirLetras(Integer numero){
		num_letras = decmillon(numero);
		return num_letras;
	} 	
}



