package poo.regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * Scrivi programma sincronia sottotitoli serie tv
 */

public class ReplaceDemo {
	public static void main(String ... libero) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserisci il nome di un file di testo : ");
		String nomeFile= sc.nextLine();
		BufferedReader br = new BufferedReader(
				new FileReader ( nomeFile) //ho aperto il file in lettura 
		);
		StringBuilder sb = new StringBuilder(2000);
		for(;;) {
			String linea = br.readLine();
			if(linea==null) break;
			sb.append(linea + "\n"); //quando arrivo a fine linea (cr e line feed) ci metto lo spazio io
		}// per leggere tutto il file 
		br.close();
		String documento = sb.toString(); 
		documento.replaceAll("java" , "JAVA"); //espressione regolare degenere java 
		//essendo le string immutabili,se usiamo replaceAll costruiamo una nuova String
		System.out.println(documento);
		sc.close();
	}//main
}
