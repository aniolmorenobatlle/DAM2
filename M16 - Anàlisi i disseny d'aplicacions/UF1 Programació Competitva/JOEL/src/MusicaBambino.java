import java.util.Scanner;

public class MusicaBambino {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        int n = sc.nextInt();
        sc.nextLine();

        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C"};

        String[] casos = new String[n];

        for (int i = 0; i < n; i++) {
            casos[i] = sc.nextLine();

            primera_inversio(notes, casos[i]);
            segona_inversio(notes, casos[i]);
            tercerda_inversio(notes, casos[i]);
        }

        // for (int i = 0; i < n; i++) {
        //     String line = casos[i];
        //     String[] elements = line.split(" ");
            
        //     System.out.println("Cas de prova " + (i + 1) + ":");
        //     for (String element : elements) {
        //         System.out.println(element);
        //     }
        // }
    }

    public static void fonamental(String notes[]) {
    }

    public static boolean primera_inversio(String notes[], String missatge) {

        String[] parts = missatge.split(" ");

        String primera = parts[0];

        for (int i = 1; i < parts.length; i++) {
            parts[i - 1] = parts[i];
        }

        parts[parts.length - 1] = primera;

        String resultat = String.join(" ", parts);

        System.out.println(resultat);

        return true;
    }

    public static boolean segona_inversio(String notes[], String missatge) {
        String[] parts = missatge.split(" ");

        String primera = parts[0];
        String segona = parts[1];

        for (int i = 2; i < parts.length; i++) {
            parts[i - 2] = parts[i];
        }

        parts[parts.length - 2] = primera;
        parts[parts.length - 1] = segona;

        String resultat = String.join(" ", parts);

        System.out.println(resultat);

        return true;
    }

    public static boolean tercerda_inversio(String notes[], String missatge) {    
        String[] parts = missatge.split(" ");
    
        String primera = parts[0];
        String segona = parts[1];
        String tercera = parts[2];
        String ultima = parts[3];
    
        parts[0] = ultima;
        parts[1] = primera;
        parts[2] = segona;
        parts[3] = tercera;
    
        String resultat = String.join(" ", parts);
        System.out.println(resultat);

        return true;
    }
}
