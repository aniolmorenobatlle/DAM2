import java.util.Scanner;

public class Ex98 {
    static int casos;
	static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        casos = sc.nextInt();

        for (int i = 0; i < casos; i++) {
            
            int dia = sc.nextInt();
            int mes = sc.nextInt();

            if (dia == 25 && mes == 12) {
                System.out.println("SI");
            } else {
                System.out.println("NO");
            }
            
        }
        
    }
}
