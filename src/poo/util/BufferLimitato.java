package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Buffer che si comporta come un Array(capacita' limitata).
*/

public class BufferLimitato<T> extends CodaAstratta<T> {
	private T[] buffer;
	private int in,out,size; 
	/*
	 * Dimensione specificata dall'utente. Di particolare interesse e' il buffer unitario==> un solo posto
	 */
	@SuppressWarnings("unchecked")
	public BufferLimitato(int n) {
		if(n<=0) throw new IllegalArgumentException();
		buffer=(T[]) new Object[n]; // coincide con la lunghezza dell'array
		in=out=size=0;//all'inizio sono uguali in e out
	}//costruttore
	
	/*
	 * Posso risparmiarmi la size con un BIT che codifica il tipo di operazione effettuata (get() allora il buffer e' vuoto, put() il buffer e' pieno)
	 */
	
	public int size() {
		return size;
	}//size
	
	public void clear() {
		/*for(int i=out;i!=in;i=(i+1)%buffer.length) {
			buffer[i]=null;
		}//for
		in=out=size=0;*/
		for(int i=out,j=0;j<size();j++, i=(i+1)%buffer.length) {
			buffer[i]=null;
		}
		in=out=size=0;
	}//clear
	
	public boolean isEmpty() {
		return size==0;
	}//isEmpty

	public boolean isFull() {
		return size==buffer.length;
	}//isFull
	
	/*
	 * Inserimento in ordine fifo
	 */
	public void put(T x) {
		//irrecuperabile
		if(isFull()) throw new RuntimeException("Buffer full, amico mio.");
		buffer[in]=x;
		in=(in+1)%buffer.length;
		size++;//non e' un indice di posizione ==> incremento lineare
	}//put
	
	public T get() {
		if(isEmpty ()) throw new NoSuchElementException("Bicos baffer is empti"); // non si ritorna null amico mio
		T x=buffer[out];
		buffer[out]=null;
		out=(out + 1 )%buffer.length;
		size--;
		return x;
	}//get
	
	public T peek() {
		if(isEmpty()) throw new NoSuchElementException("Amico, Watson, e' vuoto!");
		return buffer[out];
	}//peek
	
	public Iterator<T> iterator(){
		return new BufferIterator();
	}//iterator
	
	/*
	 * Le interfacce sono meravigliose dal punto di vista della software ingegnering
	 */
	
	private class BufferIterator implements Iterator<T>{
		//il cursore puntera' ad un el gia' consumato
		private boolean rimovibile=false;
		private int cursor=-1;
		
		public boolean hasNext() {
			if(cursor==-1)return size>0;
			return (cursor+1)%buffer.length!=in;
		}//hasNext
	
		public T next() {
			if(!hasNext()) throw new NoSuchElementException("NONI!");
			if(cursor==-1)cursor=out;//non e' 0 
			else cursor=(cursor+1)%buffer.length;
			rimovibile=true;
			return buffer[cursor];
		}//next
		
		public void remove() {
			if(!rimovibile) throw new IllegalStateException();

			rimovibile=false;
			int j=(cursor+1)%buffer.length;// prendiamo il prossimo
			while(j!=in) {
				buffer[(j-1+buffer.length)%buffer.length]=buffer[j];                      
				j=(j+1)%buffer.length;
			}
			size--;
			in=(in-1+buffer.length)%buffer.length;
			buffer[in]=null;//non posso essere certo che sia vuoto 
			cursor=(cursor-1+buffer.length)%buffer.length;
		}//remove
	}//BufferIterator
}//BoundedBuffer
