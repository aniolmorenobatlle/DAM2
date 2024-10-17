package dam2.amoreno.m6_uf1_a4;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class App 
{
	private static Scanner sc = new Scanner(System.in);
	
	
    public static void main( String[] args ) {
    	
    	// Llegir comanda
        List<String>[] resultats = llegirComanda();
        
        // Passar les llistes com a parametres
        actualitzarStock(resultats[0], resultats[1]);

//    	llegirStock();
    }
    
    
    // Llegir elements comanda XML
    public static List<String>[] llegirComanda() {
    	System.out.print("Escriu la ruta de l'arxiu XML: ");
    	String ruta = sc.nextLine();
    	
    	
    	// Llistes per guardar els valors
        List<String> idsArticles = new ArrayList<>();
        List<String> quantitatsArticles = new ArrayList<>();
    	
    	
    	try {
    		
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(new File(ruta));
			
			document.getDocumentElement().normalize();
			
			int i = 0;

			
			// Llegir contingut Comanda
            NodeList listComanda = document.getElementsByTagName("comanda");
            
            for (i = 0; i < listComanda.getLength(); i++) {            	
            	Node node = listComanda.item(i);
            	
            	
            	if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					String id_comanda = element.getElementsByTagName("id").item(0).getTextContent();
					
					System.out.println("ID de comanda: " + id_comanda);
					
					System.out.println();
            	}
            	
            }
            
            
        	// Llegir contingut Client
        	NodeList listClient = document.getElementsByTagName("client");
        	
        	for (i = 0; i < listClient.getLength(); i++) {
        		Node nodeClient = listClient.item(i);
        		
        		if (nodeClient.getNodeType() == Node.ELEMENT_NODE) {
        			Element elementClient = (Element) nodeClient;
        			
        			String nom_client = elementClient.getElementsByTagName("nom").item(0).getTextContent();
        			String adreca_client = elementClient.getElementsByTagName("adreca").item(0).getTextContent();
        			
        			System.out.println("Nom del client: " + nom_client);
        			System.out.println("Adreça del client: " + adreca_client);
        			
        			System.out.println();

        		}
        	}
        	
        	
        	// Llegir contingut Article
         	NodeList listArticle = document.getElementsByTagName("article");
         	
         	for (i = 0; i < listArticle.getLength(); i++) {
         		Node nodeArticle = listArticle.item(i);
         		
         		if (nodeArticle.getNodeType() == Node.ELEMENT_NODE) {
         			Element elementArticle = (Element) nodeArticle;
         			
         			String id_article = elementArticle.getElementsByTagName("idProducte").item(0).getTextContent();
         			String quantitat_article = elementArticle.getElementsByTagName("quantitat").item(0).getTextContent();
         			
         			System.out.println("ID article: " + id_article);
         			System.out.println("Quantitat demanada: " + quantitat_article);
         			
         			System.out.println();
         			
         			// Guardar resultat a les llistes
         			idsArticles.add(id_article);
                    quantitatsArticles.add(quantitat_article);
         			
         		}
         		
         	}
    		
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	// Returnem les llistes com una array de llistes
        return new List[]{idsArticles, quantitatsArticles};
        
    }
    
    
    
    // Llegir elements Stock XML
    public static void llegirStock() {
    	String ruta_stock = "src/main/java/dam2/amoreno/m6_uf1_a4/stock.xml";
    	
    	try {
    		
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(new File(ruta_stock));
			
			document.getDocumentElement().normalize();
			
			
			
			// Llegir contingut Producte
            NodeList listProductes = document.getElementsByTagName("article");
            
            for (int i = 0; i < listProductes.getLength(); i++) {            	
            	Node node = listProductes.item(i);
            	
            	
            	if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					String id_producte = element.getElementsByTagName("id").item(0).getTextContent();
					String descripcio_producte = element.getElementsByTagName("descripcio").item(0).getTextContent();
					String stock_producte = element.getElementsByTagName("stock").item(0).getTextContent();
					String preu_producte = element.getElementsByTagName("preu").item(0).getTextContent();

					System.out.println("ID de producte: " + id_producte);
					System.out.println("Descripció producte: " + descripcio_producte);
					System.out.println("Stock producte: " + stock_producte);
					System.out.println("Preu producte: " + preu_producte);
					
					System.out.println();
            	}
            	
            }
			
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    // Llegir llistes
    public static void llegirLlistes(List<String> idsArticles, List<String> quantitatsArticles) {
    	
        for (int i = 0; i < idsArticles.size(); i++) {
        	
            System.out.println("Article " + (i + 1) + ":");
            System.out.println("        ID: " + idsArticles.get(i));
            System.out.println("        Quantitat: " + quantitatsArticles.get(i));
            System.out.println();
            
        }
    }
    
    
    // Actualizar stock
    public static void actualitzarStock(List<String> idsArticles, List<String> quantitatsArticles) {
    	
    	System.out.print("Escriu la ruta dels stocks: ");
    	String ruta_stock = sc.nextLine();
    	
    	try {
    		
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(new File(ruta_stock));
			
			document.getDocumentElement().normalize();
			
			
			
			// Llegir contingut Article
            NodeList listProductes = document.getElementsByTagName("article");
            
            for (int i = 0; i < listProductes.getLength(); i++) {            	
            	Node node = listProductes.item(i);
            	
            	
            	if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					String id_producte = element.getElementsByTagName("id").item(0).getTextContent();
					String stock_producte = element.getElementsByTagName("stock").item(0).getTextContent();

					System.out.println("ID de producte: " + id_producte);
					System.out.println("Stock producte: " + stock_producte);
					
					System.out.println();
					
					
					
					
					for (int j = 0; j < idsArticles.size(); j++) {
						
						if (id_producte.equals(idsArticles.get(j))) {
                            int stock = Integer.parseInt(stock_producte);
                            int quantitat = Integer.parseInt(quantitatsArticles.get(j));
                            
							if (stock < quantitat) {
								System.out.println("No hi ha stock suficient per aquest producte");
								System.out.println();
								
								break;
								
							} else {
	                            
	                            
	                            // Actualitzar stock a l'arxiu
	                            try {
	                            	
	                            	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	                                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	                                
	                                File file = new File(ruta_stock);
	                                
	                                Document doc = docBuilder.parse(file);
	                                
	                                NodeList actualitzarStock = doc.getElementsByTagName("article");
	                                
	                                for (int k = 0; k < actualitzarStock.getLength(); k++) {
	                                
	                                	Element elementAct = (Element) (actualitzarStock.item(k));
	                                	
	                                	String id_producte_act = elementAct.getElementsByTagName("id").item(0).getTextContent();
	                                	
										if (id_producte_act.equals(id_producte)) {
											
											Element stockElement = (Element) elementAct.getElementsByTagName("quantitat").item(0);
											stockElement.setTextContent(String.valueOf(stock));
											
										}
	                                
	                                }
	                                
	                                
	                                // Escriure al fitxer amb les noves dades
	                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
	                                Transformer transformer = transformerFactory.newTransformer();
	                                
	                                DOMSource source = new DOMSource(doc);
	                                
	                                FileOutputStream output = new FileOutputStream(ruta_stock);
	                                
	                                StreamResult result = new StreamResult(output);
	                                transformer.transform(source, result);
	                            	
	                                
	                                System.out.println("STOCK ACTUALIZAT!!");
	                            	
	                            } catch(Exception e) {
                                	e.printStackTrace();
	                            }
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














