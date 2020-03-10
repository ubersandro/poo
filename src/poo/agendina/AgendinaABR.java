package poo.agendina;

import poo.util.ABR;

import java.util.Iterator;
/**
 * Concretizzazione di Agendina che sfrutta la logica binaria per
 * rendere più efficienti le operazioni di ricerca dei contatti. La classe si
 * basa su una Collection custom di tipo ABR parametrica in Nominativo. Il bilanciamento
 * e quindi la complessità logaritmica della ricerca è garantita dall'uso dei metodi
 * abbBilanciato e removeBilanciato.
 */
public class AgendinaABR extends AgendinaAstratta {
    private ABR<Nominativo> agenda;

    public AgendinaABR(){
        agenda=new ABR<>();
    }
    public AgendinaABR(Agendina a){
        agenda=new ABR<>();
        a.forEach(e -> agenda.addBilanciato(e));
    }//costruttore di copia

    @Override
    public void aggiungi(Nominativo n) {
        agenda.addBilanciato(n);
    }//aggiungi

    @Override
    public Iterator<Nominativo> iterator() {
        return agenda.iterator();
    }//iterator
    @Override
    public void svuota(){
        agenda.clear();
    }

    @Override
    public void rimuovi(Nominativo n){
        agenda.removeBilanciato(n);//remove()
    }

    @Override
    public Nominativo cerca(Nominativo n){
        return agenda.get(n);
    }

    public static void main(String[] args) {
        Agendina a= new AgendinaABR();
        a.aggiungi(new Nominativo("Rossi", "Marco","+39","3389914654"));
        a.aggiungi(new Nominativo("Rossi", "Mario","+39","3389914654"));
        a.aggiungi(new Nominativo("Verdi", "Antonio","+39","3389914654"));
        a.aggiungi(new Nominativo("Bianchi", "Marta","+39","3389914654"));
        a.aggiungi(new Nominativo("Einstein", "Albert","+39","7483623354"));
        a.aggiungi(new Nominativo("Rossi", "Carmine","+39","4437462836"));
        System.out.println(a);
    }
}
