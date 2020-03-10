package poo.esempi;

import poo.figure.Figura;

public class Prova{
	public static Figura areaMassima(Figura f[]){
		Figura fam = null; // dentro il metodo di default non assegna null alla variabile oggetto
		double am = 0.0D; //per estendere da float a double
		for(int i=0; i<f.length ; ++i){
			double a=f[i].area(); // oggetto figura con la visione di figura statica ma tipo dinamico specifico
			if(a>am) { fam=f[i]; am=a;}
		}//for
		return fam;
	}//areaMassima 
	
}//Prova