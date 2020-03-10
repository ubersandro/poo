package poo.recursion;
import poo.util.Stack;
import poo.util.StackConcatenato;

import java.awt.geom.Area;
import java.util.Arrays;
import java.util.List;


public class MergeSortTest {
	public static <T extends Comparable<? super T>> void mergeSortIterativo(T[] a){
		class AreaDati{
			T[] a; int inf,sup;
			AreaDati(T[] a, int inf, int sup){
				this.a=a; this.inf=inf; this.sup=sup;
			}//AreaDati
		}//AreaDati
		poo.util.Stack<AreaDati> s= new poo.util.StackConcatenato<>();
		s.push(new AreaDati(a,0,a.length-1));
		while(!s.isEmpty()){
			AreaDati ad= s.pop();
			if(ad.inf==-1){
				AreaDati ad2= s.pop(),ad1=s.pop();
				merge(ad1.inf,ad1.sup,ad2.sup,a);
			}
			if(ad.inf<ad.sup){
				int med= (ad.sup+ad.inf)/2;
				s.push(new AreaDati(a,-1,-1));
				s.push(new AreaDati(a,med+1,ad.sup));
				s.push(new AreaDati(a,ad.inf, med));
			}
		}

	}//mergeSortIterativo
	 static <T extends Comparable<? super T>> void merge(int inf,int med,int sup, T[] a) {
		@SuppressWarnings("unchecked")
		T[] aux= (T[]) new Comparable[sup-inf+1];
		int i=inf, j=med+1,k=0;
		while(i<med&&j<sup) {
			if(a[i].compareTo(a[j])>0) {aux[k]=a[i]; i++;k++;}
			else {aux[k]=a[j]; k++;j++;}
			while(i<=med) {
				aux[k]=a[i]; i++; k++;
			}
			while(j<=sup) {
				aux[k]=a[j]; j++;k++; 
			}
			for(k=0; k<aux.length;k++)a[inf+k]=aux[k]; 
		}
	}
	public static void main(String[] args) {
		Integer[] a= {43,523,2332,1,4,2,1,0,-4};
		MergeSortTest.mergeSortIterativo(a);
		System.out.println(Arrays.toString(a));

				
	}//main

}//MergeSortIter
