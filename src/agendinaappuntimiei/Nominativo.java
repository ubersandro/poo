package agendinaappuntimiei;

/*
 * genera oggetti immutabili
 * l'uguaglianza si basa sul nominativo, non sul numero
 */


public class Nominativo implements Comparable<Nominativo> {
	private String cognome , nome, prefisso, numero ; // prefisso come stringa percè c'è 0	
	
	//a volte creiamo nominativi parziali

	/*
	 * confronta per cognome e nome
	 */
	public int  compareTo(Nominativo n) {
		if(cognome.compareTo(n.cognome)<0 || cognome.equals(n.cognome)&&nome.compareTo(n.nome)<0) return -1;
		if(equals(n)) return 0;
		return -1;
	}//compareTo 
	
	public Nominativo(String cog, String nom, String pre, String tel ) {
		cognome=cog; nome=nom; prefisso=pre; numero=tel;
	}//Nominativo

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getPrefisso() {
		return prefisso;
	}

	public String getNumero() {
		return numero;
	}
	
	public String toString() {
		return cognome +" "+ nome + " "+ prefisso +"-"+numero;
	}//toString
	
	public boolean equals(Object x) {
		if(!(x instanceof Nominativo))return false;
		if(x==this) return true;
		Nominativo n= (Nominativo) x;
		return n.nome.equals(this.nome)&&this.cognome.equals(n.cognome);
	}//equals
	
	public int hashCode() {
		final int M=83;
		int h=0;
		h=h*M+cognome.hashCode();
		h=h*M+nome.hashCode();
		return h;
	}//hashCode
	
	
	/*
	 * ==> test regressivo: test che faccio su una classe in modo tale che le cose che funzionavano continuino a funzionare 
	 */
	
	public static void main(String ... porcodio) {
		Nominativo n= new Nominativo("Stefano", "Perna", "39", "22345");
		System.out.println(n);
		Nominativo m= new Nominativo("Stefano" ,"Perna", "","");
		System.out.println(m.equals(n));
		System.out.println(m.hashCode());
		System.out.println(m.getCognome().hashCode());
		System.out.println(m.compareTo(n));
		System.out.println("SESSO".equals("sesso"));
		Integer i= 8;
		System.out.println(i.hashCode());
	}//mainino ==> UNIT TEST per capire se la singola classe funziona
	
}//Nomivativo 
