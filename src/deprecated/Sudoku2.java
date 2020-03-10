package deprecated;

import java.util.Arrays;

public class Sudoku2 {
    private int[][] board;
    /*private*/ int numSol;
    private int n;
    ///////
    boolean finito;

    //private int celleOccupate; non so se possa tornarmi utile o meno

    int[][] getBoard(){
        int[][] ret= new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n;j++)
                ret[i][j]=board[i][j];
        return ret;     
    }
    public Sudoku2() {
        board = new int[9][9];
        n=board.length;
    }//Sudoku

    public Sudoku2(int[][] imp) {
        if (imp[0].length != 3) throw new IllegalArgumentException();
        board = new int[9][9];
        n=board.length;
        for (int i = 0; i < imp.length; i++) {
            imposta(imp[i][0], imp[i][1], imp[i][2]);
        }//for
    }//Sudoku

    public void imposta(int i, int j, int v) {
        if (assegnabile(i, j, v)) board[i][j] = v;
    }//imposta

    //verifica se un valore puo' essere inserito in pos i-j o meno
     /*private*/ boolean assegnabile(int i, int j, int v) {
        if (board[i][j] != 0) return false;
        int r, c;
        for (r = 0; r < board.length; r++) if (board[r][j] == v) return false;
        for (c = 0; c < board.length; c++) if (board[i][c] == v) return false;
        for (r = 3 * (i / 3); r < 3 + 3 * (i / 3); r++)
            for (c = 3 * (j / 3); c < 3 + 3 * (j / 3); c++)
                if (board[r][c] == v) return false;
        return true;
    }//assegnabile

    public void risolvi() {
        colloca(0, 0);
    }//risolvi

    public void colloca(int i, int j) {
        if (numSol == 100) finito=true; //System.exit(0);
        /*
        if (i == board.length - 1 && j == board.length - 1){
            stampaSoluzione();
            return;
        }*/
        while(board[i][j]!=0){
            if(j==board.length-1) {
                if (i == board.length - 1) {
                    //stampaSoluzione();
                    System.out.println("Sei arrivato qui.");
                    break;//ultima cella
                } else i++;
            }
            j=(j+1)%board.length;
        }

        for (int v = 1; v < 10; v++) {
            if (assegnabile(i, j, v)) {
                assegna(i, j, v);
                if(i==n-1&&j==n-1) stampaSoluzione();
                else {
                    if (j == board.length - 1) colloca(i + 1, 0);
                    else colloca(i, j + 1);
               }//else
                if(finito)return;
                deassegna(i, j, v);
            }//assegnabile
        }//for
    }//colloca



    public void stampaSoluzione() {
        numSol++;
        for (int i = 0; i < board.length; i++)
            System.out.println(Arrays.toString(board[i]));
        System.out.println("Numero soluzione:" +numSol);
    }//stampaSoluzione

    private void assegna(int i, int j, int v) {
        board[i][j] = v;

    }//assegna

    private void deassegna(int i, int j, int v) {
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
                {3, 4, 6}, {3, 4, 1}, {1, 5, 9}
                 ,{5, 4, 2}, {3, 6, 8},
                {5, 2, 7}, {7, 1, 1}, {2, 2, 1}};
        Sudoku2 s = new Sudoku2(a);
        s.risolvi();
        //new Sudoku().risolvi();

    }//main

}//Sudoku
