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

public class DOM {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println();
        
            System.out.println("0. Sortir");
            System.out.println("1. Llegir");
            System.out.println("2. Escriure");
        
            System.out.println();
            System.out.println("========================================");
            System.out.println();
        
        
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();
        
        
            System.out.println();
        
        
            switch (opcio) {
                case 1:
                    Llegir();
                    break;
        
                case 2:
                    Escriure();
                    break;
        
                case 0:
                    System.out.println("Sortint del programa...");
                    return;
        
        
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    public static void Llegir() {
        try {
            System.out.println();

            System.out.print("Introdueix la ruta del fitxer XML: ");
            String ruta = sc.nextLine();
            File fitxer = new File(ruta);

            System.out.println();

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(fitxer);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("persona");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String nom = element.getElementsByTagName("nom").item(0).getTextContent();
                    String cognom = element.getElementsByTagName("cognom").item(0).getTextContent();
                    String mail = element.getElementsByTagName("mail").item(0).getTextContent();
                    String telefon = element.getElementsByTagName("telefon").item(0).getTextContent();

                    System.out.println("Nom: " + nom);
                    System.out.println("Cognom: " + cognom);
                    System.out.println("Mail: " + mail);
                    System.out.println("Telefon: " + telefon);
                    System.out.println("----------------------");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error en llegir el fitxer.");
            e.printStackTrace();
        }
    }

    public static void Escriure() {
        try {
            System.out.println();

            System.out.print("Introdueix la ruta del fitxer XML: ");
            String ruta = sc.nextLine();
            File fitxer = new File(ruta);

            System.out.println();

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(fitxer);

            document.getDocumentElement().normalize();

            Element novaPersona = document.createElement("persona");

            Element nom = document.createElement("nom");
            System.out.print("Nom: ");
            String nomResult = sc.nextLine();
            nom.appendChild(document.createTextNode(nomResult));

            Element cognom = document.createElement("cognom");
            System.out.print("Cognom: ");
            String cognomResult = sc.nextLine();
            cognom.appendChild(document.createTextNode(cognomResult));

            Element mail = document.createElement("mail");
            System.out.print("Mail: ");
            String mailResult = sc.nextLine();
            mail.appendChild(document.createTextNode(mailResult));

            Element telefon = document.createElement("telefon");
            System.out.print("Telefon: ");
            String telefonResult = sc.nextLine();
            telefon.appendChild(document.createTextNode(telefonResult));

            novaPersona.appendChild(nom);
            novaPersona.appendChild(cognom);
            novaPersona.appendChild(mail);
            novaPersona.appendChild(telefon);

            document.getDocumentElement().appendChild(novaPersona);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            System.out.println("S'ha afegit els elements correctament!!");


        } catch (Exception e) {
            System.out.println("Error en escriure el fitxer.");
            e.printStackTrace();
        }
    }

}
