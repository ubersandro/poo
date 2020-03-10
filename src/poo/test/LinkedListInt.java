package poo.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
/*
 * 
 * programma che prende gli interi (come stringhe) da riga di comando e restituisce una linked list ordinata in maniera crescente
 * 
 * 
 */
@SuppressWarnings("unused")
public class LinkedListInt {
	public static void main(String ... args) {
		System.out.println("Inserire interi da ordinare");
		LinkedList <Integer> l= new LinkedList<>();
		if(args.length==0) throw new RuntimeException("mi prendi per il culo?");
		ListIterator<Integer> lit= l.listIterator();
		boolean flag = false;
		for(String arg:args) {
			flag=false;
			Integer x= Integer.parseInt(arg);
			while(lit.hasPrevious()&&!flag) {
				Integer previous= lit.previous();
				if(x>=previous) { 
					lit.next();
					lit.add(x);
					flag=true;
					while(lit.hasNext()) lit.next();
				}//if
			}//while
			if(!flag) { 
				lit.add(x);
				while(lit.hasNext()) lit.next();
			}//if
		}//for 
		System.out.println(l);
	}//main
}//LinkedListInt