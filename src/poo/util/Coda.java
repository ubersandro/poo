package poo.util;

import java.util.Iterator;

/*
 * completata da me 
 */

public interface Coda<T> extends Iterable<T>{
	default int size() {
		int c=0;
		for(@SuppressWarnings("unused") T t:this)c++;
		return c;
	}//size 
	
	default T get() {
		Iterator<T> it= iterator();
		if(it.hasNext()) {
			T n=it.next();
			it.remove();
			return n;
		}
		return null;
	}//get
	
	void put(T x);
	
	
	default void clear() {
		Iterator<T> it= iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}//remove
		
	default T peek() {
		Iterator<T> it=iterator();
		if(it.hasNext()) return it.next();
		return null;
	}
	
	default boolean isEmpty() {
		Iterator<T> it = iterator();
		return it.hasNext();
	}//isEmpty

	
	default boolean isFull() {
		return false;
	}


}//Coda<T>
