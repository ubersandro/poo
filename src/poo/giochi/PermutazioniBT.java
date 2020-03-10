package poo.giochi;

import java.util.Arrays;

public class PermutazioniBT {
	int[] a;
	
	public PermutazioniBT(int n) {
		a= new int [n];
	}
	
	public void risolvi() {
		permuta(0);
	}

	private void permuta(int i) {
		for(int v=1;v<=a.length;v++) {
			if(assegnabile(i,v)){
				a[i]=v;
				if(i==a.length-1) System.out.println(Arrays.toString(a));
				else permuta(i+1);
			}
	}
		}
	
	private boolean assegnabile(int i, int v) {
		for(int j=0; j<=i;j++) {
			if(a[j]==v)return false;
		}
		return true; 
	}
	
	public static void main(String[] args) {
		PermutazioniBT p= new PermutazioniBT(3);
		p.risolvi();
	}
}

