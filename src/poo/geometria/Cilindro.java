package poo.geometria;

public class Cilindro extends Disco implements FiguraSolida {
	private double altezza;
	
	public Cilindro(Disco base, double h){
		super(base);
		altezza=h;
	}//normale
	
	public Cilindro(double h, double x, double y, double r) {
			super(x,y,r);
			altezza=h;
	}
	
	public Cilindro(Punto p, double r, double h) {
		super(p,r);
		altezza=h;
	}
	
	public Cilindro(Cilindro c) {
		super(c);
		this.altezza=c.altezza;
	}
	
	public double areaDiBase(){
		return super.area();
	}//areaDiBase
	
	public double volume() {
		return areaDiBase()*altezza;
	}//volume
	
	@Override 
	public double areaLaterale() {
		return 2*Math.PI*raggio*altezza;
	}//areaLaterale
	
	public double perimetro() {
		return 2*Math.PI*raggio;
	}//perimetro
	
	public double area() {
		return areaDiBase()*2+areaLaterale();
	}//area
	
	@Override
	public String toString(){
		return "Cilindro di altezza:" + String.format("1.3f", altezza)+ " e base di raggio"+String.format("1.3f",  raggio);
	}//toString
	
	
	public int hashCode(){
		Double d= new Double(raggio+altezza);
		return d.hashCode();
	}//hashCode ==> NAIVE
}//Cilindro
	
