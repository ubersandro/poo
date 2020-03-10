package poo.util;


import java.util.HashMap;

public final class Mat{
	private static HashMap<Integer,Integer> mappa= new HashMap<>();
	private Mat(){}//questo rende la classe non istanziabile
	public static double EPSILON= 1.0E-10;
	public enum Soluzione {ITERATIVO, RICORSIVO}
	
	public static int mcd(int x, int y){
		if(x<=0||y<=0) throw new IllegalArgumentException("Numeri non positivi.");
		return mcd_Euclide(x,y);
	}//mcd

	private static int mcd_Euclide(int x, int y){
		if(y==0) return x;
		return mcd_Euclide(y, x%y);
	}//mcd_Euclide
	
	public static int mcm(int x, int y){		
		if(x<=0||y<=0) throw new IllegalArgumentException("Numeri non positivi.");		
		return (x*y)/mcd_Euclide(x,y);
	}//mcm


	public static boolean quasiUguali(double x, double y){
		return Math.abs(x-y)<=EPSILON;
	}//quasiUguali
	
	public static void main(String[] args){	
		double n1 =0.00000000000001;
		double n2 =0.000000000000001;
		System.out.println(quasiUguali(n1,n2));
		System.out.println(EPSILON);
		System.out.println(Soluzione.ITERATIVO);
		//mappa.put(2,3);
		System.out.println(mappa);
		int i,j;
		for(j=0; j<3; j++)
			for(i=(j==1)?0:1; i<4; i=(j%2==0)? i+1:i+2){
				System.out.println("<"+i+","+j+">");
			}
}//main
		
}//Mat	