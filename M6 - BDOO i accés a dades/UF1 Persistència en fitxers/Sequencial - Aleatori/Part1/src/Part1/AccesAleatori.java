package Part1;

import java.io.RandomAccessFile;
import java.io.IOException;

public class AccesAleatori {
    public static void main(String[] args) {
        String path = "src/Part1/text.txt";
        
        try (RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            // Llegir primer byte
            System.out.println("Primer byte: " + (char) raf.read());
            
            // Mou al byte 10
            raf.seek(10);
            System.out.println("Byte 11: " + (char) raf.read());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
