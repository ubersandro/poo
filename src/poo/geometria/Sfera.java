package poo.geometria;
/* mi devo chiedere se tutto ci� che metto dentro � totalmente nuovo 
NO CAMPI PERCHE' HO GIA' TUTTO QUELLO CHE MI SERVE	
*/

public class Sfera extends Disco implements FiguraSolida{
	public Sfera(double r){
		super(r);
	}

	public Sfera(double x,double y,double r){	
		super(x,y,r);
	}
	public Sfera(Punto p, double r){
		super(p,r);
	}
	public Sfera(Sfera s){
		super(s.getX(), s.getY() , s.getRaggio());
	}//per copia
	
	public double perimetro(){ //PROBLEMA ==> non posso mettere private nella sottoclasse perch� non si restringe in JAVA
		throw new UnsupportedOperationException();
	}//perimetro ==> implementato come unsupported

	public double area(){//area totale
		double r= getRaggio();
		return 4*Math.PI*r*r;
	}//area

	public double areaDiBase(){ //sul piano xy
		return super.area(); //area di Disco
	}

	public double areaLaterale(){
		return area();
	}

	//è inutile mettere getX(),getRaggio() ==> PUNTO 

	public String toString(){
		return "Sfera di raggio:"+ super.getRaggio() + " con centro:" + getX() + " " + getY();
	}//toString

	public double volume(){
		double r= getRaggio();
		return (4+Math.PI*r*r*r)/3;
	}//volume

	public int hashCode(){
		Double r= new Double(raggio);
		return r.hashCode();
	}//hashCode


}//Sfera