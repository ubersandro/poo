package poo.razionali;
import poo.util.Mat;
import poo.util.Array;
//import java.lang.Math;

public class Razionale implements Comparable{
   //Esempio di classe di oggetti immutabili
   private final int NUM, DEN;
   private static int contatore=0;
   public Razionale( int num, int den ){
      if( den==0 )
      	throw new RuntimeException("Denominatore nullo!");
      if( num!=0 ){
    	  int n=Math.abs( num ), d=Math.abs( den );
    	  int cd=Mat.mcd( n, d );
    	  num=num/cd; den=den/cd;
      }
      if( den<0 ){
         num *= -1;
         den *= -1;
      }
      this.NUM=num;
      this.DEN=den;
      contatore++;
   }
   public Razionale( Razionale r ){//costruttore di copia
      this.NUM=r.NUM;
      this.DEN=r.DEN;
      contatore++;
   }
   public int getNum(){ return NUM; }
   public int getDen(){ return DEN; }

   public Razionale add( Razionale r ){
      int mcm=(r.DEN*DEN)/Mat.mcd(r.DEN,DEN);
      int num=(mcm/DEN)*NUM + (mcm/r.DEN)*r.NUM;
      int den=mcm;
      return new Razionale( num, den );
   }//add
   public Razionale sub( Razionale r ){
	      int mcm=(r.DEN*DEN)/Mat.mcd(r.DEN,DEN);
	      int num=(mcm/DEN)*NUM - (mcm/r.DEN)*r.NUM;
	      int den=mcm;
	      return new Razionale( num, den );
   }//sub
   public Razionale mul( Razionale r ){
      return new Razionale( NUM*r.NUM, DEN*r.DEN );
   }//mul
   public Razionale mul( int s ){
      return new Razionale( NUM*s, DEN );
   }//mul

   public Razionale div( Razionale r ){
      return new Razionale( NUM*r.DEN, DEN*r.NUM );
   }//div
   public static int razionaliCreati(){ return contatore; }
   public String toString(){
      if( DEN==1 ) return ""+NUM;
      if( NUM==0 ) return "0";
      return ""+NUM+"/"+DEN;
   }//toString

/*
   private int mcd( int n, int m ){
	   int r=0, x=n, y=m;
	   do{
		   r=x%y;
		   x=y; y=r;
	   }while( r!=0 );
	   return x;
   }//mcd

   private static int mcd( int x, int y ){
	   if( y==0 ) return x;
	   return mcd( y, x%y );
   }//msd

   public static int mcd_Euclide( int x, int y ){
	   if( x<=0 || y<=0 ) throw new IllegalArgumentException();
	   return mcd(x,y);
   }//mcd_Euclide
*/
   @Override
   public int compareTo(Object x){
      Razionale r = (Razionale) x;
      int d1= DEN;
      int d2= r.DEN;
      int mcm= Mat.mcm(d1,d2);
      int n1= mcm/d1 * NUM;
      int n2= mcm/d2 *r.NUM;
      if(n1>n2) return -1;
      if(n1==n2) return 0;
      return 1;
   }//compareTo


   protected void finalize(){ contatore--; }

   public static void main( String[] args ){
	   /*
		//calcola 2/3*5/7-4/5
		Razionale r1=new Razionale(2,3);
		Razionale r2=new Razionale(5,7);
		Razionale r3=new Razionale(4,5);
      Razionale r4=new Razionale(444,5);
      Razionale r5=new Razionale(44,5);
      Razionale a[] = new Razionale[5];
      a[0]=r1;
      a[1]=r2;
      a[2]=r3;
      a[4]=r4;
      a[3]=r5;
      System.out.println(java.util.Arrays.toString(a));
      Array.selectionSort(a);
      System.out.println(java.util.Arrays.toString(a));
		System.out.println("2/3*5/7-4/5="+r1.mul(r2).sub(r3));
		*/
		char c = 'a';
		int x = (int) c;
		System.out.println(x);

		x++;
		char h= (char) x;
		System.out.println(h);
   }//main
}//Razionale
