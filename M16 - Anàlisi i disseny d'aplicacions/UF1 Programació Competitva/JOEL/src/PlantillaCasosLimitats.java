
import java.util.Scanner;
/* Exemple d'enunciat
 * Entrada: 
La primera l�nia de l�entrada contindr� el nombre de casos de prova que el programa ha de llegir. 
A continuaci� vindr� un darrera l�altre tots els casos. Cadascun d�ells consisteix una �nica l�nia 
amb un nombre enter.
Sortida: 
Per cada cas de prova el programa escriur� PAR si el cas de prova �s un nombre parell i escriur� IMPAR
si el nombre �s imparell.
 */

public class PlantillaCasosLimitats {
	static int casos;
	static Scanner teclat = new Scanner(System.in);
	
	public static void main(String[] args) {
		casos=teclat.nextInt();
		for (int i=0; i<casos; i++) {
			casDeProva();
		}
	}
	public static void casDeProva() {
		//Resolem el cas de prova
	}

}
