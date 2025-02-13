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

public class DOM3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println();

            System.out.println("0. Sortir");
            System.out.println("1. Llegir hotels");
            System.out.println("2. Afegir hotel");
            System.out.println("3. Afegir habitació");
            System.out.println("4. Modificar preu habitació");
            System.out.println("5. Llistar habitacions hotel");
            
            System.out.println();
            System.out.println("========================================");
            System.out.println();
        
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();
        
            System.out.println();
        
            switch (opcio) {
                case 1:
                    LLegirHotels();
                    break;

                case 2:
                    AfegirHotel();
                    break;

                case 3:
                    AfegirHabitacio();
                    break;

                case 4:
                    ModificarPreuHabitacio();
                    break;

                case 5:
                    LlistarHabitacionsHotel();
                    break;
        
        
                case 0:
                    System.out.println("Sortint del programa...");
                    return;
        
        
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    public static void LLegirHotels() {

        try {
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodeListHotel = document.getElementsByTagName("hotel");

            for (int i = 0; i < nodeListHotel.getLength(); i++) {
                Node nodeHotel = nodeListHotel.item(i);

                if (nodeHotel.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementHotel = (Element) nodeHotel;

                    String nom = elementHotel.getElementsByTagName("nom").item(0).getTextContent();
                    String adreca = elementHotel.getElementsByTagName("adreca").item(0).getTextContent();

                    System.out.println("Nom: " + nom);
                    System.out.println("Adreça: " + adreca);
                    System.out.println("Habitacions:");

                    NodeList nodeListHabitacio = elementHotel.getElementsByTagName("habitacio");

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
                            System.out.println("\t---------------");
                        }
                    }
                    System.out.println();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void AfegirHotel() {
        try {
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            Element nouHotel = document.createElement("hotel");

            Element nom = document.createElement("nom");
            System.out.print("Introdueix el nom de l'hotel: ");
            String nomHotel = sc.nextLine();
            nom.appendChild(document.createTextNode(nomHotel));

            Element adreca = document.createElement("adreca");
            System.out.print("Introdueix l'adreca de l'hotel: ");
            String adrecaHotel = sc.nextLine();
            adreca.appendChild(document.createTextNode(adrecaHotel));

            Element habitacions = document.createElement("habitacions");
            System.out.print("Introdueix el nombre habitacions de l'hotel: ");
            int numHabitacions = sc.nextInt();

            for (int i = 0; i < numHabitacions; i++) {
                Element nouHabitacio = document.createElement("habitacio");

                Element numero = document.createElement("numero");
                System.out.print("Introdueix el numero de l'habitacio: ");
                int numeroHabitacio = sc.nextInt();
                numero.appendChild(document.createTextNode(String.valueOf(numeroHabitacio)));

                Element tipus = document.createElement("tipus");
                System.out.print("Introdueix el tipus de l'habitacio (1.Indiviual - 2.Doble): ");
                int tipusHabitacio = sc.nextInt();
                String tipusHabitacioResult = "";
                switch (tipusHabitacio) {
                    case 1:
                        tipusHabitacioResult = "Indiviual";
                        break;
                    
                    case 2:
                        tipusHabitacioResult = "Doble";
                        break;
                
                    default:
                        System.out.println("No valida.");
                        break;
                }
                tipus.appendChild(document.createTextNode(tipusHabitacioResult));

                Element preu = document.createElement("preu");
                System.out.print("Introdueix el preu de l'habitacio: ");
                double preuHabitacio = sc.nextDouble();
                preu.appendChild(document.createTextNode(String.valueOf(preuHabitacio)));

                System.out.println();

                nouHabitacio.appendChild(numero);
                nouHabitacio.appendChild(tipus);
                nouHabitacio.appendChild(preu);

                habitacions.appendChild(nouHabitacio);
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

            System.out.println("Hotel afegit correctament!!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AfegirHabitacio() {
        try {
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix.");
                return;
            }

            System.out.print("Nom de l'hotel per afegir una habitació: ");
            String nomHotel = sc.nextLine();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodeListHotel = document.getElementsByTagName("hotel");
            Element hotelTrobat = null;

            for (int i = 0; i < nodeListHotel.getLength(); i++) {
                Element elementHotel = (Element) nodeListHotel.item(i);

                String nom = elementHotel.getElementsByTagName("nom").item(0).getTextContent();

                if (nom.equalsIgnoreCase(nomHotel)) {
                    hotelTrobat = elementHotel;
                    break;
                }
            }

            if (hotelTrobat == null) {
                System.out.println("No s'ha trobat l'hotel.");
                return;
            }

            Element elementHabitacions = (Element) hotelTrobat.getElementsByTagName("habitacions").item(0);

            Element elementHabitacio = document.createElement("habitacio");

            Element numero = document.createElement("numero");
            System.out.print("Introdueix el numero d'habitació: ");
            int numResult = sc.nextInt();
            numero.appendChild(document.createTextNode(String.valueOf(numResult)));

            Element tipus = document.createElement("tipus");
            System.out.print("Tipus d'habitació (1. individual - 2. doble): ");
            int tipusHabitacio = sc.nextInt();
    
            String tipusHabitacioResult = (tipusHabitacio == 1) ? "individual" : "doble";
            tipus.appendChild(document.createTextNode(tipusHabitacioResult));
    
            Element preu = document.createElement("preu");
            System.out.print("Preu d'habitació: ");
            int preuHabitacio = sc.nextInt();
            preu.appendChild(document.createTextNode(String.valueOf(preuHabitacio)));
    
            sc.nextLine();

            elementHabitacio.appendChild(numero);
            elementHabitacio.appendChild(tipus);
            elementHabitacio.appendChild(preu);

            elementHabitacions.appendChild(elementHabitacio);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            System.out.println("\nS'ha afegit l'habitació correctament!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ModificarPreuHabitacio() {
        try {
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            System.out.print("Introdueix el nom de l'hotel: ");
            String nomHotel = sc.nextLine();
            System.out.print("Introdueix el numero d'habitació: ");
            int numHabitacio = sc.nextInt();

            sc.nextLine();

            System.out.println();

            NodeList nodeListHotel = document.getElementsByTagName("hotel");
            boolean hotelTrobat = false;
            boolean habitacioTrobada = false;

            for (int i = 0; i < nodeListHotel.getLength(); i++) {
                Element elementHotel = (Element) nodeListHotel.item(i);

                String nom = elementHotel.getElementsByTagName("nom").item(0).getTextContent();
                
                if (nom.equalsIgnoreCase(nomHotel)) {
                    hotelTrobat = true;

                    NodeList nodeListHabitacio = elementHotel.getElementsByTagName("habitacio");

                    for (int j = 0; j < nodeListHabitacio.getLength(); j++) {
                        Element elementHabitacio = (Element) nodeListHabitacio.item(j);

                        String numero = elementHabitacio.getElementsByTagName("numero").item(0).getTextContent();

                        if (numero.equalsIgnoreCase(String.valueOf(numHabitacio))) {
                            habitacioTrobada = true;

                            String preuActual = elementHabitacio.getElementsByTagName("preu").item(0).getTextContent();

                            System.out.println("Preu actual: " + preuActual);

                            Element preu = (Element) elementHabitacio.getElementsByTagName("preu").item(0);
                            System.out.print("Introdueix el nou preu: ");
                            double preuFinal = sc.nextDouble();

                            preu.setTextContent(String.valueOf(preuFinal));

                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                            DOMSource source = new DOMSource(document);
                            StreamResult result = new StreamResult(fitxer);

                            transformer.transform(source, result);

                            System.out.println("\nPreu canviat correctament!!");
                        }
                    }
                }
            }

            if (hotelTrobat == false) {
                System.out.println("Hotel no trobat.");
                return;
            }
            if (habitacioTrobada == false) {
                System.out.println("Habitacio no trobada");
                return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LlistarHabitacionsHotel() {
        try {
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            System.out.print("Introdueix l'hotel a llistar les habitacions: ");
            String nomHotel = sc.nextLine();

            System.out.println();


            NodeList nodeListHotel = document.getElementsByTagName("hotel");
            boolean hotelTrobat = false;

            for (int i = 0; i < nodeListHotel.getLength(); i++) {
                Node nodeHotel = nodeListHotel.item(i);

                if (nodeHotel.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementHotel = (Element) nodeHotel;

                    String nom = elementHotel.getElementsByTagName("nom").item(0).getTextContent();

                    if (nom.equalsIgnoreCase(nomHotel)) {
                        hotelTrobat = true;

                        String adreca = elementHotel.getElementsByTagName("adreca").item(0).getTextContent();

                        System.out.println("Hotel: " + nom);
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

                                System.out.println("\tNumero: " + numero);
                                System.out.println("\tTipus: " + tipus);
                                System.out.println("\tPreu: " + preu);
                                System.out.println("\t--------------");
                            }
                        }
                    }
                }
            }

            if (hotelTrobat == false) {
                System.out.println("No s'ha trobat cap hotel amb aquest nom.");
                return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
