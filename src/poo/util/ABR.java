package poo.util;


import java.util.*;
import java.util.LinkedList;
import java.util.List;

/*
Visita in ordine di un albero binario = restituisce in ordine tutti gli elementi dell'albero
 */
public class ABR<T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
    private Nodo<T> radice = null;
    private int modCounter; 

    
    public static void main(String[] args) {

        ABR<Integer> b = new ABR<>();
        b.add(3);
        b.add(2);
        b.add(4);
        b.visitaPerLivelli();
        Integer[] l = new Integer[b.size()];
        b.visitaPerLivelli(l);
        System.out.println(Arrays.toString(l));

        ABR<Integer> c = new ABR<>();
        c.addBilanciato(1);
        //c.addBilanciato(2);
        c.addBilanciato(2);
        c.addBilanciato(3);
        c.addBilanciato(4);
        c.addBilanciato(5);
        c.addBilanciato(6);
        c.addBilanciato(4);
        c.addBilanciato(8);
        c.addBilanciato(-120);
        c.addBilanciato(-32);
        c.addBilanciato(-180);
        c.removeBilanciato(3);
        c.removeBilanciato(2);
        System.out.println(c.eBilanciato());
        System.out.println(c);
        c.visitaPerLivelli();
        System.out.println(c.altezza());

        ABR<String> a = new ABR<>();
        a.addIter("ciao");
        a.addIter("come");
        a.addIter("stai");
        //a.addIter("no");

        ABR<String> d = new ABR<>();
        d.add("ciao");
        d.add("come");
        d.add("stai");

        System.out.println(b.equals(c));
        System.out.println(a.equals(d));

        ABR<Integer> p = new ABR<>();
        p.addBilanciato(23);
        p.addBilanciato(3);
        p.addBilanciato(2);
        p.addBilanciato(180);
        p.addBilanciato(21);
        System.out.println(p.eBilanciato());
        Iterator<Integer> it = p.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
            //System.out.println(p.eBilanciato());
            //p.remove(21);//Concurrent modification
            //System.out.println("MODIFICA CONCORRENTE");
        }
        System.out.println(p+" VUOTO");
        
    }

    @Override
    public int size() {
        return size(radice);
    }//size

    private int size(Nodo<T> radice) {
        if (radice == null) return 0;
        return 1 + size(radice.fS) + size(radice.fD);
    }//size

    public boolean eBilanciato() {
        return eBilanciato(radice);
    }

    private  boolean eBilanciato(Nodo<T> radice) {
        if (radice == null) return true;

        if (Math.abs(size(radice.fS) - size(radice.fD)) > 1) return false;

        return eBilanciato(radice.fS) && eBilanciato(radice.fD);
    }//eBilanciato


    public void addBilanciato(T e) {
        addIter(e);
        bilancia(radice);
       
    }

    public void removeBilanciato(T e) {
        remove(e);
        bilancia(radice);
        
    }

    private void bilancia(Nodo<T> radice) {//TODO verifica nullit� radice
        if (!eBilanciato(radice)) {
            List<T> l = new ArrayList<>();
            visitaSimmetrica(radice, l);
            boolean primo = true;
            radice.fS = radice.fD = null;
            binaryAdd(radice, l, 0, l.size() - 1, primo);
        }
        if (radice != null) {
            if (radice.fS != null) bilancia(radice.fS);
            if (radice.fD != null) bilancia(radice.fD);
        }
    }//bilancia

    private void binaryAdd(Nodo<T> radice, List<T> l, int inf, int sup, boolean primo) {
        if (inf <= sup) {
            int med = (inf + sup) / 2;
            if (primo) {
                primo = false;
                radice.info = l.get(med);
            } else {
                radice = add(radice, l.get(med));
            }
            binaryAdd(radice, l, inf, med - 1, primo);
            binaryAdd(radice, l, med + 1, sup, primo);
        }


    }//binaryAdd

    @Override
    public boolean contains(T e) {
        return contains(radice, e);
    }//contains


    private boolean contains(Nodo<T> radice, T e) {
        if (radice == null) return false;
        if (radice.info.equals(e)) return true;
        if (radice.info.compareTo(e) > 0) return contains(radice.fS, e);
        return contains(radice.fD, e);
    }//contains

    public void visitaSimmetrica() {
        visitaSimmetrica(radice);
    }//vistaSimmetrica

    private void visitaSimmetrica(Nodo<T> radice) {
        if (radice != null) {
            visitaSimmetrica(radice.fS);
            System.out.println(radice.info + " ");
            visitaSimmetrica(radice.fD);
        }//radice
    }//visitaSimmetricaPriv

    public void visitaSimmetrica(List<T> l) {
        visitaSimmetrica(radice, l);
    }//vistaSimmetrica

    private void visitaSimmetrica(Nodo<T> radice, List<T> l) {
        if (radice != null) {
            visitaSimmetrica(radice.fS, l);
            l.add(radice.info);
            visitaSimmetrica(radice.fD, l);
        }//radice
    }//visitaSimmetricaPriv

    @Override
    public boolean isEmpty() {
        return radice == null;
    }//isEmpty

    @Override
    public boolean isFull() {
        return false;
    }//ifFull

    @Override
    public T get(T e) {
        return get(radice, e);
    }//get

    private T get(Nodo<T> radice, T e) {
        if (radice == null) return null;
        if (radice.info.equals(e)) return radice.info;
        if (radice.info.compareTo(e) > 0) return get(radice.fS, e);
        return get(radice.fD, e);
    }//getPrivate


    public boolean equals(Object x) {
        if (!(x instanceof ABR)) return false;
        if (x == this) return true;
        @SuppressWarnings("unchecked")
        ABR<T> a = (ABR<T>) x;
        if (this.size() != a.size()) return false;
        return equals(radice, a.radice);
    }

    private boolean equals(Nodo<T> radice1, Nodo<T> radice2) {//TODO verifica tipi raw
        if (radice1 == null) return radice2 == null;
        if (radice2 == null) return radice1 == null;
        return radice1.info.equals(radice2.info) &&
                equals(radice1.fS, radice2.fS) &&
                equals(radice1.fD, radice2.fD);
    }

    @Override
    public void clear() {
        radice = null;
        modCounter++;
    }//clear

    //l'aggiunta va fatta sempre su un sottoalbero vuoto
    @Override
    public void add(T e) {
        radice = add(radice, e);
        modCounter++;
    }//add

    private Nodo<T> add(Nodo<T> radice, T e) {
        if (radice == null) {
            Nodo<T> n = new Nodo<T>();
            n.info = e;
            n.fS = null;
            n.fD = null;
            return n;
        }//if
        //radice e' il parametro del metodo, non il campo
        if (radice.info.compareTo(e) >= 0) {
            radice.fS = add(radice.fS, e);
            return radice;
        }//if
        radice.fD = add(radice.fD, e);
        return radice;
    }//add

    /*
    Per scrivere in veste iterativa la add bisogna conoscere sempre l'ultimo movimento fatto
     */
    public void addIter(T e) {
    	modCounter++;
        Nodo<T> padre = null, figlio = radice;
        while (figlio != null) {
            if (figlio.info.compareTo(e) > 0) { //HERE
                padre = figlio;
                figlio = figlio.fS;
                //flag = true; //si va a sinistra
            }//if
            else {
                padre = figlio;
                figlio = figlio.fD;
                // flag = false;
            }//else
        }//while
        Nodo<T> n = new Nodo<>();
        n.info = e;
        n.fS = null;
        n.fD = null;
        //se padre = null sei partito da un albero vuoto
        if (padre == null) radice = n; //radice e' quella dell'albero fratm
        else {
            if (e.compareTo(padre.info) <= 0) padre.fS = n;
            else padre.fD = n;
        }//else
    }//addIter


    @Override
    public void remove(T e) {
        radice = remove(radice, e); //la radice sta cambiando
        modCounter++;
    }//remove

    private Nodo<T> remove(Nodo<T> radice, T e) {
        if (radice == null) return null;//possiamo anche restituire radice
        if (radice.info.compareTo(e) > 0) {
            radice.fS = remove(radice.fS, e);
            return radice;
        }//if
        if (radice.info.compareTo(e) < 0) {
            radice.fD = remove(radice.fD, e);
            return radice;
        }//if
        //l'albero non e'vuoto, ho trovato l'elemento
        if (radice.fS == null && radice.fD == null) return null; //foglia
        if (radice.fS == null) return radice.fD;
        if (radice.fD == null) return radice.fS;
        //se ci sono entrambi i figli andiamo sul sottoalbero destro
        if (radice.fD.fS == null) {
            //promozione
            radice.info = radice.fD.info;
            radice.fD = radice.fD.fD;
            return radice;
        }//3° caso
        //4° caso (trova vittima come minimo del sottoalbero dx)
        Nodo<T> padre = radice.fD, figlio = radice.fD.fS;
        while (figlio.fS != null) {
            padre = figlio;
            figlio = figlio.fS;
        }//while
        //promozione dopo aver trovato l'el
        radice.info = figlio.info;
        padre.fS = figlio.fD;//bypass
        return radice;//rimozione logica non fisica
    }//removePrivate


    public String toString() {

        StringBuilder sb = new StringBuilder(200);
        sb.append('[');
        toString(sb, radice);
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append(']');
        return sb.toString();
    }

    private void toString(StringBuilder sb, Nodo<T> radice) {
        if (radice != null) {
            toString(sb, radice.fS);
            sb.append(radice.info);
            sb.append(", ");
            toString(sb, radice.fD);
        }//if

    }//toStringPrivate

    @Override
    public Iterator<T> iterator() {
        return new ABRIterator();
    }

    public int altezza() {
        return altezza(radice);
    }

    private int altezza(Nodo<T> radice) {
        if (radice == null) return -1;
        if (radice.fS == null && radice.fD == null) return 0;
        return 1 + Math.max(altezza(radice.fS), altezza(radice.fD));
    }//altezza

    @SuppressWarnings("unchecked")
    public void visitaPerLivelli() {
        T a[] = (T[]) new Comparable[size()];
        VPL(a);
        System.out.println(Arrays.toString(a));
    }//visitaPerLivelli

    public void visitaPerLivelli(T[] a) {
        VPL(a);
    }//visitaPerLivelli

    private void VPL(T[] a) {
        Queue<Nodo<T>> q = new LinkedList<>();
        q.add(radice);
        int c = 0;
        while (q.size() != 0) {
            Nodo<T> n = q.remove();
            a[c] = n.info;
            if (n.fS != null) q.add(n.fS);
            if (n.fD != null) q.add(n.fD);
            c++;
        }//while
    }//VPL

    private static class Nodo<E> {
        Nodo<E> fS, fD;
        E info;
    }//Nodo

    /*
     //ipotizziamo che la struttura non cambi durante l'iterazione
     private class ABRIterator implements Iterator<T>{
         java.util.List<T> l=new LinkedList<>();
         Iterator<T> it;
         T cur;
         public ABRIterator(){
             visitaSimmetrica(l);
             it=l.iterator();
         }//costruttore
         public boolean hasNext(){
             return it.hasNext();
         }//hasNext
         public T next(){
             if(!(hasNext()))throw new NoSuchElementException();
             cur=it.next();//salvo perche' mi serve in remove
             return cur;
         }//next
         public void remove(){
             it.remove(); //eccezione eventualmente sollevata da questo iteratore qui
             ABR.this.remove(cur);//qui uso cur
         }//remove
     }//ABRIterator
     */
    private class ABRIterator implements Iterator<T> {
        private Stack<Nodo<T>> stack;
        private boolean rimovibile = false;
        private Nodo<T> cor;
        private int modifiche;//a prova di modifica concorrente
        
        public ABRIterator() {
            stack = new StackConcatenato<>();
            if (radice != null) {
                Nodo<T> n = radice;
                modifiche=ABR.this.modCounter;
                while (n != null) {
                    stack.push(n);
                    n = n.fS;
                }
            }
        }//ABRIterator

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }//hasNext

        public void remove() {
            if (!rimovibile) throw new IllegalStateException();
            if(modifiche!=ABR.this.modCounter)throw new ConcurrentModificationException();
            rimovibile = false;
            modifiche++;
            ABR.this.removeBilanciato(cor.info);
            stack = new StackConcatenato<>();
            if (radice != null) {
                Nodo<T> n = radice;
                while (n != null) {
                    stack.push(n);
                    n = n.fS;
                }
            }
        }//remove

        @Override
        public T next() {
        	if(modifiche!=ABR.this.modCounter)throw new ConcurrentModificationException();
        	if (!hasNext()) throw new NoSuchElementException();
            cor = stack.pop();
            Nodo<T> figlio = cor.fD;
            while (figlio != null) {
                stack.push(figlio);
                figlio = figlio.fS;
            }

            rimovibile = true;
            return cor.info;
        }//next
    }//ABRiterator

	public int getModCounter() {
		return modCounter;
	}

	public void setModCounter(int modCounter) {
		this.modCounter = modCounter;
	}
}//ABR
