package poo.polinomi;
import java.io.Serializable;
import java.util.Iterator;

import poo.util.ListaConcatenataOrdinata;// potrei importare pure l'interface

public class PolinomioConcatenato extends PolinomioAstratto implements Serializable {
	
	private static final long serialVersionUID = -7924506921782075589L;
	private ListaConcatenataOrdinata<Monomio> polinomio=
			new ListaConcatenataOrdinata<>();
	public PolinomioConcatenato() {}

	public PolinomioConcatenato(Polinomio p){
		for(Iterator<Monomio> it= p.iterator(); it.hasNext();){
			this.add(it.next());
		}//for
	}//costruttore di copia

	public void add(Monomio x) {
		if(x.getCoeff()==0) return;
		Monomio q=polinomio.get(x);//monomio con lo stesso grado ==> non e' detto che esista
		if(q==null) polinomio.add(x);//l'aggiunta avviene in ordine
		else {
			polinomio.remove(x);
			q=q.add(x);
			if(q.getCoeff()!=0) polinomio.add(q);//automaticamente verra' incrementato size
		}//else
	}//add
	
	@Override
	public int size() {
		return polinomio.size();
	}//size
	
	
	public Iterator<Monomio> iterator(){
		return polinomio.iterator();
	}//iterator

	@Override
	public Polinomio crea() {
		return new PolinomioConcatenato();
	}//crea

	public static void main(String...args){
		PolinomioConcatenato p= new PolinomioConcatenato();
		p.add(new Monomio(3,4));
		p.add(new Monomio(2,4));
		p.add(new Monomio(2,3));
		System.out.println(p);
	}//main
}//PolinomioConcatenato

