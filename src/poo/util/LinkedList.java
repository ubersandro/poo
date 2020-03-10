package poo.util;



import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//manca contains ==> minimalita'
public class LinkedList<T> extends AbstractList<T> {
	//l'enum va dichiarata qui, perche' nonostante serva nella inner la devo dichiarare qui ==> DE SUPERCLASS GUINS 
	private enum Move{UNKNOWN, FORWARD,BACKWARD}
	//classe doppia=> doppia lista
	private static class Nodo<E>{
		E info;
		Nodo<E> prior;
		Nodo<E> next;
	}//Nodo
	private Nodo<T> first, last=null;// di default a null
	private int size; 
	// no costruttori perche' non ho vincoli sulle dimensioni ==> default

	public int size() {return size;}
	public void clear() {
		first=last=null;
		size=0;
	}//clear
	
	public void addFirst(T x) {
		Nodo<T> n= new Nodo<T>();
		n.info=x;
		n.next=first; 
		n.prior=null;
		if(first!=null) first.prior=n;//non subito perche' potrebbe non esserci
		first=n;
		if(last==null)last=n;
		size++;
	}//e' una lista, possiamo creare duplicati ==> si aggiunge sempre alla lista(false ha senso solo nei Set)
	
	//se i metodi sono ben progettati tutte le azioni vengono fatte bene
	public void addLast(T x) {
		Nodo<T> n= new Nodo<>();
		n.info=x;
		n.next=null;
		n.prior=last; // qualunque sia il valore di prior
		if(last!=null)last.next=n;
		last=n;
		if(first==null) first=n;
		size++;//mettilo 
	}//addLast
	
	public T getFirst() {
		if(first==null) throw new NoSuchElementException();
		return first.info;
	}//getFirst
	
	public T getLast() {
		if(last==null) throw new NoSuchElementException();
		return last.info;
	}//getLast
	
	public T removeFirst() {
		if(first==null) throw new NoSuchElementException();
		T e=first.info;
		first=first.next;
		if(first!=null)first.prior=null;// e' necessario? 
		else last=null; //il garbage collector non la toccherebbe se non mettessi last a null
		size--;
		return e;
	}//removeFirst
	
	public T removeLast() {
		if(last==null) throw new NoSuchElementException();
		T e = last.info;
		last=last.prior;
		if(last!=null) last.next=null;
		else first=null;
		size--;
		return e;
	}//removeLast
	
	public boolean isEmpty() {
		return first==null;
	}//isEmpty
	
	public boolean isFull() {
		return false;
	}//isFull
	
	public Iterator<T> iterator(){
		return new ListIteratorImpl();
	}//iterator
	
	public ListIterator<T> listIterator(){
		return new ListIteratorImpl();
	}
	public ListIterator<T> listIterator(int pos){
		return new ListIteratorImpl(pos);
	}
	
	/*
	 *Prima visione semantica dell'iteratore (in mezzo) ==> quando fai next attraversi un elemento che ti viene restituito  
	 */
	private class ListIteratorImpl implements ListIterator<T>{
		private Nodo<T> previous,next;
		private Move lastMove=Move.UNKNOWN; // quale cazzo e'stato il tuo ultimo movimento?
		
		public ListIteratorImpl() {
			previous=null; next=first;
		}//ListIteratorImpl
		
		public ListIteratorImpl(int pos ) {
			if(pos<0||pos>size) throw new IllegalArgumentException();
			if(pos==size||pos==0&&isEmpty()) {//per chiarezza scriviamo pos=size / non c'e' bisogno della seconda clause
				previous=last; next=null;
			}//if
			else { previous= null; next= first;
				for(int i=0; i<pos;i++) {
					previous=next; next=next.next;
				}//for
			}//else
		}//costruttore pos
		
		public boolean hasNext() {
			return next!=null;
		}//hasNext
		//nel movimento forward previous punta al corrente. Nel previousmvt next punta al precedente  
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			lastMove=Move.FORWARD;
			previous=next;
			next=next.next;
			return previous.info;
		}//next
		
		public boolean hasPrevious() {
			return previous!=null;
		}//hasPrevious
		
		public T previous() {
			if(!hasPrevious()) throw new NoSuchElementException();
			lastMove=Move.BACKWARD;
			next=previous;
			previous=previous.prior;
			return next.info;
		}//previous
		
		public void remove() {
			if(lastMove==Move.UNKNOWN) throw new IllegalStateException("Come ti permetti?");
			Nodo<T> r=null;
			if(lastMove==Move.FORWARD)
				r=previous;
			else r=next; //ho preso la mira 
			if(r==first) {
				first=first.next;
				if(first!=null) first.prior=null;
				else last=null; //togliendo la testa la lista e' vuota GUAI A NOI
			}//if
			else if(r==last) {
				last=last.prior;
				last.next=null; //se ci fosse un solo elemento r sarebbe uguale a first
			}//else r==last
			else {//remove intermedia 
				r.prior.next=r.next;
				r.next.prior=r.prior;
			}//removeintermedia
			//tolto l'elemento aggiustiamo l'iteratore
			if(lastMove==Move.FORWARD) previous=r.prior;//next non lo devo cambiare 
			else next=r.next;
			size--;
			lastMove=Move.UNKNOWN;
		}//remove
		/*
		 * Ti e' gia' stato dato l'elemento corrente (next o previous)
		 */
		@SuppressWarnings("unused")
		public void set(T e) {
			if(lastMove==Move.UNKNOWN) throw new IllegalStateException("Tu questo non lo potevi proprio fare.");
			if(lastMove==Move.FORWARD) {
				previous.info=e;
			}//ifFORWARD
			else {
				next.info=e;
			}//else;
		}//set
		/*
		 * Giusto prima del cursore
		 */
		public void add(T e) {
			//il nodo da aggiungere va messo tra previous e next
			//NB dopo la add previous () restituisce l'elemento appena aggiunto
			Nodo<T>n= new Nodo<>();
			n.info=e;
			//la add si puo' sempre fare ==> non interrogo l'eccezione 
			n.next=next;
			n.prior=previous;

			if(next!=null) next.prior=n;
			if(previous!=null) previous.next=n;

			//adesso viene il bello
			previous=n;
			if(n.next==first)first=n;
			if(n.prior==last)last=n;
			size++;
			lastMove=Move.UNKNOWN;//non si puo' fare remove dopo la add, ma puoi fare altre add 
		}//add
		
		public int nextIndex() {
			throw new UnsupportedOperationException();//usando la linkedlist non ne faro' uso mai
		};
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
	}//JesusIterator

	public void sort(Comparator<T> c){
		if(size==0||size==1)return ;
		boolean scambi=true;
		T us=getLast();
		T limite=null;
		while(scambi){
			ListIterator<T> lit=listIterator();
			T pre=lit.next(), cor=null;
			scambi=false;
			while(lit.hasNext()){
				cor=lit.next();// T extends Comparable<? super T>
				if(cor==limite)break;
				if(c.compare(pre,cor)>0){
					//scambio con i nodi
					lit.set(pre);
					lit.previous(); lit.previous();
					lit.set(cor); lit.next();
					pre=lit.next(); us=cor;
				}//if
				else pre=cor;
			}//hasNext
			limite=us;
		}//whileScambi
	}//bubbleSortOptimized

	@SuppressWarnings("unchecked")
	public void sort(){
		if(size==0||size==1)return;
		T us=getLast();
		T limite=null;
		boolean scambi=true;
		while(scambi){
			ListIterator<T> l= listIterator();
			T pre=l.next(), cor=null;
			scambi=false;
			while(l.hasNext()){
				cor=l.next();
				if(cor==limite)break;
				if(((Comparable<? super T>)pre).compareTo(cor)>0){
					scambi=true;
					l.set(pre);
					l.previous(); l.previous();
					l.set(cor); l.next();
					pre=l.next(); us=pre;
				}
				else pre=cor;
			}//while interno
			limite=us;
		}//while
	}//sort

	@SuppressWarnings("unchecked")
	public void sortInv(){
		if(size==0||size==1)return;
		//T lim= getFirst(), us=null;
		boolean scambi= true;
		while(scambi){
			ListIterator<T> l= listIterator(size);
			scambi=false;
			T pre=l.previous(), cor=null;
			while(l.hasPrevious()){
				cor=l.previous();
				//if(cor==lim) break;
				if(((Comparable<? super T>)pre).compareTo(cor)<0){
					l.set(pre);
					l.next();l.next();
					l.set(cor);
					l.previous();
					pre=l.previous();
					scambi=true;
					//us=pre;
				}//if
				else pre=cor;
			}
			//lim=us;
		}
	}//sortInv

	public static void main(String[] args) {
		LinkedList<Integer> l= new LinkedList<>();
		System.out.println(l.getClass().getName());
		
		l.addLast(3); l.addFirst(6); l.addFirst(89); l.addLast(-11); l.addLast(-1332);
		System.out.println(l);
		l.sortInv();
		System.out.println(l);

		
	}//main 
}//LinkedList
