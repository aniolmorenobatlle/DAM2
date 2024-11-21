
import java.util.Scanner;
/* Exemple d'enunciat
 * Entrada: 
L�entrada consistir� en un nombre indeterminat de casos de prova. Cadascun d�ells consisteix un nombre enter.
Els casos de prova finalitzaran amb n�mero -1, que marcar� el final de l'entrada i que no ser� processat.
Sortida: 
Per cada cas de prova el programa escriur� PAR si el cas de prova �s un nombre parell i escriur� IMPAR
si el nombre �s imparell.
 */

public class PlantillaCasosIlimitats {
	static int casos;
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) {
		casos = teclat.nextInt();
		while (casosDeProva())
			;
	}

	public static boolean casosDeProva() {
		// Resolem el cas de prova
		int prova;
		if (!teclat.hasNext())
			return false;// marca la sortida perque no hi ha m�s entrades.
		prova = teclat.nextInt();

		if ((prova % 2) == 0)
			System.out.println("PAR");
		else
			System.out.println("IMPAR");
		return true;
	}

}
