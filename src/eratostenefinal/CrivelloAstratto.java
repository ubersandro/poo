package eratostenefinal;

import java.util.Iterator;

public abstract class CrivelloAstratto implements Crivello {
	
	public String toString() {
		StringBuilder sb = new StringBuilder(500);
		sb.append('[');
		Iterator<Integer> it=iterator();
		while(it.hasNext()) { //� legittimo chiamare una sola volta it.next()
			sb.append(it.next());
			sb.append(", ");
		}//while
		sb.delete(sb.length()-2, sb.length());
		sb.append(']');
		return sb.toString();
	}//toString
	
	public int hashCode() {
		Iterator<Integer> it= iterator();
		final int M=23;
		int h=0;
		while(it.hasNext()) {
			h=h*M+it.next().hashCode(); // anche next e basta 
		}//while
		return h;
	}//hashCode

	public boolean equals(Object x) {
		if(x==this)return false;
		if(!(x instanceof CrivelloAstratto)) return false;
		CrivelloAstratto c= (CrivelloAstratto) x;
		if(c.size()!=this.size()) return false;
		Iterator<Integer> it1= iterator();
		Iterator<Integer> it2= c.iterator();
		while(it1.hasNext())
			if(it1.next()!=it2.next())return false;
		return true;
	}//equals
}//CrivelloAstratto
