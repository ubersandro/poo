package poo.test;
import java.io.*;

public class TestFileMerging {
    public static void main(String[] args)  throws IOException{
        DataOutputStream f= new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("ciao.int"))));
        f.writeInt(1); f.writeInt(2); f.writeInt(5);
        f.close();
        f= new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("ciaociao.int"))));
        f.writeInt(3); f.writeInt(4);
        f.close();

        DataInputStream f1= new DataInputStream(new BufferedInputStream(new FileInputStream("c:\\ciao.int")));
        for(;;){
            try{
                System.out.println(f1.readInt());
            }catch(EOFException e){
                break;
            }
        }

    }


}
