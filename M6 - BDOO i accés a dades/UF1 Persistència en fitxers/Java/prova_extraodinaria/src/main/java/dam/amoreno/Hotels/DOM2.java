package dam.amoreno.Hotels;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

public class DOM2 {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println();
        
            System.out.println("0. Sortir");
            System.out.println("1. Llegir");
            System.out.println("2. Llegir hotel");
            System.out.println("3. Afegir tasca");
            System.out.println("4. Afegir hotel");
        
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
                    LlegirHotel();
                    break;

                case 3:
                    AfegirTasca();
                    break;

                case 4:
                    AfegirHotel();
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

            File ruta = new File("prova_extraodinaria/xmls/hotels.xml");

            if (!ruta.exists()) {
                System.out.println("El fitxer no existeix!!");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(ruta);
            document.normalize();

            NodeList hotels = document.getElementsByTagName("hotel");

            for (int i = 0; i < hotels.getLength(); i++) {
                Node hotel = hotels.item(i);

                if (hotel.getNodeType() == Node.ELEMENT_NODE) {
                    Element hotelElement = (Element) hotel;
                
                    String nom = hotelElement.getElementsByTagName("nom").item(0).getTextContent();
                    String adreca = hotelElement.getElementsByTagName("adreca").item(0).getTextContent();

                    System.out.println("Nom: " + nom);
                    System.out.println("Adreça: " + adreca);
                    System.out.println("Habitacions:");

                    NodeList habitacions = hotelElement.getElementsByTagName("habitacio");

                    for (int j = 0; j < habitacions.getLength(); j++) {
                        Node habitacio = habitacions.item(j);

                        if (habitacio.getNodeType() == Node.ELEMENT_NODE) {
                            Element habitacioElement = (Element) habitacio;

                            String numero = habitacioElement.getElementsByTagName("numero").item(0).getTextContent();
                            String tipus = habitacioElement.getElementsByTagName("tipus").item(0).getTextContent();
                            String preu = habitacioElement.getElementsByTagName("preu").item(0).getTextContent();

                            System.out.println("\tNumero: " + numero);
                            System.out.println("\tTipus: " + tipus);
                            System.out.println("\tPreu: " + preu);
                            System.out.println("\t------------");
                        }
                    }
                    System.out.println("------------");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LlegirHotel() {
        try {
            File ruta = new File("prova_extraodinaria/xmls/hotels.xml");
            
            if (!ruta.exists()) {
                System.out.println("El fitxer no existeix!!");
                return;
            }

            System.out.print("Introdueix el nom del hotel a llistar: ");
            String hotel = sc.nextLine();

            System.out.println();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(ruta);
            document.getDocumentElement().normalize();

            NodeList nodeListHotel = document.getElementsByTagName("hotel");

            for (int i = 0; i < nodeListHotel.getLength(); i++) {
                Node nodeHotel = nodeListHotel.item(i);

                if (nodeHotel.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementHotel = (Element) nodeHotel;

                    String nom = elementHotel.getElementsByTagName("nom").item(0).getTextContent();

                    if (nom.equalsIgnoreCase(hotel)) {
                        String adreca = elementHotel.getElementsByTagName("adreca").item(0).getTextContent();

                        System.out.println("Nom de l'hotel: " + nom);
                        System.out.println("Adreca: " + adreca);
                        System.out.println("Habitacions:");

                        NodeList nodeListHabitacio = elementHotel.getElementsByTagName("habitacio");

                        for (int j = 0; j < nodeListHabitacio.getLength(); j++) {
                            Node nodeHabitacio = nodeListHabitacio.item(j);

                            if (nodeHabitacio.getNodeType() == Node.ELEMENT_NODE) {
                                Element elementHabitacio = (Element) nodeHabitacio;

                                String numero = elementHabitacio.getElementsByTagName("numero").item(0).getTextContent();
                                String tipus = elementHabitacio.getElementsByTagName("tipus").item(0).getTextContent();
                                String preu = elementHabitacio.getElementsByTagName("preu").item(0).getTextContent();

                                System.out.println("\tNumero: " + numero);
                                System.out.println("\tTipus: " + tipus);
                                System.out.println("\tPreu: " + preu);
                                System.out.println("\t----------");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AfegirTasca() {
        try {
            File fitxer = new File("prova_extraodinaria/xmls/tasques.xml");

            if (!fitxer.exists()) {
                System.out.println("L'arxiu no existeix.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            Element tasca = document.createElement("tasca");

            Element nom = document.createElement("nom");
            System.out.print("Introdueix el nom: ");
            String nomResult = sc.nextLine();
            nom.appendChild(document.createTextNode(nomResult));

            Element data = document.createElement("data");
            LocalDate dataResult = null;

            while (dataResult == null) {
                System.out.print("Introdueix la data (YYYY-MM-DD): ");
                String input = sc.nextLine();

                try {
                    dataResult = LocalDate.parse(input, FORMATTER);
                } catch (DateTimeParseException e) {
                    System.out.println("Format de data incorrecte!!");
                }
            }
            data.appendChild(document.createTextNode(String.valueOf(dataResult)));

            Element prioritat = document.createElement("prioritat");
            System.out.print("Introdueix la prioritat (Alta, Moderada, Baixa): ");
            String prioritatResult = sc.nextLine();
            prioritat.appendChild(document.createTextNode(prioritatResult));

            Element categoria = document.createElement("categoria");
            System.out.print("Introdueix la categoria (Feina, Personal): ");
            String categoriaResult = sc.nextLine();
            categoria.appendChild(document.createTextNode(categoriaResult));

            tasca.appendChild(nom);
            tasca.appendChild(data);
            tasca.appendChild(prioritat);
            tasca.appendChild(categoria);

            document.getDocumentElement().appendChild(tasca);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);
            
            System.out.println("\nTasca afegida correctament!!");
            
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

            Element hotel = document.createElement("hotel");

            Element nom = document.createElement("nom");
            System.out.print("Introdueix el nom del hotel: ");
            String nomResult = sc.nextLine();
            nom.appendChild(document.createTextNode(nomResult));

            Element adreca = document.createElement("adreca");
            System.out.print("Introdueix l'adreça: ");
            String adrecaResult = sc.nextLine();
            adreca.appendChild(document.createTextNode(adrecaResult));

            Element habitacions = document.createElement("habitacions");
            System.out.print("Nombre d'habitacions: ");
            int nombreHabitacions = sc.nextInt();

            sc.nextLine();

            for (int i = 0; i < nombreHabitacions; i++) {
                Element habitacio = document.createElement("habitacio");

                Element numero = document.createElement("numero");
                System.out.print("Introdueix el numero d'habitació: ");
                int numeroResult = sc.nextInt();
                numero.appendChild(document.createTextNode(String.valueOf(numeroResult)));

                sc.nextLine();

                Element tipus = document.createElement("tipus");
                System.out.print("Introdueix el tipus (Individual, Doble): ");
                String tipusResult = sc.nextLine();
                tipus.appendChild(document.createTextNode(tipusResult));

                Element preu = document.createElement("preu");
                System.out.print("Introdueix el preu: ");
                double preuResult = sc.nextDouble();
                preu.appendChild(document.createTextNode(String.valueOf(preuResult)));

                sc.nextLine();

                habitacio.appendChild(numero);
                habitacio.appendChild(tipus);
                habitacio.appendChild(preu);

                habitacions.appendChild(habitacio);
            }

            hotel.appendChild(nom);
            hotel.appendChild(adreca);
            hotel.appendChild(habitacions);

            document.getDocumentElement().appendChild(hotel);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
