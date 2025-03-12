package dam.amoreno;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

import org.json.JSONObject;
import org.json.XML;
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
            System.out.println("1. Mostra les competicions, agrupades per modalitat i ordenades alfabèticament.");
            System.out.println("2. Afegeix com a mínim una competició de cada modalitat.");
            System.out.println("3. Poder identificar les competicions per un codi.");
            System.out.println("4. Modifica el nombre d'inscripcions d'una competició concreta");
            System.out.println("5. Tranformar a JSON");
            System.out.println("6. Mostra les competicions d'una modalitat indicada per pantalla.");
            System.out.println("7. Mostra totes les competicions amb un preu d'inscripció inferior a 30€, agrupats per modalitat.");
            System.out.println("8. Mostra totes les competicions d'una competició en concret (Ciclisme) que es realitzen un dia concret (13/02/2025) i guarda-les en un fitxer anomenat ciclisme.txt.");
            System.out.println("9. Mostra les competicions, agrupades per modalitat i ordenadesalfabèticament.");
        
        
            System.out.println();
            System.out.println("========================================");
            System.out.println();
        
        
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();
        
        
            System.out.println();
        
        
            switch (opcio) {
                case 1:
                    agruparIOrdenar();
                    break;

                case 2: 
                    afegirModalitats();
                    break;

                case 3:
                    identificarCodi();
                    break;

                case 4:
                    modificarNombreInscripcions();
                    break;

                case 5:
                    tranformarJson();
                    break;

                case 6:
                    mostrarCompeticionsPerModalitat();
                    break;

                case 7:
                    mostrarCompeticions30();
                    break;

                case 8:
                    mostrarCompeticionsDia();
                    break;

                case 9:
                    mostrarCompeticionsOrdenades();
                    break;
        
        
                case 0:
                    System.out.println("Sortint del programa...");
                    return;
        
        
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    public static void agruparIOrdenar() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();



        } catch (Exception e) {
            System.out.println("Error en llegir el fitxer.");
        }
    }

    public static void afegirModalitats() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println();
        
        
            System.out.println("0. Sortir");
            System.out.println("1. Natació");
            System.out.println("2. Running");
            System.out.println("3. Ciclisme");
            System.out.println("4. Tennis");
            System.out.println("5. Golf");
        
        
            System.out.println();
            System.out.println("========================================");
            System.out.println();
        
        
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();
        
        
            System.out.println();
        
        
            switch (opcio) {
                case 1:
                    modalitatNatacio();
                    break;

                case 2: 
                    modalitatRunning();
                    break;

                case 3: 
                    modalitatCiclisme();
                    break;

                case 4:
                    modalitatTennis();
                    break;

                case 5:
                    modalitatGolf();
                    break;
        
        
                case 0:
                    System.out.println("Sortint del programa...");
                    return;
        
        
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    public static void modalitatNatacio() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            Element competicio = document.createElement("competicio");

            Element id = document.createElement("id");
            System.out.print("Introdueix l'id: ");
            int idResult = sc.nextInt();
            id.appendChild(document.createTextNode(String.valueOf(idResult)));

            sc.nextLine();

            Element nom = document.createElement("nom");
            System.out.print("Introdueix l'nom: ");
            String nomResult = sc.nextLine();
            nom.appendChild(document.createTextNode(nomResult));

            Element modalitat = document.createElement("modalitat");
            modalitat.appendChild(document.createTextNode("Natacio"));

            Element edicio = document.createElement("edicio");
            System.out.print("Introdueix l'edicio: ");
            int edicioResult = sc.nextInt();
            edicio.appendChild(document.createTextNode(String.valueOf(edicioResult)));

            sc.nextLine();

            Element localitzacio = document.createElement("localitzacio");
            System.out.print("Introdueix la localitzacio: ");
            String localitzacioResult = sc.nextLine();
            localitzacio.appendChild(document.createTextNode(localitzacioResult));

            Element data = document.createElement("data");
            System.out.print("Introdueix la data (DD/MM/YYYY): ");
            String dataResult = sc.nextLine();
            data.appendChild(document.createTextNode(dataResult));

            Element inscripcions = document.createElement("inscripcions");
            System.out.print("Introdueix el numero d'inscripcions: ");
            int inscripcionsResult = sc.nextInt();
            inscripcions.appendChild(document.createTextNode(String.valueOf(inscripcionsResult)));

            sc.nextLine();

            Element preu = document.createElement("preu");
            System.out.print("Introdueix el preu: ");
            int preuResult = sc.nextInt();
            preu.appendChild(document.createTextNode(String.valueOf(preuResult)));

            sc.nextLine();

            Element equips = document.createElement("equips");

            System.out.print("Nombre d'equips: ");
            int nombreEquips = sc.nextInt();

            sc.nextLine();

            for (int i = 0; i < nombreEquips; i++) {
                Element equip = document.createElement("equip");

                Element nomEquip = document.createElement("nom");
                System.out.print("Nom de l'equip: ");
                String nomEquipResult = sc.nextLine();
                nomEquip.appendChild(document.createTextNode(nomEquipResult));
    
                Element categoria = document.createElement("categoria");
                System.out.print("Categoria de l'equip: ");
                int categoriaResult = sc.nextInt();
                categoria.appendChild(document.createTextNode(String.valueOf(categoriaResult)));

                sc.nextLine();
    
                Element jugadors = document.createElement("jugadors");
                System.out.print("Nombre de judadors de l'equip: ");
                int jugadorsResult = sc.nextInt();
                jugadors.appendChild(document.createTextNode(String.valueOf(jugadorsResult)));

                sc.nextLine();
    
                equip.appendChild(nomEquip);
                equip.appendChild(categoria);
                equip.appendChild(jugadors);
    
                equips.appendChild(equip);
            }

            competicio.appendChild(id);
            competicio.appendChild(nom);
            competicio.appendChild(modalitat);
            competicio.appendChild(edicio);
            competicio.appendChild(localitzacio);
            competicio.appendChild(data);
            competicio.appendChild(inscripcions);
            competicio.appendChild(preu);
            competicio.appendChild(equips);

            document.getDocumentElement().appendChild(competicio);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            System.out.println("Fitxer editat correctament");

        } catch (Exception e) {
            System.out.println("Error en escriure en el fitxer.");
        }
    }
    
    public static void modalitatRunning() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            Element competicio = document.createElement("competicio");

            Element id = document.createElement("id");
            System.out.print("Introdueix l'id: ");
            int idResult = sc.nextInt();
            id.appendChild(document.createTextNode(String.valueOf(idResult)));

            sc.nextLine();

            Element nom = document.createElement("nom");
            System.out.print("Introdueix l'nom: ");
            String nomResult = sc.nextLine();
            nom.appendChild(document.createTextNode(nomResult));

            Element modalitat = document.createElement("modalitat");
            modalitat.appendChild(document.createTextNode("Running"));

            Element edicio = document.createElement("edicio");
            System.out.print("Introdueix l'edicio: ");
            int edicioResult = sc.nextInt();
            edicio.appendChild(document.createTextNode(String.valueOf(edicioResult)));

            sc.nextLine();

            Element localitzacio = document.createElement("localitzacio");
            System.out.print("Introdueix la localitzacio: ");
            String localitzacioResult = sc.nextLine();
            localitzacio.appendChild(document.createTextNode(localitzacioResult));

            Element data = document.createElement("data");
            System.out.print("Introdueix la data (DD/MM/YYYY): ");
            String dataResult = sc.nextLine();
            data.appendChild(document.createTextNode(dataResult));

            Element inscripcions = document.createElement("inscripcions");
            System.out.print("Introdueix el numero d'inscripcions: ");
            int inscripcionsResult = sc.nextInt();
            inscripcions.appendChild(document.createTextNode(String.valueOf(inscripcionsResult)));

            sc.nextLine();

            Element preu = document.createElement("preu");
            System.out.print("Introdueix el preu: ");
            int preuResult = sc.nextInt();
            preu.appendChild(document.createTextNode(String.valueOf(preuResult)));

            sc.nextLine();

            Element equips = document.createElement("equips");

            System.out.print("Nombre d'equips: ");
            int nombreEquips = sc.nextInt();

            sc.nextLine();

            for (int i = 0; i < nombreEquips; i++) {
                Element equip = document.createElement("equip");

                Element nomEquip = document.createElement("nom");
                System.out.print("Nom de l'equip: ");
                String nomEquipResult = sc.nextLine();
                nomEquip.appendChild(document.createTextNode(nomEquipResult));
    
                Element categoria = document.createElement("categoria");
                System.out.print("Categoria de l'equip: ");
                int categoriaResult = sc.nextInt();
                categoria.appendChild(document.createTextNode(String.valueOf(categoriaResult)));

                sc.nextLine();
    
                Element jugadors = document.createElement("jugadors");
                System.out.print("Nombre de judadors de l'equip: ");
                int jugadorsResult = sc.nextInt();
                jugadors.appendChild(document.createTextNode(String.valueOf(jugadorsResult)));

                sc.nextLine();
    
                equip.appendChild(nomEquip);
                equip.appendChild(categoria);
                equip.appendChild(jugadors);
    
                equips.appendChild(equip);
            }

            competicio.appendChild(id);
            competicio.appendChild(nom);
            competicio.appendChild(modalitat);
            competicio.appendChild(edicio);
            competicio.appendChild(localitzacio);
            competicio.appendChild(data);
            competicio.appendChild(inscripcions);
            competicio.appendChild(preu);
            competicio.appendChild(equips);

            document.getDocumentElement().appendChild(competicio);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            System.out.println("Fitxer editat correctament");

        } catch (Exception e) {
            System.out.println("Error en escriure en el fitxer.");
        }
    }

    public static void modalitatCiclisme() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            Element competicio = document.createElement("competicio");

            Element id = document.createElement("id");
            System.out.print("Introdueix l'id: ");
            int idResult = sc.nextInt();
            id.appendChild(document.createTextNode(String.valueOf(idResult)));

            sc.nextLine();

            Element nom = document.createElement("nom");
            System.out.print("Introdueix l'nom: ");
            String nomResult = sc.nextLine();
            nom.appendChild(document.createTextNode(nomResult));

            Element modalitat = document.createElement("modalitat");
            modalitat.appendChild(document.createTextNode("Ciclisme"));

            Element edicio = document.createElement("edicio");
            System.out.print("Introdueix l'edicio: ");
            int edicioResult = sc.nextInt();
            edicio.appendChild(document.createTextNode(String.valueOf(edicioResult)));

            sc.nextLine();

            Element localitzacio = document.createElement("localitzacio");
            System.out.print("Introdueix la localitzacio: ");
            String localitzacioResult = sc.nextLine();
            localitzacio.appendChild(document.createTextNode(localitzacioResult));

            Element data = document.createElement("data");
            System.out.print("Introdueix la data (DD/MM/YYYY): ");
            String dataResult = sc.nextLine();
            data.appendChild(document.createTextNode(dataResult));

            Element inscripcions = document.createElement("inscripcions");
            System.out.print("Introdueix el numero d'inscripcions: ");
            int inscripcionsResult = sc.nextInt();
            inscripcions.appendChild(document.createTextNode(String.valueOf(inscripcionsResult)));

            sc.nextLine();

            Element preu = document.createElement("preu");
            System.out.print("Introdueix el preu: ");
            int preuResult = sc.nextInt();
            preu.appendChild(document.createTextNode(String.valueOf(preuResult)));

            sc.nextLine();

            Element equips = document.createElement("equips");

            System.out.print("Nombre d'equips: ");
            int nombreEquips = sc.nextInt();

            sc.nextLine();

            for (int i = 0; i < nombreEquips; i++) {
                Element equip = document.createElement("equip");

                Element nomEquip = document.createElement("nom");
                System.out.print("Nom de l'equip: ");
                String nomEquipResult = sc.nextLine();
                nomEquip.appendChild(document.createTextNode(nomEquipResult));
    
                Element categoria = document.createElement("categoria");
                System.out.print("Categoria de l'equip: ");
                int categoriaResult = sc.nextInt();
                categoria.appendChild(document.createTextNode(String.valueOf(categoriaResult)));

                sc.nextLine();
    
                Element jugadors = document.createElement("jugadors");
                System.out.print("Nombre de judadors de l'equip: ");
                int jugadorsResult = sc.nextInt();
                jugadors.appendChild(document.createTextNode(String.valueOf(jugadorsResult)));

                sc.nextLine();
    
                equip.appendChild(nomEquip);
                equip.appendChild(categoria);
                equip.appendChild(jugadors);
    
                equips.appendChild(equip);
            }

            competicio.appendChild(id);
            competicio.appendChild(nom);
            competicio.appendChild(modalitat);
            competicio.appendChild(edicio);
            competicio.appendChild(localitzacio);
            competicio.appendChild(data);
            competicio.appendChild(inscripcions);
            competicio.appendChild(preu);
            competicio.appendChild(equips);

            document.getDocumentElement().appendChild(competicio);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            System.out.println("Fitxer editat correctament");

        } catch (Exception e) {
            System.out.println("Error en escriure en el fitxer.");
        }
    }

    public static void modalitatTennis() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            Element competicio = document.createElement("competicio");

            Element id = document.createElement("id");
            System.out.print("Introdueix l'id: ");
            int idResult = sc.nextInt();
            id.appendChild(document.createTextNode(String.valueOf(idResult)));

            sc.nextLine();

            Element nom = document.createElement("nom");
            System.out.print("Introdueix l'nom: ");
            String nomResult = sc.nextLine();
            nom.appendChild(document.createTextNode(nomResult));

            Element modalitat = document.createElement("modalitat");
            modalitat.appendChild(document.createTextNode("Tennis"));

            Element edicio = document.createElement("edicio");
            System.out.print("Introdueix l'edicio: ");
            int edicioResult = sc.nextInt();
            edicio.appendChild(document.createTextNode(String.valueOf(edicioResult)));

            sc.nextLine();

            Element localitzacio = document.createElement("localitzacio");
            System.out.print("Introdueix la localitzacio: ");
            String localitzacioResult = sc.nextLine();
            localitzacio.appendChild(document.createTextNode(localitzacioResult));

            Element data = document.createElement("data");
            System.out.print("Introdueix la data (DD/MM/YYYY): ");
            String dataResult = sc.nextLine();
            data.appendChild(document.createTextNode(dataResult));

            Element inscripcions = document.createElement("inscripcions");
            System.out.print("Introdueix el numero d'inscripcions: ");
            int inscripcionsResult = sc.nextInt();
            inscripcions.appendChild(document.createTextNode(String.valueOf(inscripcionsResult)));

            sc.nextLine();

            Element preu = document.createElement("preu");
            System.out.print("Introdueix el preu: ");
            int preuResult = sc.nextInt();
            preu.appendChild(document.createTextNode(String.valueOf(preuResult)));

            sc.nextLine();

            Element equips = document.createElement("equips");

            System.out.print("Nombre d'equips: ");
            int nombreEquips = sc.nextInt();

            sc.nextLine();

            for (int i = 0; i < nombreEquips; i++) {
                Element equip = document.createElement("equip");

                Element nomEquip = document.createElement("nom");
                System.out.print("Nom de l'equip: ");
                String nomEquipResult = sc.nextLine();
                nomEquip.appendChild(document.createTextNode(nomEquipResult));
    
                Element categoria = document.createElement("categoria");
                System.out.print("Categoria de l'equip: ");
                int categoriaResult = sc.nextInt();
                categoria.appendChild(document.createTextNode(String.valueOf(categoriaResult)));

                sc.nextLine();
    
                Element jugadors = document.createElement("jugadors");
                System.out.print("Nombre de judadors de l'equip: ");
                int jugadorsResult = sc.nextInt();
                jugadors.appendChild(document.createTextNode(String.valueOf(jugadorsResult)));

                sc.nextLine();
    
                equip.appendChild(nomEquip);
                equip.appendChild(categoria);
                equip.appendChild(jugadors);
    
                equips.appendChild(equip);
            }

            competicio.appendChild(id);
            competicio.appendChild(nom);
            competicio.appendChild(modalitat);
            competicio.appendChild(edicio);
            competicio.appendChild(localitzacio);
            competicio.appendChild(data);
            competicio.appendChild(inscripcions);
            competicio.appendChild(preu);
            competicio.appendChild(equips);

            document.getDocumentElement().appendChild(competicio);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            System.out.println("Fitxer editat correctament");

        } catch (Exception e) {
            System.out.println("Error en escriure en el fitxer.");
        }
    }

    public static void modalitatGolf() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            Element competicio = document.createElement("competicio");

            Element id = document.createElement("id");
            System.out.print("Introdueix l'id: ");
            int idResult = sc.nextInt();
            id.appendChild(document.createTextNode(String.valueOf(idResult)));

            sc.nextLine();

            Element nom = document.createElement("nom");
            System.out.print("Introdueix l'nom: ");
            String nomResult = sc.nextLine();
            nom.appendChild(document.createTextNode(nomResult));

            Element modalitat = document.createElement("modalitat");
            modalitat.appendChild(document.createTextNode("Golf"));

            Element edicio = document.createElement("edicio");
            System.out.print("Introdueix l'edicio: ");
            int edicioResult = sc.nextInt();
            edicio.appendChild(document.createTextNode(String.valueOf(edicioResult)));

            sc.nextLine();

            Element localitzacio = document.createElement("localitzacio");
            System.out.print("Introdueix la localitzacio: ");
            String localitzacioResult = sc.nextLine();
            localitzacio.appendChild(document.createTextNode(localitzacioResult));

            Element data = document.createElement("data");
            System.out.print("Introdueix la data (DD/MM/YYYY): ");
            String dataResult = sc.nextLine();
            data.appendChild(document.createTextNode(dataResult));

            Element inscripcions = document.createElement("inscripcions");
            System.out.print("Introdueix el numero d'inscripcions: ");
            int inscripcionsResult = sc.nextInt();
            inscripcions.appendChild(document.createTextNode(String.valueOf(inscripcionsResult)));

            sc.nextLine();

            Element preu = document.createElement("preu");
            System.out.print("Introdueix el preu: ");
            int preuResult = sc.nextInt();
            preu.appendChild(document.createTextNode(String.valueOf(preuResult)));

            sc.nextLine();

            Element equips = document.createElement("equips");

            System.out.print("Nombre d'equips: ");
            int nombreEquips = sc.nextInt();

            sc.nextLine();

            for (int i = 0; i < nombreEquips; i++) {
                Element equip = document.createElement("equip");

                Element nomEquip = document.createElement("nom");
                System.out.print("Nom de l'equip: ");
                String nomEquipResult = sc.nextLine();
                nomEquip.appendChild(document.createTextNode(nomEquipResult));
    
                Element categoria = document.createElement("categoria");
                System.out.print("Categoria de l'equip: ");
                int categoriaResult = sc.nextInt();
                categoria.appendChild(document.createTextNode(String.valueOf(categoriaResult)));

                sc.nextLine();
    
                Element jugadors = document.createElement("jugadors");
                System.out.print("Nombre de judadors de l'equip: ");
                int jugadorsResult = sc.nextInt();
                jugadors.appendChild(document.createTextNode(String.valueOf(jugadorsResult)));

                sc.nextLine();
    
                equip.appendChild(nomEquip);
                equip.appendChild(categoria);
                equip.appendChild(jugadors);
    
                equips.appendChild(equip);
            }

            competicio.appendChild(id);
            competicio.appendChild(nom);
            competicio.appendChild(modalitat);
            competicio.appendChild(edicio);
            competicio.appendChild(localitzacio);
            competicio.appendChild(inscripcions);
            competicio.appendChild(data);
            competicio.appendChild(preu);
            competicio.appendChild(equips);

            document.getDocumentElement().appendChild(competicio);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fitxer);
            transformer.transform(source, result);

            System.out.println("Fitxer editat correctament");

        } catch (Exception e) {
            System.out.println("Error en escriure en el fitxer.");
        }
    }

    public static void identificarCodi() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodelistCompeticio = document.getElementsByTagName("competicio");

            System.out.print("Introdueix l'id a buscar: ");
            String idBuscar = sc.nextLine();

            boolean idTrobat = false;

            for (int i = 0; i < nodelistCompeticio.getLength(); i++) {
                Node nodeCompeticio = nodelistCompeticio.item(i);

                if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementCompeticio = (Element) nodeCompeticio;

                    String id = elementCompeticio.getElementsByTagName("id").item(0).getTextContent();

                    if (id.equalsIgnoreCase(idBuscar)) {
                        idTrobat = true;

                        String nom = elementCompeticio.getElementsByTagName("nom").item(0).getTextContent();
                        String modalitat = elementCompeticio.getElementsByTagName("modalitat").item(0).getTextContent();
                        String edicio = elementCompeticio.getElementsByTagName("edicio").item(0).getTextContent();
                        String localitzacio = elementCompeticio.getElementsByTagName("localitzacio").item(0).getTextContent();
                        String data = elementCompeticio.getElementsByTagName("data").item(0).getTextContent();
                        String inscripcions = elementCompeticio.getElementsByTagName("inscripcions").item(0).getTextContent();
                        String preu = elementCompeticio.getElementsByTagName("preu").item(0).getTextContent();

                        System.out.println("Id: " + id);
                        System.out.println("Nom: " + nom);
                        System.out.println("Modalitat: " + modalitat);
                        System.out.println("Edicio: " + edicio);
                        System.out.println("Localitzacio: " + localitzacio);
                        System.out.println("Data: " + data);
                        System.out.println("Inscripcions: " + inscripcions);
                        System.out.println("Preu: " + preu);
                        System.out.println("Equips");

                        NodeList nodelistEquip = document.getElementsByTagName("equip");

                        for (int j = 0; j < nodelistEquip.getLength(); j++) {
                            Node nodeEquip = nodelistEquip.item(j);

                            if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
                                Element elementEquip = (Element) nodeEquip;

                                String nomEquip = elementEquip.getElementsByTagName("nom").item(0).getTextContent();
                                String categoriaEquip = elementEquip.getElementsByTagName("categoria").item(0).getTextContent();
                                String jugadorsEquip = elementEquip.getElementsByTagName("jugadors").item(0).getTextContent();

                                System.out.println("\tNom: " + nomEquip);
                                System.out.println("\tCategoria: " + categoriaEquip);
                                System.out.println("\tJugadors: " + jugadorsEquip);
                                System.out.println("\t--------------------------");
                            }
                        }

                        System.out.println("------------------------------------------");
                    }
                }
            }

            if (!idTrobat) {
                System.out.println("No s'ha trobat cap activitat amb aquest id.");
                return;
            }

        } catch (Exception e) {
            System.out.println("Error en llegir per codi.");
        }
    }

    public static void modificarNombreInscripcions() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodelistCompeticio = document.getElementsByTagName("competicio");

                
            System.out.print("Introdueix l'id de la competició: ");
            String idCompeticio = sc.nextLine();

            boolean competicioTrobada = false;

            for (int i = 0; i < nodelistCompeticio.getLength(); i++) {
                Element elementCompeticio = (Element) nodelistCompeticio.item(i);

                String id = document.getElementsByTagName("id").item(0).getTextContent();

                if (id.equalsIgnoreCase(idCompeticio)) {
                    competicioTrobada = true;

                    String inscripcionsAnterior = document.getElementsByTagName("inscripcions").item(0).getTextContent();

                    Element inscripcions = (Element) document.getElementsByTagName("inscripcions").item(0);

                    System.out.println("Nombre d'inscripcions actuals: " + inscripcionsAnterior);

                    System.out.print("Introdueix el nou nombre d'inscripcions: ");
                    int nombreFinal = sc.nextInt();

                    sc.nextLine();

                    inscripcions.setTextContent(String.valueOf(nombreFinal));

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(fitxer);
                    transformer.transform(source, result);

                    System.out.println("Inscripcions modificades correctament.");

                    return;
                }
            }

            if (!competicioTrobada) {
                System.out.println("No s'ha trobat cap competicio amb aquest nom.");
                return;
            }

        } catch(Exception e) {
            System.out.println("Error en modificar el nombre d'inscripcions.");
        }
    }

    public static void tranformarJson() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            String stringJson = new String(Files.readAllBytes(Paths.get(ruta)));

            JSONObject jsonObject = XML.toJSONObject(stringJson);

            System.out.print("Introdueix la ruta a on guardar (amb nom_fitxer.json): ");
            String rutaGuardar = sc.nextLine();

            Files.write(Paths.get(rutaGuardar), jsonObject.toString(4).getBytes(), StandardOpenOption.CREATE);

            System.out.println("Fitxer convertit a JSON correctament a: " + rutaGuardar);
            
        } catch (Exception e) {
            System.out.println("Error en tranformar a JSON");
        }

    }
    
    public static void mostrarCompeticionsPerModalitat() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodelistCompeticio = document.getElementsByTagName("competicio");

            System.out.print("Introdueix nom de la modelitat a buscar: ");
            String modalitatBuscar = sc.nextLine();

            boolean modalitatTrobada = false;

            for (int i = 0; i < nodelistCompeticio.getLength(); i++) {
                Node nodeCompeticio = nodelistCompeticio.item(i);

                if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementCompeticio = (Element) nodeCompeticio;

                    String modalitat = elementCompeticio.getElementsByTagName("modalitat").item(0).getTextContent();

                    if (modalitat.equalsIgnoreCase(modalitatBuscar)) {
                        modalitatTrobada = true;

                        String id = elementCompeticio.getElementsByTagName("id").item(0).getTextContent();
                        String nom = elementCompeticio.getElementsByTagName("nom").item(0).getTextContent();
                        String edicio = elementCompeticio.getElementsByTagName("edicio").item(0).getTextContent();
                        String localitzacio = elementCompeticio.getElementsByTagName("localitzacio").item(0).getTextContent();
                        String data = elementCompeticio.getElementsByTagName("data").item(0).getTextContent();
                        String inscripcions = elementCompeticio.getElementsByTagName("inscripcions").item(0).getTextContent();
                        String preu = elementCompeticio.getElementsByTagName("preu").item(0).getTextContent();
    
                        System.out.println("Id: " + id);
                        System.out.println("Nom: " + nom);
                        System.out.println("Modalitat: " + modalitat);
                        System.out.println("Edicio: " + edicio);
                        System.out.println("Localitzacio: " + localitzacio);
                        System.out.println("Data: " + data);
                        System.out.println("Inscripcions: " + inscripcions);
                        System.out.println("Preu: " + preu);
                        System.out.println("Equips");
    
                        NodeList nodelistEquip = document.getElementsByTagName("equip");
    
                        for (int j = 0; j < nodelistEquip.getLength(); j++) {
                            Node nodeEquip = nodelistEquip.item(j);
    
                            if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
                                Element elementEquip = (Element) nodeEquip;
    
                                String nomEquip = elementEquip.getElementsByTagName("nom").item(0).getTextContent();
                                String categoriaEquip = elementEquip.getElementsByTagName("categoria").item(0).getTextContent();
                                String jugadorsEquip = elementEquip.getElementsByTagName("jugadors").item(0).getTextContent();
    
                                System.out.println("\tNom: " + nomEquip);
                                System.out.println("\tCategoria: " + categoriaEquip);
                                System.out.println("\tJugadors: " + jugadorsEquip);
                                System.out.println("\t--------------------------");
                            }
                        }
    
                        System.out.println("------------------------------------------");
                    }
                }
            }

            if (!modalitatTrobada) {
                System.out.println("No s'ha trobat cap activitat amb aquest id.");
                return;
            }

        } catch (Exception e) {
            System.out.println("Error en llegir per codi.");
        }
    }

    public static void mostrarCompeticions30() {
        // System.out.print("Introdueix la ruta del fitxer xml: ");
        // String ruta = sc.nextLine();

        // File fitxer = new File(ruta);

        // if (!fitxer.exists()) {
        //     System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
        //     return;
        // }

        // try {
        //     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //     DocumentBuilder builder = factory.newDocumentBuilder();
        //     Document document = builder.parse(fitxer);
        //     document.getDocumentElement().normalize();

        //     NodeList nodelistCompeticio = document.getElementsByTagName("competicio");

        //     for (int i = 0; i < nodelistCompeticio.getLength(); i++) {
        //         Node nodeCompeticio = nodelistCompeticio.item(i);

        //         if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
        //             Element elementCompeticio = (Element) nodeCompeticio;

        //             String preu = elementCompeticio.getElementsByTagName("preu").item(0).getTextContent();


        //             if (preu < "30") {

        //                 String id = elementCompeticio.getElementsByTagName("id").item(0).getTextContent();
        //                 String nom = elementCompeticio.getElementsByTagName("nom").item(0).getTextContent();
        //                 String modalitat = elementCompeticio.getElementsByTagName("modalitat").item(0).getTextContent();
        //                 String edicio = elementCompeticio.getElementsByTagName("edicio").item(0).getTextContent();
        //                 String localitzacio = elementCompeticio.getElementsByTagName("localitzacio").item(0).getTextContent();
        //                 String data = elementCompeticio.getElementsByTagName("data").item(0).getTextContent();
        //                 String inscripcions = elementCompeticio.getElementsByTagName("inscripcions").item(0).getTextContent();
    
        //                 System.out.println("Id: " + id);
        //                 System.out.println("Nom: " + nom);
        //                 System.out.println("Modalitat: " + modalitat);
        //                 System.out.println("Edicio: " + edicio);
        //                 System.out.println("Localitzacio: " + localitzacio);
        //                 System.out.println("Data: " + data);
        //                 System.out.println("Inscripcions: " + inscripcions);
        //                 System.out.println("Preu: " + preu);
        //                 System.out.println("Equips");
    
        //                 NodeList nodelistEquip = document.getElementsByTagName("equip");
    
        //                 for (int j = 0; j < nodelistEquip.getLength(); j++) {
        //                     Node nodeEquip = nodelistEquip.item(j);
    
        //                     if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
        //                         Element elementEquip = (Element) nodeEquip;
    
        //                         String nomEquip = elementEquip.getElementsByTagName("nom").item(0).getTextContent();
        //                         String categoriaEquip = elementEquip.getElementsByTagName("categoria").item(0).getTextContent();
        //                         String jugadorsEquip = elementEquip.getElementsByTagName("jugadors").item(0).getTextContent();
    
        //                         System.out.println("\tNom: " + nomEquip);
        //                         System.out.println("\tCategoria: " + categoriaEquip);
        //                         System.out.println("\tJugadors: " + jugadorsEquip);
        //                         System.out.println("\t--------------------------");
        //                     }
        //                 }
    
        //                 System.out.println("------------------------------------------");
        //             }
        //         }
        //     }

        // } catch (Exception e) {
        //     System.out.println("Error en llegir per codi.");
        // }
    }

    public static void mostrarCompeticionsDia() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodelistCompeticio = document.getElementsByTagName("competicio");

            boolean modalitatTrobada = false;
            boolean diaTrobat = false;

            for (int i = 0; i < nodelistCompeticio.getLength(); i++) {
                Node nodeCompeticio = nodelistCompeticio.item(i);

                if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementCompeticio = (Element) nodeCompeticio;

                    String modalitat = elementCompeticio.getElementsByTagName("modalitat").item(0).getTextContent();

                    if (modalitat.equalsIgnoreCase("Ciclisme")) {
                        modalitatTrobada = true;

                        String data = elementCompeticio.getElementsByTagName("data").item(0).getTextContent();

                        if (data.equalsIgnoreCase("13/02/2025")) {
                            diaTrobat = true;

                            String id = elementCompeticio.getElementsByTagName("id").item(0).getTextContent();
                            String nom = elementCompeticio.getElementsByTagName("nom").item(0).getTextContent();
                            String edicio = elementCompeticio.getElementsByTagName("edicio").item(0).getTextContent();
                            String localitzacio = elementCompeticio.getElementsByTagName("localitzacio").item(0).getTextContent();
                            String inscripcions = elementCompeticio.getElementsByTagName("inscripcions").item(0).getTextContent();
                            String preu = elementCompeticio.getElementsByTagName("preu").item(0).getTextContent();
        
                            System.out.println("Id: " + id);
                            System.out.println("Nom: " + nom);
                            System.out.println("Modalitat: " + modalitat);
                            System.out.println("Edicio: " + edicio);
                            System.out.println("Localitzacio: " + localitzacio);
                            System.out.println("Data: " + data);
                            System.out.println("Inscripcions: " + inscripcions);
                            System.out.println("Preu: " + preu);
                            System.out.println("Equips");
        
                            NodeList nodelistEquip = document.getElementsByTagName("equip");
        
                            for (int j = 0; j < nodelistEquip.getLength(); j++) {
                                Node nodeEquip = nodelistEquip.item(j);
        
                                if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
                                    Element elementEquip = (Element) nodeEquip;
        
                                    String nomEquip = elementEquip.getElementsByTagName("nom").item(0).getTextContent();
                                    String categoriaEquip = elementEquip.getElementsByTagName("categoria").item(0).getTextContent();
                                    String jugadorsEquip = elementEquip.getElementsByTagName("jugadors").item(0).getTextContent();
        
                                    System.out.println("\tNom: " + nomEquip);
                                    System.out.println("\tCategoria: " + categoriaEquip);
                                    System.out.println("\tJugadors: " + jugadorsEquip);
                                    System.out.println("\t--------------------------");
                                }
                            }
        
                            System.out.println("------------------------------------------");
                        }
                    }
                }
            }

            if (!modalitatTrobada) {
                System.out.println("No s'ha trobat cap modalitat anomenada Ciclisme.");
                return;
            }

            if (!diaTrobat) {
                System.out.println("No s'ha trobat cap competició en aquest dia.");
                return;
            }


        } catch (Exception e) {
            System.out.println("Error en llegir per codi.");
        }
    }

    public static void mostrarCompeticionsOrdenades() {
        System.out.print("Introdueix la ruta del fitxer xml: ");
        String ruta = sc.nextLine();

        File fitxer = new File(ruta);

        if (!fitxer.exists()) {
            System.out.println("El fitxer no existeix. Comprova que la ruta sigui correcte.");
            return;
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodelistCompeticio = document.getElementsByTagName("competicio");

            List<Ordernar> listOrdenar = new ArrayList<>();

            for (int i = 0; i < nodelistCompeticio.getLength(); i++) {
                Node nodeCompeticio = nodelistCompeticio.item(i);

                if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementCompeticio = (Element) nodeCompeticio;

                    String id = elementCompeticio.getElementsByTagName("id").item(0).getTextContent();
                    String nom = elementCompeticio.getElementsByTagName("nom").item(0).getTextContent();
                    String modalitat = elementCompeticio.getElementsByTagName("modalitat").item(0).getTextContent();
                    String edicio = elementCompeticio.getElementsByTagName("edicio").item(0).getTextContent();
                    String localitzacio = elementCompeticio.getElementsByTagName("localitzacio").item(0).getTextContent();
                    String data = elementCompeticio.getElementsByTagName("data").item(0).getTextContent();
                    String inscripcions = elementCompeticio.getElementsByTagName("inscripcions").item(0).getTextContent();
                    String preu = elementCompeticio.getElementsByTagName("preu").item(0).getTextContent();

                    NodeList nodelistEquip = document.getElementsByTagName("equip");

                    for (int j = 0; j < nodelistEquip.getLength(); j++) {
                        Node nodeEquip = nodelistEquip.item(j);

                        if (nodeCompeticio.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementEquip = (Element) nodeEquip;

                            String nomEquip = elementEquip.getElementsByTagName("nom").item(0).getTextContent();
                            String categoriaEquip = elementEquip.getElementsByTagName("categoria").item(0).getTextContent();
                            String jugadorsEquip = elementEquip.getElementsByTagName("jugadors").item(0).getTextContent();

                            listOrdenar.add(new Ordernar(id, nom, modalitat, edicio, localitzacio, data, inscripcions, preu, nomEquip, categoriaEquip, jugadorsEquip));

                            listOrdenar.sort(Comparator.comparingInt(Ordernar::getOrdenacio));
    
                            for (Ordernar o : listOrdenar) {
                                System.out.println(o);
                            }
                        }
                    }

                    System.out.println("------------------------------------------");
                }
            }

        } catch (Exception e) {
            System.out.println("Error en llegir per codi.");
        }
    }

}

class Ordernar {
    private String id, nom, modalitat, edicio, localitzacio, data, inscripcions, preu, nomEquip, categoria, jugadors;

    public Ordernar() {
    }

    

    public Ordernar(String id, String nom, String modalitat, String edicio, String localitzacio, String data,
            String inscripcions, String preu, String nomEquip, String categoria, String jugadors) {
        this.id = id;
        this.nom = nom;
        this.modalitat = modalitat;
        this.edicio = edicio;
        this.localitzacio = localitzacio;
        this.data = data;
        this.inscripcions = inscripcions;
        this.preu = preu;
        this.nomEquip = nomEquip;
        this.categoria = categoria;
        this.jugadors = jugadors;
    }



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public String getNom() {
        return nom;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }



    public String getModalitat() {
        return modalitat;
    }



    public void setModalitat(String modalitat) {
        this.modalitat = modalitat;
    }



    public String getEdicio() {
        return edicio;
    }



    public void setEdicio(String edicio) {
        this.edicio = edicio;
    }



    public String getLocalitzacio() {
        return localitzacio;
    }



    public void setLocalitzacio(String localitzacio) {
        this.localitzacio = localitzacio;
    }



    public String getData() {
        return data;
    }



    public void setData(String data) {
        this.data = data;
    }



    public String getInscripcions() {
        return inscripcions;
    }



    public void setInscripcions(String inscripcions) {
        this.inscripcions = inscripcions;
    }



    public String getPreu() {
        return preu;
    }



    public void setPreu(String preu) {
        this.preu = preu;
    }



    public String getNomEquip() {
        return nomEquip;
    }



    public void setNomEquip(String nomEquip) {
        this.nomEquip = nomEquip;
    }



    public String getCategoria() {
        return categoria;
    }



    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }



    public String getJugadors() {
        return jugadors;
    }



    public void setJugadors(String jugadors) {
        this.jugadors = jugadors;
    }



    public int getOrdenacio() {
        switch (modalitat) {
            case "Natacio":
                return 1;
            
            case "Running":
                return 2;

            case "Ciclisme":
                return 3;

            case "Tennis":
                return 4;

            case "Golf":
                return 5;
        
            default:
                return 6;
        }
    }

    @Override
    public String toString() {
        return "Ordernar [id=" + id + ", nom=" + nom + ", modalitat=" + modalitat + ", edicio=" + edicio
                + ", localitzacio=" + localitzacio + ", data=" + data + ", inscripcions=" + inscripcions + ", preu="
                + preu + "]";
    }
}
