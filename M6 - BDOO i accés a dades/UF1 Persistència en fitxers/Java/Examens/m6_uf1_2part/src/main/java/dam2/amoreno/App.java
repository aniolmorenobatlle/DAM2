package dam2.amoreno;

import java.io.File;
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
import java.util.Scanner;

public class App 
{
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args )
    {
        System.out.println("1. Afegir nova habitació a un hotel: ");
        System.out.println("2. Modificar el preu d'una habitació: ");
        System.out.println("3. LListar tots el hotels: ");
        System.out.println("4. LListar les habitacions d'un hotel determinat: ");
        
        System.out.println();

        System.out.print("Selecciona una opció: ");
        int opcio = sc.nextInt();

        System.out.println();

        switch (opcio) {
            case 1:
                afegirHabitacio();
                break;

            case 2:
                modificarPreu();
                break;

            case 3:
                llistarHotels();
                break;

            case 4:
                llistarHabitacions();
                break;
        
            default:
                break;
        }
    }


    // 1. Afegir habitació
    public static void afegirHabitacio() {

        System.out.print("Escriu la ruta del fitxer XML (amb el nom i l'extensió): ");
        String ruta = sc.next();
        System.out.println();
        
        File file = new File(ruta);

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(file);

            doc.normalize();


            System.out.print("Nom de l'hotel: ");
            String nom_hotel = sc.next();
            System.out.println();

            System.out.print("Número d'habitació: ");
            String num_habitacio = sc.next();
            System.out.println();

            System.out.print("Tipus d'habitació (individual, doble, suite): ");
            String tipus_habitacio = sc.next();
            System.out.println();

            System.out.print("Preu de l'habitació: ");
            String preu_habitacio = sc.next();
            System.out.println();

            Element hotel = doc.createElement(nom_hotel);
            Element habitacio = doc.createElement(num_habitacio);

            habitacio.appendChild(doc.createTextNode(preu_habitacio));
            habitacio.appendChild(doc.createTextNode(tipus_habitacio));

            doc.getDocumentElement().appendChild(hotel);
            hotel.appendChild(habitacio);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource();
            StreamResult result = new StreamResult();

            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 2. Modificar preu
    public static void modificarPreu() {

    }


    // 3. Llistar tots els hotels
    public static void llistarHotels() {

        System.out.print("Escriu la ruta del fitxer XML (amb el nom i l'extensió): ");
        String ruta = sc.next();
        System.out.println();
        
        File file = new File(ruta);


        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(file);

            doc.normalize();

            NodeList llista = doc.getElementsByTagName("hotel");


            System.out.println("HOTELS: ");
            System.out.println("------------------------------------------------------");


            for (int i = 0; i < llista.getLength(); i++) {
                Node nodeLlista = llista.item(i);


                if (nodeLlista.getNodeType() == Element.ELEMENT_NODE) {
                    Element elementLlista = (Element) nodeLlista;

                    String hotel_nom = elementLlista.getElementsByTagName("nom").item(0).getTextContent();
                    String hotel_adreca = elementLlista.getElementsByTagName("adreça").item(0).getTextContent();


                    

                    System.out.println("Nom de l'hotel: " + hotel_nom);
                    System.out.println("Adreça de l'hotel: " + hotel_adreca);



                    for (int j = 0; j < llista.getLength(); j++) {
                        String habitacio_numero = elementLlista.getElementsByTagName("número").item(j).getTextContent();
                        String habitacio_tipus = elementLlista.getElementsByTagName("tipus").item(j).getTextContent();
                        String habitacio_preu = elementLlista.getElementsByTagName("preu").item(j).getTextContent();

                                            
                        System.out.println("    Habitació:");

                        System.out.println("        Número: " + habitacio_numero);
                        System.out.println("        Tipus: " + habitacio_tipus);
                        System.out.println("        Preu: " + habitacio_preu);
                    }


                    System.out.println("------------------------------------------------------");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 4. LListar habitacions
    public static void llistarHabitacions() {
        System.out.print("Escriu la ruta del fitxer XML (amb el nom i l'extensió): ");
        String ruta = sc.next();
        System.out.println();
        
        File file = new File(ruta);

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(file);

            doc.normalize();

            // System.out.print("Quin hotel vols llistar les habitacions? ");
            // String hotel = sc.next();

            NodeList llista = doc.getElementsByTagName("habitacions");


            System.out.println("HABITACIONS: ");
            System.out.println("------------------------------------------------------");


            for (int i = 0; i < llista.getLength(); i++) {
                Node nodeLlista = llista.item(i);


                if (nodeLlista.getNodeType() == Element.ELEMENT_NODE) {
                    Element elementLlista = (Element) nodeLlista;

                    for (int j = 0; j < llista.getLength(); j++) {
                        String habitacio_numero = elementLlista.getElementsByTagName("número").item(j).getTextContent();
                        String habitacio_tipus = elementLlista.getElementsByTagName("tipus").item(j).getTextContent();
                        String habitacio_preu = elementLlista.getElementsByTagName("preu").item(j).getTextContent();

                                            
                        System.out.println("    Habitació:");

                        System.out.println("        Número: " + habitacio_numero);
                        System.out.println("        Tipus: " + habitacio_tipus);
                        System.out.println("        Preu: " + habitacio_preu);
                    }


                    System.out.println("------------------------------------------------------");
                }
            }



            // falta poder triar l'hotel

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
