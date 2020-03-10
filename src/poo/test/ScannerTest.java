package poo.test;

import java.util.Scanner;

public class ScannerTest {
		public static void main(String...strings) {
			Scanner sc= new Scanner(System.in);
			for(int i=0;i<5;i++) {
				System.out.println('>');
				int j= sc.nextInt();
				System.out.println(j);
			}
			String prova= "e dio lo gnomo mongoloide, ciao mamma: guarda come-mi-diverto";
			sc.close();
			sc=new Scanner(prova);
			sc.useDelimiter("[\\-;:\\+ ,]+");//regex delimiters 
			while(sc.hasNext())System.out.println(sc.next());
			sc.close();
		}//main
}
