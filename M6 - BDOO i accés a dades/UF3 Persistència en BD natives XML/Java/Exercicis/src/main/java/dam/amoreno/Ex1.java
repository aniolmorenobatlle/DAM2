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


public class Ex1 {

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

                
                case 0:
                    System.out.println("Sortint del programa...");
                    return;
                
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    private static void ExerciciA(String collectionPath) throws XMLDBException {
        String xquery = "distinct-values(for $x in doc('centres_postobligatori.xml')/response/row/row where $x/nom_comarca = \"Gironès\" and contains($x/codi_ensenyament, \"CF\") return <titol>{$x/nom_ensenyament/text()}</titol>)";
    
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
        String xquery = "for $x in doc('centres_postobligatori.xml')/response/row/row where $x/nom_comarca = \"Gironès\" and contains($x/codi_ensenyament, \"CFPS\") return <titol>{$x/nom_ensenyament/text()}</titol>";
    
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
        String xquery = "for $x in doc('centres_postobligatori.xml')/response/row/row where $x/nom_delegaci = \"Girona\" and contains($x/codi_ensenyament, \"CF\") return <resultat> <titol>{ $x/nom_ensenyament/text() }</titol> <centre>{ $x/denominaci_completa/text() }</centre> <places>{ $x/nombre_places/text() }</places> </resultat>";
    
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
        String xquery = "for $x in doc('centres_postobligatori.xml')/response/row/row where number($x/nombre_places) < 30 return <resultat> <titol>{ $x/nom_ensenyament/text() }</titol> <centre>{ $x/denominaci_completa/text() }</centre> <places>{ $x/nombre_places/text() }</places> </resultat>";
    
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
        String xquery = "let $arxiu := doc('centres_postobligatori.xml') let $filtrats := $arxiu/response/row/row[nom_naturalesa=\"Públic\"] for $delegacio in distinct-values($filtrats/nom_delegaci) let $centres := $filtrats[nom_delegaci = $delegacio]/denominaci_completa order by $delegacio return <resultat> <delegacio>{ $delegacio }</delegacio> <centres> { for $centre in $centres order by $centre return $centre } </centres> </resultat>";
    
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
        String xquery = "let $filtrats := doc('centres_postobligatori.xml')/response/row/row[ contains(codi_ensenyament, \"CFPM\") and nom_comarca = \"Gironès\" ] return <resultat> <comarca>Gironès</comarca> <cicles_formatius>Cicles Formatius de Grau Mitjà</cicles_formatius> <total_places>{ sum($filtrats/nombre_places) }</total_places> </resultat>";
    
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

    private static void ExerciciG(String collectionPath) throws XMLDBException {
        String xquery = "let $filtrats := doc('centres_postobligatori.xml')/response/row/row[ contains(codi_ensenyament, \"CFPS\") and nom_comarca = \"Gironès\" ] return <resultat> <comarca>Gironès</comarca> <cicles_formatius>Cicles Formatius de Grau Mitjà</cicles_formatius> <total_places>{ sum($filtrats/nombre_places) }</total_places> </resultat>";
    
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
        String xquery = "for $x in doc('centres_postobligatori.xml')/response/row/row where $x/torn = \"Matí\" return <resultat> <cicle_formatiu>{ $x/nom_ensenyament/text() }</cicle_formatiu> <centre>{ $x/denominaci_completa/text() }</centre> <comarca>{ $x/nom_comarca/text() }</comarca> <places_ofertades>{ $x/places_ofertades_a_la/text() }</places_ofertades> </resultat>";
    
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
        String xquery = "for $x in doc('centres_postobligatori.xml')/response/row/row where $x/torn = \"Tarda\" return <resultat> <cicle_formatiu>{ $x/nom_ensenyament/text() }</cicle_formatiu> <centre>{ $x/denominaci_completa/text() }</centre> <comarca>{ $x/nom_comarca/text() }</comarca> <places_ofertades>{ $x/places_ofertades_a_la/text() }</places_ofertades> </resultat>";
    
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
        String xquery = "for $x in doc('centres_postobligatori.xml')/response/row/row where $x/torn = \"Matí i tarda\" return <resultat> <cicle_formatiu>{ $x/nom_ensenyament/text() }</cicle_formatiu> <centre>{ $x/denominaci_completa/text() }</centre> <comarca>{ $x/nom_comarca/text() }</comarca> <places_ofertades>{ $x/places_ofertades_a_la/text() }</places_ofertades> </resultat>";
    
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
        String xquery = "for $x in doc('centres_postobligatori.xml')/response/row/row[ contains(codi_ensenyament, \"CF\") and nom_comarca = \"Baix Empordà\" and curs = \"2021/2022\" ] let $cicle_2023 := doc('centres_postobligatori.xml')/response/row/row[ contains(codi_ensenyament, $x/codi_ensenyament) and nom_comarca = \"Baix Empordà\" and curs = \"2022/2023\" ] where not($cicle_2023) return <resultat> <cicle_formatiu>{ $x/nom_ensenyament/text() }</cicle_formatiu> <centre>{ $x/denominaci_completa/text() }</centre> <places_ofertades>{ $x/places_ofertades_a_la/text() }</places_ofertades> </resultat>";
    
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

        System.out.println("Encara no implementat.");

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

    private static void ExerciciM(String collectionPath) throws XMLDBException {

        System.out.println("Encara no implementat.");

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

}
