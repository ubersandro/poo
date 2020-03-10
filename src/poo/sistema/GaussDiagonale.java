package poo.sistema;

import poo.util.Mat;

public class GaussDiagonale extends Gauss {

	public GaussDiagonale(double[][] a, double[] y) {
		super(a, y);
		}
	
	@Override
	protected void triangolazione() {
		int n=this.getN();
		for( int j=0; j<n; j++ ){
			if( Mat.quasiUguali(a[j][j],0D) ){
				int p=j+1;
				for( ; p<n; p++ )
					if( !Mat.quasiUguali(a[p][j],0D) ) break;
				if( p==n ) throw new RuntimeException("Sistema singolare");
				//scambia riga p con riga j
				double[] tmp=a[j];
				a[j]=a[p]; a[p]=tmp;
			}
			//azzera elementi sulla colonna j, dalla riga (j+1)-esima all'ultima
			for( int i=j+1; i<n; i++ ){
				if( !Mat.quasiUguali(a[i][j],0D) ){
					double coeff=a[i][j]/a[j][j];
					//sottrai dalla riga i-esima la riga j-esima moltip per coeff
					for( int k=j; k<n+1; k++ )
						a[i][k] -= a[j][k]*coeff;
				}
			}//for sugli elementi delle righe sottostante
			for(int i=j-1;i>0;i--) {
				if(!Mat.quasiUguali(a[i][j], 0D)) {
					double c=a[i][j]/a[j][j];
					for(int h=j;h<n+1;h++)
						a[i][h]-=a[j][h]*c;
				}//if
			}
		}//for esterno su j
		
		for(int i=0;i<=n;i++) {
			a[i][i]/=a[i][i]; a[i][n]/=a[i][i];
		}
	
	
	
	}//triangolarizza()

}
