package dam2.amoreno.m6_uf1_a4;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;


public class App2 
{
	private static Scanner sc = new Scanner(System.in);
	
	
    public static void main( String[] args ) {
    	
    	
    	System.out.println("0. Sortir");
    	System.out.println("1. Afegir Comanda");
    	System.out.println("2. Afegir Stock");
    	
    	System.out.println();
    	
    	System.out.print("Selecciona una opció: ");
    	
    	switch (sc.nextInt()) {
    		case 0:
    			System.exit(0);
    			break;
				
    		case 1:
    			comanda();
				break;
				    
    		case 2:
    			stock();
				break;
				
    	}
    	
    }
    
    
    
    // Crear Comanda
    public static void comanda() {

    	System.out.print("Escriu la ruta del fitxer XML al servidor: ");
        String remoteFile = sc.next();

        // Client
        System.out.print("Escriu el nom del client: ");
        String nom_client = sc.next();

        System.out.print("Escriu l'adreça del client: ");
        String mail_client = sc.next();

        // Articles
        System.out.print("Escriu l'Id del producte: ");
        int id_producte = sc.nextInt();

        System.out.print("Escriu la quantitat del producte: ");
        int quantitat_producte = sc.nextInt();

        // Informacio del servidor SFTP
        String remoteHost = "192.168.1.6";
        int port = 22;
        String username = "alalca";
        String password = "1234";

        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        

        try {
            // Connexio al servidor SFTP
            session = jsch.getSession(username, remoteHost, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            
            // Obrir el canal SFTP
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            
            // Llegir el fitxer XML remot en un flux
            InputStream inputStream = channelSftp.get(remoteFile);

            
            // Crear el DocumentBuilder
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputStream);

            
            // Modificar el contingut XML
            Element rootElement = doc.getDocumentElement();
            
            if (rootElement == null) {
                rootElement = doc.createElement("comandes");
                doc.appendChild(rootElement);
            }

            
            // Trobar l'ultim ID de comanda existent
            NodeList comandes = doc.getElementsByTagName("comanda");
            int maxId = 0;
            
            for (int i = 0; i < comandes.getLength(); i++) {
                Element comanda = (Element) comandes.item(i);
                int id = Integer.parseInt(comanda.getElementsByTagName("id").item(0).getTextContent());
                
                if (id > maxId) {
                    maxId = id;
                }
            }
            
            int nouId = maxId + 1;

            // Crear una nova comanda
            Element comanda = doc.createElement("comanda");

            
            // Client
            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(String.valueOf(nouId)));
            comanda.appendChild(id);

            
            Element client = doc.createElement("client");
            comanda.appendChild(client);

            
            Element nom = doc.createElement("nom");
            nom.appendChild(doc.createTextNode(nom_client));
            client.appendChild(nom);

            
            Element mail = doc.createElement("adreca");
            mail.appendChild(doc.createTextNode(mail_client));
            client.appendChild(mail);

            
            // Articles
            Element articles = doc.createElement("articles");
            comanda.appendChild(articles);

            
            Element article = doc.createElement("article");
            articles.appendChild(article);

            
            Element idProducte = doc.createElement("idProducte");
            idProducte.appendChild(doc.createTextNode(String.valueOf(id_producte)));
            article.appendChild(idProducte);

            
            Element quantitat = doc.createElement("quantitat");
            quantitat.appendChild(doc.createTextNode(String.valueOf(quantitat_producte)));
            article.appendChild(quantitat);

            
            // Afegir la nova comanda a l'element arrel
            rootElement.appendChild(comanda);

            
            // Guardar el document modificat en un flux
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");

            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);

            
            // Escriure el fitxer modificat de nou al servidor
            InputStream modifiedInput = new ByteArrayInputStream(outputStream.toByteArray());
            channelSftp.put(modifiedInput, remoteFile);

            
            // Tancar fluxos i connexions
            modifiedInput.close();
            channelSftp.disconnect();
            session.disconnect();

            System.out.println("La comanda s'ha creat i desat correctament al servidor!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hi ha hagut algun error en crear la comanda :(");
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }


    // Crear Stock
    public static void stock() {
    	System.out.print("Escriu la ruta del fitxer XML al servidor: ");
        String remoteFile = sc.next();
        
        
        System.out.print("Escriu la descripció de l'article: ");
        String descripcio_article = sc.next();

        
        System.out.print("Escriu la quantitat de l'article: ");
        int quantitat_article = sc.nextInt();

        
        System.out.print("Escriu el preu de l'article: ");
        float preu_article = sc.nextFloat();

        
        // Informacio del servidor SFTP
        String remoteHost = "192.168.1.6";
        int port = 22;
        String username = "alalca";
        String password = "1234";

        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;

        
        try {
            // Connexio al servidor SFTP
            session = jsch.getSession(username, remoteHost, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            
            // Obrir el canal SFTP
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            
            // Llegir el fitxer XML remot en un flux
            InputStream inputStream = channelSftp.get(remoteFile);

            
            // Crear el DocumentBuilder
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputStream);

            
            // Trobar l'element arrel
            Element rootElement = doc.getDocumentElement();
            
            if (rootElement == null) {
                rootElement = doc.createElement("stock");
                doc.appendChild(rootElement);
            
                System.out.println("No s'ha trobat cap element inicial. Per defecte s'ha creat un element anomenat 'stock'");
            }

            
            // Trobar l'ultim ID existent
            NodeList articles = doc.getElementsByTagName("article");
            
            int maxId = 0;
            
            for (int i = 0; i < articles.getLength(); i++) {
                Element article = (Element) articles.item(i);
                int id = Integer.parseInt(article.getElementsByTagName("id").item(0).getTextContent());
            
                if (id > maxId) {
                    maxId = id;  // Actualitzar l'ultim ID mes alt
                }
            }
            
            int nouId = maxId + 1;

            
            // Crear un nou article
            Element article = doc.createElement("article");

            
            Element idElement = doc.createElement("id");
            idElement.appendChild(doc.createTextNode(String.valueOf(nouId)));
            article.appendChild(idElement);

            
            Element descripcio = doc.createElement("descripcio");
            descripcio.appendChild(doc.createTextNode(descripcio_article));
            article.appendChild(descripcio);

            
            Element quantitat = doc.createElement("quantitat");
            quantitat.appendChild(doc.createTextNode(String.valueOf(quantitat_article)));
            article.appendChild(quantitat);

            
            Element preu = doc.createElement("preu");
            preu.appendChild(doc.createTextNode(String.valueOf(preu_article)));
            article.appendChild(preu);

            
            // Afegir el nou article a l'element arrel
            rootElement.appendChild(article);

            
            // Guardar els canvis a l'XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");

            
            // Escriure el document modificat a un flux
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);

            
            // Escriure el fitxer modificat de nou al servidor
            InputStream modifiedInput = new ByteArrayInputStream(outputStream.toByteArray());
            channelSftp.put(modifiedInput, remoteFile);

            
            // Tancar fluxos i connexions
            modifiedInput.close();
            channelSftp.disconnect();
            session.disconnect();

            System.out.println("S'ha afegit el producte correctament!!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hi ha hagut un problema en afegir l'stock :(");
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }   
}