package eratostenefinal;
import java.util.*;

public class CrivelloSet extends CrivelloAstratto {
	private final int N;
	private Set<Integer> crivello= new TreeSet<>(); // potrebbe essere una scelta veloce ma non ï¿½ ordinata ==> potremmo creare un TreeSet al volo 
	//private Set<Integer> crivello= new TreeSet<>(); //O(log2n) al posto di O(i) comunque poca roba
	public CrivelloSet(final int N) { //non lo puoi modificare
		if(N<2) throw new IllegalArgumentException();
		this.N=N;
		for(int x=2; x<=N; ++x) crivello.add(x);
	}//Crivello
	
	public Iterator<Integer> iterator(){
		return crivello.iterator(); //l'iteratore restituisce gli oggetti in maniera disordinata==il toString() sopra mi rappresenta gli elementi disordinatamente
	}//iterator

	//recuperiamo la situazione mantendendo l'ordine su HashSet ==> inner class iteratore 
	
	

	@Override
	public void filtra() {
		for(int x=2; x<=Math.round(Math.sqrt(N));x=(x==2)?x+1:x+2) {//inutile andare su tutti i numeri ma non dannoso perchï¿½ perdo solo un po' di tempo
			if(crivello.contains(x)) {
				int mx=x+x;
				while(mx<=N) {
					crivello.remove(mx); //remove viene da Collection ==> usiamo l'hashing, sarï¿½ veloce 
					mx+=x;  //se faccio una remove su un elemento che giï¿½ non c'ï¿½ nessuna eccezione viene sollevata
				}//while
			}//if
		}//for
	}//filtra
	
	@Override
	public boolean isPrime(int x) {
		return crivello.contains(x);
	}//isPrime
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder(1000);
		int c=0;
		for(int p: this) {
			sb.append(String.format("%10d", p));
			if(c%7==0)sb.append('\n');
			c++;
		}//for
		sb.append('\n'); //perchè l'ultima riga è incompleta se no  
		return sb.toString();
	}//toString
	
	public static void main(String...porcodio) {
		Crivello c= new CrivelloSet(103);
		System.out.println(c+"-----------------------------------------------------------------------");
		c.filtra();
		System.out.println(c);
	}//main
}//CrivelloSet

