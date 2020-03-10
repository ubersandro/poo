package poo.util;

import java.util.Scanner;
import java.util.StringTokenizer;

public class ValutatoreRPN {
    public static void main(String...args){
        Stack<Integer> stack=new StackConcatenato<>();
        String RPN="((\\d+|[/\\*\\-\\+])\\s*)+";
        		//+ "[\\d\\s+[\\-\\+\\*\\/]+]+";//"[\\d+\\s+[\\-\\+\\*\\/]+]+"
        Scanner sc=new Scanner(System.in);
        for(;;){
            int op1,op2;
            System.out.println("Inserire espressione RPN da valutare(q per uscire): \n >");
            String line=sc.nextLine();
            if(line.matches(RPN)){
            	System.out.println("MATCH");
                try {
                    StringTokenizer st = new StringTokenizer(line, " +-*/",true);
                    while(st.hasMoreTokens()){
                        String token=st.nextToken();
                        if(token.matches("[\\-\\*\\/\\+]")){
                            op1=stack.pop(); op2=stack.pop();
                            switch(token){
                                case "+":
                                    stack.push(op2+op1);
                                    break;
                                case  "-":
                                    stack.push(op2-op1);
                                    break;
                                case "*":
                                    stack.push(op2*op1);
                                    break;
                                case "/":
                                    stack.push(op2/op1);
                                    break;
                            }//switch
                        }//ifSign
                        else if(!(token.matches("\\s"))){
                            //System.out.println("Numero");
                            int op=Integer.parseInt(token);
                            stack.push(op);
                        }//elseIf
                    }//whileHasMoreTokens
                    System.out.println("Risultato: "+stack.pop());
                }catch(Exception e){//e.printStackTrace();
                System.out.println("Espressione malformata");}
            }//ifMatches
            else if(line.matches("[Qq]")) break;
        }//for
        sc.close();
    }//main
}//ValutatoreRPN
