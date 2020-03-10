package poo.test;

import poo.util.List;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class TestChar {
	/**
	 * @param strings
	 */
	public static void main(String ... strings){
		for(String s:strings) System.out.println(s);
		System.out.println(("max char="+(int) Character.MAX_VALUE)); //?
		System.out.println("Numero di byte per rappresentare un char=" + Character.BYTES);
		char c= 0x41 + 0x20 ;
		System.out.println(c);
		System.out.println(Character.isLetter(c));
		Character c1= new Character('c');
		System.out.println(c1);
		System.out.println((int)c1.charValue());
		System.out.println(Character.getNumericValue(c1));
		long l= Long.MAX_VALUE;
		System.out.println(l);
		System.out.println("\u00ff");
		String prova= "casa";
		Stack<String> x= new Stack<>();
		x.push(new String("CIAO"));
		System.out.println("Il tuo stack �"+x);
		
		int i=(int) ' ';
		System.out.println("Carattere spazio = " +i);
		i++;
		char c3=(char) i; 
		System.out.println(c3);
		char c4=(char) (c3+c1.charValue());
		int c5= (int)c4;
		System.out.println(Integer.MAX_VALUE);
		System.out.println(c5==c4);
		String prova2=" ";
		System.out.println(prova.compareTo(prova2)>0);
		char[] a= new char[prova.length()];
		a= prova.toCharArray();
		System.out.println(Arrays.toString(a));
		StringTokenizer st= new StringTokenizer(prova);
		String g= "ciao " +1;
		System.out.println(g);
		System.out.println((int) ' ');
		System.out.println("-----------------------------------");
		List<Integer> lista= new poo.util.LinkedList<>();
		lista.addFirst(3);
		System.out.println(lista instanceof Object);


		System.out.println("DIOCANE");
		String stringa="a";
		String strin= "A";
		System.out.println((short)strin.charAt(0));
		System.out.println(strin.compareTo(stringa)>0);
		}//main

	native static void prova();
		
	
}
