package poo.util;

import java.util.Iterator;

/*
 *Coda che mi permette di rimuovere e aggiungere sia all'inizio che alla fine  
 */
@SuppressWarnings("unused")
public interface Dequeue<T> extends Iterable<T>{
	default int size() {
		int s=0;
		for(T e:this) s++;
		return s;
	}//size
	
	default void clear() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next(); it.remove();
		}
	}//clear
	
	default boolean contains(T x) {
		for(T e:this)if(e.equals(x))return true;
		return false;
	}
	
	default boolean isEmpty() {
		return size()==0;
	}
	
	default boolean isFull() {return false;}
	
	void addFirst(T x);
	void addLast(T x);
	
	default T getFirst() {
		Iterator<T>it=iterator();
		T first=null;
		if(it.hasNext()) {
			first= it.next();
			it.remove();
		}
		return first;
	}
	
	default T getLast() {
		T last=null;
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			last=it.next();
		}//while hasNext
		return last;
	}//getLast
	
	default void removeFirst() {
		Iterator<T>it= iterator();
		if(it.hasNext()) {
			it.next();
			it.remove();
		}//if it hasNext
	}//removeFirst
	
	default void removeLast() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next();
			if (!(it.hasNext())) it.remove();
		}//while
	}//removeLast

}//Dequeue
