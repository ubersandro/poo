package poo.polinomi;

import java.io.Serializable;

public class Monomio implements Comparable<Monomio>,Serializable{

	
	private static final long serialVersionUID = -1415930212840505804L;
	private final int coeff, grado;
	public Monomio( int coeff, int grado ){
		if( grado<0 ) 
			throw new RuntimeException("Grado negativo");
		this.coeff=coeff; this.grado=grado;
	}//Monomio
	public Monomio( Monomio m ){
		coeff=m.coeff; grado=m.grado;
	}//Monomio
	public int getCoeff(){ return coeff; }
	public int getGrado(){ return grado; }
	public Monomio add( Monomio m ){
		if( !this.equals(m) ) 
			throw new RuntimeException("Monomi non simili");
		return new Monomio( coeff+m.coeff, grado );
	}//add
	public Monomio mul( Monomio m ){
		return new Monomio( coeff*m.coeff, grado+m.grado );
	}//mul
	public Monomio mul( int s ){
		return new Monomio( coeff*s, grado );
	}//mul
	public int compareTo( Monomio m ){
		if( grado>m.grado ) return -1;
		if( grado<m.grado ) return +1;
		return 0;
	}//compareTo
	public boolean equals( Object o ){
		//uguaglianza come similitudine
		if( !( o instanceof Monomio ) ) return false;
		if( o==this ) return true;
		Monomio m=(Monomio)o;
		return this.grado==m.grado;
	}//equals
	public int hashCode(){
		return grado;
	}//hashCode

	//TODO se ci sono piu' monomi nulli ne voglio vedere uno solo (a patto che il polinomio non abbia altri monomi non nulli)

	public String toString(){
		StringBuilder sb=new StringBuilder();
		if( coeff<0 ) sb.append('-');
		if( Math.abs(coeff)!=1 || grado==0 )
			sb.append( Math.abs(coeff) );
		if( coeff!=0 && grado>0 ) sb.append('x');
		if( coeff!=0 && grado>1 ){
			sb.append('^');
			sb.append( grado );
		}
		return sb.toString();
	}//toString
}//Monomio