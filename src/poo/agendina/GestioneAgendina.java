package poo.agendina;
import java.util.*;
import java.io.*;

/*
 * In questa applicazione non uso il this. Se devo solo istanziare un singleton della classe e' meglio usare l'ambiente globale. 
 * Potrei utilizzare anche variabili non static==> chiedi a nonno libero
 * Interfaccia a riga di comando(front-end)
 */
public class GestioneAgendina{ 
	//ambiente globale
	static Agendina agenda;
	static String linea;
	static StringTokenizer st;
	static Scanner sc; // esempio= con Scanner potrei chiedere all'utente di salvare al momento della chiusura.
	
	/*
	 * Quando si scrive un main è raccomandabile tentare di farlo il più semplice possibile (anche il più breve).
	 */
	
	public static void main( String []args ) throws IOException{
		System.out.println("Programma Agendina Telefonica");
		System.out.println();
		System.out.println("Scegli tra: 0-AgendinaAL 1-AgendinaLL 2-AgendinaSet 3-AgendinaMap 4-AgendinaVector");
		sc=new Scanner( System.in );
		int tipo=0, capacita=0;
		do{
			tipo=sc.nextInt();
			if( tipo<0 || tipo>4 )
				System.out.print("Cosa ? Digitare un numero da 0 a 4 ");
		}while(tipo<0 || tipo>4);
		if( tipo==0 || tipo==4 ){
			System.out.print("Capacita' = ");
			capacita=sc.nextInt();
		}
		switch(tipo){
			case 0: 
				agenda=new AgendinaAL(capacita); break;
			case 1: 
				agenda=new AgendinaLL(); break;
			case 2: 
				agenda=new AgendinaSet(); break;
			case 3: 
				agenda=new AgendinaMap(); break;
			default:
				agenda=new AgendinaVector(capacita);
		}
		
		sc.nextLine(); //salta fine linea
		comandi();//il programma tende ad essere molto grande e illegibile se non uso metodi ausiliari.
		 //labelled statement
		ciclo: for(;;){
			System.out.print(">");
			linea=sc.nextLine();
			st=new StringTokenizer(linea, " ");
			char comando=(st.nextToken().toUpperCase()).charAt(0); // e' piu' semplice lasciarli caratteri perche' con l'enumerazione non ci guadagno
			switch( comando ){ //ogni comando ha un metodo omonimo che lo gestisce ==> confinamento dell'informazione: le informazioni vanno messe dove servono!
				case 'Q': quit(); break ciclo; //con un break etichettato ottengo un break multilivello.
				case 'A': aggiungiNominativo(); break;
				case 'R': rimuoviNominativo(); break;
				case 'T': ricercaTelefono(); break;
				case 'P': ricercaPersona(); break;
				case 'Z': azzera(); break;
				case 'E': mostraElenco(); break;
				case 'S': salva(); break;
				case 'C': carica(); break;
				default: errore();
			}
		}//for
		System.out.println("Bye.");
	}//main
	
	static void aggiungiNominativo(){
		try{
			String cog=st.nextToken().toUpperCase();
			String nom=st.nextToken().toUpperCase();
			String pre=st.nextToken();
			String tel=st.nextToken();
			Nominativo n=new Nominativo( cog, nom, pre, tel );
			agenda.aggiungi( n );
		}catch( Exception e ){ //sto gestendo l'eccezione==> per il resto me ne sbatto; il programma non e' interrotto perche' il programma e' ROBUSTO.
			System.out.println("Dati incompleti!");
		}
	}//aggiugiNominativo
	
	static void rimuoviNominativo(){ //NORMALIZATION: tutti i dati sono normalizzati.
		try{
			String cog=st.nextToken().toUpperCase();
			String nom=st.nextToken().toUpperCase();
			agenda.rimuovi( new Nominativo(cog, nom, "", "") );
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//rimuoviNominativo
	
	static void azzera(){
		agenda.svuota();
	}//azzera
	
	static void ricercaTelefono(){
		try{
			String cog=st.nextToken().toUpperCase();
			String nom=st.nextToken().toUpperCase();
			Nominativo n=agenda.cerca( new Nominativo(cog, nom, "", "") );
			if( n==null ) 
				System.out.println("Nominativo inesistente!");
			else 
				System.out.println(n.getPrefisso()+"-"+n.getTelefono());		
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//ricercaTelefono
	
	static void ricercaPersona(){
		try{
			String pre=st.nextToken();
			String tel=st.nextToken();
			Nominativo n=agenda.cerca( pre, tel );
			if( n==null ) 
				System.out.println("Nominativo inesistente!");
			else 
				System.out.println(n.getCognome()+" "+n.getNome());			
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//ricercaPersona
	
	static void mostraElenco(){
		System.out.println( agenda );
	}//mostraElenco
	
	static void salva(){
		String nomeFile=null;
		try{
        	nomeFile=st.nextToken();
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
			return;
		}
		try{
			agenda.salva( nomeFile );
		}catch( IOException e ){
			System.out.println();
		}
	}//salva
	
	static void carica(){
		String nomeFile=null;
		try{
        	nomeFile=st.nextToken();
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
			return;
		}
		File f=new File( nomeFile );
		if( !f.exists() ){
			System.out.println("File inesistente!");
			return;
		}		
		try{
			agenda.ripristina( nomeFile );
		}catch(IOException e){
			System.out.println("Nessuna apertura!");
		}
	}//carica
	
	

	static void comandi(){
		System.out.println();
		System.out.println("Comandi ammessi e relativi parametri:");
		System.out.println("A(ggiungi  cog  nom  pre  tel");
		System.out.println("R(imuovi  cog  nom");
		System.out.println("Z(azzera");
		System.out.println("T(elefono  cog  nom");
		System.out.println("P(persona  pre  tel");
		System.out.println("E(lenco");
		System.out.println("S(alva  nomefile");
		System.out.println("C(arica  nomefile");
		System.out.println("Q(uit");
		System.out.println();
	}//comandi
	
	static void errore(){
		System.out.println("Comando sconosciuto!");
		comandi();
	}//errore
	
	static void quit(){
		System.out.print("Vuoi salvare il contenuto dell'agenda prima di terminare(y/n)?");
		String yesno=sc.nextLine();
		if( yesno.toLowerCase().equals("y") ){
			System.out.print("nome file=");
			String nomeFile=sc.nextLine();
			try{
				agenda.salva( nomeFile );
			}catch( IOException ioe ){
				System.out.println("Errore salvataggio!");
			}
		}
	}//quit
	
}//GestioneAgendina
