package poo.test;
import poo.geometria.*;
public class C implements Cloneable {
	int x;
	Disco d;
	public C(int x, Disco d) {
		this.x=x; this.d=d;
	}
	
	/*
	 * 13/11
	 * CLONEABLE  � una marker interface. C is cloneable (CLONEABBBILE)
	 * Il metodo Clone fa una shallow(superficiale) copy dell'oggetto, non deep. Un oggetto in memoria � una successione di byte.
	 * Fare la copia superficiale vuol dire fare la copia dei byte cos� come sono. Il problema � che si crea aliasing con i puntatori ad altri oggetti
	 * contenuti nell'oggetto clonato==> es Disco d del mio oggetto di partenza. Devo assicurarmi che il mio cloning abbia una semantica PROFONDA!
	 */
	
	@Override
	public C clone() throws CloneNotSupportedException {
		C c= (C) super.clone(); 
		c.d= new Disco(this.d); // copia profonda 
		
		
		return c;
	}
	
	public String toString() {
		return "x = " + x +", Disco = "+ d; 
	}//toString

}//C

