package poo.util;
/*
 * Niente ordine==> quelli di Java se ne sbattono 
 * Mantenere la semantica delle operazioni. Es. lit.add() ==> aggiungo l'el che mi deve essere restituito con previous();
 *	tutti i metodi si posso implementare con il listIterator
 */


import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;//importa solo questo perche' le altre cose le dobbiamo fare noi

public interface List<T> extends Iterable<T>{
	default int size(){
		int c=0;
		for(Iterator<T> it = iterator();it.hasNext();it.next() )c++;
		return c;
	}
	default void clear(){
		/*for(T x:this){
			if(((Comparable<? super T>) x).compareTo(x)==0) return;*/
		}


	void addFirst(T x);
	void addLast(T x);
	T getFirst();//non rimuove
	T getLast();
	T removeFirst();
	T removeLast();
	boolean isEmpty();
	boolean isFull();
	//Nel metodo static non posso sfruttare la genericita' dell'interfaccia quindi lo dichiaro generico
	//Gli oggetti devono ammettere il confronto (a cura dell'utente)
	//modifica in modo che si ottimizzi l'algoritmo
	@SuppressWarnings("unchecked")
	static <T> void sort(List<T> l){
		T us=l.getLast();
		boolean scambi=true;
		while(scambi) {
			ListIterator<T> lit=l.listIterator();
			scambi=false;
			T pre= lit.next();
			T cor=null;
			T limite=us;
			while(lit.hasNext()) {
				cor=lit.next();
				if(cor==limite) break;
				if(((Comparable<? super T>) pre).compareTo(cor)>0) {
					lit.set(pre);
					lit.previous(); lit.previous();
					lit.set(cor);
					//elemento scambiato per ultimo
					lit.next();
					pre=lit.next();
					scambi=true;
					us=pre;
				}
				else pre=cor;
			}
		}//while scambi
	}//sort 
	
	//Comparator
	static <T> void sort(List<T> l, Comparator<T> c){
		if(l.isEmpty()||l.size()==1)return ;
		boolean scambi=true;
		while(scambi) {
			ListIterator<T> lit=l.listIterator();
			T pre=lit.next(), cor=null;
			scambi=false;
			while(lit.hasNext()) {
				cor=lit.next();
				if(c.compare(cor,pre)<0) {
					lit.set(pre);
					lit.previous();
					lit.previous();
					lit.set(cor);
					lit.next(); pre=lit.next();
					scambi=true;
					
				}
				else {pre=cor;}
			}
		}
	}//sort Comparator

	static void sortWC(List<?> l){

	}
	ListIterator<T> listIterator();
	ListIterator<T> listIterator(int pos); //size, 0 , in mezzo 
	//non ha senso fare remove e add perche' ho il listIterator()
	public static void main(String[] args) {
		poo.util.LinkedList<Integer> l= new LinkedList<>();
		/*l.addFirst(2);
		l.addFirst(3);
		l.addLast(-1);
		l.sort((i,j)->{
			if(i>j)return 21;
			if(i==j)return 0;
			return -21;
		});
*/
		l.addFirst(1);
		l.addFirst(5);
		l.addFirst(10);
		System.out.println(l);
		List.sort(l);
		System.out.println(l);
	}
}//List
