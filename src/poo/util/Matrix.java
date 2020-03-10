package poo.util;

public final class Matrix{
	private Matrix(){}

	public static int[][] add(int[][] m1,int[][] m2){
		return null; //TODO
	}//add

	public static int [][] sub(int [][] m1,int [][] m2){
		return null; //TODO
	}//sub

	public static double[][] add(double[][] m1,double[][] m2){
		return null; //TODO
	}//add

	public static double[][] sub( double[][] m1, double[][] m2){
		return null; //TODO
	}//sub

	public static int [][] trasposta(int [][]m){
		return null; //TODO
	}//trasposta

	public static double[][] trasposta(double [][]m){
		return null; //TODO
	}//trasposta

	public static int[][] moltiplica( int[][]a, int[][]b ){
		return null; //TODO
	}//moltiplica

	public static double[][] moltiplica( double[][]a, double[][]b ){
		return null; //TODO
	}//moltiplica

	public static double[][] minore( double[][] a, int i, int j ){
		//ritorna il minore che si ottiene da a rimuovendo la riga i e la colonna j
		return null; //TODO
	}//minore

	public static double determinante( double[][] m ){
		for( int i=0; i<m.length; ++i )
			if( m[i].length!=m.length )
				throw new IllegalArgumentException("Matrice non quadrata.");
		int n=m.length;
		//matrice locale
		double[][] a=new double[n][n];
		//copia m su a
		for( int i=0; i<n; ++i )
			System.arraycopy( m[i], 0, a[i], 0, m.length );
		int scambi=0;
		for( int j=0; j<n; ++j ){
			if( Mat.quasiUguali(a[j][j],0D) ){
				//pivoting
				int p=j+1;
				for(; p<n; ++p)
					if( !Mat.quasiUguali(a[p][j],0D) ) break;
				if( p==n ) return 0D;
				//scambia riga p con riga j
				double[] tmp=a[j]; a[j]=a[p]; a[p]=tmp;
				scambi++;
			}
			//azzeramento
			for( int i=j+1; i<n; ++i ){
				if( !Mat.quasiUguali(a[i][j],0D) ){
					double c=a[i][j]/a[j][j];
					for( int k=j; k<n; ++k )
						a[i][k]=a[i][k]-a[j][k]*c;
				}
			}
		}
		//produttoria elementi diagonali
		double d=1D;
		for( int i=0; i<n; ++i )
			d=d*a[i][i];
		if( scambi%2!=0 ) d=(-1)*d;
		return d;
	}//determinante

	public static void scrivi( double[][] a ){
		for( int i=0; i<a.length; ++i ){
			for( int j=0; j<a[i].length; ++j )
				System.out.printf("%8.2f",a[i][j]);
			System.out.println();
		}
	}//scrivi

	public static void main(String[]args){

		double[][] a={{1,3,5},{2,4,1},{1,0,2}};
		double det=determinante(a);
		System.out.printf("det=%1.2f%n",det);

	}//main

}//Matrix