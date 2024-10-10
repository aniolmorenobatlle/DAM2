import java.util.Scanner;

public class Ex616 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            int sorolls = sc.nextInt();
            int posicio = 0;
            int marcador1 = 0;
            int marcador2 = 0;
            
            for (int i = 0; i < sorolls; i++) {
                String onomatopeya = sc.next();


                if (onomatopeya == "PIC") {
                    posicio += 1;
                }

                if (posicio % 2 != 0 && onomatopeya == "PONG!") {
                    marcador2 += 1;
                } else {
                    marcador1 += 1;
                }

                System.out.println(marcador1 + marcador2);

            }
            
            

            if (sorolls == 0) break;


        }
    }
}
