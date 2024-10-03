import java.util.Scanner;

public class Ex155 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        while (sc.hasNext()) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println((x + y) * 2);
        }
        
    }
}