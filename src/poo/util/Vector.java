 package poo.util;

import java.util.Iterator;

/**Dovrebbe corrispondere ad un array monodimensionale
 *
 * @author Libero
 * 
*/

//eredita il metodo iterator() dall'interfaccia
public interface Vector <T> extends Iterable<T>{  // T rappresenta un qualunque tipo che io possa esprimere in Java 
	//default se lo voglio scrivere già nell'interfaccia
	default int size() {
		int c=0;
		Iterator<T> it= iterator();
		while(it.hasNext()) { 
			c++;
			it.next();
		}//while
		return c;
	}//default method ==> se non ti sta bene cara classe te lo ridefinisci, porcodio
	//quanti elementi ci sono dentro effettivamente 
	
	/** @param i di tipo int
	 * 
	 * @author Sandro
	 * @return T
	 * @result l'elemento di indice T
	 */
	default T get(int i) {
		if(i<0||i>=size()) throw new IndexOutOfBoundsException();
		T x=null;
		int j=0;
		for(T e:this) {//uso l'iteratore
			if(j==i) { x=e; break;}
			j++; // devo incrementare la j
		}//for each
		return x;
	}//get(i)
	
	//questo non lo posso implementare 
	T set(int i, T x); //non inserisce, ma sostituisce 
	
	default void add(T o) {//come append in StringBuilder. Fa aumentare la size perchè aggiunge un elemento.
		add(size(), o);
	}//add
	
	void add(int i, T x);
	
	default void remove(T x) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T y=it.next();
			if(y.equals(x)) it.remove(); //sempre dopo la next
			return; //anche solo break;
		}//while 
	}//remove
	
	default T remove(int i) {
		if(i>=size()||i<0) throw new IndexOutOfBoundsException("Ma che mi passi amico mio?");
		int j=0;
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			T y=it.next();
			if(i==j) {
				it.remove(); return y;
			}//if
			j++; // se non è uguale
		}//while
		return null; //la scrivo ma non verrà mai eseguita
	}//remove
	
	
	
	default void clear() {
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			it.next(); it.remove();
		}//while
	}//clear
	
	default int indexOf(T x){
		int i=0;
		for(T e: this) {
			if(e.equals(x)) return i;
		i++;
		}//for
		return -1;
	}//indexOf
	
	default boolean isEmpty() {
		return size()==0;
	}//isEmpty
	
	default boolean contains(T x) {
		int i=indexOf(x);
		return i!=-1;
	}//contains 
		
}//Vector


/*non conviene implementare toString(), equals(), hashCode() 
 * perchè le classi astratte implementano l'interfaccia ma
 *  ereditano da Object! ==> è INUTILE perchè Object WINS!
 */


