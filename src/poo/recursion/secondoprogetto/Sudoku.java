package poo.recursion.secondoprogetto;

public class Sudoku {
    private int[][] board;
    private int numSol;
    private boolean traccia[][];
    private final int N=9;
    private boolean stop;
    private int celleRimaste;

    
    int[][] getBoard(){
        int[][] ret= new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N;j++)
                ret[i][j]=board[i][j];
        return ret;
    }

    
     int getCelleRimaste(){return celleRimaste; }

    public Sudoku() {
        board = new int[N][N];
        traccia= new boolean[N][N];
        celleRimaste=81;
        stop=false; //ridondante
    }//Sudoku

    int getNumSol(){return numSol; }
    void setNumSol(int n){numSol=n; }
   
    public Sudoku(int[][] imp) {
    	for(int i=0; i<imp.length;++i) if (imp[i].length != 3) throw new IllegalArgumentException();
        board = new int[N][N];
        celleRimaste=81;
        traccia= new boolean[N][N];
        stop=false; //ridondante
        for (int i = 0; i < imp.length; i++) {
            imposta(imp[i][0], imp[i][1], imp[i][2]);
        }//for
    }//Sudoku

    public void imposta(int i, int j, int v) {
    	if(i<0||i>8||j<0||j>8||v<1||v>9)throw new RuntimeException();
        if (assegnabile(i, j, v)){
            board[i][j] = v;
            traccia[i][j]=true;
            celleRimaste--;
        }//if
        else throw new IllegalArgumentException
                ("Valore non assegnabile:<"+i+","+j+";"+v+">");
    }//imposta

     /*private*/ boolean assegnabile(int i, int j, int v) {
    	 if(i<0||i>8||j<0||j>8||v<1||v>9)throw new RuntimeException();
         if(traccia[i][j]) return false;
        int r, c;
        for (r = 0; r < N; r++) if (board[r][j] == v) return false;
        for (c = 0; c < N; c++) if (board[i][c] == v) return false;
        for (r = 3 * (i / 3); r < 3 + 3 * (i / 3); r++)
            for (c = 3 * (j / 3); c < 3 + 3 * (j / 3); c++)
                if (board[r][c] == v) return false;
        return true;
    }//assegnabile

    public void risolvi() {
        colloca(0, 0);
    }//risolvi

    private void colloca(int i, int j) {
        if (!traccia[i][j]) {
            for (int v = 1; v < 10; v++) {
                if (assegnabile(i, j, v)) {
                    assegna(i, j, v);
                    if (celleRimaste==0) {
                        stampaSoluzione();
                        if(getNumSol() ==100)stop=true; //limite al numero delle soluzioni
                        if(stop)return;
                    } else {
                        if (j == N - 1) colloca(i + 1, 0);
                        else colloca(i, (j + 1) % N);
                    }
                    if(stop)return;
                    deassegna(i, j);
                }
            }
        }

       else {
            if (j == N - 1) colloca(i + 1, 0);
            else colloca(i, (j + 1) % N);
        }
    }


    //alternativa a toString con aggiornamento di numSol
    public void stampaSoluzione() {
        setNumSol(getNumSol() + 1);
        for (int i = 0; i < board.length; i++) {
            System.out.println("------------------------");
            for(int j=0; j<N;++j){
                if(j%3==0&&j!=0)System.out.print("|");
                System.out.print(" "+board[i][j]);
                if(j==N-1) System.out.print('\n');
            }//forJ
        }//forI
        System.out.println("Numero soluzione:" + getNumSol());
    }//stampaSoluzione

    /*private*/ void assegna(int i, int j, int v) {
        board[i][j] = v;
        celleRimaste--;
    }//assegna

    private void deassegna(int i, int j) {
            board[i][j] = 0;
            celleRimaste++;
    }//deassegna

    public String toString() {
        StringBuilder sb = new StringBuilder(300);
        for (int i = 0; i < board.length; i++) {
            sb.append('[');
            for (int j = 0; j < board.length; j++) {
                sb.append(board[i][j]);
                sb.append(' ');
            }//for
            sb.append(']');
            sb.append('\n');
        }//for
        return sb.toString();
    }//toString

    public static void main(String... ciao) {
        int[][] a = {{0, 0, 3}, {1, 3, 4}, {1, 4, 5},
                     {8, 5, 2}, {8, 4, 7}, {7, 4, 3},
                     {8, 2, 5}, {1, 6, 3},
                    {3, 8, 6}, {3, 4, 1}, {1, 5, 9},
                    {5, 4, 2}, {3, 6, 8},{8,8,3},
                    {5, 2, 7}, {7, 1, 1}, {2, 2, 1}};
        Sudoku s = new Sudoku(a);
        System.out.println(s);
        s.risolvi();
    }//main

}//Sudoku
