package poo.util;
import java.util.Iterator;
/*
 * Non pre-implementiamo nulla ==> ci sporchiamo le mani amico mio
 *
 */
 @SuppressWarnings("unused")
public interface CollezioneOrdinata<T extends Comparable<? super T>> extends Iterable<T>{
	/*
 	default int size(){
		int s=0;
		Iterator<T> it= iterator();
		while(it.hasNext()){
			it.next(); s++;
		}
		return s;
	}//size

	default boolean contains(T e){
		Iterator<T>it= iterator();
		while(it.hasNext()){
			if(it.next().equals(e))return true;

		}NON FURBA
		return false;
	}//contains
	*/
	default int size(){
		int x=0;
		for(T e:this)x++;
		return x;
	}//size
	default boolean contains(T x){
		for(T e:this){
			if(e.equals(x))return true;
			if(e.compareTo(x)>0) return false;
		}
		return false;
	}//contains
	default boolean isEmpty(){
		return !iterator().hasNext(); //size()==0
	}
	default boolean isFull(){return false; }
	//per quale motivo non posso restituire un riferimento all'elemento? controlla
	T get(T e); //non contiene tutte le informazioni quello che gli passo ==> es Nominativo costruito solo con nome e cognome

	default void clear(){
		Iterator<T> it= iterator();
		while(it.hasNext()){
			it.next(); it.remove();
		}
	}//clear

	void add(T e);

	default void remove(T e){
		Iterator<T> it= iterator();
		while(it.hasNext()){
			if(it.next().equals(e)){
				it.remove();
				break;
			}
		}//while
	}//remove
	
}//CollezioneOrdinata
