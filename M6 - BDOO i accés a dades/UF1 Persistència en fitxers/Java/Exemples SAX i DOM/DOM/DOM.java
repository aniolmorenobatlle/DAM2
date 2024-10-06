package exempleDOM;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;

public class DOM {

    public static void main(String[] args) {
    	// Demana el fitxer XML a l'usuari
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix el nom del fitxer XML: ");
        String fitxerXML = scanner.nextLine();

        try {
            // Crea una instància de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Crea una instància de DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Carrega el fitxer XML i crea l'objecte Document
            Document document = builder.parse(new File(fitxerXML));

            // Normalitza l'estructura XML
            document.getDocumentElement().normalize();

            // Obtén la llista de tots els elements "llibre"
            NodeList llistaLlibres = document.getElementsByTagName("llibre");

            // Itera a través de la llista de llibres
            for (int i = 0; i < llistaLlibres.getLength(); i++) {
                Node node = llistaLlibres.item(i);

                // Comprova que el node sigui de tipus element
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element llibre = (Element) node;

                    // Obté el títol i l'autor
                    String titol = llibre.getElementsByTagName("titol").item(0).getTextContent();
                    String autor = llibre.getElementsByTagName("autor").item(0).getTextContent();

                    // Mostra els valors per pantalla
                    System.out.println("Títol: " + titol);
                    System.out.println("Autor: " + autor);
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

