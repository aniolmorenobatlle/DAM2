
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PassantLlista {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int nNoms = 200;

        ArrayList<String> noms2 = new ArrayList<String>();
        String[] nomsOrdenats = new String[nNoms];

        boolean recollidaAcabat = false;
        while (!recollidaAcabat) {
            String nom = sc.nextLine();
            
            if (nom.isEmpty()) {
                recollidaAcabat = true;
            }

            if (!recollidaAcabat) noms2.add(nom);
        }

        String[] noms = noms2.toArray(new String[0]);

        Arrays.sort(noms, Comparator
                .comparing((String nom) -> nom.substring(nom.indexOf(' ') + 1))
                .thenComparing(nom -> nom.substring(0, nom.indexOf(' '))));

        for (int i = 0; i < noms.length; i++) {
            String nom = noms[i].substring(0, noms[i].indexOf(' '));

            int j = 0;
            boolean trobat = false;

            while (!trobat && j < noms.length) {
                if (i != j) {
                    String nom2 = noms[j].substring(0, noms[j].indexOf(' '));
                    if (nom.equals(nom2)) {
                        nomsOrdenats[i] = noms[i];
                        trobat = true;
                    } else {
                        nomsOrdenats[i] = nom;
                    }
                }

                j++;
            }
        }

        for (int i = 0; i < noms.length; i++) {
            System.out.println(nomsOrdenats[i]);
        }

    }
}