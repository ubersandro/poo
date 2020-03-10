package poo.geometria;
import java.util.*;

class Punto{
   private double x, y;
   public Punto(){//costruttore di default
      this(0,0);
   }
   public Punto( double x, double y ){//costruttore normale
      this.x=x; this.y=y;
   }
   public Punto( Punto p ){//costruttore di copia
      x=p.x; y=p.y;
   }
   public double getX(){ return x; }
   public double getY(){ return y; }
   public void sposta( double nuovaX, double nuovaY ){
      x=nuovaX; y=nuovaY;
   }//sposta
   public double distanza( Punto p ){
      return Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y));
   }//distanza
   public String toString(){
	return "Punto<"+ String.format("%7.3f",x) +"," +String.format("%7.3f", y) +">"; //METTE 0 PER OGNI CIFRA RAZIONALE NON DATA 
	//String.format("7.3f% , "7.3f", x, y) SCORCIATOIA	
      //return "Punto<"+x+","+y+">";
   }//toString
}//Punto

class Triangolo{
	private Punto p1, p2, p3;
	private double a, b, c;
	public Triangolo( Punto p1, Punto p2, Punto p3 ){
		a=p1.distanza(p2);
		b=p2.distanza(p3);
		c=p3.distanza(p1);
		//verifica triangolo
		if( a>=b+c || b>=a+c || c>=a+b ){
			throw new IllegalArgumentException("Triangolo inesistente!");
		}
       this.p1=new Punto( p1 );
       this.p2=new Punto( p2 );
       this.p3=new Punto( p3 );
   }
   public Triangolo( Triangolo t ){
	   p1=new Punto( t.p1 );
	   p2=new Punto( t.p2 );
	   p3=new Punto( t.p3 );
	   this.a=t.a;
	   this.b=t.b;
	   this.c=t.c;
   }
   public double getA(){ return a; }
   public double getB(){ return b; }
   public double getC(){ return c; }
   public Punto[] getVertici(){
	   return new Punto[]{new Punto(p1),new Punto(p2),new Punto(p3)};
   }//getVertici
   public double perimetro(){
	   return a+b+c;
   }//perimetro
   public double area(){
	   double s=this.perimetro()/2;
	   return Math.sqrt(s*(s-a)*(s-b)*(s-c));
   }//area
   public String tipo(){
	   if( a==b && b==c ) return "Equilatero";
	   if( a==b || a==c || b==c ) return "Isoscele";
	   return "Scaleno";
   }//tipo
   public String toString(){
	   return "Triangolo con vertici: "+p1+" "+p2+" "+p3+ " tipo "+tipo();
   }//toString
}//Triangolo

public class Geometria {
	public static void main( String[] args ){
	   Punto p=new Punto(5,7);
	   Punto q=new Punto(); //nell'origine
	   System.out.println("<"+p.getX()+","+p.getY()+">");
	   //o si ricorre a toString
	   System.out.println(p.toString());
	   //toString è invocabile implicitamente
	   System.out.println(p); //schema preferito
	   p.sposta(3.567,-4.6789);
	   System.out.println(p);
	   double d=q.distanza(p);
	   System.out.println("distanza="+d);

	   Triangolo t=null;
	   t=new Triangolo( new Punto(0,1), new Punto(3,2), new Punto(4,7) );
	   System.out.println(t);
	   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());
	   t=new Triangolo( new Punto(2,2), new Punto(4,2), new Punto(4,5) );
	   System.out.println(t);
	   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());
	   t=new Triangolo( new Punto(2,0), new Punto(2,2), new Punto() );
	   System.out.println(t);
	   System.out.printf("perimetro=%1.2f area=%1.2f\n",t.perimetro(),t.area());
	}//main
}//Geometria
