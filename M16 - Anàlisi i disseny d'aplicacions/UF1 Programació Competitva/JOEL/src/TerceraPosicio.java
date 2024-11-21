import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TerceraPosicio {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        HashMap<Integer, Integer> puntuacions = new HashMap<>();

        int casos = sc.nextInt();
        sc.nextLine();

        
        for (int i = 0; i < casos; i++) {
            int puntuacio = sc.nextInt();
            puntuacions.put(puntuacio, puntuacions.getOrDefault(puntuacio, 0) + 1); // Posar la puntuacio i sumar 1, si no existeix posar 0
        }


        // Ordenar les puntuacions en ordre descendent
        List<Integer> puntuacionsOrdenades = new ArrayList<>(puntuacions.keySet());
        Collections.sort(puntuacionsOrdenades, Collections.reverseOrder()); // Ordre descendent


        int terceraPosicioValor = puntuacionsOrdenades.get(2);
        int repeteix = puntuacions.get(terceraPosicioValor);


        System.out.println(terceraPosicioValor + " " + repeteix);
    }
}