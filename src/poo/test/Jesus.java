package poo.test;

import java.util.*;

/*
 * 13/11
 */
@SuppressWarnings("unused")
public class Jesus<T> {
	 void stampaLista(List<T> lo) { // non object
		for(T o: lo)
			System.out.println(o);
	}
	@SuppressWarnings("unused") 
	public static void main(String ...strings ) {
		List<String>a = new ArrayList<>();
		a.add("PORCODIO");
		List<? extends String> l= a;
		System.out.println(l);
		a.add("Ges� Cristo");
		System.out.println(l);
		//Collections.copy(new LinkedList<String>(), l);
		String[] bestemmie= { "dio", "cane","frocio" ,"perna" };
		Object[] anabolicSteroids=bestemmie; // si pu� fare 
		System.out.println(anabolicSteroids[0]);
		//anabolicSteroids[0]= new Integer(8);
		String ER= "5[123][1-7]";//faccio uso dei metasimboli perche' appartengono al metalinguaggio
		System.out.println("532".matches(ER)); //pattern matching
		System.out.println("542".matches(ER)); 
		// one of := sono alternative
		ER="5[432][2]";
		System.out.println("532".matches(ER)); 
		double COST= .25; // potrebbe mancare la cifra sopra lo zero
		int _diocane$= 8;
		String NOME_JAVA_REGEX ="[a-zA-Z_][a-zA-Z_0-9$]*"; // tutti i possibili nomi che possiamo usare in Java
		String NUMERO_INTERO = "\\-?[0-9]+ "; 
		int i = +8;
		String esempio_regex="[0-9] {5,}"; //si ripete 5 o piu volte 
		String targa="[A-Z]{2}[0-9] {3}[A-Z] {2}"; // potrebbe essere una targa (Struttura standard di una targa) 
		String prefisso= "[0-9] {2,4}[0-9] {0,7}";
		String VOLO = "(AZ|FR)[0-9] {4}"; //alternative tra gruppi
		String REALE= "\\-?([0-9]+|([0-9]+)?\\.[0-9]+)([Ee][\\+\\-]?[0-9] {1,3})?[DdFf]?";
		System.out.println("------------------------------------------");
		Map<Integer, Integer> m= new TreeMap<>();
		m.put(2,3);
		m.put(3,2);
		System.out.println(m);
	}
	
	
}
