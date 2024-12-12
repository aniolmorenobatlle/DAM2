import java.util.Scanner;

public class ArtModern {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int numFiles = sc.nextInt();
        int numColumnes = sc.nextInt();
        int nEleccions = sc.nextInt();
        sc.nextLine();

        int[] columnes = new int[numColumnes];
        int[] files = new int[numFiles];

        for (int i = 0; i < nEleccions; i++) {
            char tipo = sc.next().charAt(0);
            int dir = sc.nextInt() - 1;
            sc.nextLine();

            if (tipo == 'C') {
                columnes[dir]++;
            } else {
                files[dir]++;
            }
        }

        int contador = 0;

        for (int i = 0; i < files.length; i++) {
            for (int j = 0; j < columnes.length; j++) {
                if ((files[i] + columnes[j]) % 2 == 1)
                    contador++;
            }
        }

        System.out.println(contador);
    }
}