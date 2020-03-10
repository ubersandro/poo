package poo.recursion;

import poo.util.CollezioneOrdinata;
import poo.util.List;

import java.util.Iterator;
import java.util.Scanner;

/*
    non mi aspetto di poter trasformare tutti i metodi in metodi ricorsivi. Ricorda che la lista e' ordinata.
 */
public class ListaRec <T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private static class Lista<E>{
        E info;
        Lista<E> next;
    }//Lista
    private Lista<T> lista;

    public int size(){
        return size(lista);
    }//size
    private int size(Lista<T> lista){
        if(lista==null)return 0;
        return 1+size(lista.next);
    }//privateSize

    public boolean contains(T e){
        return contains(lista, e);
    }//contains

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    /*La lista lineare si presta solo a fare ricerca lineare.
     Prima di tutto studiamo il caso in cui e' vuota. Ci sono tre casi di uscita.*/
    private boolean contains(Lista<T>lista, T e){
        if(lista==null)return false;
        if(lista.info.equals(e))return true;
        if(lista.info.compareTo(e)>0)return false;
        return contains(lista.next,e);
    }//contains

    public T get(T e){return get(lista,e);}

    @Override
    public void clear() {

    }

    private  T get(Lista<T>lista, T e){
        if(lista==null)return null;
        if(lista.info.equals(e))return lista.info;
        if(lista.info.compareTo(e)>0)return null;
        return get(lista.next,e);
    }//get

    public String toString(){
        StringBuilder sb= new StringBuilder(300);
        sb.append('[');
        toString(lista,sb);
        sb.append(']');
        return sb.toString();
    }//toString

    private void toString(Lista<T> lista, StringBuilder sb){
        if(lista==null)return;
        sb.append(lista.info);
        if(lista.next==null)sb.append(", ");//se esiste un residuo
        toString(lista.next, sb);
    }//priv_toString

    public boolean equals(Object x){
        if(!(x instanceof ListaRec))return false;
        if(x==this) return true;
        @SuppressWarnings("unchecked")
		ListaRec<T> l=(ListaRec<T>)x;
        return equals(lista,l.lista);
    }//equals
    //attenzione: i parametri sono di tipo Lista<T> perche' sono nodi, non ListaRec<T>.
    private boolean equals(Lista<T> l1, Lista<T> l2){
        if(l1==null&&l2==null)return true;
        if(l1==null||l2==null)return false;
        //se uso la size() [ricorsiva] diventa pesante per come e' scritta
        if(!(l1.info.equals(l2.info))) return false;
        return equals(l1.next,l2.next);
    }//equals

    public int hashCode(){
        return hashCode( lista);
    }//hashCode
    private int hashCode(Lista<T>lista){
        if(lista==null) return 0;
        return lista.info.hashCode()+83*hashCode(lista.next);
    }//privateHashCode
    /*puo' cambiare anche la testa nel fare la add*/
    public void add(T e){
        lista=add(lista, e);//restituisce una NUOVA lista(la lista cambia).
    }//add
    private Lista<T> add(Lista<T> lista, T e){
        if(lista==null){
            Lista<T> n= new Lista<>();
            //lista=new Lista<>();
            n.info=e; n.next=null; //anche n.next=lista; ==> sempre null
            return n;
        }//lista==null
        //prima del primo
        if(lista.info.compareTo(e)>=0){
            Lista<T> n= new Lista<>(); n.info=e;
            n.next=lista;
            return n;
        }//if
        lista.next=add(lista.next,e);
        return lista;
    }//add

    public void remove(T e){
        lista=remove(lista, e);
    }//remove
    private Lista<T> remove(Lista<T> lista, T e) {
        if(lista==null) return lista; //e' gia' rimosso
        //ci mettiamo sul primo el(puo' anche essere la testa di una lista residua)
        if(lista.info.equals(e)) return lista.next;
        if(lista.info.compareTo(e)>0) return lista;
        lista.next=remove(lista.next,e);
        return lista;//siamo sicuri che il puntatore di testa non cambia
    }//removePrivate

    public static void main(String...ciao){
        Scanner sc=new Scanner(System.in);
        ListaRec<Integer> l=new ListaRec<>();
        for(;;){
            System.out.print("int (0 per terminare)> ");
            int x=sc.nextInt();
            if(x==0)break;
            l.add(x);
        }
        System.out.println(l);
        System.out.println("x=");
        int x=sc.nextInt();
        l.remove(x);
        sc.close();
        System.out.println(l);
    }//main
}//ListaRec


//TODO usala in esercizio add numeri inseriti da tastiera
