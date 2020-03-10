package poo.test;
import java.util.*;

public class SequenzaMaxAlt {
    public static void main(String[] args){
        int[] a={1,2,4,-3,-5,6,-7,8,-9,2,-4};
        System.out.println(Arrays.toString(sequenzaAlternata(a)));
    }

    public static int[] sequenzaAlternata(int[] a){
        if(a.length==1) return Arrays.copyOf(a,1);//se a ha un solo elemento
        int i=0, f=1,primo=i;//indici di inizio e fine della sottosequenza
        int k=0;//indice per scorrere l'array
        int inizioMax=i, fineMax=f, lunghezzaMax=1;
        while(k<a.length-1){
            if(a[i]*a[f]<0){
                if((f-primo+1)>lunghezzaMax){
                    fineMax=f; inizioMax=primo; lunghezzaMax=fineMax-inizioMax+1;
                }
                f++; i++;
            }
            else{
                i=f; f++; primo=i;
            }
            k++;
        }
        int[] ret=new int[lunghezzaMax];
        for(int j=inizioMax, x=0; j<=fineMax;j++, x++){
            ret[x]=a[j];
        }
        return ret;
    }
}//SequenzaMaxAlt
