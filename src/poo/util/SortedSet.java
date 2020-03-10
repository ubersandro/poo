package poo.util;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortedSet<T extends Comparable<? super T>> {
    private Comparator<? super T> comp;
    private LinkedList<T> s;
    public SortedSet(Comparator<? super T> c){
        this.comp=c;
        s=new LinkedList<>();
    }
    public void setComparatorAndSort(Comparator<? super T> c){
        this.comp=c;
        heapSort(comp);
    }
    public void add(T e){
        s.addLast(e);
    }
    public String toString(){return s.toString(); }
    private void heapSort(Comparator <? super T> c){
        PriorityQueue<T> p= new PriorityQueue<>(c);
        Iterator<T> it;
        for(it=iterator();it.hasNext();) p.add(it.next());
        System.out.println("_________");
        System.out.println(p);
        System.out.println(s);
        System.out.println("_________");
        LinkedList<T> n=new LinkedList<>();
        for(it=p.iterator(); it.hasNext();)n.addLast(it.next());
        s=n;
    }

    public Iterator<T>iterator(){return s.iterator();}

    public static void leggiFile(String nomeFile, SortedSet<String> s) throws IOException{
       // BufferedReader br= new BufferedReader(new FileReader(new File(nomeFile)));
        Scanner sc= new Scanner(new File(nomeFile));
        sc.useDelimiter(" ,.");
        StringTokenizer st;
        String linea=sc.nextLine();
        while(linea!=null){
            st=new StringTokenizer(linea, " .,");
            while(st.hasMoreTokens())s.add(st.nextToken());
        }
    }
    public static void main(String[] args) throws IOException {
        SortedSet<String> s= new SortedSet<>((i,j)->{
            return i.compareTo(j);
        });
        s.add("ciao");
        s.add("anna");
        System.out.println(s);
        s.setComparatorAndSort((i,j)->{return -i.compareTo(j);});
        System.out.println(s);
        leggiFile("c:\\prova", s);
    }
}
