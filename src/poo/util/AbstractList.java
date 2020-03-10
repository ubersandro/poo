package poo.util;

import java.util.Iterator;



public abstract class AbstractList<T> implements List<T> {
	@SuppressWarnings("unchecked")
	public boolean equals(Object x) {
		if(x==this)return true;
		if(!(x instanceof List))return false;
		List<T> l=(List<T>) x;
		if(this.size()!=l.size())return false;
		Iterator<T> it1=l.iterator(), it2=this.iterator();
		while(it1.hasNext())
			if(it1.next()!=it2.next()) return false;
		return true;
	}
	
	public int hashCode() {
		int h=0;
		int MUL= 89;
		for(T e:this) {
			h=h*MUL+e.hashCode();
		}
		return h;
	}
	
	public String toString() {
		StringBuilder sb= new StringBuilder(200);
		sb.append('[');
		Iterator<T> it= this.iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext()) sb.append(" ,");
		}//while
		sb.append(']');
		return sb.toString();
	}
}
