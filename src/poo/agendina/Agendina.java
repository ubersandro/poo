package poo.agendina;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 * Tipo di dato astratto che descrive un'agendina telefonica.
 * Gli elementi sono ti tipo Nominativo. Non si ammettono le
 * omonimie. L'agendina e' supposta mantenuta ordinata
 * per cognome crescente e a parita' di cognome per nome crescente.
 * @author Libero Nigro
 */
public interface Agendina extends Iterable<Nominativo>{
	
	/**
	 * Restituisce il numero di nominativi dell'agenda.
	 * @return il numero di nominativi in agenda.
	 */
	default int size(){
		int c=0;
		for( Iterator<Nominativo> it=iterator(); it.hasNext(); it.next(),c++ );
		return c;
	}//size
	
	/**
	 * Svuota il contenuto dell'agendina.
	 */
	default void svuota(){
		Iterator<Nominativo> it=iterator();
		while( it.hasNext() ) {
			it.next(); it.remove();
		}
	}//svuota
	
	/**
	 * Aggiunge un nominativo all'agenda. Non si ammettono
	 * le omonimie. L'aggiunta avviene in ordine alfabetico crescente
	 * del cognome ed a parita' di cognome in ordine alfabetico del nome.
	 * @param n il nominativo da aggiungere
	 */
	void aggiungi( Nominativo n );
	
	/**
	 * Rimuove un nominativo dall'agenda.
	 * @param n il nominativo da rimuovere dall'agenda.
	 */
	default void rimuovi( Nominativo n ){
		Iterator<Nominativo> it=iterator();
		while( it.hasNext() ) {
			Nominativo x=it.next(); 
			if( x.equals(n) ){ it.remove(); break;}
			else if( x.compareTo(n)>0 ) break;
		}
	}//rimuovi
	
	/**
	 * Cerca un nominativo uguale ad n.
	 * @param n il nominativo da cercare, significativo solo per nome e cognome.
	 * @return il nominativo dell'agenda uguale ad n o null se n non e' in agenda.
	 */
	default Nominativo cerca( Nominativo n ){
		for( Nominativo x: this ){
			if( x.equals(n) ) return x;
			if( x.compareTo(n)>0 ) return null;
		}
		return null;
	}//cerca
	
	/**
	 * Cerca un nominativo nell'agenda, di assegnato prefisso e numero di telefono.
	 * @param prefisso 
	 * @param telefono
	 * @return il nominativo trovato o null
	 */
	default Nominativo cerca( String prefisso, String telefono ){
		for( Nominativo n: this )
			if( n.getPrefisso().equals(prefisso) && n.getTelefono().equals(telefono) ) return n;
		return null;
	}//cerca
	
	/**
	 * Salva il contenuto dell'agenda su file.
	 * @param nomeFile il nome esterno del file per il salvataggio.
	 * @throws IOException 
	 */
	default void salva(String nomeFile) throws IOException{
    	PrintWriter pw=new PrintWriter( new FileWriter(nomeFile));
		for( Nominativo n: this ) pw.println(n);
		pw.close();		
	}//salva
	
	/**
	 * Ripristina il contenuto dell'agenda, a partire da un file.
	 * @param nomeFile il nome esterno del file da cui attingere.
	 * @throws IOException es. se il file non esiste
	 */
	default void ripristina(String nomeFile)throws IOException{
		BufferedReader br=new BufferedReader( new FileReader(nomeFile) );
		String linea=null;
		StringTokenizer st=null;

		LinkedList<Nominativo> tmp=new LinkedList<Nominativo>();
		//tmp e' utile per far fronte a malformazioni del file
		boolean okLettura=true;
		for(;;){
			linea=br.readLine();
			if( linea==null ) break; //eof di br
			st=new StringTokenizer(linea," -");
			try{
				String cog=st.nextToken();
				String nom=st.nextToken();
				String pre=st.nextToken();
				String tel=st.nextToken();
				tmp.add( new Nominativo( cog, nom, pre, tel ) ); //aggiunge in coda
			}catch(Exception e){
				okLettura=false;
				break;
			}
		}
		br.close();
		if( okLettura ){ 
			this.svuota();
			for( Nominativo n: tmp ) this.aggiungi(n);
		}
		else 
			throw new IOException();
	}//ripristina
	
}//Agendina