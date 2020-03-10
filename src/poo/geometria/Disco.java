package poo.geometria;


public class Disco extends Punto implements FiguraPiana{
	protected double raggio; //corretto a protected perchè lo devono vedere gli eredi
	public Disco(double r){
		super();
		if(raggio<0) throw new IllegalArgumentException();
		this.raggio=r;
	}//default
	
	public Disco(Punto p, double r){
		super(p);
		if(raggio<0) throw new IllegalArgumentException("Raggio minore di 0.");
		raggio=r;
	}//Punto e raggio dati

	public Disco(double x, double y,double r){
		super(x,y);
		if(raggio<0) throw new IllegalArgumentException();
		this.raggio=r;
	}//default
	
	public Disco(Disco d){
		super(d.getX(), d.getY());
		raggio=d.raggio;
	}


	public double getRaggio(){ return raggio;}
	
	public double perimetro(){ return 2*Math.PI*raggio;}
	
	public double area(){	return raggio*raggio*Math.PI;}

	public String toString(){ return "Disco  centro="+super.toString()+"raggio"+raggio;}//toString
	
	

	public static void main(String[] args){	
		Punto p=new Punto(3,4);
		Disco d= new Disco(5);
		Punto p1=new Punto(1,2);
		Disco d1=new Disco(10,23,4);
		System.out.println((d1 instanceof Punto));
		p1=d1;
		
		//System.out.println(p.getRaggio()); NON SI PUO' FARE
		if(p1 instanceof Disco)	System.out.println(((Disco) p1).getRaggio());
 		System.out.println(p1);
		double dist= p.distanza(d);
		System.out.printf(String.format("%1.3f", dist));
		System.out.println(d);
	}//main
}//Disco