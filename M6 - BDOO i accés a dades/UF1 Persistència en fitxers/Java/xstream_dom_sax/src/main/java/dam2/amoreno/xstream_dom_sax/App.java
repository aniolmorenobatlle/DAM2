package dam2.amoreno.xstream_dom_sax;

/* DOM */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/* XSTREAM */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.WildcardTypePermission;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/* SAX */
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import java.util.Scanner;



public class App
{
	static int option;
	static Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		do {
			
			System.out.println("Selecciona una opció:");
			System.out.println();
			
			System.out.println("0. Sortir");
			System.out.println("1. DOM");
			System.out.println("2. XStream");
			System.out.println("3. SAX");

			System.out.print("Selecciona una opció: ");
			option = sc.nextInt();

			switch (option) {
				case 0:
					System.out.println("Sortint del programa...");
					break;
				
				case 1:
					DOM();
					break;
	
				case 2:
					xStream();
					break;
	
				case 3:
					SAX();
					break;

				default:
					System.out.println("Opció no vàlida. Torna-ho a provar.");
					break;
				}
			
		} while (option != 0);
	}
    

	
	/* DOM */
	public static void DOM() {
		System.out.println("1. Llegir");
		System.out.println("2. Escriure");
		
		System.out.println("Selecciona una opció: ");
		option = sc.nextInt();
		
		System.out.println();
		
		switch(option) {
            case 1:
                DOMRead();
                break;
                
            case 2:
                DOMWrite();
                break;
		}
	}
	
	/* DOM READ*/
	public static void DOMRead() {
		
		String XMLfile = "src/main/java/dam2/amoreno/xstream_dom_sax/dom.xml";
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document = builder.parse(new File(XMLfile));
			
			document.getDocumentElement().normalize();
			
				// Llegir l'informacio que estan dins l'etiqueta persona
	            NodeList listPeople = document.getElementsByTagName("persona");
	            
	            for (int i = 0; i < listPeople.getLength(); i++) {
	            	// Iterar sobre els elements de persona
	            	Node node = listPeople.item(i);
	            	
	            	// Mirar que sigui l'ultim element, que no hi hagi subelements
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
						System.out.println();
	            	}
	            }

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* DOM WRITE */
	public static void DOMWrite() {
	    String XMLfile = "src/main/java/dam2/amoreno/xstream_dom_sax/dom.xml";
	    
	    try {
	    	
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document document = builder.parse(new File(XMLfile));
	        
	        // Normalitzem el document (es per si hi han errors com espais en blanc, eliminar aquests errors per a que el document sigui correcte d'escriure)
	        document.getDocumentElement().normalize();
	        
	        
	        // Dades (elements) de la nova persona
	        Element newPersona = document.createElement("persona");

	        Element newNom = document.createElement("nom");
	        System.out.print("Introdueix el nom: ");
	        newNom.appendChild(document.createTextNode(sc.next()));
	        
	        Element newCognom = document.createElement("cognom");
	        System.out.print("Introdueix el cognom: ");
	        newCognom.appendChild(document.createTextNode(sc.next()));
	        
	        Element newMail = document.createElement("mail");
	        System.out.print("Introdueix el mail: ");
	        newMail.appendChild(document.createTextNode(sc.next()));
	        
	        Element newTelefon = document.createElement("telefon");
	        System.out.print("Introdueix el telefon: ");
	        newTelefon.appendChild(document.createTextNode(sc.next()));
	        
	        
	        // Afegim els elements
	        newPersona.appendChild(newNom);
	        newPersona.appendChild(newCognom);
	        newPersona.appendChild(newMail);
	        newPersona.appendChild(newTelefon);
	        
	        // Afegim persona dins l'element de agenda
	        document.getDocumentElement().appendChild(newPersona);
	        
	        // Indentar el fitxer perque estigui ben estructurat i no quedi tot amb una linia
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        
	        DOMSource source = new DOMSource(document);
	        StreamResult result = new StreamResult(new File(XMLfile));
	        
	        // Escriu els canvis al fitxer
	        transformer.transform(source, result);
	        
	        
	        System.out.println("S'ha afegit correctament!!");
	        System.out.println();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	/* XSTREAM */
	public static void xStream() {
		System.out.println("1. Llegir");
		System.out.println("2. Escriure");
		
		System.out.println("Selecciona una opció: ");
		option = sc.nextInt();
		
		switch(option) {
            case 1:
                xStreamRead();
                break;
                
            case 2:
                xStreamWrite();
                break;
		}
	}
	
	/* XSTREAM READ */
	public static void xStreamRead() {

        XStream xstream = new XStream();

        // Donar els permisos al fitxer
        xstream.addPermission(AnyTypePermission.ANY);
        
        
        // Alias per posar el nom de l'element de la llista, per defecte es list
        xstream.alias("agenda", List.class);

        // Alias per indicar la classe que es vol obtenir
        xstream.alias("persona", Persona_XStream.class);
        
        try {
        	
            FileReader reader = new FileReader(new File("src/main/java/dam2/amoreno/xstream_dom_sax/xstream.xml"));

            // Convertir XML a objecte per aixi poder llegir-lo
            List<Persona_XStream> persones = (List<Persona_XStream>) xstream.fromXML(reader);


            for (int i = 0; i < persones.size(); i++) {
                Persona_XStream persona = persones.get(i);
                System.out.println(persona);
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
    /* XSTREAM WRITE */
	public static void xStreamWrite() {
		XStream xstream = new XStream();
		
		System.out.print("Escriu al ruta de l'arxiu: ");
		String ruta = sc.next();

        File fitxer = new File(ruta);
        
        xstream.addPermission(AnyTypePermission.ANY);
        

		xstream.alias("agenda", List.class);
        xstream.alias("persona", Persona_XStream.class);

        
        // Crea llista buida per les persones a crear
        List<Persona_XStream> persones = new ArrayList<>();


        // Comprovar si el fitxer existeix
        if (fitxer.exists()) {
            try {
                FileReader reader = new FileReader(fitxer);
                persones = (List<Persona_XStream>) xstream.fromXML(reader);
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // Dades de la nova persona
        System.out.print("Introdueix el nom: ");
        String nom = sc.next();
        
        System.out.print("Introdueix el cognom: ");
        String cognom = sc.next();
        
        System.out.print("Introdueix el mail: ");
        String mail = sc.next();
        
        System.out.print("Introdueix el telefon: ");
        String telefon = sc.next();
        
        System.out.println();

        // Afegeix noves persones a la llista
        persones.add(new Persona_XStream(nom, cognom, mail, telefon));

        try {
            // Escriure la nova persona
            FileWriter writer = new FileWriter(fitxer);
            xstream.toXML(persones, writer);
            writer.close();

            System.out.println("S'ha afegit correctament!!");
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}	
	
	
	
	/* SAX */
	public static void SAX() {
		String SAXfile = "src/main/java/dam2/amoreno/xstream_dom_sax/sax.xml";
		
		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			
			SAXParser parser = factory.newSAXParser();
			
			SAXHandler handler = new SAXHandler();
			
			File file = new File(SAXfile);
			
			parser.parse(file, handler);
			
			System.out.println();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
