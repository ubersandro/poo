package poo.agendina;
import java.util.*;
/*
 * In questa implementazione non ho bisogno di costruttori (LinkedList). Una LinkedList è grande quanto è grande,amico mio. 
 * Ha un ingombro di memoria dipendente dal numero di elementi. Posso creare una LinkedList grande quanto la mia memoria.
 * Il metodo cerca() non e' overridden perche' non si puo' raggiungere un grado di efficienza maggiore rispetto a quello dell'interfaccia.
 */
public class AgendinaLL extends AgendinaAstratta{
	private LinkedList<Nominativo> tabella=
		new LinkedList<Nominativo>();
	
	@Override
	public Iterator<Nominativo> iterator(){
		return tabella.iterator();
	}//iterator
	
	/*
	 è più efficiente della svuota() dell'ArrayList perchè basta mettere a null i puntatori ai due elementi iniziale e finale
	 e non a tutti gli elementi come nell'AL
	 */
	
	@Override
	public void svuota(){ tabella.clear(); } 
	
	/*
	 * Iteratore funziona a meraviglia sulla LinkedList
	 */
	
	@Override
	public void aggiungi( Nominativo n ){ // lo facciamo cosi per il principio della consistenza
		//aggiunge n in ordine, evitando le omonimie
		//se n e' gia' presente, si sovrascrive
		ListIterator<Nominativo> lit=tabella.listIterator();
		boolean flag=false;
		while( lit.hasNext() && !flag ){
		      Nominativo x=lit.next();
		      if( x.equals(n) ){ lit.set(n); flag=true; } //update 
		      else if( x.compareTo(n)>0 ){
		    	  lit.previous();
		    	  lit.add(n);
		    	  flag=true;
		      }
		}
		if( !flag ) lit.add(n);
	}//aggiungi
	
	@Override
	public int size(){ return tabella.size(); }
	
}//AgendinaLL