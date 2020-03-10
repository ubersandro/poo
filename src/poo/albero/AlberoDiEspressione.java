package poo.albero;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

public class AlberoDiEspressione implements Serializable {
    private static final long serialVersionUID= -6583619539749313419L;
    //gerarchia di nodi. Niente genericita'.
    private static class Nodo{
        Nodo fS,fD;
    }//nodo
    //ereditarieta' tra classi static
    private static class NodoOperando extends Nodo {
        int opnd;

        public String toString() {
            return "" + opnd; //l'ha fatto lui, lo posso fare pure io.
            //return String.valueOf(opnd); //in piu' varianti
        }//toString
    }//NodoOperando
        private static class NodoOperatore extends Nodo{
            char op;
            public String toString(){
                return ""+op;
            }//toString
        }//NodoOperatore
        //metodo build per costruire l'albero a partire dalla stringa
        //metodi di visita
        //gli alberi sono strutture radicate (si sa sempre da dove partire)


    private Nodo radice=null;

    public void build(String expr){
        //non e' possibile scrivere regex ricorsive
        String EXPR="(\\d|[\\+\\-\\*/\\(\\)])+";
        //questa condizione e' solo necessaria, non sufficiente. Questo e' un accrocchio.
        if(!expr.matches(EXPR)) throw new RuntimeException("Espressione malformata.");
        //non ci sono spazi nella mia expr
        StringTokenizer st= new StringTokenizer(expr,"+*/-()",true);
        // suddividiamo il lavoro da fare affidandolo a due metodi ==> costruiamo operandi ed espressioni
        radice=creaEspressione(st); //se va a buon fine ha creato l'albero
    }//build

    public void buildPreOrder(String expr){
        String EXPR="(\\d+|[\\*\\-\\+/ ])+";
        
        if(!expr.matches(EXPR))throw new RuntimeException();
        StringTokenizer st= new StringTokenizer(expr);
        radice= creaEspressionePreOrder(st);
    }//buildPreOrder

    private Nodo creaEspressionePreOrder(StringTokenizer st){
    	NodoOperatore r=new NodoOperatore();
        r.op= st.nextToken().charAt(0);
        while(st.hasMoreTokens()){
            r.fS=creaOperandoPre(st);
            r.fD=creaOperandoPre(st);
        }
        return r;
    }//creaEspressionePreOrder

    private Nodo creaOperandoPre(StringTokenizer st){
        String token=st.nextToken();
        if(token.matches("\\s")) token=st.nextToken();
        if(token.matches("\\d+")){
            NodoOperando n= new NodoOperando();
            n.opnd=Integer.parseInt(token);
            return n;
        }
        if(token.matches("[/\\*\\-\\+]")){
            NodoOperatore nop= new NodoOperatore();
            nop.op=token.charAt(0);
            nop.fD=creaOperandoPre(st);
            nop.fS=creaOperandoPre(st);
            return nop;
        }
        throw new RuntimeException();
    }//creaOperandoPre


    private Nodo creaEspressione(StringTokenizer st ){
        Nodo radice=creaOperando(st);
        while(st.hasMoreTokens()){
            char op= st.nextToken().charAt(0); //charAt perche' ho un char
            if(op==')') return radice;
            NodoOperando opnd= (NodoOperando) creaOperando(st);//ci vuole il casting
            NodoOperatore nop=new NodoOperatore();
            nop.op=op; nop.fS=radice; nop.fD=opnd;
            radice=nop; //bottom-up
        }//while
        return radice;
    }//creaEspressione
    //potremmo restituire un NodoOperando?
    private Nodo creaOperando(StringTokenizer st){
        String tk=st.nextToken();
        if(tk.matches("\\d+")){
            NodoOperando nopnd= new NodoOperando();
            nopnd.opnd=Integer.parseInt(tk);
            nopnd.fS=null; nopnd.fD=null;
            return nopnd;
        }//if tk.matches
        if(tk.charAt(0)=='(') return creaEspressione(st);
        throw new RuntimeException("Espressione malformata.");
    }//creaOperando

    private void preOrder(Nodo radice, List<String> l ){
        if(radice!=null){//albero non vuoto
            l.add(radice.toString());
            preOrder(radice.fS,l);
            preOrder(radice.fD,l);
        }//radice!=null
    }//preOrderPrivate
    public void inOrder(List<String> l){
        inOrder(radice, l);
    }//inOrder
    public void preOrder(List<String> l){
        preOrder(radice,l);//sempre il privato e' ricorsivo
    }//preOrder
    private void postOrder(Nodo radice, List<String> l ){
        if(radice!=null){//albero non vuoto
            postOrder(radice.fS,l);
            postOrder(radice.fD,l);
            l.add(radice.toString());//postOrder
        }//radice!=null
    }//preOrderPrivate
    public void postOrder(List<String> l){
        postOrder(radice,l);//sempre il privato e' ricorsivo
    }//preOrder

    private void inOrder(Nodo radice, List<String > l){
        if(radice!=null)
            if(radice instanceof NodoOperatore )l.add("(");
            inOrder(radice.fS,l);
            l.add(radice.toString());
            inOrder(radice.fD, l);
            if(radice instanceof NodoOperatore)l.add(")");
    }//inOrder

    //visita a scandaglio
    public int valore(){
        if(radice==null) throw new RuntimeException("Nessuna espressione!");
        return valore(radice);
    }//valore

    //sono sicuro che l'albero non e' vuoto ==> faccio una specie di visita e sono sicuro che non arrivo mai su un nodo null
    private int valore(Nodo radice){
        if(radice instanceof NodoOperando) return ((NodoOperando) radice).opnd;
        else {//e' un nodo operatore
            int v1 = valore(radice.fS);
            int v2=valore(radice.fD);
            char op=((NodoOperatore)radice).op;
            switch(op){
                case '+': return v1+v2;
                case '-': return v1-v2;
                case '*': return v1*v2;
                case '/': return v1/v2;
                default: throw new RuntimeException("Operatroie inatteso. Scarrafone exception.");
            }//switch
        }//else
    }//valorePrivate

    public static void main(String[] args) {
        AlberoDiEspressione a= new AlberoDiEspressione();
        a.buildPreOrder("* + 2 4 - * 2 3 2");
        System.out.println(a.valore());
        System.out.println("333-3645374".matches("\\d{3}-\\d{7}"));
    }
}//AlberoDiEspressione
//TODO BUILD2 (STRING EXPR) CON EXPR PRE(SUGGERITA) O POST ORDER