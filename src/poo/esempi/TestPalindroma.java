package poo.esempi;
import poo.util.*;
import java.util.Scanner; //evito errori nell'import di stack 

public class TestPalindroma {
	public static void main(String... Pascali) {
		Scanner sc= new Scanner(System.in);
		String INPUT="\\w+$\\w+";
		System.out.print("Fornisci input amico mio >");
		String linea=sc.nextLine(); sc.close();
		if(!linea.matches(INPUT)) {
			System.out.println(linea+ " scorretta!");
			System.exit(-1);
		}
		Stack<Character> pila= new StackConcatenato<>();
		int i=0;
		while(i<linea.length()&&linea.charAt(i)!='$') {
			pila.push(linea.charAt(i));
			i++;
		}//while
		i++; //schipp dollaro $
		while(i<linea.length()) {
			char x=pila.pop();
			if(x!=linea.charAt(i)) break;
			else i++;
		}
		if(i<linea.length()||!pila.isEmpty()) System.out.println(linea+ " non e' palindroma");
		else 
			System.out.println(linea+" e' palindroma");
	}//main
	
}//TestPalindroma
