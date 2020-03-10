package poo.polinomi;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

/*
 * Implementazione di Polinomio basato su una Map
 */

public class PolinomioMap extends PolinomioAstratto implements Serializable {
	private static final long serialVersionUID = 8561307158360601027L;
	//per preservare l'ordine e' meglio usare un Comparator che ordina gli Integer (keys) all'inverso.
	private TreeMap<Integer,Monomio> p;
	
	public PolinomioMap() {
		 p=new TreeMap<>(new ComparatoreInverso());
	}//PolinomioMap

	
	public PolinomioMap(PolinomioMap polinomio){
		p=new TreeMap<>(new ComparatoreInverso());
		this.p.putAll(polinomio.p);
	}//PolinomioMap

	private class ComparatoreInverso implements Comparator<Integer>, Serializable{
		/*
		 * Classe privata introdotta per risolvere il problema della serializzazione. 
		 */
		private static final long serialVersionUID = -8769285897837271650L;

				public int compare(Integer o1, Integer o2) {
					if(o1>o2) return -2;
					if(o1<o2) return 2;
					return 0;
				}//compare
		}//ComparatoreInverso

	public PolinomioMap(Polinomio p) {
		this(); 
		Iterator<Monomio> it= p.iterator();
		while(it.hasNext()) {
			Monomio cur=it.next();
			this.p.put(cur.getGrado(),cur);
		}//while
	}//Costruttore per copia

	@Override
	public void add(Monomio m) {
		if(m.getCoeff()==0) return;
		Monomio q= p.remove(m.getGrado());
		if(q!=null)p.put(q.getGrado(),q.add(m));
		else p.put(m.getGrado(),m);
	}//add

	@Override
	public Polinomio crea() {
		return new PolinomioMap();
	}//crea

	
	public Iterator<Monomio> iterator(){return p.values().iterator();};


	public static void main(String[] args) {
		PolinomioAL p= new PolinomioAL();
		p.add(new Monomio(3,2));
		p.add(new Monomio(4,2));
		p.add(new Monomio(3,1));
		System.out.println(p);
		PolinomioMap pm= new PolinomioMap(p);
		System.out.println(pm);
	}
}//PolinomioMap
