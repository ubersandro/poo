package poo.regex;

import java.util.Scanner;

/*
 * Ogni volta che gli passo una stringa mi deve dire che stringa �
 * 3 casi := ID(java) , String , int
 * Se vogliamo essere precisi limitiamo il numero delle cifre a 10 (tipo int:= 32 bit) 
 */

public class Classificatore {
	public static void main(String ... porcodio) {
		System.out.println("Inserisci un identificatore o un numero e termina con STOP");
		//davanti a d il '+' � un metasimbolo
		String SIGN= "[\\+\\-]";
		String UNSIGNED_INT="\\d+";
		String INT = SIGN + "?" + UNSIGNED_INT; // CONCATENAZIONE sono d'accordo con te amico mio
		String EXPO = "([Ee]"+SIGN+"?"+UNSIGNED_INT+")";
		String SUF = "[dDfF]"; //non ha bisogno del punto interrogativo
		String MANTISSA = "("+ UNSIGNED_INT +"|(" +UNSIGNED_INT+")?"+"\\."+UNSIGNED_INT+")"; // se metto UNSIGNED_INT in un gruppo seguito da '?' il programma funziona
		String REAL = SIGN+"?"+MANTISSA+EXPO+"?"+SUF+"?"; 
		//String INT = "[\\+\\-]?\\d+"; //sequenze di escape un solo '\'    
		//String IDENT = "[a-zA-Z_][a-zA-Z_$\\d]*";
		String IDENT = "[a-zA-Z_]\\w*";
		String $="ciao";
		System.out.println($);
		// equivalente a tutti i caratteri di una word
		//se obbedisce a questo formato, � giusto(non viceversa) == condizione NECESSARIA	
		// String REAL= "[\\+\\-]?(\\d+|\\d*\\.\\d+)(eE[\\+\\*]?\\d{1,3})?[dDfF]?"; // parentesi tonde := gruppi di caratteri | alternative con '|'
		Scanner sc= new Scanner(System.in);
		for(;;) {
			System.out.print("> ");
			String linea= sc.nextLine(); //rivedi Scanner
			//if( linea.contentEquals("STOP")) break;
			//if( linea.matches("STOP")) break; // � ugale
			if( linea.equalsIgnoreCase("STOP")) break; // usa la tua fantasia amico mio==> COSI' E' MEGLIO
			else if( linea.matches(INT)) System.out.println(linea+" intero");
			else if(linea.matches(REAL)) System.out.println(linea +" numero reale");
			else if (linea.matches(IDENT)) System.out.println(linea + " identficatore Java");
			else System.out.println(linea + " non riconosciuta");
		}//for
		System.out.println("Bye ;)");
		sc.close();
	}//main
}//Classificatore

//Scrivere una regex che modella la linea di input POLINOMIO
//usando StringTokenizer costruisci polinomio facendo add polinomio.add(monomio)
// scanner su una stringa ==> delimitatori e next, hasnext per prendere i delimitatori 
