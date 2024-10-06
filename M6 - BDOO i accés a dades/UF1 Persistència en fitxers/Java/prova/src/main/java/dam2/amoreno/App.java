package dam2.amoreno;


import java.util.Scanner;

/* DOM */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
/* SAX */
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
/* XSTREAM */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args )
    {
        System.out.println("0. Sortir");
        System.out.println("1. DOM");
        System.out.println("2. SAX");
        System.out.println("3. XStream");

        System.out.println();

        System.out.print("Selecciona una opció: ");
        int opcio = sc.nextInt();

        System.out.println();


        switch (opcio) {
            case 0:
                System.out.println("Sortin del programa...");
                break;

            case 1:
                DOM();
                break;

            case 2: 
                SAX();
                break;
            
            case 3:
                XStream();
                break;
        
            default:
                break;
        }
       
    }

    public static void DOM() {

        System.out.println("1. Llegir");
        System.out.println("2. Escriure");

        System.out.println();

        System.out.print("Selecciona una opció: ");
        int opcio = sc.nextInt();

        System.out.println();

        switch (opcio) {
            case 1:
                DOMLlegir();
                break;

            case 2:
                DOMEscriure();
                break;
        
            default:
                break;
        }

    }

    public static void DOMLlegir() {

        System.out.print("Escriure la ruta del fitxer: ");
        String ruta = sc.next();


        try {

            File file = new File(ruta);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("agenda");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String name = element.getElementsByTagName("nom").item(0).getTextContent();
                    String surnames = element.getElementsByTagName("cognom").item(0).getTextContent();
                    String mail = element.getElementsByTagName("mail").item(0).getTextContent();
                    String number = element.getElementsByTagName("telefon").item(0).getTextContent();

                    System.out.println("Nom: " + name);
                    System.out.println("Cognoms: " + surnames);
                    System.out.println("Mail: " + mail);
                    System.out.println("Telefon: " + number);
                    System.out.println();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DOMEscriure() {
        System.out.print("Escriure la ruta del fitxer: ");
        String ruta = sc.next();
        System.out.println();

        try {
            File file = new File(ruta);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(file);

            doc.getDocumentElement().normalize();

            Element newPersona = doc.createElement("persona");

            Element newName = doc.createElement("nom");
            System.out.print("Escriu el nom de la nova persona: ");
            String nameText = sc.next();
            newName.appendChild(doc.createTextNode(nameText));


            Element newSurname = doc.createElement("cognom");
            System.out.print("Escriu el cognom de la nova persona: ");
            String surnameText = sc.next();
            newSurname.appendChild(doc.createTextNode(surnameText));


            Element newMail = doc.createElement("mail");
            System.out.print("Escriu el mail de la nova persona: ");
            String mailText = sc.next();
            newMail.appendChild(doc.createTextNode(mailText));


            Element newNumber = doc.createElement("telefon");
            System.out.print("Escriu el telefon de la nova persona: ");
            String numberText = sc.next();
            newNumber.appendChild(doc.createTextNode(numberText));


            newPersona.appendChild(newName);
            newPersona.appendChild(newSurname);
            newPersona.appendChild(newMail);
            newPersona.appendChild(newNumber);

            doc.getDocumentElement().appendChild(newPersona);

            TransformerFactory tranformerFactory = TransformerFactory.newInstance();
            Transformer transformer = tranformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SAX() {

    }

    public static void XStream() {

    }
}
