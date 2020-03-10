package agendinaappuntimiei;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Set;

/*
 * non serve implementare di nuovo cerca perchè tanto uso sempre l'iteratore
 */

public class AgendinaSet extends AgendinaAstratta{
	private Set<Nominativo> tabella= new TreeSet<>();	
	public Iterator<Nominativo> iterator(){
		return tabella.iterator();
	}
	
	public void aggiungi(Nominativo n) {
			tabella.remove(n);
			tabella.add(n);
	}//aggiungi
	
	public int size() {
		return tabella.size();
	}//size
	
	public void rimuovi(Nominativo o) {
		tabella.remove(o);
	}
}//AgendinaSet
