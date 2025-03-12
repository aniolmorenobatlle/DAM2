package dam.amoreno;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

public class DOM2 {

  static Scanner sc = new Scanner(System.in);
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static void main(String[] args) {
    while (true) {
      System.out.println("\n========================================");
      System.out.println();
    
    
      System.out.println("0. Sortir");
      System.out.println("1. Afegir nova tasca");
      System.out.println("2. Mostra la llista de tasques");
      System.out.println("3. Mostra la llista de tasques ordenades per prioritats");
      System.out.println("4. Convertir fitxer a JSON");
      System.out.println("5. Modificar preu habitació d'un hotel");
      System.out.println("6. Llistar hotels");
      System.out.println("7. Llistar habitacions hotel");
    
    
      System.out.println();
      System.out.println("========================================");
      System.out.println();
    
    
      System.out.print("Selecciona una opció: ");
      int opcio = sc.nextInt();
      sc.nextLine();
    
    
      System.out.println();
    
    
      switch (opcio) {
        case 1:
          afegirNovaTasca();
          break;

        case 2:
          mostrarDades();
          break;
          
        case 3:
          mostrarDadesOrdenades();
          break;

        case 4:
          convertirJson();
          break;

        case 5:
          modificarPreuHabitacio();
          break;

        case 6:
          llistarHotels();
          break;

        case 7:
          llistarHabitacions();
          break;
    
        case 0:
          System.out.println("Sortint del programa...");
          return;
    
    
        default:
          System.out.println("Opció no vàlida. Intenta-ho de nou.");
      }
    }
  }

  public static void afegirNovaTasca() {
    File fitxer = new File("xmls/tasques.xml");

    if (!fitxer.exists()) {
      System.out.println("El fitxer no existeix.");
      return;
    }

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(fitxer);
      document.getDocumentElement().normalize();

      System.out.print("Quantes tasques vols afegir? ");
      int numeroTasques = sc.nextInt();

      System.out.println();

      for (int i = 0; i < numeroTasques; i++) {
        Element tasca = document.createElement("tasca");

        sc.nextLine();

        Element nom = document.createElement("nom");
        System.out.print("Introdueix el nom de la tasca: ");
        String nomResult = sc.nextLine();
        nom.appendChild(document.createTextNode(nomResult));        

        Element data = document.createElement("data");
        LocalDate dataResult = null;
        while (dataResult == null) {
          System.out.print("Introdueix la data de la tasca (YYYY-MM-DD): ");
          String dataInput = sc.nextLine();
          
          try {
            dataResult = LocalDate.parse(dataInput, FORMATTER);
          } catch (DateTimeParseException e) {
            System.out.println("Format de data incorrecte!!");
          }
        }
        data.appendChild(document.createTextNode(String.valueOf(dataResult)));
  
        Element prioritat = document.createElement("prioritat");
        System.out.print("Introdueix la prioritat (1. Alta, 2. Moderada, 3. Baixa): ");
        int prioritatResult = sc.nextInt();
        String prioritatFinal = null;

        switch (prioritatResult) {
          case 1:
            prioritatFinal = "Alta";
            break;

          case 2:
            prioritatFinal = "Moderada";
            break;

          case 3:
            prioritatFinal = "Baixa";
            break;
        
          default:
            System.out.println("Opció no vàlida!!");
            break;
        }
        prioritat.appendChild(document.createTextNode(prioritatFinal));
  
        Element categoria = document.createElement("categoria");
        System.out.print("Introdueix la categoria (1. Feina, 2. Personal): ");
        int categoriaResult = sc.nextInt();
        String categoriaFinal = null;
        switch (categoriaResult) {
          case 1:
            categoriaFinal = "Feina";     
            break;

          case 2:
            categoriaFinal = "Personal";
            break;
        
          default:
            System.out.println("Opció no vàlida!!");
            break;
        }
        categoria.appendChild(document.createTextNode(categoriaFinal));
  
        tasca.appendChild(nom);
        tasca.appendChild(data);
        tasca.appendChild(prioritat);
        tasca.appendChild(categoria);
  
        document.getDocumentElement().appendChild(tasca);
  
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
  
        DOMSource source = new DOMSource(document);
        StreamResult stream = new StreamResult(fitxer);
  
        transformer.transform(source, stream);
  
        System.out.println("Tasca " + (i + 1) + " afegida amb exit!!");

        System.out.println();
      }

    } catch (Exception e) {
      System.out.println("Error en afegir la tasca en el fitxer.");
    }
  }

  public static void mostrarDades() {
    File fitxer = new File("xmls/tasques.xml");

    if (!fitxer.exists()) {
      System.out.println("El fitxer no existeix.");
      return;
    }

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(fitxer);
      document.getDocumentElement().normalize();

      NodeList nodeListTasca = document.getElementsByTagName("tasca");

      for (int i = 0; i < nodeListTasca.getLength(); i++) {
        Node node = nodeListTasca.item(i);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;

          String nom = element.getElementsByTagName("nom").item(0).getTextContent();
          String data = element.getElementsByTagName("data").item(0).getTextContent();
          String prioritat = element.getElementsByTagName("prioritat").item(0).getTextContent();
          String categoria = element.getElementsByTagName("categoria").item(0).getTextContent();
        
          System.out.println();

          System.out.println("Nom: " + nom);
          System.out.println("Data: " + data);
          System.out.println("Proritat: " + prioritat);
          System.out.println("Categoria: " + categoria);
          System.out.println("---------------------------");
        }
      }
      
    } catch (Exception e) {
      System.out.println("Error en llegir el fitxer.");
    }
  }

  public static void mostrarDadesOrdenades() {
    File fitxer = new File("xmls/tasques.xml");

    if (!fitxer.exists()) {
      System.out.println("El fitxer no existeix.");
      return;
    }

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(fitxer);
      document.getDocumentElement().normalize();

      NodeList nodeListTasca = document.getElementsByTagName("tasca");

      List<Ordenar> listTasca = new ArrayList<>();
      
      for (int i = 0; i < nodeListTasca.getLength(); i++) {
        Node node = nodeListTasca.item(i);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;

          String nom = element.getElementsByTagName("nom").item(0).getTextContent();
          String data = element.getElementsByTagName("data").item(0).getTextContent();
          String prioritat = element.getElementsByTagName("prioritat").item(0).getTextContent();
          String categoria = element.getElementsByTagName("categoria").item(0).getTextContent();

          listTasca.add(new Ordenar(nom, data, prioritat, categoria));
        }
      }
      
      listTasca.sort(Comparator.comparingInt(Ordenar::getNivellPrioritat));

      for (Ordenar o : listTasca) System.out.println(o);
      
    } catch (Exception e) {
      System.out.println("Error en llegir el fitxer.");
    }
  }

  public static void convertirJson() {
    String ruta = "xmls/tasques.xml";
    File fitxer = new File(ruta);

    if (!fitxer.exists()) {
      System.out.println("El fitxer no existeix.");
      return;
    }

    try {
      String jsonString = new String(Files.readAllBytes(Paths.get(ruta)));

      JSONObject jsonObject = XML.toJSONObject(jsonString);

      String rutaGuardar = "xmls/tasques.json";

      Files.write(Paths.get(rutaGuardar), jsonObject.toString(4).getBytes(), StandardOpenOption.CREATE);

      System.out.println("Fitxer guardat correctament a: " + rutaGuardar);
      
    } catch (Exception e) {
      System.out.println("Error en convertir el fitxer XML a JSON.");
    }
  }

  public static void modificarPreuHabitacio() {
    File fitxer = new File("xmls/hotels.xml");

    if (!fitxer.exists()) {
      System.out.println("El fitxer no existeix.");
      return;
    }

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(fitxer);
      document.getDocumentElement().normalize();

      System.out.print("Introdueix el nom de l'hotel: ");
      String nomHotel = sc.nextLine();
      System.out.print("Introdueix el numero de l'habitació: ");
      int numHabitacio = sc.nextInt();

      sc.nextLine();

      System.out.println();

      NodeList nodeListHotel = document.getElementsByTagName("hotel");
      boolean hotelTrobat = false;
      boolean habitacioTrobada = false;

      for (int i = 0; i < nodeListHotel.getLength(); i++) {
        Element elementHotel = (Element) nodeListHotel.item(i);

        String hotel = elementHotel.getElementsByTagName("nom").item(0).getTextContent();

        if (hotel.equalsIgnoreCase(nomHotel)) {
          hotelTrobat = true;

          NodeList nodeListHabitacio = elementHotel.getElementsByTagName("habitacio");

          for (int j = 0; j < nodeListHabitacio.getLength(); j++) {
            Element elementHabitacio = (Element) nodeListHabitacio.item(j);

            String numero = elementHabitacio.getElementsByTagName("numero").item(0).getTextContent();

            if (numero.equalsIgnoreCase(String.valueOf(numHabitacio))) {
              habitacioTrobada = true;

              String preu = elementHabitacio.getElementsByTagName("preu").item(0).getTextContent();

              System.out.println("Preu actual: " + preu);

              Element preuElement = (Element) elementHabitacio.getElementsByTagName("preu").item(0);
              System.out.print("Introdueix el nou preu: ");
              double preuFinal = sc.nextDouble();

              preuElement.setTextContent(String.valueOf(preuFinal));

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
      System.out.println("Error en modifcar el preu de l'habitació.");
    }
  }

  public static void llistarHotels() {
    File fitxer = new File("xmls/hotels.xml");

    if (!fitxer.exists()) {
      System.out.println("El fitxer no existeix.");
      return;
    }

    try {
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

          System.out.println("Nom de l'hotel: " + nom);
          System.out.println("Adreça de l'hotel: " + adreca);
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
              System.out.println("\tPreu: " + preu + "€");
              System.out.println("\t------------------------");
            }
          }

          System.out.println("----------------------------------");
          System.out.println();
        }
      }

    } catch (Exception e) {
      System.out.println("Error en llistar els hotels.");
    }
  }

  public static void llistarHabitacions() {
    File fitxer = new File("xmls/hotels.xml");

    if (!fitxer.exists()) {
      System.out.println("El fitxer no existeix.");
      return;
    }

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(fitxer);
      document.getDocumentElement().normalize();

      System.out.print("Introdueix el nom de l'hotel: ");
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

            System.out.println("Nom de l'hotel: " + nom);
            System.out.println("Adreça de l'hotel: " + adreca);
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
                System.out.println("\tPreu: " + preu + "€");
                System.out.println("\t------------------------");
              }
            }
  
            System.out.println("----------------------------------");
            System.out.println();
          }          
        }
      }

      if (!hotelTrobat) {
        System.out.println("No s'ha trobat cap hotel amb aquest nom.");
        return;
      }

    } catch (Exception e) {
      System.out.println("Error en llistar els hotels.");
    }
  }

}

class Ordenar {
  private String nom, data, prioritat, categoria;

  public Ordenar() {
  }

  public Ordenar(String nom, String data, String prioritat, String categoria) {
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
    return "Nom: " + nom + 
           "\nData: " + data + 
           "\nPrioritat: " + prioritat + 
           "\nCategoria: " + categoria + 
           "\n-----------------------------";
  }
}