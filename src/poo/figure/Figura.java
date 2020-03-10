package poo.figure;

import poo.geometria.FiguraPiana;

public abstract class Figura implements FiguraPiana{
	private double dimensione; //almeno una dimensione la hanno 
	// non serve fare il costruttore protected perch� viene usato solo quando lo chiamano altri costruttori
	public Figura(double d){ // argmomenti sono le cose attuali che gli passo, per questo solleva IAE
		if(d<=0) throw new IllegalArgumentException("Dimensione non valida");
		dimensione=d;
		} // serve perch� mi devo appoggiare al costruttore di sopra
	
	protected double getDimensione(){ //figura non ben caratterizzata --> lo usano gli eredi perch� � generico
		return dimensione;
	}//getDimensione
	/*
	@Override // non obbligatoria
	public abstract double perimetro(); // un metodo astratto si riduce alla sua intestazione
	public abstract double area(); // NON HANNO IL CORPO --> NO {}
	// i metodi astratti si riducono alla loro intestazione (signature)
	MEGLIO NON SCRIVERLI PERCHE' SONO UN ONERE PER GLI EREDI ==> LO DEVONO FARE 
	*/ 
	public String toString(){
		return "Figura di dimensione:"+ String.format("%5.2f" , dimensione);
	}
	
}//Figura