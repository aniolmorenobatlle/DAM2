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
                break;
        }
    }


    // 1.1 Afegir llibre
    public static void afegirLlibre() {
        
    }


    // 1.2 Llistar llibres
    public static void llistarLlibres(DAOLlibres DAOLlibres) {
        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Llibres: ");
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
    }


    // 1.3 Actualitzar llibre
    public static void actualitzarLlibre() {}


    // 1.4 Eliminar llibre
    public static void eliminarLlibre(DAOLlibres DAOLlibres) {
        sc.useDelimiter("\\n");

        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Eliminar llibre:");
        System.out.println();

        System.out.println("Llibres: ");
        System.out.println();

        List<Llibres> llibres = DAOLlibres.LlistarLlibres();

        System.out.println("---------------------------------------------");

        int i = 1;

        for (Llibres llibre : llibres) {
            System.out.println(i + ". " + llibre.getTitol() + " - " + llibre.getAutor());
            i++;
            System.out.println("---------------------------------------------");
        }

        Llibres eliminarLlibre = new Llibres();

        System.out.println();
        System.out.print("Id del llibre a eliminar: ");
        eliminarLlibre.setId(sc.nextInt());

        System.out.println();

        boolean eliminat = DAOLlibres.delete(eliminarLlibre.getId());

        if (eliminat) {
            System.out.println("Llibre eliminat correctament!!");
        } else {
            System.out.println("Error en eliminar el llibre!!");;
        }
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

        switch (opcio) {
            case 1:
                afegirAutor();
                break;

            case 2: 
                llistarAutors(DAOAutors);
                break;

            case 3:
                actualitzarAutor();
                break;

            case 4: 
                eliminarAutor(DAOAutors);
                break;
        
            default:
                break;
        }
    }


    // 2.1 Afegir autor
    public static void afegirAutor() {
        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Afegir autor:");
        System.out.println();


        Autors nouAutor = new Autors();
        sc.useDelimiter("\\n");

        System.out.print("Nom del l'autor: ");
        nouAutor.setNom(sc.next());

        System.out.print("Cognoms de l'autor: ");
        nouAutor.setCognoms(sc.next());

        System.out.print("Data de naixement de l'autor (format YYYY-MM-DD): ");
        nouAutor.setData(sc.next());

        System.out.println();

        boolean afegit = DAOAutors.create(nouAutor);

        if (afegit) {
            System.out.println("Autor creat correctament!!");
        } else {
            System.out.println("Hi ha hagut algun problema en crear l'autor!!");
        }
    }


    // 2.2 Llistar autors
    public static void llistarAutors(DAOAutors DAOAutors) {
        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Autors: ");
        System.out.println();

        List<Autors> autors = DAOAutors.LlistarAutors();

        System.out.println("---------------------------------------------");

        for (Autors autor : autors) {
            System.out.println("Nom: " + autor.getNom());
            System.out.println("Cognoms: " + autor.getCognoms());
            System.out.println("Any de naixement: " + autor.getData());

            System.out.println("---------------------------------------------");
        }
    }


    // 2.3 Actualitzar autor
    public static void actualitzarAutor() {}


    // 2.4 Eliminar autor
    public static void eliminarAutor(DAOAutors DAOAutors) {
        sc.useDelimiter("\\n");

        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Eliminar autor:");
        System.out.println();

        System.out.println("Autors: ");
        System.out.println();

        List<Autors> autors = DAOAutors.LlistarAutors();

        System.out.println("---------------------------------------------");

        int i = 1;

        for (Autors autor : autors) {
            System.out.println(i + ". " + autor.getNom() + autor.getCognoms());
            i++;
            System.out.println("---------------------------------------------");
        }

        Autors eliminarAutor = new Autors();

        System.out.println();
        System.out.print("Id del autor a eliminar: ");
        eliminarAutor.setId(sc.nextInt());

        System.out.println();

        boolean eliminat = DAOAutors.delete(eliminarAutor.getId());

        if (eliminat) {
            System.out.println("Autor eliminat correctament!!");
        } else {
            System.out.println("Error en eliminar l'autor!!");
        }
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

        switch (opcio) {
            case 1:
                llistarCategories(DAOCategories);
                afegirCategoria();
                break;

            case 2: 
                llistarCategories(DAOCategories);
                break;

            case 3:
                actualitzarCategoria();
                break;

            case 4:
                eliminarCategoria(DAOCategories);
                break;
        
            default:
                break;
        }
    }


    // 2.1 Afegir categoria
    public static void afegirCategoria() {
        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Afegir categoria:");
        System.out.println();


        Categories novaCategoria = new Categories();
        sc.useDelimiter("\\n");

        System.out.print("Nom de la categoria (Ex: Ficció): ");
        novaCategoria.setNom(sc.next());

        System.out.println();

        boolean afegit = DAOCategories.create(novaCategoria);

        if (afegit) {
            System.out.println("Categoria creada correctament!!");
        } else {
            System.out.println("Hi ha hagut algun problema en crear la categoria!!");
        }
    }


    // 2.2 Llistar cateogires
    public static void llistarCategories(DAOCategories DAOCategories) {
        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Categories: ");
        System.out.println();

        List<Categories> categories = DAOCategories.LlistarCategories();

        System.out.println("---------------------------------------------");

        for (Categories categoria : categories) {
            System.out.println(categoria.getNom());

            System.out.println("---------------------------------------------");
        }
    }


    // 2.3 Actualitzar categoria
    public static void actualitzarCategoria() {}


    // 2.4 Eliminar categoria
    public static void eliminarCategoria(DAOCategories DAOCategories) {
        sc.useDelimiter("\\n");

        System.out.println("---------------------------------------------");

        System.out.println();
        System.out.println("Eliminar categoria:");
        System.out.println();


        System.out.println("Categories Actuals: ");
        System.out.println();

        List<Categories> categories = DAOCategories.LlistarCategories();

        System.out.println("---------------------------------------------");

        int i = 1;

        for (Categories categoria : categories) {
            System.out.println(i + ". " + categoria.getNom());
            i++;
            System.out.println("---------------------------------------------");
        }

        Categories eliminarCategoria = new Categories();

        System.out.println();
        System.out.print("Id de la categoria a eliminar: ");
        eliminarCategoria.setId(sc.nextInt());

        System.out.println();

        boolean eliminat = DAOCategories.delete(eliminarCategoria.getId());

        if (eliminat) {
            System.out.println("Categoria eliminada correctament!!");
        } else {
            System.err.println("Error en eliminar la categoria!!");
        }
    }

}
