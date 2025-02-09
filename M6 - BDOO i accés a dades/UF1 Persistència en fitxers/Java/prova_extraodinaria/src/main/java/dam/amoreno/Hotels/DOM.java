package dam.amoreno.Hotels;

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
            System.out.println("3. Llegir sub etiquetes");
            System.out.println("4. Llistar habitacions d'un hotel determinat");
            System.out.println("5. Modificar preu habitació");
        
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

                case 3:
                    LlegirSub();
                    break;

                case 4:
                    LlegitHabitacionsHotel();
                    break;

                case 5:
                    ModificarPreuHabitacio();
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
            // System.out.print("Introdueix la ruta del fitxer XML: ");
            // String ruta = sc.nextLine();
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");

            System.out.println();

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(fitxer);

            document.getDocumentElement().normalize();

            Element nouHotel = document.createElement("hotel");

            Element nom = document.createElement("nom");
            System.out.print("Nom de l'hotel: ");
            String nomResult = sc.nextLine();
            nom.appendChild(document.createTextNode(nomResult));

            Element adreca = document.createElement("adreca");
            System.out.print("Adreça de l'hotel: ");
            String adrecaResult = sc.nextLine();
            adreca.appendChild(document.createTextNode(adrecaResult));

            Element habitacions = document.createElement("habitacions");
            System.out.print("Nombre d'habitacions: ");
            int numHabitacions = sc.nextInt();

            sc.nextLine();

            for (int i = 0; i < numHabitacions; i++) {
                Element habitacio = document.createElement("habitacio");

                Element numero = document.createElement("numero");
                System.out.print("Número de l'habitació " + (i + 1) + ": ");
                int numHabitacio = sc.nextInt();
                numero.appendChild(document.createTextNode(String.valueOf(numHabitacio)));

                Element tipus = document.createElement("tipus");
                System.out.print("Tipus d'habitació (1. individual - 2. doble): ");
                int tipusHabitacio = sc.nextInt();

                String tipusHabitacioResult = "";
                switch (tipusHabitacio) {
                    case 1:
                        tipusHabitacioResult = "individual";
                        break;
                    case 2:
                        tipusHabitacioResult = "doble";
                        break;
                    default:
                        System.out.println("Opció no disponible.");
                        return;
                }

                tipus.appendChild(document.createTextNode(tipusHabitacioResult));

                sc.nextLine();

                Element preu = document.createElement("preu");
                System.out.print("Preu d'habitació: ");
                int preuHabitacio = sc.nextInt();
                preu.appendChild(document.createTextNode(String.valueOf(preuHabitacio)));

                habitacio.appendChild(numero);
                habitacio.appendChild(tipus);
                habitacio.appendChild(preu);

                habitacions.appendChild(habitacio);
            }

            
            nouHotel.appendChild(nom);
            nouHotel.appendChild(adreca);
            nouHotel.appendChild(habitacions);

            document.getDocumentElement().appendChild(nouHotel);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            System.out.println("\nS'ha afegit els elements correctament!!");


        } catch (Exception e) {
            System.out.println("Error en escriure el fitxer.");
            e.printStackTrace();
        }
    }

    public static void LlegirSub() {
        try {

            // System.out.print("Introdueix la ruta del fitxer XML: ");
            // String ruta = sc.nextLine();
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");

            System.out.println();

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(fitxer);

            document.getDocumentElement().normalize();

            NodeList nodeListHabitacions = document.getElementsByTagName("hotel");

            for (int i = 0; i < nodeListHabitacions.getLength(); i++) {
                Node nodeHabitacions = nodeListHabitacions.item(i);

                if (nodeHabitacions.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementHabitacions = (Element) nodeHabitacions;

                    String nom = elementHabitacions.getElementsByTagName("nom").item(0).getTextContent();
                    String adreca = elementHabitacions.getElementsByTagName("adreca").item(0).getTextContent();

                    System.out.println("Nom: " + nom);
                    System.out.println("Adreça: " + adreca);
                    System.out.println("Habitació");

                    NodeList nodeListHabitacio = elementHabitacions.getElementsByTagName("habitacio");

                    for (int j = 0; j < nodeListHabitacio.getLength(); j++) {
                        Node nodeHabitacio = nodeListHabitacio.item(j);

                        if (nodeHabitacio.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementHabitacio = (Element) nodeHabitacio;

                            String numero = elementHabitacio.getElementsByTagName("numero").item(0).getTextContent();
                            String tipus = elementHabitacio.getElementsByTagName("tipus").item(0).getTextContent();
                            String preu = elementHabitacio.getElementsByTagName("preu").item(0).getTextContent();
                            
                            System.out.println("\tNúmero: " + numero);
                            System.out.println("\tTipus: " + tipus);
                            System.out.println("\tPreu: " + preu + "€");
                            System.out.println("\t--------------");

                        }
                    }
                }
                
                System.out.println("--------------");
            }
            
        } catch (Exception e) {
            System.out.println("Error en llegir el fitxer.");
            e.printStackTrace();
        }
    }

    public static void LlegitHabitacionsHotel() {
        try {
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");

            System.out.print("Introdueix el nom de l'hotel que vols consultar: ");
            String nomHotelConsultat = sc.nextLine();

            System.out.println();

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcta.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodeListHotels = document.getElementsByTagName("hotel");

            boolean hotelFound = false;

            for (int i = 0; i < nodeListHotels.getLength(); i++) {
                Node nodeHotel = nodeListHotels.item(i);

                if (nodeHotel.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementHotel = (Element) nodeHotel;

                    String nom = elementHotel.getElementsByTagName("nom").item(0).getTextContent();

                    if (nom.equalsIgnoreCase(nomHotelConsultat)) {
                        hotelFound = true;

                        String adreca = elementHotel.getElementsByTagName("adreca").item(0).getTextContent();

                        System.out.println("Nom de l'hotel: " + nom);
                        System.out.println("Adreça: " + adreca);
                        System.out.println("Habitacions:");

                        NodeList nodeListHabitacions = elementHotel.getElementsByTagName("habitacio");

                        for (int j = 0; j < nodeListHabitacions.getLength(); j++) {
                            Node nodeHabitacio = nodeListHabitacions.item(j);

                            if (nodeHabitacio.getNodeType() == Node.ELEMENT_NODE) {
                                Element elementHabitacio = (Element) nodeHabitacio;

                                String numero = elementHabitacio.getElementsByTagName("numero").item(0).getTextContent();
                                String tipus = elementHabitacio.getElementsByTagName("tipus").item(0).getTextContent();
                                String preu = elementHabitacio.getElementsByTagName("preu").item(0).getTextContent();

                                System.out.println("\tNúmero: " + numero);
                                System.out.println("\tTipus: " + tipus);
                                System.out.println("\tPreu: " + preu + "€");
                                System.out.println("\t--------------");
                            }
                        }
                    }
                }
            }

            if (!hotelFound) {
                System.out.println("No s'ha trobat cap hotel amb el nom especificat.");
            }

        } catch (Exception e) {
            System.out.println("Error en llegir el fitxer.");
            e.printStackTrace();
        }
    }

    public static void ModificarPreuHabitacio() {
        try {
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");
            System.out.println();

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcta.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            System.out.print("Introdueix el nom de l'hotel: ");
            String nomHotelBuscat = sc.nextLine();

            System.out.print("Introdueix el número de l'habitació: ");
            int numHabitacioBuscada = sc.nextInt();

            System.out.println();

            // Buscar hotel
            NodeList nodeListHotels = document.getElementsByTagName("hotel");
            boolean hotelTrobat = false;
            boolean habitacioTrobada = false;

            for (int i = 0; i < nodeListHotels.getLength(); i++) {
                Element elementHotel = (Element) nodeListHotels.item(i);
                String nomHotel = elementHotel.getElementsByTagName("nom").item(0).getTextContent();

                if (nomHotel.equalsIgnoreCase(nomHotelBuscat)) {
                    hotelTrobat = true;

                    // Buscar habitacio dins de l'hotel
                    NodeList nodeListHabitacions = elementHotel.getElementsByTagName("habitacio");

                    for (int j = 0; j < nodeListHabitacions.getLength(); j++) {
                        Element elementHabitacio = (Element) nodeListHabitacions.item(j);
                        String numHabitacio = elementHabitacio.getElementsByTagName("numero").item(0).getTextContent();

                        if (numHabitacio.equalsIgnoreCase(String.valueOf(numHabitacioBuscada))) {
                            habitacioTrobada = true;

                            // Mostrar preu actual
                            Element preuElement = (Element) elementHabitacio.getElementsByTagName("preu").item(0);
                            System.out.println("Preu actual: " + preuElement.getTextContent());
                        
                            // Demanar nou preu
                            System.out.print("Intodueix el nou preu: ");
                            int nouPreu = sc.nextInt();

                            // Modificar el preu
                            preuElement.setTextContent(String.valueOf(nouPreu));

                            // Guardar els canvis
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                            DOMSource source = new DOMSource(document);
                            StreamResult result = new StreamResult(fitxer);
                            transformer.transform(source, result);

                            System.out.println("El preu de l'habitació s'ha actualitzat correctament!!");
                        }
                    }
                }
            }

            if (!hotelTrobat) {
                System.out.println("no s'ha trbat cap hotel amb aquest nom.");
            } else if (!habitacioTrobada) {
                System.out.println("No s'ha trobat cpa habitació amb aquest número.");
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
