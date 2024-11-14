
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NuvolsTags {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashMap<String, Integer> contador = new HashMap<>();
        int nCasos = sc.nextInt();
        sc.nextLine();

        String comentari = "";

        for (int i = 0; i < nCasos; i++) {

            comentari = sc.nextLine().toLowerCase();

            // comentari = comentari.substring(comentari.indexOf(" "), comentari.length());
            comentari = comentari.substring(comentari.indexOf(" ") + 1); // per saltar espai despres del nom


            String[] paraules = comentari.split(" ");

            for (String paraula : paraules) {
                contador.put(paraula, contador.getOrDefault(paraula, 0) + 1);
            }

        }

        ArrayList<String> paraulesTrobades = new ArrayList<>();

        for (Object key : contador.keySet()) {
            int valor = contador.get(key);

            paraulesTrobades.add((String) key + " " + valor);
        }

        paraulesTrobades.sort((str1, str2) -> {
            int num1 = Integer.parseInt(str1.split(" ")[1]);
            int num2 = Integer.parseInt(str2.split(" ")[1]);

            int resultat = Integer.compare(num2, num1);

            if (resultat == 0) {
                return str1.split(" ")[0].compareTo(str2.split(" ")[0]);
            }

            return resultat;
        });

        int nContador = 0;

        if (paraulesTrobades.get(0).equals(" ")) {
            paraulesTrobades.remove(0);
        }

        for (int i = 0; i < paraulesTrobades.size(); i++) {
            String apariciones = paraulesTrobades.get(i).substring(paraulesTrobades.get(i).indexOf(" ") + 1);
            int nAparicions = Integer.parseInt(apariciones);

            String paraula = paraulesTrobades.get(i).substring(0, paraulesTrobades.get(i).indexOf(" "));

            if (nAparicions == nCasos) {
                System.out.println(paraula);
                nContador++;
            }

        }
        
        if (nContador == 0) {
            System.out.println("OK");
        }
    }

}