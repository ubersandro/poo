package poo.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * La classe inner non punta alla outer ==> risprarmio in termine di complessita' spaziale.
 * Su tantissimi puntatori risparmio un casino di spazio.
 */
//@SuppressWarnings("unused")

public class ListaConcatenataOrdinata<T extends Comparable<? super T>> implements CollezioneOrdinata<T>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6784419760360975668L;

	/*
	 * Primo esempio di definizione ricorsiva di un'entita'. Campo next di tipo Nodo<E> nella classe Nodo.
	 * Il costruttore di default lo fornisce Java. Libero il costruttore non lo mette per strategia didattica.
	 * La lista e' fatta di nodi, non di elementi(contenuti nei nodi). 
	 * Con E designamo una genericita' non collegata alla classe ListaConcatenata<T>.
	 * Nodo<T> e' una classe interna, all'esterno non la conoscono ==> la collezione e' generica in T.
	 * L'iteratore restituisce il contenuto del nodo preso in esame, non il Nodo<T>. Fai attenzione, DIOCANE.
	 */
	private static class Nodo<E> implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 5823363198056284816L;
		E info;
		Nodo<E> next;
	}//Nodo
	private Nodo<T> testa;//Di default messo a null (ricorda amico mio);
	private int size=0;

	public int size() {return size;}
	public boolean isEmpty() {return testa == null;} //anche size==0
	public boolean isFull() {return false;} // su una ListaConcatenata l'unico limite è la memoria disponibile
	
	//abbi cura di mettere size a 0 per coerenza,amico mio
	public void clear() { testa=null; size=0; }// garbage collector acts like "CCA E' TUTTU U MEU"
	
	/*
	 * La lista e' ordinata ma no ricerca binaria. Potrei fermarmi appena trovo un elemento piu' grande senza aver
	 * trovato il mio.
	 
	public boolean contains(T x) {
		Nodo<T> cor=testa; //aliasing tra testa e cor ==> primo nodo della lista
		while(cor!=null) {//deve essere diverso da null ==> se la lista e' vuota non ci entro proprio
			if(cor.info.equals(x)) return true;  //cor e' un riferimento
			if(cor.info.compareTo(x)>0) return false; //giustificato dal fatto che e' ordinata
			cor=cor.next; //ATTENZIONE zuccherino ==> questa e' l'essenza della lista
		}//while	
		return false;
	}//contains
	*/

	public boolean contains(T x) {
		Nodo<T> cor=testa; //aliasing tra testa e cor ==> primo nodo della lista
		while(cor!=null&&cor.info.compareTo(x)<0) { //piu' professionale 
			cor=cor.next; //ATTENZIONE zuccherino ==> questa e' l'essenza della lista
		}//while	
		if(cor!=null&&cor.info.equals(x)) return true; // solo scritta cosi' perche' altrimenti ==> NullPointerException
		//solo qui posso fare return true perche' ogni altra condizione dara' false
		return false;
	}//contains
	
	public T get(T e) {
		Nodo<T> cor= testa;
		while(cor!=null&&cor.info.compareTo(e)<0) cor=cor.next;

		if(cor!=null&&cor.info.equals(e)) return cor.info; //return dell'oggetto con tutte le informazioni
		return null; //quando non lo trovo 
	}//get
	
	public String toString() {
		StringBuilder sb= new StringBuilder(100);//"perche' 100? WAINOTT?"
		sb.append("[");
		Nodo<T> cor=testa;
		while(cor!=null) {
			sb.append(cor.info);
			cor=cor.next; 
			if(cor!=null) sb.append(", ");//quando e' a null sono alla fine 
		}//while
		sb.append(']');
		return sb.toString();
	}//toString 
	
	/*
	 * Uso Collezione (tipo statico) perche' mi interessano i due contenuti. User0' i metodi dell'interfaccia e non della
	 * classe ListaC
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if(!(o instanceof CollezioneOrdinata)) return false;
		if(o==this) return true;
		CollezioneOrdinata<T> co=(CollezioneOrdinata<T>) o;
		Iterator<T> it1= this.iterator(); //iterator di questa lista(this)
		Iterator<T> it2= co.iterator();
		if(size!=co.size()) return false; // su co non posso usare il campo size perche' e' di tipo CollezioneOrdinata
		while(it1.hasNext()) {
			if(!(it1.next().equals(it2.next()))) return false;
		}
		return true;
	}//equals

	public int hashCode() {
		int h=0;
		final int M= 83;
		Nodo<T> cor=testa;
		while(cor!=null) {
			h=h*M + cor.info.hashCode();
			cor=cor.next;
		}//while
		return h;
	}//hashCode
	
	/*
	 * 3 CASI
	 
	
	public void add(T x) {
		Nodo<T> pre=null, cor=testa;
		while(cor!=null&&cor.info.compareTo(x)<0) {
			pre=cor;
			cor=cor.next;
		}//while
		Nodo<T> nuovo=new Nodo<>();
		nuovo.info=x;
		if(cor==testa) { //puo' anche succedere che sia vuota
			nuovo.next=testa; //primo caso
			testa=nuovo;//inserimento in TESTA
		}//if
		else { //inserimento in pos intermendia o alla fine 
			nuovo.next=cor; //sono sicuro che il nuovo elemento e' minore di cor
			pre.next=nuovo;
		}//else
		size++;
	}//add
	*/
	
	public void add(T x) {
		Nodo<T> nuovo= new Nodo<>();
		nuovo.info=x;// va fatto sempre
		if(testa==null||testa.info.compareTo(x)>=0) {
			//inserimento in testa
			nuovo.next=testa;//supponiamo che testa==null. E' giusto perche' cosi' punto all'ultimo.
			testa=nuovo;
		}//if
		else {//inserimento in posizione intermedia o alla fine 
			Nodo<T> pre=testa, cor=testa.next;//sono certo che prima del primo non lo inserisco==> evito una GIRATA in piu'
			while(cor!=null&&cor.info.compareTo(x)<0) {
				pre=cor; pre=cor.next;
			}//while
			//inserisci nuovo tra pre e cor
			pre.next=nuovo;
			nuovo.next=cor; // se cor fosse null va benissimo lo stesso DIOCANE
		}//else
		size++; //questo va fatto sempre ATTENZIONE
	}//add
	

	/*
	 * Non si sollevano eccezioni se l'elemento non c'e' PORCO DI DIO, attento amico mio.
	 * Me ne vado in modo silenzioso. 
	 */
	
	public void remove(T x) {
		Nodo<T> pre=null, cor=testa;
		while(cor!=null&&cor.info.compareTo(x)<0) {
			pre=cor; cor=cor.next; 
		}//while
		//se a questo punto l'elemento non c'e' cor vale null (anche perche' la lista e' vuota)
		if(cor!=null&&cor.info.equals(x)) { // se no i NullPointerException POOvono
			if(cor==testa) testa=testa.next;//e' la testa
			else  pre.next=cor.next; //bypass ==> non e' la testa (Francesca)
			size--;
		}//if
	}//remove
	
	public Iterator<T> iterator(){
		return new LCIterator();
	}//iterator
	
	/*
	 *  il tipo generico T è lo STESSO della outer class
	 *  Se pre==cor allora niente remove
	 *  Se e' diverso da null punta ad un nodo il cui contenuto e' gia' stato restituito 
	 */
	private class LCIterator implements Iterator<T>{
		Nodo<T> pre=null, cor=null;

		public boolean hasNext() {
			if(cor==null) return testa!= null;//non punta ad un nodo gia' restituito ==> vediamo se esiste almeno un nodo
			return cor.next!=null;//ci sara' un prossimo elemento solo se il nodo il cui contenuto e' gia' stato restituito (puntato da cor) ha next!= null
		}//hasNext
		
		public T next() {
			if(!hasNext()) throw new NoSuchElementException("Amico mio, non ti scordar di me");
			if(cor==null)cor=testa;//ora posso andare sul primo elemento
			else {
				pre=cor;
				cor=cor.next;
			}//else ho gia' cominciato ad iterare
			return cor.info;
		}//next
		
		public void remove() {
			if(cor==pre) throw new IllegalStateException("La forbice non si e' apertaaaaa");
			//sono diversi==> devo rimuovere il nodo cor
			//porto cor indietro, cosi' non posso fare un'altra remove
			if(cor==testa) testa=cor.next; // la classe inner non statica vede tutto cio' che c'e' nella outer
			else pre.next=cor.next; // rimozione fatta amico mio
			size--;//RICORDA che c'e' un elemento in meno
			cor=pre;//cor stava puntando ad un nodo rimosso ==> non puo' rimanere com'era. Lo devo spostare sul nodo gia' consumato
			// non posso perdere la semantica dell'iteratore	
		}//remove
	
	}//LCIterator


	
}//ListaConcatenata
