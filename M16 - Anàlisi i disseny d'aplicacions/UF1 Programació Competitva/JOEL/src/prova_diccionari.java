import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class prova_diccionari {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Crear un diccionari
        HashMap<String, String> diccionari = new HashMap<>();

        // Llegir casos
        int casos = Integer.parseInt(sc.nextLine());


        for (int i = 0; i < casos; i++) {

            String linia = sc.nextLine();

            // Trobar index per separar nom i comentari
            int espaiIndex = linia.indexOf(" ");

            if (espaiIndex != 1) {
                String nom = linia.substring(0, espaiIndex);
                String comentari = linia.substring(espaiIndex + 1);

                // Afegir al diccionari
                diccionari.put(nom, comentari);
            }
        }

        System.out.println();

        // Mostrar els resultats
        for (Map.Entry<String, String> entry : diccionari.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        System.out.println();

        // Mostrar nomes el segon resultat
        if (diccionari.size() > 1) {
            int count = 0;

            for (Map.Entry<String, String> entry : diccionari.entrySet()) {
                if (count == 1) { // Agafar el 2 
                    System.out.println("Segon: ");
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                    break;
                }

                count++;
            }
        }

        
        
    }
}
