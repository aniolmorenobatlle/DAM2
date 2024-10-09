import java.util.Scanner;

public class Ex112 {
	static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        while (true) {
            int distancia = sc.nextInt();
            int velocitatkm = sc.nextInt();
            int temps = sc.nextInt();

            if (distancia == 0 && velocitatkm == 0 && temps == 0) break;

            if (temps >= 0) {

                // Per calcula una velocitat = distancia / temps, però s'ha de multiplicar per 3.6 perque necessitem amb km/h no amb m/s
                double velocitat = (distancia / (double) temps) * 3.6; // 3.6 perquè 1 m/s = 3.6 km/h
                double limit = velocitatkm * 1.2; // 1.2 perquè es un 20%

                if (velocitat <= velocitatkm) {
                    System.out.println("OK");
                } else if (velocitat > velocitatkm && velocitat < limit) {
                    System.out.println("MULTA");
                } else if (velocitat >= limit) {
                    System.out.println("PUNTOS");
                }

            } else {
                System.out.println("ERROR");
            }
        }
    }
}
