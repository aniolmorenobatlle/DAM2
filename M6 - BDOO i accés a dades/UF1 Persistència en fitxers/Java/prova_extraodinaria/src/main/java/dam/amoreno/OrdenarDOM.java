package dam.amoreno;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OrdenarDOM {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {

            System.out.println();
            System.out.print("Introdueix la ruta del fitxer XML: ");
            String ruta = sc.nextLine();
            File fitxer = new File(ruta);
            
            System.out.println();

            if (!fitxer.exists()) {
                System.out.println("El fitxer no existeix.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(fitxer);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("tasca");
            List<Tasca> tasques = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String nom = element.getElementsByTagName("nom").item(0).getTextContent();
                    String data = element.getElementsByTagName("data").item(0).getTextContent();
                    String prioritat = element.getElementsByTagName("prioritat").item(0).getTextContent();
                    String categoria = element.getElementsByTagName("categoria").item(0).getTextContent();

                    tasques.add(new Tasca(nom, data, prioritat, categoria));
                }
            }

            tasques.sort(Comparator.comparingInt(Tasca::getNivellPrioritat));

            for (Tasca t : tasques) {
                System.out.println(t);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Tasca {
    private String nom;
    private String data;
    private String prioritat;
    private String categoria;

    public Tasca(String nom, String data, String prioritat, String categoria) {
        this.nom = nom;
        this.data = data;
        this.prioritat = prioritat;
        this.categoria = categoria;
    }

    // Retorna un valor numèric per ordenar la prioritat (Alta > Moderada > Baixa)
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
        return "nom: " + nom + "\n"
        + "data: " + data + "\n"
        + "prioritat: " + prioritat + "\n"
        + "categoria: " + categoria + "\n"
        + "------------";
    }
}
