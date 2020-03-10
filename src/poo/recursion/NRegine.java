package poo.recursion;

public class NRegine {
    private boolean board[][]; //se in una casella metto true, c'e' la regina
    private int n;//piu' semplice di fare board.length
    private int numSol=0;
    public NRegine(int n){
        if(n<4) throw new IllegalArgumentException("Che dobbiamo fare?");
        this.n=n;
        board=new boolean[n][n];//uso il parametro per non chiedere sempre length
        //all false by default
    }
    public void risolvi(){
        collocaRegina(0);//primo punto di scelta=0
    }//risolvi

    /*
    Sicuramente row non e' l'ultima.
    In generale, esiste uno spazio di soluzioni e io le voglio tutte. Il metodo e' per esaustione.
     */
    private void collocaRegina(int row){
        for(int col=0; col<n; col++)
            if(assegnabile(row,col)){
                assegna(row,col);
                if(row==n-1)scriviSoluzione();
                else collocaRegina(row+1);
                //arrivato qui sicuramente mi torna il controllo perche' la chiamata o scriviSoluzione falliscono
                deassegna(row,col);
            }//if
    }//collocaRegina
    // il metodo mette in atto una ricorsione a fisarmonica ==> si va avanti finche' e' possibile, dopo di che backTracking
    //cambiando il problema non cambia la struttura

    /**Metodo assegnabile(row.col):
     * Responsabile dell'imposizione dei vincoli*/
    private boolean assegnabile(int row,int col){
        //verifica NORD
        for(int r=row-1;r>=0;r--) if(board[r][col]) return false;
        //NORD-EST
        for(int r=row-1, c=col+1; r>=0&& c<n;--r,++c)
            if(board[r][c]) return false;
        //NORD OVEST
        for(int r=row-1, c=col-1;r>=0&&c>=0; --r,--c)if(board[r][c])return false;
        return true;
    }//assegnabile

    /*
    Scrivili sempre i metodi perche' cosi mantieni l'architettura del problema
     */

    private void assegna(int row,int col){
        board[row][col]=true;
    }//assegna

    private void deassegna(int row, int col){
        board[row][col]=false;
    }//deassegna

    private void scriviSoluzione(){
        numSol++;
        System.out.print(numSol+": ");
        for(int i=0; i<n;++i)
            for(int j=0;j<n;j++)
                if(board[i][j]){
                    System.out.print("<"+i+","+j+">"); break;
                }//if
        System.out.println();
    }//scriviSoluzione

    public static void main(String... fabrizio){
        new NRegine(8).risolvi();
    }//main

}//NRegine
