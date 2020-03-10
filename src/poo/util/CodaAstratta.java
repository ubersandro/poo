package poo.util;

import java.util.Iterator;

public abstract class CodaAstratta<T> implements Coda<T> {
	public String toString() {
		StringBuilder sb= new StringBuilder(300);
		sb.append('[');
		/*
		for(;;) {//questa implementazione e' stupida 
			if(this.peek()==null) break;
			sb.append(get());
		}//for
		*/
		Iterator<T> it= iterator();
		while(it.hasNext()) sb.append(it.next());
		sb.append(']');
		return sb.toString();
	}//toString 
	
	
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object s) {
		if(this==s) return true;
		if(!(s instanceof Coda )) return false;
		Coda<T> c= (Coda<T>) s; //verificare parametrizzazione warning
		if(c.size()!=this.size()) return false;
		Iterator<T> it= iterator();
		Iterator<T> it2= c.iterator();
		while(it.hasNext())
			if(!(it.next().equals(it2.next()))) return false;
		return true;
	}//equals
	
	public int hashCode() {
		int h=0;
		int MUL=23;
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			h=h*MUL + it.next().hashCode();
		}//while
		return h;
	}
}
