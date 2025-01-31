package dam.amoreno;

import java.io.File;
import java.util.Scanner;

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


public class Ex4 {

    private static final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            DatabaseManager.registerDatabase(database);

            while (true) {
                System.out.println("\n========================================");
                System.out.println();

                System.out.println("0. Sortir");
                System.out.println("1. Crear col·lecció");
                System.out.println("2. Afegir fitxer XML a la col·lecció");
                System.out.println("3. Executar consulta XQuery");
                System.out.println("4. Llistar col·leccions");
                System.out.println("5. Eliminar col·leccions");

                System.out.println();
                System.out.println("========================================");
                System.out.println();

                System.out.print("Selecciona una opció: ");
                int opcio = sc.nextInt();
                sc.nextLine();

                System.out.println();

                switch (opcio) {
                    case 1:
                        System.out.print("Introdueix el nom de la col·lecció: ");
                        String collectionPath = sc.nextLine();
                        createCollection(collectionPath);

                        System.out.println();
                        break;

                    case 2:
                        System.out.print("Introdueix la ruta del fitxer XML a carregar: ");
                        String filePath = sc.nextLine();
                        addFileToCollection(filePath);

                        System.out.println();
                        break;

                    case 3:
                        executeXQuery();

                        System.out.println();
                        break;

                    case 4:
                        listCollections();
                        break;

                    case 5:
                        deleteCollections();
                        break;
                    
                    case 0:
                        System.out.println("Sortint del programa...");
                        return;
                    
                    default:
                        System.out.println("Opció no vàlida. Intenta-ho de nou.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createCollection(String collectionPath) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(URI + "/db", USERNAME, PASSWORD);
        if (col == null) {
            System.out.println("No s'ha pogut connectar amb la base de dades.");
            return;
        }
    
        // Comprovar si la col·leccio existeix
        Collection newCollection = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
        if (newCollection != null) {
            System.out.println("LA COL·LECCIÓ " + collectionPath + " JA EXISTEIX!!");
            return;
        }
    
        // Si no existeix es crea
        CollectionManagementService service = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        service.createCollection(collectionPath.substring("/db/".length()));
        System.out.println("Col·lecció creada correctament a: " + collectionPath);
    }

    private static void listCollections() throws XMLDBException {
        Collection col = DatabaseManager.getCollection(URI + "/db", USERNAME, PASSWORD);
        if (col == null) {
            System.out.println("No s'ha pogut connectar amb la base de dades.");
            return;
        }
    
        System.out.println("Col·leccions disponibles:");
        String[] children = col.listChildCollections();
    
        if (children == null || children.length == 0) {
            System.out.println("No hi ha subcol·leccions disponibles.");
        } else {
            for (String child : children) {
                System.out.println(child);
            }
        }
    }

    private static void deleteCollections() throws XMLDBException {
        Collection col = DatabaseManager.getCollection(URI + "/db", USERNAME, PASSWORD);
        if (col == null) {
            System.out.println("No s'ha pogut connectar amb la base de dades.");
            return;
        }

        System.out.print("Introdueix el nom de la col·lecció a eliminar: ");
        String collectionPath = sc.nextLine();

        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }

        Collection collection = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
        if (collection == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        CollectionManagementService service = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        service.removeCollection(collectionPath.substring("/db/".length()));
        System.out.println("Col·lecció eliminada correctament.");
    }

    private static void addFileToCollection(String filePath) throws XMLDBException {
        File xmlFile = new File(filePath);
        if (!xmlFile.exists()) {
            System.out.println("El fitxer no existeix.");
            return;
        }
    
        System.out.print("Introdueix el nom de la col·lecció a la qual afegir el fitxer: ");
        String collectionPath = sc.nextLine();
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }
    
        Resource resource = col.createResource(xmlFile.getName(), "XMLResource");
        resource.setContent(xmlFile);
        col.storeResource(resource);
        System.out.println("Fitxer carregat correctament a la col·lecció.");
    }

    private static void executeXQuery() throws XMLDBException {

        while (true) {
            System.out.println("\n========================================");
            System.out.println();

            System.out.println("0. Sortir");
            System.out.println("1. Exercici A ");
            System.out.println("2. Exercici B ");
            System.out.println("3. Exercici C ");
            System.out.println("4. Exercici D ");
            System.out.println("5. Exercici E ");
            System.out.println("6. Exercici F ");
            System.out.println("7. Exercici G ");
            System.out.println("8. Exercici H ");
            System.out.println("9. Exercici I ");
            System.out.println("10. Exercici J ");
            System.out.println("11. Exercici K ");
            System.out.println("12. Exercici L ");
            System.out.println("13. Exercici M ");
            System.out.println("14. Exercici N ");


            System.out.println();
            System.out.println("========================================");
            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();

            System.out.println();

            System.out.print("Introdueix el nom de la col·lecció a la qual fer la consulta: ");
            String collectionPath = sc.nextLine();

            System.out.println();

            switch (opcio) {
                case 1:
                    ExerciciA(collectionPath);
                    break;

                case 2:
                    ExerciciB(collectionPath);
                    break;

                case 3:
                    ExerciciC(collectionPath);
                    break;
                
                case 4:
                    ExerciciD(collectionPath);
                    break;

                case 5:
                    ExerciciE(collectionPath);
                    break;

                case 6:
                    ExerciciF(collectionPath);
                    break;

                case 7:
                    ExerciciG(collectionPath);
                    break;

                case 8:
                    ExerciciH(collectionPath);
                    break;

                case 9:
                    ExerciciI(collectionPath);
                    break;

                case 10:
                    ExerciciJ(collectionPath);
                    break;

                case 11:
                    ExerciciK(collectionPath);
                    break;

                case 12:
                    ExerciciL(collectionPath);
                    break;

                case 13:
                    ExerciciM(collectionPath);
                    break;

                case 14:
                    ExerciciN(collectionPath);
                    break;

                
                case 0:
                    System.out.println("Sortint del programa...");
                    return;
                
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    private static void ExerciciA(String collectionPath) throws XMLDBException {
        String xquery = "for $x in doc('ex1/meteorite-landings.xml')/response/row/row where number($x/mass) > 10 return <resultat> <nom>{ $x/name/text() }</nom> </resultat>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciB(String collectionPath) throws XMLDBException {
        String xquery = "for $x in doc('ex1/meteorite-landings.xml')/response/row/row order by $x/year return <resultat> <nom>{ $x/name/text() }</nom> <any>{ $x/year/text() }</any> </resultat>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciC(String collectionPath) throws XMLDBException {
        String xquery = "for $x in doc('ex1/meteorite-landings.xml')/response/row/row where number($x/mass) > 10 return <resultat> <nom>{ $x/name/text() }</nom> <coordenades> <lat>{ $x/reclat/text() }</lat> <long>{ $x/reclong/text() }</long> </coordenades> </resultat>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciD(String collectionPath) throws XMLDBException {
        String xquery = "for $x in doc('ex1/meteorite-landings.xml')/response/row/row where number($x/mass) > 20 and number($x/mass) < 50 return <resultat> <nom>{ $x/name/text() }</nom> </resultat>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciE(String collectionPath) throws XMLDBException {
        String xquery = "for $x in doc('ex1/meteorite-landings.xml')/response/row/row where empty($x/year) return <resultat> <nom>{ $x/name/text() }</nom> </resultat>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciF(String collectionPath) throws XMLDBException {

        System.out.println("Query encara no implementar.");

        // String xquery = "";
    
        // if (!collectionPath.startsWith("/db")) {
        //     collectionPath = "/db/" + collectionPath;
        // }
    
        // Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        // if (col == null) {
        //     System.out.println("Col·lecció no trobada.");
        //     return;
        // }

        // XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        // CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        // ResourceSet result = xQueryService.execute(compiledXQuery);

        // System.out.println("Resultats de la consulta:");
        // for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
        //     Resource resource = i.nextResource();
        //     System.out.println(resource.getContent());
        // }
    }

    private static void ExerciciG(String collectionPath) throws XMLDBException {
        String xquery = "let $x := doc('ex1/meteorite-landings.xml')/response/row/row let $maxMass := max($x/mass) let $maxRow := $x[mass = $maxMass] return <resultat> <nom>{ $maxRow/name/text() }</nom> <massa>{ $maxRow/mass }</massa> </resultat>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciH(String collectionPath) throws XMLDBException {
        String xquery = "let $x := doc('ex1/meteorite-landings.xml')/response/row/row for $group in distinct-values($x/recclass) let $count := count($x[recclass = $group]) return <result> <name>{ $group }</name> <total>{ $count }</total> </result>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciI(String collectionPath) throws XMLDBException {
        String xquery = "let $total := count( doc('ex1/meteorite-landings.xml')/response/row/row[ fall = \"Fell\" and year < \"1900\" ] ) return <total> { $total } </total>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciJ(String collectionPath) throws XMLDBException {
        String xquery = "for $x in doc('ex1/meteorite-landings.xml')/response/row/row where empty($x/name) or empty($x/id) or empty($x/nametype) or empty($x/recclass) or empty($x/mass) or empty($x/fall) or empty($x/year) or empty($x/reclat) or empty($x/reclong) or empty($x/geolocation) return <result> <nom>{ $x/name/text() }</nom> </result>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciK(String collectionPath) throws XMLDBException {
        String xquery = "let $resultats := for $x in doc('ex1/meteorite-landings.xml')/response/row/row where $x/mass order by number($x/mass) descending return <resultat> <nom>{ $x/name/text() }</nom> <mass>{ $x/mass/text() }</mass> </resultat> return subsequence($resultats, 1, 5)";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciL(String collectionPath) throws XMLDBException {

        System.out.println("Query encara no implementar.");

        // String xquery = "let $resultats := for $x in doc('ex1/meteorite-landings.xml')/response/row/row where $x/mass order by number($x/mass) descending return <resultat> <nom>{ $x/name/text() }</nom> <mass>{ $x/mass/text() }</mass> </resultat> return subsequence($resultats, 1, 5)";
    
        // if (!collectionPath.startsWith("/db")) {
        //     collectionPath = "/db/" + collectionPath;
        // }
    
        // Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        // if (col == null) {
        //     System.out.println("Col·lecció no trobada.");
        //     return;
        // }

        // XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        // CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        // ResourceSet result = xQueryService.execute(compiledXQuery);

        // System.out.println("Resultats de la consulta:");
        // for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
        //     Resource resource = i.nextResource();
        //     System.out.println(resource.getContent());
        // }
    }

    private static void ExerciciM(String collectionPath) throws XMLDBException {
        String xquery = "let $x := doc('ex1/meteorite-landings.xml')/response/row/row let $deu := $x[ number(mass) <= 10 ] let $cinquanta := $x[ number(mass) > 10 and number(mass) <= 50 ] let $mes := $x[ number(mass) > 50 ] return <result> <intervals> <deu> <count>{ count($deu) }</count> </deu> <cinquanta> <count>{ count($cinquanta) }</count> </cinquanta> <mes> <count>{ count($mes) }</count> </mes> </intervals> </result>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

        XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
        CompiledExpression compiledXQuery = xQueryService.compile(xquery);
        ResourceSet result = xQueryService.execute(compiledXQuery);

        System.out.println("Resultats de la consulta:");
        for (ResourceIterator i = result.getIterator(); i.hasMoreResources(); ) {
            Resource resource = i.nextResource();
            System.out.println(resource.getContent());
        }
    }

    private static void ExerciciN(String collectionPath) throws XMLDBException {
        String xquery = "let $any_actual := year-from-date(current-date()) let $ultims_cinquanta := $any_actual -50 for $x in doc('ex1/meteorite-landings.xml')/response/row/row where number(substring($x/year, 1, 4)) >= $ultims_cinquanta return <result> <name>{ $x/name/text() }</name> <id>{ $x/id/text() }</id> <recclass>{ $x/recclass/text() }</recclass> <mass>{ $x/mass/text() }</mass> <fall>{ $x/fall/text() }</fall> <year>{ $x/year/text() }</year> <reclat>{ $x/reclat/text() }</reclat> <reclong>{ $x/reclong/text() }</reclong> </result>";
    
        if (!collectionPath.startsWith("/db")) {
            collectionPath = "/db/" + collectionPath;
        }
    
        Collection col = DatabaseManager.getCollection(URI + collectionPath, USERNAME, PASSWORD);
    
        if (col == null) {
            System.out.println("Col·lecció no trobada.");
            return;
        }

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
