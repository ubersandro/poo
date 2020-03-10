package poo.esempi;
import poo.util.*;

public class InsString { //array eterogenei

	public static void main(String[] args) {
		String[] s= {"zaino", "lupo","tana","abaco"};
		Vector<String> v=new ArrayVector<>(s.length); 
		for(int i=0;i<s.length;i++) {
			String x= s[i];
			int j=0;
			while(j<v.size() && v.get(j).compareTo(x)<0) j++; 
			v.add(j,x); // perchè l'elemento j-esimo è maggiore del corrente x
		}//for
		System.out.println(v);
		v.remove(v.size()-1);
		//Vector w= new ArrayVector(150); // QUI GLI ELEMENTI SONO DI TIPO OBJECT ==> GREZZO = RAW 
		System.out.println(v);
		
	}//main

}//InsString
