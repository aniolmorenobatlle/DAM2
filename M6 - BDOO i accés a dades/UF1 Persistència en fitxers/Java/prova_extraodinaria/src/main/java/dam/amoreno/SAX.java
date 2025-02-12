package dam.amoreno;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAX {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println();
        
            System.out.println("0. Sortir");
            System.out.println("1. SAX");
        
            System.out.println();
            System.out.println("========================================");
            System.out.println();
        
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();
        
            System.out.println();
        
            switch (opcio) {
                case 1:
                    SAXLlegir();
                    break;
        
        
                case 0:
                    System.out.println("Sortint del programa...");
                    return;
        
        
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    public static void SAXLlegir() {
        String ruta = "prova_extraodinaria/xmls/tasques.xml";
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();

            File fitxer = new File(ruta);

            parser.parse(fitxer, handler);

            System.out.println();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
