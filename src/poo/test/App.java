package poo.test;
import java.util.Scanner;
public class App{
    public static void main(String... args){ // si chiamano argomenti del programma ==> converte in stringhe
        int[] v=null;
        if(args.length>0){
            v=new int[args.length];
            for(int i=0;i<v.length;i++){
            v[i]= Integer.parseInt(args[i]);//args[i] è un array 
            }
        }//if
        else{
            Scanner sc= new Scanner(System.in);
            System.out.print("n=");
            int n=sc.nextInt();
            v=new int[n];
            System.out.println("Fornisci "+n+" interi.");
            for(int i=0;i<v.length;++i) v[i]=sc.nextInt();
            sc.close();
        }//else
        System.out.println(java.util.Arrays.toString(v));
}

}//App