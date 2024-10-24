import java.util.ArrayList;
import java.util.Scanner;

public class feliciano {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        int n = sc.nextInt(); sc.nextLine();

        int numeros = 0;

        ArrayList<String> entrades = new ArrayList<>();

        ArrayList<String> alumnes = new ArrayList<>();

        alumnes.add("F");
        alumnes.add("C");
        alumnes.add("S");

        for (int i = 0; i < n; i++) {
            String entrada = sc.nextLine();
            entrades.add(entrada);
        }

        for (int i = 0; i < entrades.size(); i++) {

            for (int j = 0; j < alumnes.size(); j++) {
                
                if (entrades.get(i) == alumnes.get(j)) {
                    numeros += 3;
                } else {
                    entrades.remove(i);
                }

            }

        }

        System.out.println(numeros);

    }

}
