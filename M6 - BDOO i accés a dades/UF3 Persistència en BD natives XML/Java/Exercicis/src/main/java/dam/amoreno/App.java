package dam.amoreno;

import java.io.File;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XQueryService;

public class App {

   private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
   private static final String COLLECTION_PATH = "/db/uf3";
   // private static final String XML_FILE_PATH = "/db/uf3/books.xml";
   private static final String USERNAME = "admin";
   private static final String PASSWORD = "";

   public static void main(String[] args) {
       try {
           // Inicialitza la base de dades
           Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
           Database database = (Database) cl.getDeclaredConstructor().newInstance();
           DatabaseManager.registerDatabase(database);

           // Connexió amb la col·lecció
           Collection col = DatabaseManager.getCollection(URI + COLLECTION_PATH, USERNAME, PASSWORD);

           if (col == null) {
               System.out.println("Col·lecció no trobada. Creant col·lecció...");
               col = createCollection(COLLECTION_PATH);
           }

           /*
           // Carrega el fitxer XML a la col·lecció
           File xmlFile = new File(XML_FILE_PATH);
           if (!xmlFile.exists()) {
               System.out.println("El fitxer XML no existeix: " + XML_FILE_PATH);
               return;
           }
           addFileToCollection(col, xmlFile);
            */

           // Executa una consulta XQuery
           String xquery = "for $x in doc('books.xml')/bookstore/book/title return $x";
           executeXQuery(col, xquery);

       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   private static Collection createCollection(String collectionPath) throws XMLDBException {
       Collection rootCol = DatabaseManager.getCollection(URI + "/db", USERNAME, PASSWORD);
       CollectionManagementService service = (CollectionManagementService) rootCol.getService("CollectionManagementService", "1.0");
       return service.createCollection(collectionPath.substring("/db/".length()));
   }

   private static void addFileToCollection(Collection col, File file) throws XMLDBException {
       Resource resource = col.createResource(file.getName(), "XMLResource");
       resource.setContent(file);
       col.storeResource(resource);
       System.out.println("Fitxer carregat correctament a la col·lecció.");
   }

   private static void executeXQuery(Collection col, String xquery) throws XMLDBException {
       XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
       CompiledExpression compiledXQuery = xQueryService.compile(xquery);
       ResourceSet result = xQueryService.execute(compiledXQuery);

       System.out.println("Resultats de la consulta:");
       for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
           Resource resource = i.nextResource();
           System.out.println(resource.getContent());
       }
   }
}
