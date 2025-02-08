package dam.amoreno;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class App 
{
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args )
    {
        while (true) {
            System.out.println("\n========================================");
            System.out.println();
        
        
            System.out.println("0. Sortir");
            System.out.println("1. DOM");
        
        
            System.out.println();
            System.out.println("========================================");
            System.out.println();
        
        
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();
        
        
            System.out.println();
        
        
            switch (opcio) {
                case 1:
                    DOM();
                    break;
        
        
                case 0:
                    System.out.println("Sortint del programa...");
                    return;
        
        
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    public static void DOM() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println();
        
            System.out.println("0. Sortir");
            System.out.println("1. Llegir fitxer");
            System.out.println("2. Escriure en fitxer");
        
            System.out.println();
            System.out.println("========================================");
            System.out.println();
        
        
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();
        
        
            System.out.println();
        
        
            switch (opcio) {
                case 1:
                    DOMLlegir();
                    break;

                case 2:
                    DOMEscriure();
                    break;
        
        
                case 0:
                    System.out.println("Tirant enrera...");
                    return;
        
        
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    public static void DOMLlegir() {
        try {
            System.out.println();

            // 1. Ruta del fitxer
            System.out.print("Ruta del fitxer XML: ");
            String ruta = sc.nextLine();
            File fitxer = new File(ruta);

            System.out.println();

            if (!fitxer.exists()) {
                System.out.println("El fitxer no exiteix.");
                return;
            }

            // 2. Crear el parser
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 3. Carregar i parsejar el fitxer
            Document document = builder.parse(fitxer);

            // 4. Normalitzar el document
            document.getDocumentElement().normalize();

            // 5. Obtenir els elements
            NodeList nodeList = document.getElementsByTagName("persona");

            // 6. Iterar el NodeList
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // 7. Obtenir dades dels elements
                    String nom = element.getElementsByTagName("nom").item(0).getTextContent();
                    String cognom = element.getElementsByTagName("cognom").item(0).getTextContent();
                    String mail = element.getElementsByTagName("mail").item(0).getTextContent();
                    String telefon = element.getElementsByTagName("telefon").item(0).getTextContent();

                    // 8. Imprimir dades
                    System.out.println("Nom: " + nom);
                    System.out.println("Cognom: " + cognom);
                    System.out.println("Mail: " + mail);
                    System.out.println("Telèfon: " + telefon);
                    System.out.println("----------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DOMEscriure() {
        try {
            System.out.println();

            // 1. Ruta del fitxer
            System.out.print("Ruta del fitxer XML: ");
            String ruta = sc.nextLine();
            File fitxer = new File(ruta);

            System.out.println();

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix.");
                return;
            }

            // 2. Crear el parser
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 3. Carregar i parsejar el fitxer
            Document document = builder.parse(fitxer);

            // 4. Normalitzar el document
            document.getDocumentElement().normalize();

            // 5. Crear nous elements
            Element novaPersona = document.createElement("persona");

            Element nom = document.createElement("nom");
            System.out.print("Introdueix el nom: ");
            String nomEntrada = sc.nextLine();
            nom.appendChild(document.createTextNode(nomEntrada));

            Element cognom = document.createElement("cognom");
            System.out.print("Introdueix el cognom: ");
            String cognomEntrada = sc.nextLine();
            cognom.appendChild(document.createTextNode(cognomEntrada));

            Element mail = document.createElement("mail");
            System.out.print("Introdueix el mail: ");
            String mailEntrada = sc.nextLine();
            mail.appendChild(document.createTextNode(mailEntrada));

            Element telefon = document.createElement("telefon");
            System.out.print("Introdueix el telefon: ");
            String telefonEntrada = sc.nextLine();
            telefon.appendChild(document.createTextNode(telefonEntrada));

            // 6. Afegir els elements a persona
            novaPersona.appendChild(nom);
            novaPersona.appendChild(cognom);
            novaPersona.appendChild(mail);
            novaPersona.appendChild(telefon);

            // 7. Afegir la nova persona al document
            document.getDocumentElement().appendChild(novaPersona);

            // 8. Configurar el transformer per guardar els canvis
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // 9. Escriure els canivs al fitxer
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            System.out.println("\nNova persona afegida correctament!!");
            

        } catch (Exception e) {
            System.out.println("Error en afegir la nova persona");
            e.printStackTrace();
        }
    }

}
