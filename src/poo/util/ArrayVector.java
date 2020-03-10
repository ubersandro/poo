package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**implementa Iterable per ereditarietà
 * 
 * @author alessandro
 *
 * @param <T>
 */
public class ArrayVector<T> extends AbstractVector<T>{
	private int size;
	private T[] array; //slot = posizioni
	/*
	 * Se lavoriamo sui singoli elementi (es add set) possiamo lavorare sull'array di T. Se faccio return di un array di T KALASHNIKOV
	 */
	@SuppressWarnings("unchecked") 
	public ArrayVector(int n) {
		if(n<=0) throw new IllegalArgumentException("Qua nessuno vuole giocare.");
		array=(T[])new Object[n]; size=0; // anche se java di default inizializza la size a 0
	}//costruttore con capacità
	
	public ArrayVector(){
		this(17);
	}// senza capacità	
	
	public int indexOf(T x) {
		for(int i=0; i<array.length;i++) if(array[i].equals(x)) return i;
		return -1;
		
	}//index of
	
	public boolean isEmpty() {
		if(size==0)	return false;
		return true;
	}//isEmpty
	
	public boolean contains(T x) {
		for(int i=0; i<array.length;i++) if(array[i].equals(x)) return true;
		return false;
	}
	
	
	@Override
	public int size() {
		return size;
	}//size

	@Override
	public T get(int i) {
		if(i<0||i>=size) throw new IndexOutOfBoundsException("Ma che mi passi amico mio?");
		return array[i];
	}//get

	@Override
	public T set(int i, T x) {
		if(i<0||i>=size) throw new IndexOutOfBoundsException("KALASHNIKOV");
		T y=array[i];
		array[i]=x;
		return y;	
	}//set

	
	@Deprecated 
	public void add(T o) {//appending into the last position the element
		if(size==array.length) {
			array=java.util.Arrays.copyOf(array, size*2); //se tu devi risolvere un problema e già hai qualcosa disponibile nella libreria usalo PORCODIO
			/*T[] nuovo= new T[size*2]; //estensione
			//System.arraycopy(this.array, 0, nuovo, 0, size);
			for(int i=0; i<size;i++) nuovo[i]=array[i];
			array=nuovo; */
		}
		array[size]=o;
		size++;
	}//add

	@Override
	public void add(int i, T x) {
		if(i<0||i>size) throw new IndexOutOfBoundsException(); //qui niente uguale perchè se i==size chiama add semplice  STO USANDO LA VARIABILE SIZE, NON IL METODO
		if(size==array.length) array=java.util.Arrays.copyOf(array, size*2); //SCALING
		//fai scorrimento
		for(int j=size-1;j>=i;j--) { // qui non uso l'iteratore perchè ho già il mio contenitore
			array[j+1]=array[j];
		}//for
		array[i]=x;
		size++; // non ti scoardar mai di me, amico mio
	}//AIaddIO 
	
	@Override
	public T remove(int i){
		if(i<0||i>=size) throw new IndexOutOfBoundsException();
		T x= array[i];
		for(int j=i+1; j<size; j++) array[j-1]=array[j];
		size--;
			//if(size<array.length/2) array= java.util.Arrays.copyOf(array, size/2);
		return x;
	}//remove 
	
	@Override
	public void remove(T x){
		int i=0;
		for(;i<size;i++) 
			if(array[i].equals(x)) break;
		if (i==size) return; // metodo che ritorna void ma c'è sempre la return perchè indica che bisogna terminare il metodo 
		remove(i);
		/* int i=indexOf();
		 * if(i==-1) return;
		 * remove(i);
		 */
	}//remove 
/*
	@Override
	public void clear(){
		for(int i=0;i<size;i++)
			array[i]=null; //garbage collector caccia gli elementi che c'erano prima 
		size=0;
	}//clear

	public String toString() {
		StringBuilder sb= new StringBuilder(300); //StringBuilder si autoscala
		sb.append("[");
		for(int i=0;i<size;i++) {
			sb.append(array[i]);
			if(i<size-1) sb.append(", "); //potrei anche ridurre la lunghezza di sb di 2 per rimuovere i caratteri in più
		}//for 
		sb.append("]"); 
		return sb.toString(); 
	}//toString
	*/
	@Override
	public Iterator<T> iterator() {
		return new ArrayVectorIterator(); // la classe la metto qui ==>INNER CLASS 
	}//iterator
	
	
	/**
	 * La inner class ha visibilità ridotta solo alla classe ArrayVector
	 * Non è una classe generica. Il mio cursore, se non vale -1, punta ad un elemento già
	 * restituito da next().
	 * Eccezioni che può sollevare:NoSuchElementException, IllegalStateException, ConcurrentModificationException 
	 * E' raccomandabile iterare fino alla fine perchè si potrebbe creare una situazione di inconsistenza ==> MODIFICHE CONCORRENZIALI
	 * 
	 * @author aless
	 *
	 */
	
	private class ArrayVectorIterator implements Iterator<T>{ 
		private int cur = -1; //prima del primo
		private boolean rimovibile= false; // non posso fare remove() se non faccio next()
		public boolean hasNext() { 
			if(cur==-1) return size>0; // a meno di mascheramento
			return cur<(size-1); // questa condizione ingloba anche quella sopra (credits Zio Perna) 
		}//hasNext()

		/**
		 * della classe superiore vedo pure il tipo generico
		 */
		public T next() {
			if(!hasNext()) throw new NoSuchElementException("NO U SUCH");
			cur++; // se prima era -1 ora punta al primo el ====[0]
			rimovibile=true;
			return array[cur];
		}//next
			
		public void remove() {
			if(!rimovibile)throw new IllegalStateException("Siamo illegali, siamo di Reggio Calabria");
			rimovibile=false; 
			for(int i=cur+1;i<size;i++) {
				array[i-1]=array[i];
			}
			size--;
			//adesso cur punta all'elemento successivo all'elemento rimosso
			cur--; // se rimuovo su 0 cur torna a -1
		}//remove 	
	}//ArrayVectorIterator
	
	/**
	 * @param perna
	 */
	public static void main(String ... perna) {
		String as[]= {"dio", "porco", "la", "tana","del","di"}	;
		Vector<String> vs= new ArrayVector<>();
		for(String s: as) {
			int i=0; boolean flag=false;
			while(i<vs.size()&&!flag) {
				if(vs.get(i).compareTo(s)>0) {//l'el in pos i è o uguale o più grande 
					vs.add(i,s); flag=true;
				}
				else i++;
			}//while
			if(!flag) vs.add(s);
		}//for each
		Iterator<String> it= vs.iterator();
		System.out.println(it.getClass());
		System.out.println(vs);
		vs.remove(2);
		//System.out.println(vs);
		
	}//main
	
	
	
}//ArrayVector
