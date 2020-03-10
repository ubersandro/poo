package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CodaConcatenata<T> extends CodaAstratta<T>{
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}//Nodo<E>
	private Nodo<T> first=null,last=null;
	private int size=0;
	public int size() {return size;}
	public void clear() { first=last=null;} //chain
	public boolean isEmpty() { return first==null;}
	public boolean isFull() {
		return false; // no concetto di full
	}


	public T get() {
		if(first==null) throw new NoSuchElementException();
		T x =first.info;
		first=first.next;
		if(first==null) last=null; //se no last puntera' ancora all'elemento restituito(in questo caso l'unico elemento)
		size--;
		return x;
	}//get
	
	@Override
	/*
	 * inserimento in coda==> facile se tengo la fine da parte
	 */
	public void put(T x) {
		Nodo<T> n=new Nodo<>();
		n.info=x;
		n.next=null;//diventa l'ultimo della lista

		if(first==null) first=n;//l'ultimo e' anche il primo
		else last.next=n;
		last=n;//funziona sempre

		size++;
	}//put
	
	@Override
	public T peek() {
		if(first==null) throw new NoSuchElementException();
		return first.info;
	}//peek
	
	@Override
	public Iterator<T> iterator(){
		return new CodaConcatenataIterator();
	}//iterator
	
	private class CodaConcatenataIterator implements Iterator<T>{
		private Nodo<T> pre=null, cor=null;

		@Override
		public boolean hasNext() {
			if(cor==null) return first!=null;
			return cor.next!=null;
		}//hasNext

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			if(cor==null)cor=first;
			else {
				pre=cor;
				cor=cor.next;
			}
			return cor.info;
		}
		public void remove() {
			if(cor==pre) throw new IllegalStateException();

			if(cor==first) {
				first=first.next;
				if(first==null)last=null;
			}//if(cor==first)
			else if(cor==last) {
				pre.next=null; last=pre;
			}//if(cor==last)
			else pre.next=cor.next;
			cor=pre;

			size--;
		}//remove
	}//CodaConcIterator
	
	
	public static void main(String... Pasquale) {

		Scanner sc= new Scanner(System.in);
		Coda<String> fila= new CodaConcatenata<>();
		String COM= "([Aa]\\s+\\w+|[Pp]|[Qq])";
		char c; 
		cicloEXT: for(;;) {
			System.out.println("> ");
			String command=sc.nextLine();
			if(!command.matches(COM)) {
				System.out.println("Comando sconosciuto");
				continue;
			}
			else {
				c= Character.toUpperCase(command.charAt(0));
			}
			switch(c) {
				case 'A': 
					int i=command.lastIndexOf(' ');
					String s=command.substring(i+1);
					fila.put(s);
					System.out.println(s +" entra.");
					break;
				case 'P':
					String s1= fila.get();
					System.out.println(s1 +" va via.");
					System.out.println("Residuo coda="+ fila);
					break;
				case 'Q':
					System.out.println("Si chiude!!");
					System.out.println("Chiudiamo coda con "+fila);
					break cicloEXT;
			}//switch
		}
		sc.close();
	}

}//CodaConcatenata<T>
