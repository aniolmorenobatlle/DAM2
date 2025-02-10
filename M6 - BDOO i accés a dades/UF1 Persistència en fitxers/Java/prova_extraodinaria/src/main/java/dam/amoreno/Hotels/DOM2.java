package dam.amoreno.Hotels;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOM2 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println();
        
            System.out.println("0. Sortir");
            System.out.println("1. Llegir");
            System.out.println("2. Llegir hotel");
        
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
}
