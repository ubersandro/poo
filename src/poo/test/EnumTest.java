package poo.test;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.TreeSet;

@SuppressWarnings("unused")
public class EnumTest {
	public enum Saluti {CIAO, PORCODIO, DIOFROCIO, DIOMERDA};
	
	
	public static void main(String ...strings ) {
		System.out.println(Arrays.toString(Saluti.values()));
		System.out.println(Saluti.CIAO.compareTo(Saluti.DIOFROCIO));
		System.out.println(Saluti.valueOf("CIAO").name());
		
	}//main
	
}//EnumTest

