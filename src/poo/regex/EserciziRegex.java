package poo.regex;

import java.util.Scanner;

public class EserciziRegex {
    static boolean pariDispari(String expr){
        String PARI_DISPARI="(a{2})*(b{2})*b[c]*";
        return expr.matches(PARI_DISPARI);
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        for(;;){
            System.out.println(pariDispari(sc.nextLine()));
        }
    }
}
