package poo.esempi;
import java.util.Comparator;
import poo.util.Array;

public class TestOrdinamentoString {
	public static void main(String[] args) {
		String[] v= {"diocane", "edificio","dioporco", "rafeli","zen","tana","morte"};
		Comparator<String> sc = new StringComparator();
		Array.insertionSort(v, sc); 
		System.out.println(java.util.Arrays.toString(v));
	}//TestOrdinamentoString
}		
/*
 * Concretizzazione di una interfaccia funzionale.
 */
 class StringComparator implements Comparator<String>{ //visibilità package
	public int compare(String s1, String s2) {
		if(s1.length()<s2.length()||s1.length()==s2.length()&&s1.compareTo(s2)<0) return -1;
		if(s1.equals(s2)) return 0;
		return 1;
	}//compare
 }//StringComparator

