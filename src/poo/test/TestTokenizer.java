package poo.test;
import java.util.*;
import java.io.*;

public class TestTokenizer {
	public static void main(String ...strings ) throws IOException{
		String c= "CIAO";
		String d="ciao"; 
		
		System.out.println(c.compareTo(d)<0);
		//System.out.println(c.compareTo(p));
		ArrayList<String> a= new ArrayList<>();
		a.add(c); a.add(d); 
		Collections.sort(a);
		System.out.println(a);
		File f= new File("c:\\users\\aless\\fileRinom.txt");
		
		System.out.println(f.exists());
		PrintStream p= new PrintStream(f);
		p.println("ciao hey you");
		p.println("ciao mamma \nguarda come mi diverto");
		//f.renameTo(new File("c:\\users\\aless\\fileRinom"));
		//f.delete();
		p.checkError();
		p.close();
		
		String REGEX= ".*come.*";
		BufferedReader br= new BufferedReader(new FileReader(f));
		StringBuilder sb= new StringBuilder(200); 
		
		for(;;) {
			String line= br.readLine();
			if(line==null)break; 
			System.out.println(line.matches(REGEX));
			line=line.replaceFirst(REGEX,"sesso");
			sb.append(line+"\n");
		}
		br.close();
		System.out.println(sb);
		String n= new String("la tanadellupo");
		String test= "([A-Z][1-9]*)+";
		System.out.println("A9".matches(test));
		System.out.println(n.matches(".*\\s.*"));
	}

}
