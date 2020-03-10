package poo.polinomi;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class PolinomioAL extends PolinomioAstratto implements Serializable {
	private static final long serialVersionUID=7074496226790560878L;
	private ArrayList<Monomio> contenuto= new ArrayList<>();;
	
	public PolinomioAL(){}
	
	public PolinomioAL(Polinomio p) {
		Iterator<Monomio> lit= p.iterator();
		while(lit.hasNext()) contenuto.add(lit.next());
	}//Costruttore per copia 
	
	/*@Override
	public void add(Monomio m) {
		if(m.getCoeff()==0) return;
		boolean flag= false; // non c'è nella lista
		Iterator<Monomio> it = contenuto.iterator();
		while(it.hasNext()&&!flag) {
			Monomio corrente= it.next();
			if(corrente.equals(m)) { 
				m=corrente.add(m); 
				flag=true;
				it.remove(); 
			}
		}//while
		contenuto.add(m); 
	}//add 
	*/
	public void add(Monomio m) {
		int i=Collections.binarySearch(contenuto,m);
		System.out.println(m); System.out.println(i);
		if(i>=0) contenuto.set(i, m.add(contenuto.get(i)));
		else {
			i=0;
			for(Monomio e:this) {
				if(m.compareTo(e)<0) break; 
				i++;
			}	
			contenuto.add(i,m);
		}
	}

	@Override
	public Polinomio crea() {
		return new PolinomioAL();
	}//crea

	@Override
	public Iterator<Monomio> iterator() {
		return contenuto.iterator();
	}//iterator


	public static void main(String[] args) {
		PolinomioAL p = new PolinomioAL();
		p.add(new Monomio(3,5));
		p.add(new Monomio(4,5));
		p.add(new Monomio(-2,4));
		p.add(new Monomio(1,8));
		System.out.println(p);
		PolinomioAL p1= new PolinomioAL(p); 
		System.out.println(p1);
	}
}
