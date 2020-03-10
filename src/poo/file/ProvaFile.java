package poo.file;

import java.io.*;

@SuppressWarnings("unused")
public class ProvaFile {
	public static void main(String...strings ) throws IOException{
		//InputStream is= new FileInputStream("fileBinario.bin"); 
		InputStream is= new BufferedInputStream(new FileInputStream("fileBinario.bin")); 
		//OutputStream os= new FileOutputStream("nuovoFile.bin");
		OutputStream os= new BufferedOutputStream(new FileOutputStream("nuovoFile.bin"), 10);//con size (numero byte)
		int dato;
		for(;;) {
			dato=is.read();//si puo' anche usare available() da BufferedInputStream per capire se ci sono altri byte da leggere
			if(dato==-1) break; //restituisce -1 quando il file BINARIO e' finito
			os.write(dato);
		}
		os.close();
		is.close();
		//per file binari
		
		DataInputStream d= new DataInputStream(new FileInputStream("fileInteri.int")); //perche' se e' di tipo statico DataInput non solleva warnings "unclosed"?
		DataOutputStream dos= new DataOutputStream(new FileOutputStream("copia.int"));
		FOR: for(;;) {
			System.out.println("LOOP");
			try{
				int c= d.readInt();
				dos.writeInt(c);
		}catch(EOFException e ){
			System.out.println("Copia finita");
			break FOR;
		}//catch 
			
		}//for
		d.close(); dos.close();
		
		DataInputStream dis=new DataInputStream(new FileInputStream("copia.int"));
		System.out.println("Vediamone il contenuto.");
		for(;;) try {
			System.out.println(dis.readInt());
		}catch(EOFException e) {
			System.out.println("File finito.");
			break;
		}//catch
		dis.close(); //chiudi il gas e vieni via 
		//file tipati (es. char)
		
		RandomAccessFile raf= new RandomAccessFile("fileInteri.int", "rw");
		System.out.println(raf.length());
		System.out.println(raf.getFilePointer());
		boolean flag=false;
		int x= Integer.MAX_VALUE;
		while(raf.getFilePointer()<raf.length()&&!flag) {
			System.out.println(raf.getFilePointer()+"CIAO");
			int y=raf.readInt();
			raf.writeInt(Integer.MAX_VALUE);//se chiami write()==>InputStream e scrive un byte non un int 
			if(x>y) flag=true;
			System.out.println(raf.getFilePointer());//il file pointer si sposta ad ogni lettura o scrittura 
		}//while
		if(flag)System.out.println("Yes,amico mio. Puoi inserire.");
		raf.close();
		//raf
		File f= new File("c:\\users\\");
		BufferedWriter p2= new BufferedWriter(new PrintWriter(new File("c:\\ciao.txt")));
	}//main
}//ProvaFile
	
