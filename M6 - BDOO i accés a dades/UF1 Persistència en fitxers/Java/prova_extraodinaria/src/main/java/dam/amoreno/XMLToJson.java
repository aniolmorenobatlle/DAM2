package dam.amoreno;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.XML;

public class XMLToJson {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // 1. Demanar la ruta del fixer XML i llegir el fitxer com una cadena
            System.out.print("Introdueix la ruta del fitxer XML: "); 
            String ruta = sc.nextLine();

            String xmlContent = new String(Files.readAllBytes(Paths.get(ruta)));

            // Convertir XML a JSON
            JSONObject json = XML.toJSONObject(xmlContent);

            // Guardar el fitxer JSON
            System.out.println();
            System.out.print("Introdueix la ruta on vols guardar el fitxer (el nom i l'extensio .json al fitxer inclos): ");
            String guardar = sc.nextLine();
            Files.write(Paths.get(guardar), json.toString(4).getBytes(), StandardOpenOption.CREATE);

            System.out.println();

            System.out.println("JSON guardat a: " + guardar);

        } catch (Exception e) {
            System.out.println("Error en convertir el fitxer XML a JSON.");
            e.printStackTrace();
        }
    }
}
