package agendinaappuntimiei;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.StringTokenizer;

public interface Agendina extends Iterable<Nominativo> {
	int size();
	default void svuota() {
		for(Nominativo n: this) {
			rimuovi(n);
		}
	}
	void aggiungi (Nominativo n); // hai la respomnsabilità dell'ordine
	
	default void rimuovi(Nominativo n) {
		Iterator<Nominativo> it= iterator();
		while(it.hasNext()) {//ricerca lineare ottimizzata
			Nominativo m = it.next();
			if(n.equals(m)) {it.remove(); return;}
			if(m.compareTo(n)>0) return; // non lo troverò mai 
		}
	}//rimuovi
	
	default Nominativo cerca(Nominativo n){
		Iterator<Nominativo> it = iterator();
		while(it.hasNext()) {
			Nominativo m= it.next();
			if(m.getPrefisso().equals(n.getPrefisso())&& m.getNumero().equals(n.getNumero())) return n;
		}//while
		return null;
	} //il nominativo che cerco è parziali, per questo non è banale.
	
	default Nominativo cerca(String prefisso, String telefono) { // return null if not present 
		return null;
	}
	
	/*
	 * il filesystem di windows salva i file codificati in ASCII mentre java utilizza UNICODE
	 * FileWriter esegue la conversione.
	 *N.B. il this mi viene fornito dalla classe concreta che costruirari amico mio.
	 */
	
	default void salva(String nomefile) throws IOException{
		PrintWriter pw= new PrintWriter(new FileWriter(nomefile)); 
		for(Nominativo n: this) {
			pw.println(n);
		}//for 
		pw.close(); // perchè se no poi mi risulta il file aperto e non mi si chiude
	}//salva
	
	
	/*
	 * I nominativi già presenti nel file rimpiazzano quelli già presenti nell'agenda.
	 * N.B. Ogni classe di file specifica in modo diverso come finisce il file (devo controllare).
	 *Se il file è sballato sollevo un'eccezione generica
	 */
	
	
	default void carica (String nomefile) throws IOException{ 
		svuota();
		BufferedReader br= new BufferedReader( new FileReader( nomefile));
		try {
			for(;;) {
				String linea= br.readLine();
				if(linea==null) break; // se il file è finito 
				StringTokenizer st= new StringTokenizer(linea, " -");//prima di usare lo ST dovrei usare hasToken()
				String cog= st.nextToken();
				String nom = st.nextToken();
				String pre= st.nextToken();
				String tel= st.nextToken();
				this.aggiungi(new Nominativo(cog,nom,pre,tel));
			}//for
		} catch( Exception e) {
			throw new IOException();
		} finally {
			br.close();
		} // questa istruzione sarà eseguita sempre e comunque, ricorda amico mio. è molto più importante fare una close su un file in scrittura.
	}//carica 
	
	
	
	
	
	

}//Agendina
