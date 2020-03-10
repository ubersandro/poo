package eratostene;

import java.util.Iterator;

public abstract class CrivelloAstratto implements Crivello {
	
	public String toString() {
		StringBuilder sb = new StringBuilder(500);
		sb.append('[');
		Iterator<Integer> it=iterator();
		while(it.hasNext()) { //è legittimo chiamare una sola volta it.next()
			sb.append(it.next());
			sb.append(", ");
		}//while
		return sb.toString();
	}//toString
	
	public int hashCode() {
		int h=0;
		final int MUL=23;
		for(Integer i:this) {
			h=h*MUL + i.hashCode();
		}//for
		return h; 
	}

	public boolean equals(Object x) {
		if(x instanceof CrivelloAstratto) return false;
		Crivello c=(CrivelloAstratto) x;
		if(c.size()!=this.size()) return false;
		Iterator<Integer> itc=c.iterator();
		Iterator<Integer> it = this.iterator();
		while(it.hasNext()) {
			if(!(itc.next().equals(it.next()))) return false;
		}
		return true;
		
	}//equals
}//CrivelloAstratto
