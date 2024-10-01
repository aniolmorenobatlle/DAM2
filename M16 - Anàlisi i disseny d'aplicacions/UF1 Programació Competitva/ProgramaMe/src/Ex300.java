import java.util.Scanner;
 

public class Ex300 {
    static Scanner sc = new Scanner(System.in);
    static int casos;

    public static void main(String[] args) {
        casos = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < casos; i++) {
            String paraula = sc.nextLine().toLowerCase();

            if (esPentavolica(paraula)) {
                System.out.println("SI");
            } else {
                System.out.println("NO");
            }

        }
    }

    public static boolean esPentavolica(String paraula) {
        boolean conteA = false;
        boolean conteE = false;
        boolean conteI = false;
        boolean conteO = false;
        boolean conteU = false;

        for (int i = 0; i < paraula.length(); i++) {
            String lletra = paraula.substring(i, i + 1);

            if (lletra.equals("a")) {
                conteA = true;
            } else if (lletra.equals("e")) {
                conteE = true;
            } else if (lletra.equals("i")) {
                conteI = true;
            } else if (lletra.equals("o")) {
                conteO = true;
            } else if (lletra.equals("u")) {
                conteU = true;
            }

        }

        return conteA && conteE && conteI && conteO && conteU;
    }
}
