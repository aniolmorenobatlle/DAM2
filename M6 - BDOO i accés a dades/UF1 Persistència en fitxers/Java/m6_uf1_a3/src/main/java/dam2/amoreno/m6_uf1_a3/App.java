package dam2.amoreno.m6_uf1_a3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.XML;
import org.json.JSONObject;



public class App 
{
	private static Scanner sc = new Scanner(System.in);
	
	
	 public static void main(String[] args) throws IOException {

	        System.out.print("Escriu la ruta de l'arxiu XML: ");
	        String rutaXml = sc.nextLine();

	        // Llegir fitxer XML com un string
	        String contingutXml = llegirFitxer(rutaXml);
	        
	        System.out.print("Vols que el JSON estigui a la mateixa ruta que XML? (S/N)");
	        String mateixa_ruta = sc.nextLine();
	        
			if (mateixa_ruta.equalsIgnoreCase("S")) {
				// Agafar el nom de l'arxiu XML sense el .xml
				String nomXml = new File(rutaXml).getName().replaceFirst("[.][^.]+$", "");
								
				// Agafar la ruta del XML menys l'arxiu
				String rutaJson = new File(rutaXml).getParent() + "/" +nomXml + ".json";
				
		        // Convertir XML a JSON
				JSONObject json = XML.toJSONObject(contingutXml);

		        // Escriure en el JSON
		        escriureFitxer(rutaJson, json.toString());

		        System.out.println("Arxiu JSON creat a la ruta: " + rutaJson);
				
			} else {
		        System.out.print("Escriu la ruta per guardar l'arxiu JSON: ");
		        String rutaJson = sc.nextLine();
		        
		        JSONObject json = XML.toJSONObject(contingutXml);

		        escriureFitxer(rutaJson, json.toString());

		        System.out.println("Arxiu JSON creat a la ruta: " + rutaJson);
			}

	    }
	 
	 	
	    private static String llegirFitxer(String ruta) throws IOException {
	        return new String(Files.readAllBytes(Paths.get(ruta)));
	    }
	    

	    private static void escriureFitxer(String ruta, String contingut) throws IOException {
	        Files.write(Paths.get(ruta), contingut.getBytes());
	    }
}
