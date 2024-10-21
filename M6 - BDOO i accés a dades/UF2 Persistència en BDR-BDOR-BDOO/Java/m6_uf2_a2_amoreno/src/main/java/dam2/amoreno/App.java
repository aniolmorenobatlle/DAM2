package dam2.amoreno;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class App 
{
    static Scanner sc = new Scanner(System.in);
    static Connection conn;

    static {
        try {
            conn = SingletonBD.getConnection();
        } catch (SQLException e) {
            System.out.println("Error en connectar-se a la base de dades!!");
        }
    }

    static DAOLlibres DAOLlibres = new LlibresDAOImpl(conn);
    static DAOAutors DAOAutors = new AutorsDAOImpl(conn);
    static DAOCategories DAOCategories = new CategoriesDAOImpl(conn);

    public static void main( String[] args )
    {
        
        try {
            System.out.println("Operacions: ");
            System.out.println();
            System.out.println("1. Operacions pels Llibres ");
            System.out.println("2. Operacions per Autors ");
            System.out.println("3. Operacions per Categories ");
            System.out.println();
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            System.out.println();

            switch (opcio) {
                case 1:
                    llibres();
                    break;

                case 2:
                    autors();
                    break;

                case 3:
                    categories();
                    break;

                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 1. LLibres
    public static void llibres() {
        
        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Llibres:");
        System.out.println();

        System.out.println("1. Afegir nou llibre");
        System.out.println("2. Llistar els llibres");
        System.out.println("3. Actualitzar un llibre");
        System.out.println("4. Eliminar un llibre");

        System.out.println();

        System.out.print("Selecciona una opció: ");
        int opcio = sc.nextInt();

        System.out.println();
        System.out.println("---------------------------------------------");

        switch (opcio) {
            case 1:
                afegirLlibre();
                break;

            case 2: 
                llistarLlibres(DAOLlibres);
                break;

            case 3:
                actualitzarLlibre();
                break;

            case 4: 
                eliminarLlibre(DAOLlibres);
                break;
        
            default:
                System.out.println("Opció no vàlida.");
                break;
        }
    }


    // 1.1 Afegir llibre
    public static void afegirLlibre() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Afegir un llibre: ");
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println();

        Llibres nouLlibre = new Llibres();

        System.out.print("Nom del llibre: ");
        nouLlibre.setTitol(sc.next());

        System.out.print("ISBN del llibre (ex: 978-0747532699): ");
        nouLlibre.setISBN(sc.next());

        System.out.print("Data de llençament del llibre: ");
        nouLlibre.setAny(sc.next());
        
        
        System.out.println();

        List<Autors> autors = DAOAutors.LlistarAutors();

        System.out.println("Autors: ");

        System.out.println("---------------------------------------------");


        for (Autors autor : autors) {
            System.out.println(autor.getId() + ". " + autor.getNom() + " " + autor.getCognoms());
            System.out.println("---------------------------------------------");
        }

        System.out.println();

        System.out.print("ID de l'autor de llibre: ");
        nouLlibre.setAutor(sc.next());

        System.out.println();


        List<Categories> categories = DAOCategories.LlistarCategories();

        System.out.println("Categories: ");

        System.out.println("---------------------------------------------");

        for (Categories categoria : categories) {
            System.out.println(categoria.getId() + ". " + categoria.getNom());
            System.out.println("---------------------------------------------");
        }

        System.out.println();

        System.out.print("ID de la categoria del llibre: ");
        nouLlibre.setCategoria(sc.next());

        System.out.println();

        boolean afegit = DAOLlibres.create(nouLlibre);

        if (afegit) System.out.println("El llibre s'ha afegir correctament!!");
    }


    // 1.2 Llistar llibres
    public static void llistarLlibres(DAOLlibres DAOLlibres) {
        System.out.println();
        System.out.println("Llistar llibres: ");
        System.out.println();

        List<Llibres> llibres = DAOLlibres.LlistarLlibres();

        System.out.println("---------------------------------------------");

        for (Llibres llibre : llibres) {
            System.out.println("Títol: " + llibre.getTitol());
            System.out.println("ISBN: " + llibre.getISBN());
            System.out.println("Any de publicació: " + llibre.getAny());
            System.out.println("Autor: " + llibre.getAutor());
            System.out.println("Categoria: " + llibre.getCategoria());

            System.out.println("---------------------------------------------");
        }

        System.out.println();
    }


    // 1.3 Actualitzar llibre
    public static void actualitzarLlibre() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Afegir un llibre: ");
        System.out.println();
        System.out.println("---------------------------------------------");


        List<Llibres> llibres = DAOLlibres.LlistarLlibres();

        System.out.println();
        System.out.println("Llibres: ");
        System.out.println("---------------------------------------------");

        for (Llibres llibre : llibres) {
            System.out.println(llibre.getId() + ". " + llibre.getTitol() + " - " + llibre.getAutor());
            System.out.println("---------------------------------------------");
        }

        System.out.println();


        Llibres nouLlibre = new Llibres();

        System.out.print("ID del llibre: ");
        nouLlibre.setId(sc.nextInt());

        System.out.println();
        
        System.out.println("NOTA: S'actualitzen totes les dades, si hi ha alguna dada que no vols actualitzar hauràs d'escriure la dada que ja hi havia!!");

        System.out.println();

        System.out.print("Nou nom del llibre: ");
        nouLlibre.setTitol(sc.next());

        System.out.print("Nova data de llençament del llibre: ");
        nouLlibre.setAny(sc.next());


        System.out.println();

        List<Autors> autors = DAOAutors.LlistarAutors();

        System.out.println("Autors: ");

        System.out.println("---------------------------------------------");


        for (Autors autor : autors) {
            System.out.println(autor.getId() + ". " + autor.getNom() + " " + autor.getCognoms());
            System.out.println("---------------------------------------------");
        }

        System.out.println();

        System.out.print("Selecciona l'ID l'autor del llibre: ");
        nouLlibre.setAutor(sc.next());

        System.out.println();


        List<Categories> categories = DAOCategories.LlistarCategories();

        System.out.println("Categories: ");

        System.out.println("---------------------------------------------");

        for (Categories categoria : categories) {
            System.out.println(categoria.getId() + ". " + categoria.getNom());
            System.out.println("---------------------------------------------");
        }

        System.out.println();

        System.out.print("Selecciona l'ID la categoria del llibre: ");
        nouLlibre.setCategoria(sc.next());

        System.out.println();

        boolean afegit = DAOLlibres.update(nouLlibre);

        if (afegit) System.out.println("El llibre s'ha afegir correctament!!");

    }


    // 1.4 Eliminar llibre
    public static void eliminarLlibre(DAOLlibres DAOLlibres) {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Eliminar llibre:");
        System.out.println();
        System.out.println("---------------------------------------------");
        
        System.out.println();
        System.out.println("Llibres: ");

        List<Llibres> llibres = DAOLlibres.LlistarLlibres();

        System.out.println("---------------------------------------------");


        for (Llibres llibre : llibres) {
            System.out.println(llibre.getId() + ". " + llibre.getTitol() + " - " + llibre.getAutor());
            System.out.println("---------------------------------------------");
        }

        Llibres eliminarLlibre = new Llibres();

        System.out.println();
        System.out.print("ID del llibre a eliminar: ");
        eliminarLlibre.setId(sc.nextInt());

        System.out.println();

        boolean eliminat = DAOLlibres.delete(eliminarLlibre.getId());

        if (eliminat) System.out.println("Llibre eliminat correctament!!");
    }




    // 2. Autors
    public static void autors() {
        System.out.println("---------------------------------------------");

        System.out.println("Autors:");
        System.out.println();

        System.out.println("1. Afegir nou autor");
        System.out.println("2. Llistar els autors");
        System.out.println("3. Actualitzar un autor");
        System.out.println("4. Eliminar un autor");

        System.out.println();

        System.out.print("Selecciona una opció: ");
        int opcio = sc.nextInt();

        System.out.println();
        System.out.println("---------------------------------------------");

        switch (opcio) {
            case 1:
                afegirAutor();
                break;

            case 2: 
                llistarAutors(DAOAutors);
                break;

            case 3:
                actualitzarAutor(DAOAutors);
                break;

            case 4: 
                eliminarAutor(DAOAutors);
                break;
        
            default:
                System.out.println("Opció no vàlida.");
                break;
        }
    }


    // 2.1 Afegir autor
    public static void afegirAutor() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Afegir autor:");
        System.out.println();
        System.out.println("---------------------------------------------");


        Autors nouAutor = new Autors();

        System.out.println();

        System.out.print("Nom del nou autor: ");
        nouAutor.setNom(sc.next());

        System.out.print("Cognoms del nou autor: ");
        nouAutor.setCognoms(sc.next());

        System.out.print("Data de naixement del nou autor (format YYYY-MM-DD): ");
        nouAutor.setData(sc.next());

        System.out.println();

        boolean afegit = DAOAutors.create(nouAutor);

        if (afegit) System.out.println("Autor creat correctament!!");
            
    }


    // 2.2 Llistar autors
    public static void llistarAutors(DAOAutors DAOAutors) {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Autor:");
        System.out.println();
        System.out.println("---------------------------------------------");

        List<Autors> autors = DAOAutors.LlistarAutors();

        for (Autors autor : autors) {
            System.out.println("Nom: " + autor.getNom());
            System.out.println("Cognoms: " + autor.getCognoms());
            System.out.println("Any de naixement: " + autor.getData());

            System.out.println("---------------------------------------------");
        }

        System.out.println();
    }


    // 2.3 Actualitzar autor
    public static void actualitzarAutor(DAOAutors DAOAutors) {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Actualitzar un autor:");
        System.out.println();
        System.out.println("---------------------------------------------");


        List<Autors> autors = DAOAutors.LlistarAutors();

        for (Autors autor : autors) {
            System.out.println(autor.getId() + ". " + autor.getNom() + " " + autor.getCognoms());
            System.out.println("---------------------------------------------");
        }

        System.out.println();


        Autors actualitzarAutor = new Autors();


        System.out.print("ID de l'autor a actualizar: ");
        actualitzarAutor.setId(sc.nextInt());

        System.out.println();

        System.out.println("NOTA: S'actualitzen totes les dades, si hi ha alguna dada que no vols actualitzar hauràs d'escriure la dada que ja hi havia!!");

        System.out.println();
        

        System.out.print("Nom: ");
        actualitzarAutor.setNom(sc.next());

        System.out.print("Cognoms: ");
        actualitzarAutor.setCognoms(sc.next());

        System.out.print("Any de naixement (en format YYYY-MM-DD): ");
        actualitzarAutor.setData(sc.next());

        System.out.println();

        boolean actualitzat = DAOAutors.update(actualitzarAutor);

        if (actualitzat) System.out.println("Autor actualitzat correctament!!");
    }


    // 2.4 Eliminar autor
    public static void eliminarAutor(DAOAutors DAOAutors) {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Eliminar un autor:");
        System.out.println();
        System.out.println("---------------------------------------------");

        System.out.println();

        System.out.println("Autors: ");
        System.out.println();

        List<Autors> autors = DAOAutors.LlistarAutors();

        System.out.println("---------------------------------------------");


        for (Autors autor : autors) {
            System.out.println(autor.getId() + ". " + autor.getNom() + autor.getCognoms());
            System.out.println("---------------------------------------------");
        }

        Autors eliminarAutor = new Autors();

        System.out.println();
        System.out.print("ID del autor a eliminar: ");
        eliminarAutor.setId(sc.nextInt());

        System.out.println();

        boolean eliminat = DAOAutors.delete(eliminarAutor.getId());

        if (eliminat) System.out.println("Autor eliminat correctament!!");
    }




    // 3. Categories
    public static void categories() {
        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Categories:");
        System.out.println();

        System.out.println("1. Afegir nou categoria");
        System.out.println("2. Llistar els categories");
        System.out.println("3. Actualitzar un categoria");
        System.out.println("4. Eliminar un categoria");

        System.out.println();

        System.out.print("Selecciona una opció: ");
        int opcio = sc.nextInt();

        System.out.println();
        System.out.println("---------------------------------------------");

        switch (opcio) {
            case 1:
                afegirCategoria();
                break;

            case 2: 
                llistarCategories(DAOCategories);
                break;

            case 3:
                actualitzarCategoria(DAOCategories);
                break;

            case 4:
                eliminarCategoria(DAOCategories);
                break;
        
            default:
                System.out.println("Opció no vàlida.");
                break;
        }
    }


    // 2.1 Afegir categoria
    public static void afegirCategoria() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Afegir una categoria:");
        System.out.println();
        System.out.println("---------------------------------------------");

        System.out.println();

        Categories novaCategoria = new Categories();

        System.out.print("Nom de la nova categoria (Ex: Ficció): ");
        novaCategoria.setNom(sc.next());

        System.out.println();

        boolean afegit = DAOCategories.create(novaCategoria);

        if (afegit) System.out.println("Categoria creada correctament!!");
    }


    // 2.2 Llistar cateogires
    public static void llistarCategories(DAOCategories DAOCategories) {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Categories:");
        System.out.println();
        System.out.println("---------------------------------------------");

        List<Categories> categories = DAOCategories.LlistarCategories();

        for (Categories categoria : categories) {
            System.out.println(categoria.getNom());

            System.out.println("---------------------------------------------");
        }
    
        System.out.println();
    }


    // 2.3 Actualitzar categoria
    public static void actualitzarCategoria(DAOCategories DAOCategories) {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Actualitzar una categoria:");
        System.out.println();
        System.out.println("---------------------------------------------");

        Categories actualitzarCategoria = new Categories();


        List<Categories> categories = DAOCategories.LlistarCategories();

        for (Categories categoria : categories) {
            System.out.println(categoria.getId() + ". " + categoria.getNom());
            System.out.println("---------------------------------------------");
        }

        System.out.println();

        System.out.print("ID de la categoria a actualitzar: ");
        actualitzarCategoria.setId(sc.nextInt());

        System.out.println();

        System.out.println("NOTA: S'actualitzen totes les dades, si hi ha alguna dada que no vols actualitzar hauràs d'escriure la dada que ja hi havia!!");

        System.out.println();

        System.out.print("Escriu el nou nom de la categoria: ");
        actualitzarCategoria.setNom(sc.next());

        System.out.println();

        boolean actualitzat = DAOCategories.update(actualitzarCategoria);

        if (actualitzat) System.out.println("La categoria s'ha actualitzat correctament!!");

    }


    // 2.4 Eliminar categoria
    public static void eliminarCategoria(DAOCategories DAOCategories) {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Eliminar una categoria:");
        System.out.println();
        System.out.println("---------------------------------------------");

        System.out.println();

        System.out.println("Categories Actuals: ");

        List<Categories> categories = DAOCategories.LlistarCategories();

        System.out.println("---------------------------------------------");

        for (Categories categoria : categories) {
            System.out.println(categoria.getId() + ". " + categoria.getNom());
            System.out.println("---------------------------------------------");
        }

        Categories eliminarCategoria = new Categories();

        System.out.println();
        System.out.print("ID de la categoria a eliminar: ");
        eliminarCategoria.setId(sc.nextInt());

        System.out.println();

        boolean eliminat = DAOCategories.delete(eliminarCategoria.getId());

        if (eliminat) System.out.println("Categoria eliminada correctament!!");
    }

}
