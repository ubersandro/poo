package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Valutatore {
	
	public static int valutaEspressione( StringTokenizer st ) {
		int ris=valutaOperando(st);
		while( st.hasMoreTokens() ) {
			char op=st.nextToken().charAt(0);
			if( op==')' ) return ris;
			int opnd=valutaOperando(st);
			switch( op ) {
			case '+': ris=ris+opnd; break;
			case '-': ris=ris-opnd; break;
			case '*': ris=ris*opnd; break;
			case '/': ris=ris/opnd; break;
			default : throw new RuntimeException();
			}
		}
		return ris;
	}//valutaEspressione
	
	private static int valutaOperando( StringTokenizer st ) {
		String tk=st.nextToken();
		if( tk.contentEquals("(") ) return valutaEspressione(st);
		return Integer.parseInt(tk);
	}//valutaOperando
	
	public static void main( String...args ) {
		Scanner sc=new Scanner( System.in );
		//per il momento si ignorano le eccezioni...
		System.out.println("Fornisci una stringa-espressione");
		System.out.print("> ");
		String espr=sc.nextLine();
		StringTokenizer st=new StringTokenizer( espr, "+-*/()",true );
		int ris=valutaEspressione(st);
		System.out.println(espr+"="+ris);
		sc.close();
	}//main
	
}//Valutatore
