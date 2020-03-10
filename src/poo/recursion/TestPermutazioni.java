package poo.recursion;

import java.util.Arrays;

public class TestPermutazioni {
    public static void main(String[] args) {

        int[] a= new int[]{1, 2, 3};
        permuta(a,0);
    }

    static void permuta(int[] a, int i){
        if(i==a.length-1)System.out.println(Arrays.toString(a));
        else{
            for(int j=i; j<a.length; j++){
                int park= a[i]; a[i]=a[j]; a[j]=park;
                permuta(a,i+1);
                park=a[i]; a[i]=a[j]; a[j]=park;
            }
        }//else
    }
}
