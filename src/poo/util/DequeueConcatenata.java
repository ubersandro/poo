package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeueConcatenata<T> extends DequeueAstratta<T>  {

    private static class Nodo<E>{
        E info;
        Nodo<E> next;
    }//Nodo
    private int size;
    private Nodo<T> first,last;

    @Override
    public boolean isEmpty(){ return size==0;}

    @Override
    public void clear(){
        first=last=null; size=0;
    }//clear

    @Override
    public void addFirst(T x) {
        Nodo<T> n= new Nodo<>();
        n.info=x; //e' necessario n.next=null?
        if(first==null) last=n;
        else n.next=first;
        first=n;
        size++;
    }//addFirst

    @Override
    public void addLast(T x) {
        Nodo<T> n=new Nodo<>();
        n.info=x;

        if(last==null) first=n;
        else last.next=n;
        last=n;
        size++;
    }//addLast

    @Override
    public void removeFirst(){
        if(first==null) throw new NoSuchElementException();
        if(first==last) first=last=null;
        else first=first.next;
        size--;
    }//removeFirst

    @Override
    public T getFirst() {
        if(first==null) throw new NoSuchElementException("La lista e' vuota.");
        return first.info;
    }//getFirst

    public T getLast(){
        if(last==null)throw new NoSuchElementException();
        return last.info;
    }//getLast
    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }//iterator

    private class DequeIterator implements Iterator<T>{
        private Nodo<T> pre,cor;

        @Override
        public boolean hasNext() {
            if(cor==null)return first!=null;
            return cor.next!=null;
        }//hasNext

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();
            if(cor==null)cor=first;
            else{
                pre=cor; cor=cor.next;
            }
            return cor.info;
        }//next

        @Override
        public void remove() {
            if(pre==cor) throw new IllegalStateException();
            if(pre!=null) {
                pre.next = cor.next;
                if (cor == last)
                    last = pre; //preferibile fare come fa lui
            }
            else{
                first=first.next;
                if(first==null)last=null;
            }

            cor=pre;
            size--;
        }//remove
    }
    public static void main(String[] args) {
		
	}
}//DequeConcatenata
