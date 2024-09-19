package plantilles;

import java.util.Scanner;
/* Exemple d'enunciat
 * Entrada: 
L’entrada consistirà en un nombre indeterminat de casos de prova. Cadascun d’ells consisteix un nombre enter.
Els casos de prova finalitzaran amb número -1, que marcarà el final de l'entrada i que no serà processat.
Sortida: 
Per cada cas de prova el programa escriurà PAR si el cas de prova és un nombre parell i escriurà IMPAR
si el nombre és imparell.
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
			return false; // marca la sortida del bucle quan es compleix la condició
		if ((prova % 2) == 0)
			System.out.println("PAR");
		else
			System.out.println("IMPAR");
		return true;
	}

}
