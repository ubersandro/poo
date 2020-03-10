package poo.esempi;

public class Vararg {
        public static void main(String[] args) {
            stampaLista(1,2,3,4,7);
        }

        private static void stampaLista(int ... x) {
            for(int i=0; i<x.length; i++) System.out.printf("%d\n", x[i]);
        }//stampaLista
}
