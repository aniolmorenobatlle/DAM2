import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ninots {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        HashMap<String, Integer> contador = new HashMap<>();

        int n = -1;

        while (n != 0) {
            n = sc.nextInt();
            String[] noms = new String[n];

            for (int i = 0; i < n; i++) {
                noms[i] = sc.next();
                contador.put(noms[i], contador.getOrDefault(noms[i], 0) + 1);
            }

            for (HashMap.Entry<String, Integer> entry : contador.entrySet()) {
                String valor = entry.getKey();
                int valor2 = entry.getValue();

                System.out.println(valor + "  " + valor2);
            }

            String maxNom = null;

            int maxContador = -1;

            for (Map.Entry<String, Integer> entry : contador.entrySet()) {

                if (entry.getValue() > maxContador) {
                    maxContador = entry.getValue();
                    maxNom = entry.getKey();
                }
            }

        }

    }

}
