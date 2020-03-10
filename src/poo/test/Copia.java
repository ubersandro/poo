package poo.test;

import java.io.*;

public class Copia {
	public static void main(String ... LIBERO) throws IOException {
		InputStream source= new FileInputStream("f1.dat");
		OutputStream dest= new FileOutputStream("f2.dat");
		int dato; // sto leggendo un byte ma lo converte in intero
		for(;;) {
			dato= source.read();
			if( dato == -1) break; //end of file f1
			dest.write(dato);
		}
		source.close();
		dest.close();
	}
}
