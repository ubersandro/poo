package poo.test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class TestCollections {
	public static void main(String ... arcuri) {
		Set<Integer> s = new HashSet<>();
		s.add(8);
		s.add(9);
		Set<Integer> s2= new TreeSet<>(s);
		System.out.println(s2);
		int [] a= {1,2,4,56,345,44};
		System.out.println(Arrays.hashCode(a));
		List<Integer> b= Arrays.asList(1,2,3,4,5,6,7); //utile quando mi scoccio a fare 
		System.out.println(b.getClass());
	}//main
}//TestCollections
