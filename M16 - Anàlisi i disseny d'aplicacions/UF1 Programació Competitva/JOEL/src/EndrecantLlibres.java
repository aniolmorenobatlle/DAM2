import java.util.Scanner;

public class EndrecantLlibres {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String libros = sc.nextLine();
        String[] estanteria = libros.split("");

        int movimientos = 0;

        for (int i = 0; i < estanteria.length - 1; i++) {
            for (int j = i; j < estanteria.length; j++) {
                if (estanteria[i].compareTo(estanteria[j]) > 0) {
                    String canvi = estanteria[i];
                    estanteria[i] = estanteria[j];
                    estanteria[j] = canvi;
                    movimientos++;
                }
            }
        }

        System.out.println(movimientos);
    }
}
