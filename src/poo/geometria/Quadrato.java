package poo.geometria;

import poo.figure.Figura;

//hashCode è intero, ma io ho una quantità reale ==> tutte le classi della libreria Java hanno hashcode
//creo un oggetto Double 
//ho fatto override di tutti i metodi astratti di Figura==> Quadrato si può istanziare
	
public class Quadrato extends Figura{
	public Quadrato(double lato){
		super(lato);
	}//costruttore normale
	
	public double getLato(){ return getDimensione();
	}//getLato
	
	public double perimetro(){
		return 4*getDimensione();
	}//perimetro
	
	public double area(){		
		double l=getDimensione();
		return l*l;
	} //area

	public String toString(){	
		return "Quadrato di lato:"+String.format("1.2f", getDimensione());
	}//toString
		
		
	public int hashCode(){	
		Double d= new Double(getDimensione());
		return d.hashCode();
	}//hashCode

}//Quadrato