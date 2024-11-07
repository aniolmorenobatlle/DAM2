import java.util.Scanner;

public class BaixaLaTapaDelVater {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int casos = sc.nextInt();

        for (int i = 0; i < casos; i++) {
            
            String enquesta = sc.next();

            int amunt = 0;
            int baix = 0;
            int total = enquesta.length();

            for (int j = 0; j < enquesta.length(); j++) {

                if (enquesta.charAt(j) == 'A') {
                    amunt++;
                } else if (enquesta.charAt(j) == 'B') {
                    baix++;
                }
            }

            System.out.println(amunt + " " + total + " " + baix);

        }
        
    }
}
