import java.util.Scanner;

public class Ex116 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        long option = sc.nextInt();

        for (int i = 0; i < option; i++) {
            System.out.println("Hola mundo.");
        }

        sc.close();
    }
}
