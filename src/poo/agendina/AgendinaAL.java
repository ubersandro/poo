package poo.agendina;
import java.util.*;

public class AgendinaAL extends AgendinaAstratta{
	private List<Nominativo> tabella; //tipo statico List
	public AgendinaAL(){
		this(100);
	}
	public AgendinaAL( int n ){
		if( n<=0 ) throw new IllegalArgumentException();
		tabella=new ArrayList<Nominativo>(n);
	}
	
	@Override
	public int size(){ return tabella.size(); }
	
	@Override
	public void svuota(){ tabella.clear(); }
	
	@Override
    public void aggiungi( Nominativo n ){
		int i=Collections.binarySearch( tabella, n );
		if( i>=0 ){ tabella.set(i,n); return; } //update del contatto
		i=0;
		while( i<tabella.size() ){
			Nominativo x=tabella.get(i);
			if( x.compareTo(n)>0 ) break;
			i++;
		}
		tabella.add(i,n); //inserisce n in posizione i
	}//aggiungi
	
	@Override
	public void rimuovi( Nominativo n ){
		int i=Collections.binarySearch( tabella, n );
		if( i<0 ) return;
		tabella.remove(i);
	}//rimuovi
	
	@Override
	public Nominativo cerca( Nominativo n ){ //il costo è prevalentemente quello della ricerca binaria perchè le due operazioni in più sono una costante
		int i=Collections.binarySearch( tabella, n );
		if( i<0 ) return null;
		return tabella.get(i);
	}//cerca
	
	/*
	 *Fossimo al compito solo aggiungi() e iterator() perchè sono quelli necessari per motivi di efficienza. 
	 */
	@Override
	public Iterator<Nominativo> iterator(){
		return tabella.iterator();
	}//iterator

}//AgendinaAL