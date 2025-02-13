package dam.amoreno;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
            System.out.println("4. Llegir ordenat");
            System.out.println("5. Modificar etiqueta");
            System.out.println("6. Afegir habitació");
        
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
                    LlegirOrdernat();
                    break;

                case 5:
                    ModificarEtiqueta();
                    break;

                case 6:
                    AfegirHabitacio();
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

    public static void LlegirSub() {
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

    public static void LlegirOrdernat() {
        try {
            File fitxer = new File("prova_extraodinaria/xmls/tasques.xml");

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodeListTasca = document.getElementsByTagName("tasca");
            List<Ordernar> listPrioritat = new ArrayList<>();

            for (int i = 0; i < nodeListTasca.getLength(); i++) {
                Node node = nodeListTasca.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String nom = element.getElementsByTagName("nom").item(0).getTextContent();
                    String data = element.getElementsByTagName("data").item(0).getTextContent();
                    String prioritat = element.getElementsByTagName("prioritat").item(0).getTextContent();
                    String categoria = element.getElementsByTagName("categoria").item(0).getTextContent();

                    listPrioritat.add(new Ordernar(nom, data, prioritat, categoria));
                }
            }

            listPrioritat.sort(Comparator.comparingInt(Ordernar::getNivellPrioritat));

            for (Ordernar o : listPrioritat) {
                System.out.println(o);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ModificarEtiqueta() {
        try {
            File fitxer = new File("prova_extraodinaria/xmls/hotels.xml");

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix.");
                return;
            }

            System.out.print("Introdueix el nom de l'hotel: ");
            String nomHotel = sc.nextLine();
            System.out.print("Introdueix el numero d'habitació: ");
            int numHabitacio = sc.nextInt();

            sc.nextLine();
            System.out.println();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

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

                        if (numero.equals(String.valueOf(numHabitacio))) {
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

                            System.out.println("Preu canviat correctament!!");
                        }
                    }
                }
            }

            if (!hotelTrobat) System.out.println("No s'ha trobat cap hotel amb aquest nom.");
            if (!habitacioTrobada) System.out.println("No s'ha trobat el numero d'habitacio en aquest hotel.");
            
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

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            System.out.print("Introdueix el nom de l'hotel: ");
            String nomHotel = sc.nextLine();

            NodeList nodeListHotel = document.getElementsByTagName("hotel");
            Element hotelTrobat = null;

            for (int i = 0; i < nodeListHotel.getLength(); i++) {
                Element elementHotel = (Element) nodeListHotel.item(i);

                String hotel = elementHotel.getElementsByTagName("nom").item(0).getTextContent();

                if (hotel.equalsIgnoreCase(nomHotel)) {
                    hotelTrobat = elementHotel;
                    break;
                }
            }

            if (hotelTrobat == null) {
                System.out.println("Hotel no trobat."); 
                return;
            }

            Element habitacions = (Element) hotelTrobat.getElementsByTagName("habitacions").item(0);

            // Crear nova habitacio
            Element habitacio = document.createElement("habitacio");

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

            habitacio.appendChild(numero);
            habitacio.appendChild(tipus);
            habitacio.appendChild(preu);

            habitacions.appendChild(habitacio);

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

}

class Ordernar {
    private String nom, data, prioritat, categoria;

    public Ordernar() {
    }

    public Ordernar(String nom, String data, String prioritat, String categoria) {
        this.nom = nom;
        this.data = data;
        this.prioritat = prioritat;
        this.categoria = categoria;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrioritat() {
        return prioritat;
    }

    public void setPrioritat(String prioritat) {
        this.prioritat = prioritat;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getNivellPrioritat() {
        switch (prioritat) {
            case "Alta":
                return 1;

            case "Moderada":
                return 2;
            
            case "Baixa":
                return 3;
        
            default:
                return 4;
        }
    }

    @Override
    public String toString() {
        return "Nom: " + nom
        + "\nData: " + data
        + "\nPrioritat: " + prioritat
        + "\nCategoria: " + categoria
        + "\n---------------";
    }
}