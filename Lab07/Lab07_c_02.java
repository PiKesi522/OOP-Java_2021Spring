package Lab07;
import java.io.*;
public class Lab07_c_02 {
    public static void NoBuffered() throws IOException{
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try{
            fin = new FileInputStream("IHaveADream.txt");
            fout = new FileOutputStream("NoBuffered.txt");

            int bytee;
            Long start = System.nanoTime();
            // Record the time when it started
            while((bytee = fin.read()) != -1){
                fout.write(bytee);
            }
            Long end = System.nanoTime();
            // Record the time when it finished
            System.out.println("In No-Buffered Stream, it need " + (end-start) + "ms.");

        }finally{
            if(fin != null){
                fin.close();
            }
            if(fout != null){
                fout.close();
            }
        }
    }

    public static void Buffered() throws IOException{
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try{
            bin = new BufferedInputStream(new FileInputStream("IHaveADream.txt"));
            bout = new BufferedOutputStream(new FileOutputStream("Buffered.txt"));

            int bufferr;
            byte[] bytes = new byte[1024];
            // Use a Buffer within 1024 Bytes, which means it can handle 
            // nearly 1024 times data compared to No-Buffered Stream
            Long start = System.nanoTime();
            // Record the time when it started
            while((bufferr = bin.read(bytes)) != -1){
                bout.write(bytes, 0, bufferr);
            }
            Long end = System.nanoTime();
            // Record the time when it finished
            System.out.println("In Buffered Stream, it need " + (end-start) +"ms.");

        }finally{
            if(bin != null){
                bin.close();
            }
            if(bout != null){
                bout.close();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        NoBuffered();
        Buffered();
    }
}
