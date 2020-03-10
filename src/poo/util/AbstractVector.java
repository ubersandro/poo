package poo.util;

import java.util.Iterator;

public abstract class AbstractVector<T> implements Vector<T>{
	 
	/* adesso lo scrivo 
	 * con l'iteratore
	 */
	/*
	public String toString(){
		StringBuilder sb= new StringBuilder(200);
		sb.append('[');
		for(int i=0;i<size();i++) {
			sb.append(get(i));
			if(i!=size()-1) sb.append(" ,");
		}//for
		sb.append(']');
		return sb.toString();
	}//toString
	
	*/
	@Override 
	public String toString(){//faccio uso dell'iteratore perchè così so sempre se c'è un prossimo==> uso l'iterator
		StringBuilder sb = new StringBuilder(300);
		sb.append('[');
		for(T e: this) {
			sb.append(e);
			sb.append(", ");
		}//for each
		if(sb.length()>1) sb.setLength(sb.length()-2); //tolgo virgola e spazio
		//anche se c'è un unico elemento length() > 1
		sb.append(']');
		return sb.toString();
	}//toString 
	

	@Override
	@SuppressWarnings("unchecked") //elimina sti warnings porco di dio
	public boolean equals(Object o) {
		if(!(o instanceof Vector)) //confronto grezzo 
			return false;
		if(o==this) return true; //sono lo stesso oggetto
		Vector<T> v=(Vector<T>) o; //dammi il tempo amico mio
		if(size()!=v.size()) return false;
		Iterator<T> it1= iterator(), it2=v.iterator();
		while(it1.hasNext()) {
			T e1=it1.next(), e2=it2.next();
			if(!e1.equals(e2)) return false;
		}//while
		return true; // porcodio ho fatto tutti i confronti porcodioooooo
	}//equals 
	
	/*
	 * ogni volta che chiamo hashcode() potrebbe avere valore diverso perchè il contenuto del mio vettore cambia
	 * un vector si evolve dinamicamente quindi non sarà mai uguale 
	 * equals e hashcode 
	 */
	public int hashCode() { // due oggetti possono essere diversi e avere lo stesso ascicodd ricchiunazzu
		int h=0;
		final int MUL = 43;
		/*
		for(int i=0; i<size();i++) h*=MUL +get(i).hashCode() ;
		*/
		for(T e:this) {
			h=h*MUL+e.hashCode();		}
		return h;
	}//hashCode
	/*
	public int compareTo(AbstractVector<T> v) { ha senso?
		return 0; //TODO
	}
	*/
	
}//AbstractVector
