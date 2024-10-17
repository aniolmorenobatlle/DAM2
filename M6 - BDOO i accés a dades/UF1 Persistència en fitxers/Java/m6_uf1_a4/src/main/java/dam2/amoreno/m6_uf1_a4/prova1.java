package dam2.amoreno.m6_uf1_a4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class prova1 {
	
	private static Scanner sc = new Scanner(System.in);

    // Mètode per llegir comanda i retornar les dades com a llistes
    public static List<String>[] llegirComanda() {
        System.out.print("Escriu la ruta de l'arxiu XML: ");
        String ruta = sc.nextLine();

        // Llistes per emmagatzemar els valors
        List<String> idsArticles = new ArrayList<>();
        List<String> quantitatsArticles = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(ruta));
            document.getDocumentElement().normalize();

            // Llegir contingut Article i guardar en llistes
            NodeList listArticle = document.getElementsByTagName("article");
            for (int i = 0; i < listArticle.getLength(); i++) {
                Node nodeArticle = listArticle.item(i);
                if (nodeArticle.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementArticle = (Element) nodeArticle;
                    String id_article = elementArticle.getElementsByTagName("id").item(0).getTextContent();
                    String quantitat_article = elementArticle.getElementsByTagName("quantitat").item(0).getTextContent();
                    
                    // Guardar els valors en les llistes
                    idsArticles.add(id_article);
                    quantitatsArticles.add(quantitat_article);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retornar les llistes com un array de llistes
        return new List[]{idsArticles, quantitatsArticles};
    }

    // Mètode per llegir stock (exemple de com rebre les llistes com a paràmetre)
    public static void llegirStock(List<String> idsArticles, List<String> quantitatsArticles) {
    	
        for (int i = 0; i < idsArticles.size(); i++) {
        	
            System.out.println("Article " + (i + 1) + ":");
            System.out.println("        ID: " + idsArticles.get(i));
            System.out.println("        Quantitat: " + quantitatsArticles.get(i));
            System.out.println();
            
        }
    }

    public static void main(String[] args) {
        // Llegir comanda i obtenir les llistes
        List<String>[] resultats = llegirComanda();
        
        // Passar les llistes com a paràmetres a llegirStock
        llegirStock(resultats[0], resultats[1]);
    }
}

