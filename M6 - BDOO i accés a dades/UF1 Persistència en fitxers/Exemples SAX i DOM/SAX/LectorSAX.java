package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.Scanner;

public class LectorSAX {

    public static void main(String[] args) {
    	// Demana el fitxer XML a l'usuari
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix el nom del fitxer XML: ");
        String fitxerXML = scanner.nextLine();

		 try {
		     // Crea una instància de SAXParserFactory
			 SAXParserFactory factory = SAXParserFactory.newInstance();
		
			 // Obté una instància de SAXParser
			 SAXParser saxParser = factory.newSAXParser();
		
			 // Crea un objecte del handler
			 SAXHandler handler = new SAXHandler();
		
			 // Llegeix i processa el document XML passat com a argument
		     File fitxer = new File(fitxerXML);
		     saxParser.parse(fitxer, handler);
		
		 } catch (Exception e) {
		     e.printStackTrace();
		 }

    }
}
