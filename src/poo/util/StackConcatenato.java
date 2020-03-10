package poo.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException; // e' la stessa dell'iteratroie
import java.util.Queue;

//@SuppressWarnings("unused")
public class StackConcatenato<T> extends StackAstratto<T>{
	
	private static class Nodo<E>{
		Nodo<E> next;
		E info;
	}//NODO
	
	private Nodo<T> testa=null;
	private int size=0;
	public int size() {	return size; }//size
	public void clear() { size=0; testa=null;}
	/*
	 * Essendo uno stack una lista non ho problemi di ripetizione degli elementi. Cardinalita' idealmente infinita. 
	 */
	public void push(T x) {
		Nodo<T> n= new Nodo<>(); // questa new alloca l'oggetto nel Heap ==> tutta la ram e' disponibile e quindi push non puo' fallire.
		n.info=x;
		n.next=testa;
		testa=n;
		size++;//sempre
	}//push
	
	public T pop() {
		if(testa==null) throw new NoSuchElementException();
		T x=testa.info;//sicuramente c'e'
		testa=testa.next;//non e' un errore non mettere a null il next dell'elemento appena bypassato==> non ha importanza che lui punti a qualcun altro
		size--;
		return x; // non restituisce il nodo ma l'elemento 
	}//pop == pull
	
	public T top() {
		if(testa==null) throw new NoSuchElementException();
		return testa.info;
	}//top == peek(come in ObjectFile)
	
	/*
	 * con il metodo is empty puoi evitare le eccezioni
	 */
	
	public boolean isEmpty() { return size==0;} //anche testa==null;
	public boolean isFull() { return false; }
	
	/*
	 * Dalla cima al bottom amico mio perche' li ottieni in quell'ordine gli elementi 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(200);
		sb.append('[');
		Nodo<T> cor= testa;
		while(cor!=null) {
			sb.append(cor.info);
			cor=cor.next;
			if(cor!=null)sb.append(", ");
		}
		sb.append(']');
		return sb.toString();
	}//toString

	public Iterator<T> iterator(){
		return new StackIterator();
	}
	/*
	 * Posso usare T dato che la classe non e' statica
	 */
	private class StackIterator implements Iterator<T>{
		Nodo<T> pre=null,cor=null;
		public boolean hasNext() {
			if(cor==null)return testa!=null; // cor punta a un nodo gia' consumato
			return cor.next!=null;
		}//hasNext()
		/*
		 * restituisce T non un nodo amico mio. Chi e' questo nodo? Ma stiamo scherzando?
		 */
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			if(cor==null) cor=testa;
			else {
				pre=cor;
				cor=cor.next;
			}
			return cor.info;
		}//next 
		
		public void remove() {
			if(cor==pre) throw new IllegalStateException(); //da notare che sia pre che cor sono inizializzati a null (uguali)
			//togliamo cor
			if(cor==testa) testa=testa.next; //cor sta puntando ancora al primo nodo, ma poi non esistera' piu'
			else { pre.next=cor.next;}
			cor=pre; // SEMANTICA se cor non e' null deve puntare a un nodo gia restituito
		}//remove
		

	}//StackIterator

	public static void main(String[] args) {
		Queue<Integer> q= new LinkedList<>();
		q.add(3);
		System.out.println(q);
		q.add(8);
		q.remove();
		System.out.println(q);
	}
	
}//StackConcatenato<T>
