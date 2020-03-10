package poo.sistema;
import java.util.Arrays;

import poo.util.Matrix;


public class Cramer extends Sistema{
	protected double [][]a;
	/**
	 * @param m
	 * @param y
	 */
	public Cramer(double m[][], double [] y) {
		super(m,y);
		double[][] copia=new double[getN()][getN()+1]; //matrice completa del sistema 
		for(int i=0; i<m.length;i++) {
			System.arraycopy(m[i], 0, copia[i], 0, getN());
			copia[i][m.length]=y[i];
		}//for 
		a=copia;
	}//costruttore
	
	public double[] risolvi() {
		double det =Matrix.determinante(quadrataNoti(0, false)); //determinante della mia matrice
		if(det<=0) throw new RuntimeException("Sistema singolare!");
		double[] determinanti= determinanti();//singoli determinanti i-esimi della matrice con il vettore dei termini noti in luogo della colonna i-esima
		return risultato(determinanti,det);
 	}//risolvi
	
	private double[] determinanti() {
		double[] determinanti= new double[getN()];
		for(int i=0; i<getN();i++) {
				determinanti[i]= Matrix.determinante(quadrataNoti(i,true));
		}//for
		return determinanti;
	}//determinanti()

	private double[][] quadrataNoti(int j, boolean noti){	//VARARG
		double[][] quadrata = new double[getN()][getN()];
		for(int i=0; i<a.length;i++) {
			System.arraycopy(a[i], 0, quadrata[i], 0, a.length);
			if(noti)quadrata[i][j]=a[i][a.length];
		}//for
		return quadrata;
	}//quadrata
	
	
	private double[] risultato(double[] determinanti, double det) {
		double[] y= new double[getN()];
		
		System.out.printf("Il determinante della tua matrice è: "+String.format("%3.2f",det)+"\n");
		for(int i=0; i<getN(); i++) {
			y[i]=determinanti[i]/det;
			
		}//for
		return y;
	}//risultato
	
	public String toString() {
		StringBuilder sb= new StringBuilder(500);
		for(int i=0; i<a.length; i++) {
			sb.append('|');
			for(int j=0; j<a.length+1;j++) {
				sb.append(String.format("%3.2f", a[i][j]));
				sb.append(' ');
			}//for colonne
			sb.append('|');	
			sb.append('\n');
		}//for righe
		return sb.toString();
	}//toString
	
	
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String ... args) {
		double[][] a= {{1,0,0},{0,1,0},{0,0,2}};
		/*for(int i=0; i<a.length;i++) 
			for(int j=0; j<a.length; j++) {
				if(i==j)a[i][j]=1;
				a[i][j]=0;
			}//for
		*/	
		double[] x= new double[a.length];
		x[0]=0;
		x[1]=0;
		x[2]=0;
		Cramer c= new Cramer(a,x);
		System.out.println(c);
		System.out.println("____________________________");
		System.out.println(Arrays.toString(c.risolvi()));
	}//main
}//Cramer //TODO metodo quadrataNoti() con vararg, provare a mettere toString() nella classe astratta Sistema, metti condizione non singolarità numero soluzioni
