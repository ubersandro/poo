package poo.calcolatore;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Calcolatrice {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		String espr=sc.nextLine();
		StringTokenizer st= new StringTokenizer(espr, "+-/*");
		int ris= valutaEspressione(st);
		System.out.println(espr+"="+ris);
		sc.close();
		}//main
	
	static int valutaEspressione(StringTokenizer st) {//la prima cosa che incontro è un operando 	
		int ris = valutaOperando(st);
		while (st.hasMoreTokens()) {
			char op=st.nextToken().charAt(0);
			if(op == ')') return ris;
			int opnd=valutaOperando(st) ;//ricorda l'ultima posizione 
			switch(op) {
				case '+': ris=ris+opnd; break;
				case '-': ris=ris-opnd; break;
				case'*': ris=ris*opnd;break;
				case '/': ris=ris/opnd; break;
				default: throw new RuntimeException("Mannaia l'ostia. Ma che mi passi?");
			}//switch
		}//while
		return ris;
	}//valutaEspressione
	
	static int valutaOperando(StringTokenizer st) {
		String tk=st.nextToken(); //sposta la freccia ==>
		if(tk.equals("(")) return valutaEspressione(st); //chiamataRicorsiva  qui "(" è una stringa
	    return Integer.parseInt(tk); // perchè è un numero e devo convertirlo
		//l'else è inutile mannaiadioeacruci
		
	}//valutaOperando
	
	
	
}//Calcolatore
