package poo.recursion.secondoprogetto;

public class SudokuDepr {
    private int[][] board;
    /*private*/ int numSol;
    private final int n=9;
    private boolean traccia[][];
    private final int N=9;
    private boolean stop;

    int[][] getBoard(){
        int[][] ret= new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n;j++)
                ret[i][j]=board[i][j];
        return ret;
    }

    public SudokuDepr() {
        board = new int[N][N];
        traccia= new boolean[N][N];
    }//Sudoku

    public SudokuDepr(int[][] imp) {
        if (imp[0].length != 3) throw new IllegalArgumentException();
        board = new int[N][N];
        traccia= new boolean[N][N];
        for (int i = 0; i < imp.length; i++) {
            imposta(imp[i][0], imp[i][1], imp[i][2]);
        }//for
    }//Sudoku

    public void imposta(int i, int j, int v) {
        if (assegnabile(i, j, v)){
            board[i][j] = v;
            traccia[i][j]=true;
        }//if
    }//imposta

     /*private*/ boolean assegnabile(int i, int j, int v) {
        //if(traccia[i][j]) return false;
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

    public void colloca(int i, int j) {
        if (!traccia[i][j]) {
            for (int v = 1; v < 10; v++) {
                if (assegnabile(i, j, v)) {
                    assegna(i, j, v);
                    if (i == N - 1 && j == N - 1) {
                        stampaSoluzione();
                        if(numSol==100)stop=true;
                        if(stop)return;
                    } else {
                        if (j == N - 1) colloca(i + 1, 0);
                        else colloca(i, (j + 1) % N);
                    }
                    if(stop)return;
                    deassegna(i, j, v);
                }
            }
        }
        else if(j==N-1&&i==N-1){
            stampaSoluzione();
            if(numSol==100)stop=true;
        }
       else {
            if (j == N - 1) colloca(i + 1, 0);
            else colloca(i, (j + 1) % N);
        }
    }



    public void stampaSoluzione() {
        numSol++;
        for (int i = 0; i < board.length; i++) {
            System.out.println("------------------------");
            for(int j=0; j<N;++j){
                if(j%3==0&&j!=0)System.out.print("|");
                System.out.print(" "+board[i][j]);
                if(j==N-1) System.out.print('\n');
            }//forJ
        }//forI
        System.out.println("Numero soluzione:" +numSol);

            // System.exit(0);
    }//stampaSoluzione

    private void assegna(int i, int j, int v) {
        board[i][j] = v;
    }//assegna

    private void deassegna(int i, int j, int v) {
       // if(!traccia[i][j])
            board[i][j] = 0;
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
                {8, 2, 5}, {7, 4, 7}, {1, 6, 3},
                {3, 4, 6}, {3, 4, 1}, {1, 5, 9},
                {5, 4, 2}, {3, 6, 8},{8,8,3},
                {5, 2, 7}, {7, 1, 1}, {2, 2, 1}};
        SudokuDepr s = new SudokuDepr(a);
        System.out.println(s);
        s.risolvi();
        //new Sudoku().risolvi();

    }//main

}//Sudoku
