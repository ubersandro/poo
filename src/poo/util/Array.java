package poo.util;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
/*
 * Classe di utilita' (non generica) ma che contiene metodi generici! Se la classe e' generica tutti  i metodi possono sfruttare la genericita'.
 */

@SuppressWarnings("unused")
public final class Array {  //non puoi progettare una classe che estenda questa qui ====>FINAL
    private Array() {
    } // costruttore

    public static int ricercaLineare(int[] a, int r) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == r) return i;
        }//for 
        return -1;
    }//ricercaLineare

    public static int ricercaLineare(double[] a, double r) {
        for (int j = 0; j < a.length; j++)//{
            if (Mat.quasiUguali(a[j], r)) return j;
        // }//for
        return -1;
    }//ricercaLineare_double

    public static <T extends Comparable<T>> int ricercaBinaria(Vector<T> v, T t) {
        return ricercaBinaria(v, t, 0, v.size() - 1);
    }


    public static <T extends Comparable<T>> int ricercaBinaria(Vector<T> v, T t, int i1, int i2) {
        int centro = (i1 + i2) / 2;
        T centrale = v.get(centro);
        if (t.equals(centrale)) return centro;
        if (i1 < i2) {
            if (centrale.compareTo(t) > 0) return ricercaBinaria(v, t, centro + 1, v.size() - 1);
            if (centrale.compareTo(t) < 0) return ricercaBinaria(v, t, 0, centro - 1);
        }
        return -1;
    }


    public static int ricercaBinaria(int[] a, int n) {
        return ricercaBinaria(a, n, 0, a.length - 1);
    }//ricercaBinaria

    private static int ricercaBinaria(int[] a, int n, int i1, int i2) {
        int centro = (i1 + i2) / 2;
        if (n == a[centro]) return centro;
        if (i1 < i2) {
            if (n > a[centro]) return ricercaBinaria(a, n, centro + 1, i2);
            if (n < a[centro]) return ricercaBinaria(a, n, i1, centro - 1);
        }//if
        return -1;
    }//ricercaBin

    public static int ricercaBinaria(double[] a, double n) {
        return ricercaBinaria(a, n, 0, a.length - 1);
    }//ricercaBinaria2r

    private static int ricercaBinaria(double[] a, double n, int i1, int i2) {
        int centro = (i1 + i2) / 2;
        if (Mat.quasiUguali(n, a[centro])) return centro;
        if (i1 <= i2) {
            if (n > a[centro]) return ricercaBinaria(a, n, centro + 1, i2);
            if (n < a[centro]) return ricercaBinaria(a, n, i1, centro - 1);
        }//if
        return -1;
    }//ricercaBinDouble

    public static int[] copia(int[] a) {
        int[] ret = new int[a.length];
        for (int i = 0; i < a.length; i++) ret[i] = a[i];
        return ret;
    }//copia

    public static void stampa(int[] a) {
        for (int i = 0; i < a.length; i++) System.out.println("" + a[i]);
    }//stampa

    public static void stampa(double[] a) {
        for (int i = 0; i < a.length; i++) System.out.println(String.format("%7.3f%n", a[i]));
    }//stampa

    public static double[] copia(double[] a) {
        double[] ret = new double[a.length];
        for (int i = 0; i < a.length; i++) ret[i] = a[i];
        return ret;
    }//copiaDOUBLE

    public static void bubbleSort(double[] a) {
        boolean scambio = true;
        int i;
        double t;
        int n = a.length - 2;
        while (scambio) {
            scambio = false;
            for (i = 0; i < n + 1; i++) {
                if (a[i] > a[i + 1]) {
                    t = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = t;
                    scambio = true;
                }//if
            }//for
            n -= 1;
        }//while
    }//bubbleSortDOUBLE

    public static void bubbleSort(int[] a) {
        boolean scambio = true;
        int i;
        int t;
        int n = a.length - 2;
        while (scambio) {
            scambio = false;
            for (i = 0; i < n + 1; i++) {
                if (a[i] > a[i + 1]) {
                    t = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = t;
                    scambio = true;
                }//if
            }//for
            n -= 1;
        }//while
    }//bubbleSortINT

    public static void insertionSort(double[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            //int j=i; per tenere conto dell'elemento al quale sono arrivato
            double x = a[i];
            for (int j = i; j > 0; j--) {
                if (a[j - 1] > x) {
                    a[j] = a[j - 1];
                }//if
                a[j] = x;
            }//for
        }//for
    }//insertionSort

    public static void insertionSort(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            //int j=i; per tenere conto dell'elemento al quale sono arrivato
            int x = a[i];
            for (int j = i; j > 0; j--) {
                if (a[j - 1] > x) {
                    a[j] = a[j - 1];
                }//if
                a[j] = x;
            }//for
        }//for
    }//insertionSort

    public static void selectionSort(int[] a) { //best
        int i = a.length - 1;
        while (i > 0) {
            int imax = 0;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[imax]) imax = j;
            }//for
            int t = a[i];
            a[i] = a[imax];
            a[imax] = t;
            i--;
        }//while
    }//selectionSort

    public static void selectionSort(Comparable[] v) { //grezzo perche' non uso tipi precisi
        for (int j = v.length - 1; j > 0; --j) { //0 perche' arrivo al singoletto
            int iMax = 0;
            for (int i = 1; i <= j; ++i)
                if (v[i].compareTo(v[iMax]) > 0) iMax = i;
            Comparable park = v[iMax]; // non la istanzio
            v[iMax] = v[j];
            v[j] = park;
        }//forExt
    }//selectionSort


    public static void selectionSort(double[] a) {
        int i = a.length - 1;
        while (i > 0) {
            int imax = 0;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[imax]) imax = j;
            }//for
            double t = a[i];
            a[i] = a[imax];
            a[imax] = t;
            i--;
        }//while
    }//selectionSort

    public static int prodottoScalare(int[] a, int[] b) {
        if (a.length != b.length) throw new IllegalArgumentException("Operandi non validi.");
        int ret = 0;
        for (int i = 0; i < a.length; i++) ret += (a[i] * b[i]);
        return ret;
    }//prodottoScalare

    public static int[] vettoreColonna(int[][] m, int i) {
        int[] ret = new int[m.length];
        for (int j = 0; j < m[0].length; j++) ret[j] = m[j][i];
        return ret;
    }//vettoreColonna

    public static void insertionSort(Object[] v, Comparator c) {//non fa scambi
        for (int i = 0; i < v.length; ++i) {
            Object x = v[i];
            int j = i;
            while (j > 0 && (c.compare(v[j - 1], x) > 0)) {
                v[j] = v[j - 1];
                j--;
            }//while
            v[j] = x;
        }//for
    }//insertionSort


    public static <T> void selectionSort(Vector v, Comparator c) {//come uso il comparator per confrontare oggetti del supertipo Vector???
        int i = v.size() - 1;
        while (i > 0) {
            int imax = i;
            Object max = null;
            for (int j = 0; j < v.size() - 1; j++) {
                if (c.compare(v.get(i), v.get(j)) < 0) {
                    imax = j;
                    max = v.get(j);
                }//if
            }//for
            v.remove(imax);
            v.add(imax, v.get(i));
            v.remove(i);
            v.add(i, max);
        }//while
    }//selectionSort

    /*
     * Throws RuntimeException if the class of the element cast doesn't extend Comparable.
     * Non diminuisco ius di uno volta per volta. Nel caos peggiore l'ultimo scambio lo faccio proprio in size-1. Quindi la condizione di arresto e' corretta.
     * Metodo generico in T. La scrittura � troppo restrittiva perche' impone che la classe T abbia la proprieta' del confronto.
     * ... extends Comparable<T> diventa Comparable<? super T> := qualunque super tipo di T puo' darmi il Comparable
     */
    public static <T extends Comparable<? super T>> void bubbleSort(Vector<T> v) {//bubbleSort ottimizzato
        boolean scambi = true; //pessimismo
        int ius = v.size() - 1;//indice ultimo scambio ==>furbissimo
        while (scambi) {
            scambi = false;
            int limite = ius;//servono tutti e due mannaia a dio, PER FAVORE
            for (int i = 1; i <= limite; ++i) { //parto da 1 perche' devo confrontare le coppie
                if ((v.get(i)).compareTo(v.get(i - 1)) < 0) { //questo casting non sempre funziona ((Comparable)v.get(i).compareTo(v.get(i-1))<0)
                    T park = v.get(i);
                    v.set(i, v.get(i - 1));
                    v.set(i - 1, park);
                    ius = i - 1; //qui ho fatto lo scambio
                    scambi = true; //ho fatto uno scambio amico mio
                }//if
            }//for
        }//while
    }//bubbleSort

    public static <T> void bubbleSort(Vector<T> v, Comparator<T> c) {//bubbleSort ottimizzato
        boolean scambi = true; //pessimismo
        int ius = v.size() - 1;//indice ultimo scambio ==>furbissimo
        while (scambi) {
            scambi = false;
            int limite = ius;//servono tutti e due mannaia a dio, PER FAVORE
            for (int i = 1; i <= limite; ++i) { //parto da 1 perche' devo confrontare le coppie
                if (c.compare(v.get(i), v.get(i - 1)) < 0) { //questo casting non sempre funziona ((Comparable)v.get(i).compareTo(v.get(i-1))<0)
                    T park = v.get(i);
                    v.set(i, v.get(i - 1));
                    v.set(i - 1, park);
                    ius = i - 1; //qui ho fatto lo scambio
                    scambi = true; //ho fatto uno scambio amico mio
                }//if
            }//for
        }//while
    }//bubbleSort

    /*
     * Sto istanziando una classe anonima
     */
    public static void main(String... diofrocio) {
        /*
        Vector<String> vs = new ArrayVector<>();
        vs.add("DIOCANE");
        vs.add("diocane");
        Array.bubbleSort(vs); //c'e' l'inferenza dei tipi
        //System.out.println(vs);
        Vector<String> vs1 = new ArrayVector<>();
        Array.bubbleSort(vs1, new Comparator<String>() {
            public int compare(String s1, String s2) {
                if (s1.length() < s2.length() || s1.length() == s2.length() && s1.compareTo(s2) < 0) return -1;
                if (s1.equals(s2)) return 0;
                return 1;
            }
        }); //classe anonima

        Array.bubbleSort(vs1, (String s1, String s2) -> { //sapendo che il tipo si puo anche infeirire
            if (s1.length() < s2.length() || s1.length() == s2.length() && s1.compareTo(s2) < 0) return -1;
            if (s1.equals(s2)) return 0;
            return 1;
        }); //LAMBDA EXPRESSION


        Array.bubbleSort(vs1, (s1, s2) -> { //sapendo che il tipo si puo anche infeirire
            if (s1.length() < s2.length() || s1.length() == s2.length() && s1.compareTo(s2) < 0) return -1;
            if (s1.equals(s2)) return 0;
            return 1;
        }); //LAMBDA EXPRESSION(senza dichiarazione dei tipi)
        System.out.println("_____________________________");
        LinkedList<Integer> list = new LinkedList<>();
        list.add(459);
        list.add(81);
        list.add(82);
        list.add(80);
        list.add(88);
        list.add(87);
        list.add(86);
        list.add(85);
        list.add(84);
        list.add(833);
        list.add(79);
        Array.bubbleSort(list);
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(list);
        List<Integer> l = Arrays.asList(a);
        System.out.println(l);
        System.out.println(l.getClass());*/
        Integer[] a = {4, 3, 2, 1, -1,-854,-98,-23418};
        Array.mergeSortIterativo(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }//main

    /*
     * Nell'ipotesi che l'array contenga numeri tra 0 e 100 ---> quando non ho questo vincolo
     * 														  devo usare un algoritmo classico
     * Il numero di operazioni e' proporzionale ad n ---> O(n)
     * Non posso trascuare la lettura ( n letture)
     */
    public static void bucketSort(int[] v) {
        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            int x = sc.nextInt();
            if (x < 0 || x > 100) break;
            v[x]++;
        }//for
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j < v[i]; j++)
                System.out.println(i);
        }//stampa la sequenza ordinata
        sc.close();
    }//bucketSort


    public static <T extends Comparable<? super T>> void bubbleSort(LinkedList<T> l) {
        boolean scambi = true;
        boolean primo = true;
        T current = null;
        while (scambi) {
            scambi = false; //nel caso migliore (gia' ordinata) rimarra' false
            ListIterator<T> lit = l.listIterator();//accendo un iteratore che parte da -1
            if (primo && lit.hasNext()) current = lit.next();
            while (lit.hasNext()) {
                T next = lit.next();
                if (current.compareTo(next) > 0) {// 2next
                    lit.remove();
                    lit.previous();
                    lit.add(next); // a questo punto lit punta dopo l'elemento minore tra i due e sono pronto per fare un eventuale nuovo scambio
                    scambi = true;
                } else current = next;
            }//whileHasNext
        }//whileScambi
    }//bubbleSortIterator

    public static int max(int[] a) {
        return max(a, 0, a.length - 1);
    }

    private static int max(int[] a, int inf, int sup) {
        if (inf == sup) return a[inf];
        int med = inf + sup / 2;
        int m1 = max(a, inf, med);
        int m2 = max(a, med + 1, sup);
        if (m2 > m1) return m2;
        return m1;
    }

    /*public static <T extends Comparable<? super T>> T max(T[] a) {
        return null;
    }*/

    public static <T extends Comparable<? super T>> void mergeSort(T[] v) {
        mergeSort(v, 0, v.length - 1);
    }//mergeSort

    //Da notare che il metodo non tocca gli elementi fuori dagli estremi
    private static <T extends Comparable<? super T>> void mergeSort(T[] v, int inf, int sup) {

        if (inf < sup) {//se sono uguali non faccio nulla
            int med = inf + sup / 2;
            mergeSort(v, inf, med);
            mergeSort(v, med + 1, sup);
            merge(v, inf, med, sup);//qui avviene la fusione amico mio
        }//if
    }//mergeSort private

    /*
    Questo metodo e' pericoloso perche' si corre il rischio di perdere elementi==> Array di appoggio
    Quando arrivo ai singoletti torno su ==> Albero binario
     */

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> void merge(T[] v, int inf, int med, int sup) {
        T[] aux = (T[]) new Comparable[sup - inf + 1];//occhio al +1
        int i = inf;//i si muove sulla prima parte
        int j = med + 1;//j si muove sulla seconda parte
        int k = 0;//indice su aux
        while (i <= med && j <= sup) {
            if (v[i].compareTo(v[j]) < 0) {
                aux[k] = v[i];
                i++;
            }//if
            else {
                aux[k] = v[j];
                j++;
            }//else
            k++; //e' un'operazione comune a entrambi
        }//while
        //processiamo cio' che rimane ==> gestione residuo
        while (i <= med) {
            aux[k] = v[i];
            i++;
            k++;
        }
        while (j <= med) {
            aux[k] = v[j];
            j++;
            k++;
        }
        //riversiamo il contenuto di aux su v[inf...sup]
        for (k = 0; k < aux.length; ++k) {
            v[k + inf] = aux[k];
        }//for
    }//merge
    //elevata complessita' spaziale ==> VERSIONE ITERATIVA

    public static <T extends Comparable<? super T>> void mergeSortIterativo(T[] v, int inf, int sup) {
        class AreaDati {
            int inf, sup, med;
            AreaDati(int i, int s, int m) {
                inf = i;
                sup = s;
                med = m;
            }//builder
        }//AreaDati
        Stack<AreaDati> s = new StackConcatenato<>();
        s.push(new AreaDati(inf, sup, -1));
        while (!s.isEmpty()) {
            AreaDati a = s.pop();
            if (a.med != -1) merge(v, a.inf, a.med, a.sup);
            else if (a.inf < a.sup) {
                    int med = (a.inf + a.sup) / 2;
                    s.push(new AreaDati(a.inf, a.sup, med));//area del merge
                    s.push(new AreaDati(med + 1, a.sup, -1));
                    s.push(new AreaDati(a.inf, med, -1));
            }//if
        }//while
    }//mergeIter

}//Array