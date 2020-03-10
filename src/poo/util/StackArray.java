package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class StackArray<T> implements Stack<T>{
	private T[] pila;
	private int size;
	public StackArray(int n) {
		if(n<=0) throw new IllegalArgumentException();
		pila=(T[]) new Object[n];
		size=0;
	}//costruttore
	
	public StackArray() {this(17);}
	public int size() {return size;}
	public void clear() {
		for(int i=0;i<size;++i) pila[i]=null;
		size=0;
	}

	public boolean isFull() {
		return false;
	}
	public boolean isEmpty() {
		return size==0;
	}//isEmpty
	
	public void push(T x) {
		if(size==pila.length) pila=java.util.Arrays.copyOf(pila, size*2);
		pila[size]=x;
		size++;
	}//push

	public T pop() {
		if(size==0) throw new NoSuchElementException();
		size--;//porto size sull'ulitimo
		T x=pila[size];
		pila[size]=null;//devo garantire che sia null
		return x;
	}//pop

	public T top() {
		if(size==0) throw new NoSuchElementException();
		return pila[size-1];
	}

	public Iterator<T> iterator(){
		return new StackArrayIterator();
	}
	/*
	 * Per non affogarci non consideriamo la concurrent modification
	 */
	private class StackArrayIterator implements Iterator<T>{
		private int cor=size-1; //molte volte lo mettevamo a -1. Stavolta no perche' lo stack comincia da size-1. 
		private boolean rimovibile=false; //  
		public boolean hasNext() {
			return cor>0;
		}//hasNext
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			rimovibile=true;
			cor--;
			return pila[cor];
 		}
		public void remove() {
			if(!rimovibile) throw new IllegalStateException();
			rimovibile=false;
			for(int i=cor; i<size-1; ++i)
				pila[i]=pila[i+1]; //no rischio di override
			size--;
			// non spostiamo cor perche' punta ad un elemento gia' restituito quindi semanticamente siamo al top amico mio
			//COR NON SI TOCCA...
		}
	}//StackArrayIterator
}