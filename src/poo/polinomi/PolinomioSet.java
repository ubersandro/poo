package poo.polinomi;
import java.io.Serializable;
import java.util.*;

public class PolinomioSet extends PolinomioAstratto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5007375383100567723L;
	private Set<Monomio> contenuto=new TreeSet<Monomio>();
	
	public PolinomioSet(){};
	
	public PolinomioSet( Polinomio p ){
		Iterator<Monomio> it=p.iterator();
		while( it.hasNext() ){
			Monomio m=(Monomio)it.next(); 
			contenuto.add(m);
		}
	}
	
	public PolinomioSet crea(){
		return new PolinomioSet();
	}//create
	
	public Iterator<Monomio> iterator(){
		return contenuto.iterator();
	}//iterator
	
	public int size(){ return contenuto.size(); }

	public void add( Monomio m ){
		if( m.getCoeff()==0 ) return;
		if( !contenuto.contains(m) ) contenuto.add(m);
		else{
			Iterator<Monomio> it=contenuto.iterator();
			Monomio m1=null;
			while( it.hasNext() ){
				m1=it.next();
				
				if( m1.equals(m) ) { 
					it.remove();
					m=m.add(m1); 
					break;
				}
			}
			if( m.getCoeff()!=0 ) contenuto.add(m);
		}
	}//add

	public static void main(String[] args) {
		PolinomioSet s= new PolinomioSet();
		s.add(new Monomio(8,9));
		s.add(new Monomio(3,2));
		s.add(new Monomio(3,2));
		s.add(new Monomio(3,5));
		System.out.println(s.getClass().getName());
		System.out.println(s);
	}
	}//PolinomioSet
