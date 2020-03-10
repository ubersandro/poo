package poo.recursion;

public class Ricorsione {
	
	//se io controllassi i parametri appena chiamo il metodo ricorsivo aggiungerei un costo in termini spaziali e temporali non indifferente
	private int Gauss(int n) {
		if(n==1)return 1;
		return Gauss(n-1)+n;
	}//Gauss
	
	//soluzione elegande==> responsabilita di verificare la correttezza dei parametri
	public int GAUSS(int n) {
		if(n<=0) throw new IllegalArgumentException("Amico mio, te salutoooo");
		return Gauss(n);
	}
	
	//riscriviamo ricorsivamente definendo bene il processo di calcolo
	public int power(int a, int n) {
		if(n<=0) throw new IllegalArgumentException();
		int p=1;
		for(int i=0;i<n;++i) {
			p=p*a;
		}
		return p;
	}//pot
	
	public static int pot(int a, int n) {
		if(n<0)throw new IllegalArgumentException();
		return potenza(a,n);
	}//pot
	
	//definizione nel caso normale 
	private static int potenza(int a, int n) {
		if(n==0)return 1;
		if(n==1)return a;
		return a*potenza(a,n-1); 
	}//potenza
	
	public static boolean palindroma(String s) {
		int i=0, j=s.length()-1;
		while(i<j) {
			if(s.charAt(j)!=s.charAt(j)) return false;
			i++;j--;
		}
		return true;
	}//palindroma 
	
	public static boolean palindromaRicorsivo(String s) {
		if(s.length()<=1) return true;
		if(s.charAt(0)!=s.charAt(s.length()-1))return false; //secondo caso di uscita 
		return palindromaRicorsivo(s.substring(1,s.length()-1));
	}

	// @param n non negativo
	//rimanere sul numero==> non String 
	//dividi per 10 tante volte e ciao ti saluto 
	/*public void scriviInverso(int n) {
		if(n/10==0) return
	}
	*/
	//TODO metodo scriviInverso()
}//Ricorsione
