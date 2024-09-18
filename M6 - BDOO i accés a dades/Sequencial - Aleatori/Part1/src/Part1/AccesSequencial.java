package Part1;

import java.io.FileReader;
import java.io.IOException;

public class AccesSequencial {
    public static void main(String[] args) {
		
		char[] array = new char[100];
		
		try {
			FileReader path = new FileReader("src/Part1/text.txt");
			
			path.read(array);
			
			System.out.println(array);
			
			path.close();
		} catch(IOException e) {
            e.printStackTrace(); 
        }
	}

}
