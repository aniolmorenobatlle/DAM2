import java.util.Scanner;

public class LlebreITortuga {
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        int casos = sc.nextInt();

        for (int i = 0; i < casos; i++) {

            int metres = sc.nextInt();
            int veloT = sc.nextInt();
            
            int veloL = sc.nextInt();
            int aguantar = sc.nextInt();


            System.out.println("Metres: " + metres);
            System.out.println("VeloT: " + veloT);
            System.out.println("VeloL: " + veloL);
            System.out.println("Aguantar: " + aguantar);
        
        }
        
    }
}
