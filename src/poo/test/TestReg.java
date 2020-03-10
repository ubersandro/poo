package poo.test;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class TestReg {
    public static TestReg a;
    public static final TestReg B=null;

    public static void main(String[] args) throws IOException {
       /* String j= "Java is fantastic!";
        String REGEX= ".*Java.*fantastic.*";
        System.out.println(j.matches(REGEX));

        ArrayList<Integer> l= new ArrayList<>();
        l.add(8);l.add(1);l.add(-3);
        System.out.println(l);
        Collections.reverse(l);
        System.out.println(l);*/
        BufferedReader br= new BufferedReader(new FileReader("c:\\prova.txt"));
        String EXPR="^(([\\d]{1,4})([\\s]+)){5}";
        String line= br.readLine();
        while(line!=null) {
            System.out.print(line + "  ");
            System.out.println(line.matches(EXPR));
            line=br.readLine();
        }
        int $ciao=0;
    }//main
}
