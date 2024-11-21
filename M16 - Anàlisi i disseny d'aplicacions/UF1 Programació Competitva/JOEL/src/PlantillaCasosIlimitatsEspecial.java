
import java.util.Scanner;
/* Exemple d'enunciat
 * Entrada: 
L�entrada consistir� en un nombre indeterminat de casos de prova. Cadascun d�ells consisteix un nombre enter.
Els casos de prova finalitzaran amb n�mero -1, que marcar� el final de l'entrada i que no ser� processat.
Sortida: 
Per cada cas de prova el programa escriur� PAR si el cas de prova �s un nombre parell i escriur� IMPAR
si el nombre �s imparell.
 */

public class PlantillaCasosIlimitatsEspecial {
	
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) {
	
		while (casosDeProva())
			;
	}

	public static boolean casosDeProva() {
		// Resolem el cas de prova
		int prova;
		prova = teclat.nextInt();
		if (prova == -1)
			return false; // marca la sortida del bucle quan es compleix la condici�
		if ((prova % 2) == 0)
			System.out.println("PAR");
		else
			System.out.println("IMPAR");
		return true;
	}

}
