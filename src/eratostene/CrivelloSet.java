package eratostene;
import java.util.*;

public class CrivelloSet extends CrivelloAstratto {
	private final int N;
	private int modCounter;
	private Set<Integer> crivello= new HashSet<>(); // potrebbe essere una scelta veloce ma non è ordinata ==> potremmo creare un TreeSet al volo 
	//private Set<Integer> crivello= new TreeSet<>(); //O(log2n) al posto di O(i) comunque poca roba
	public CrivelloSet(final int N) { //non lo puoi modificare
		if(N<2) throw new IllegalArgumentException();
		this.N=N;
		modCounter=0;
		for(int x=2; x<=N; ++x) crivello.add(x); //rifare con iterator 
	}//Crivello
	
	/*

	//recuperiamo la situazione mantendendo l'ordine su HashSet ==> inner class iteratore 
	
	*/
	public Iterator<Integer> iterator(){
		return new SetIterator();
	}//iteratorMIO

	public void filtra() { 
		for(int x=2; x<=N/2;++x) {//inutile andare su tutti i numeri ma non dannoso perchè perdo solo un po' di tempo
			if(crivello.contains(x)) {
				int mx=x+x;
				while(mx<=N) {
					crivello.remove(mx);//remove viene da Collection ==> usiamo l'hashing, sarà veloce 
					modCounter++;
					mx+=x;  //se faccio una remove su un elemento che già non c'è nessuna eccezione viene sollevata
				}//while
			}//if
		}//for
	}//filtra

	public boolean isPrime(int x) {
		return crivello.contains(x);
	}//isPrime
	
	
	
	
	
	/*
	 * @author IO
	 */
	private class SetIterator implements Iterator<Integer>{
		private Set<Integer> s= new TreeSet<>(crivello);
		private boolean rimovibile=false;
		private int c=-1;
		private Iterator<Integer> it= s.iterator();
		private int modificheTS=modCounter;
		//perchè non ci mettiamo un costruttore?
		
		public boolean hasNext() {
			if(c<0||c>=crivello.size()-1) return false;
			return true ;
		}//hasNext

		public Integer next() {
			if(modCounter!=modificheTS) throw new ConcurrentModificationException();
			if(!hasNext()) throw new NoSuchElementException("NO U SUCH");
			rimovibile=true;
			c++;
			return it.next();
		}
		/*
		 * DIOCANE
		 */
		public void remove() {
			if(modCounter!=modificheTS) throw new ConcurrentModificationException();
			if(!rimovibile) throw new IllegalStateException();
			rimovibile=false;
			crivello.remove(c);
			c--;
			it.remove();
		}//remove
		
	}//SetIterator
	
	
	
	
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder(1000);
		int c=0;
		Iterator<Integer> s= new SetIterator();
		crivello.remove(5);
		while(s.hasNext()) {
			int n=s.next();
			sb.append(String.format("%10d", n));
			if(c%7==0)sb.append('\n');
			c++;
		}//for
		sb.append('\n'); //perchè l'ultima riga è incompleta se no  
		return sb.toString();
	}//toString
	
	public static void main(String[] args) {
		CrivelloSet c = new CrivelloSet(100);
		System.out.println(c);
		c.filtra();
		System.out.println(c);
		//Iterator<Integer> it= c.iterator();
		
	}
}//CrivelloSet

