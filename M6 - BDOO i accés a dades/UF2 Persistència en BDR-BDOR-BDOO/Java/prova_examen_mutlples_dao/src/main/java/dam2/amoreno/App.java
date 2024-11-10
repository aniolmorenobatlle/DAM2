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

    static LlibresDAO llibresDAO = new LlibresDAOImpl(conn);

    public static void main( String[] args )
    {


        boolean exit = false;

        while (!exit) {

            System.out.println();

            System.out.println("-------------------------");
    
            System.out.println("0. Sortir");
            System.out.println("1. Llibres");
            System.out.println("2. Autors");
            System.out.println("3. Categories");
    
            System.out.println("-------------------------");
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

                case 0:
                    System.out.println("Sortin...");
                    exit = true;
                    break;
            
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }

        }
    }


    // 1. Llibres
    static void llibres() {
        System.out.println();
    
        boolean exit = false;
    
        while (!exit) {
            System.out.println("-------------------------");
            System.out.println("Operacions Llibre:");
            System.out.println("-------------------------");
    
            System.out.println("0. Sortir");
            System.out.println("1. Afegir llibre");
            System.out.println("2. Modificar llibre");
            System.out.println("3. Eliminar llibre");
            System.out.println("4. Llistar llibres");

            System.out.println("-------------------------");

            System.out.println();
    
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            System.out.println();
    
            switch (opcio) {
                case 1:
                    afegirLlibre();
                    break;
                case 2:
                    modificarLlibre();
                    break;
                case 3:
                    eliminarLlibre();
                    break;
                case 4:
                    llistarLlibres();
                    break;

                case 0:
                    System.out.println("Sortin...");
                    exit = true;
                    break;
                    
                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }
    

    // 1.1 Afegir llibre
    static void afegirLlibre() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Afegir llibre: ");
        System.out.println();

        Llibres llibre = new Llibres();

        System.out.print("Titol: ");
        llibre.setTitol(sc.next());

        System.out.print("ISBN: ");
        llibre.setISBN(sc.next());

        System.out.print("Any de publicació: ");
        llibre.setAny(sc.next());

        System.out.print("ID de l'autor: ");
        llibre.setAutor(sc.next());

        System.out.print("ID de la categoria: ");
        llibre.setCategoria(sc.next());

        System.out.println();

        boolean afegit = llibresDAO.create(llibre);

        if (afegit) System.out.println("Llibre afegit correctament!!");

        System.out.println();
    }

    // 1.2 Modificar Llibre
    static void modificarLlibre() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Editar llibre: ");
        System.out.println();

        Llibres llibre = new Llibres();

        System.out.print("ID del llibre a editar: ");
        llibre.setId(sc.nextInt());


        System.out.println();
        System.out.println("NOTA: Si no vols editar alguna dada hauràs d'escriure la dada anterior.");
        System.out.println();


        System.out.print("Titol: ");
        llibre.setTitol(sc.next());

        System.out.print("Any de publicació: ");
        llibre.setAny(sc.next());

        System.out.print("ID de l'autor: ");
        llibre.setAutor(sc.next());

        System.out.print("ID de la categoria: ");
        llibre.setCategoria(sc.next());

        System.out.println();

        boolean modificat = llibresDAO.update(llibre);

        if (modificat) System.out.println("Llibre modificar correctament!!");

        System.out.println();
    }

    // 1.3 Eliminar llibre
    static void eliminarLlibre() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("Afegir llibre: ");
        System.out.println();

        System.out.print("ID del llibre a eliminar: ");
        int id = sc.nextInt();

        System.out.println();

        boolean eliminat = llibresDAO.delete(id);

        if (eliminat) System.out.println("Llibre eliminat correctament!!");

        System.out.println();
    }

    // 1.4 Llistar llibres
    static void llistarLlibres() {
        System.out.println();
        System.out.println("Llistar llibres: ");
        System.out.println();

        List<Llibres> llibres = llibresDAO.LlistarLlibres();

        System.out.println("-------------------------");

        for (Llibres llibre : llibres) {
            System.out.println("Títol: " + llibre.getTitol());
            System.out.println("ISBN: " + llibre.getISBN());
            System.out.println("Any de publicació: " + llibre.getAny());
            System.out.println("Autor: " + llibre.getAutor());
            System.out.println("Categoria: " + llibre.getCategoria());

            System.out.println("-------------------------");
        }

        System.out.println();
    }



    // 2. Autors
    static void autors() {

    }



    // 3. Categories
    static void categories() {

    }

}
