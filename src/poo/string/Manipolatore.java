package poo.string;

import poo.razionali.Razionale;
import poo.util.Array;

public class Manipolatore {

	public static void main(String[] args) {
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
      
	}

}
