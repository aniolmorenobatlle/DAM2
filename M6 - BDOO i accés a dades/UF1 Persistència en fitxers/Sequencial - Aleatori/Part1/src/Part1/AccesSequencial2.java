package Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AccesSequencial2 {
    public static void main(String[] args) {
        String path = "src/Part1/text.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

