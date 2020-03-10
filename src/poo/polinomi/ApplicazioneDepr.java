package poo.polinomi;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ApplicazioneDepr {

	public static void main(String...strings) {
		Scanner sc = new Scanner(System.in);
		//String SEGNO="[\\-\\+]";
		Polinomio p1 = null,p2=null;
		String MONOMIO = "[\\-\\+]?([0-9]|[1-9]?x[\\^\\d{1,3}]?)"; //includo nel primo gruppo 0 per comodita' ==> monomio degenere CHIEDI
		String POLINOMIO_REGEX="["+MONOMIO+"]+";
		
		String poli=null;
		ciclo:while(true) {
			while(p1==null||p2==null){
				System.out.println("Inserire polinomio:");
				System.out.print('>');
				poli=sc.nextLine();
				if(poli.matches(POLINOMIO_REGEX)) {
					System.out.println("Corretto.");
					if(p1==null) {
						p1=costruisciPolinomio(poli);	
					}//p1null
					else p2=costruisciPolinomio(poli);
				}//if(poli.matches(REGEX))
				else System.out.println("Non corretto. Inserisci nuova espressione.");	
				
			}//while polinomi
	
		
cicloInterno:for(;;) {
			
				System.out.println("Inserire codice operazione:");
				System.out.println("1)SOMMA ALGEBRICA");
				System.out.println("2)PRODOTTO ");
				System.out.println("STOP per terminare il programma");
				System.out.print("> ");
				String comando=sc.nextLine();
				Polinomio risultato=new PolinomioSet();
				switch(comando) {
					case "1": 
						risultato=p1.add(p2);
						System.out.println(risultato);
						p1=null; p2=null;
						break cicloInterno;
					case "2":
						risultato=p1.mul(p2);
						System.out.println(risultato);
						p1=null; p2=null;
						break cicloInterno;
					case "STOP": case"stop": case "S":case "s":
						break ciclo;
					default:
						System.out.println("Errore, riprova.");
				}//switch
				
			}//for
		
		}//whiletrue
		sc.close();
		System.out.println("Bye");
		
	}//main
	
	
	 static Polinomio costruisciPolinomio(String polinomio) {
		StringTokenizer st= new StringTokenizer(polinomio,"+-", true);
		String MONOMIO_DEGENERE="[1-9]+";
		Polinomio risultato=new PolinomioSet();
		String MONOMIO_VAR_EXP="[1-9]*x(\\^[\\-]?[1-9]{1,3})?";
		boolean segno=true;
		while(st.hasMoreTokens()){
			String token=st.nextToken();
			if(token.equals("-")) segno=false;
			if(token.equals("+"))segno=true;
			if(token.matches(MONOMIO_DEGENERE)) {
				if(!segno)risultato.add(new Monomio(-Integer.parseInt(token),0));
				else risultato.add(new Monomio(Integer.parseInt(token),0));
			}
			
			if(token.matches(MONOMIO_VAR_EXP)) {
				if(!segno) {
					if(token.charAt(0)!='x') {
						if(token.contains("^")) risultato.add( new Monomio(-Integer.parseInt(token.substring(0,token.indexOf("x"))),
															Integer.parseInt(token.substring(token.indexOf('^')+1))));
						else risultato.add(new Monomio(-Integer.parseInt(token.substring(0,token.indexOf("x"))),1));
					} 
					else {
						if(token.contains("^")) risultato.add( new Monomio(-1,
								Integer.parseInt(token.substring(token.indexOf('^')+1))));
						else risultato.add(new Monomio(-1,1));
					}
				}//if segno
				else { 
					if(token.charAt(0)!='x') {
						if(token.contains("^")) risultato.add( new Monomio(Integer.parseInt(token.substring(0,token.indexOf("x"))),
															Integer.parseInt(token.substring(token.indexOf('^')+1))));
						else risultato.add(new Monomio(Integer.parseInt(token.substring(0,token.indexOf("x"))),1));
					} 
					else {
						if(token.contains("^")) risultato.add( new Monomio(1,
								Integer.parseInt(token.substring(token.indexOf('^')+1))));
						else risultato.add(new Monomio(1,1));
					}
				}//else 
			}//if segno
		}//while
		
		return risultato;	
	}//costruisciPolinomio
	
}//Applicazione


