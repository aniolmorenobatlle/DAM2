package dam2.amoreno;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App 
{

    static Scanner sc = new Scanner(System.in);

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    private static EntityManager em = emf.createEntityManager();

    private static LlibresDAO llibresDAO = new LlibresDAOImpl(emf);

    public static void main( String[] args )
    {
        System.out.println();

        boolean exit = false;

        while (!exit) {

            System.out.println("------------------------------");
            System.out.println();

            System.out.println("0. Sortir");
            System.out.println("1. Operacions Llibre");
            System.out.println("1. Operacions Llibre");
            System.out.println("3. Operacions Categoria");

            System.out.println();
            System.out.println("------------------------------");

            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            System.out.println();

            switch (opcio) {
                case 1:
                    llibre();
                    break;
                
                case 2:
                    autor();
                    break;

                case 3:
                    categoria();
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


    // 1. Llibre
    static void llibre() {

        boolean exit = false;

        while (!exit) {

            System.out.println();

            System.out.println("------------------------------");
            System.out.println("Operacions Llibre");
            System.out.println("------------------------------");
    
            System.out.println();

            System.out.println("------------------------------");
            System.out.println();

            System.out.println("0. Sortir");
            System.out.println("1. Afegir llibre");
            System.out.println("2. Modificar llibre");
            System.out.println("3. Eliminar llibre");
            System.out.println("4. Llistar llibres");
            
            System.out.println();
            System.out.println("------------------------------");

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
                    System.out.println("Opció no vàlida");
            }
        }
    }


    // 1.1 Afegir llibre
    static void afegirLlibre() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("------------------------------");
        System.out.println("Afegir Llibre");
        System.out.println("------------------------------");

        System.out.println();

            
        System.out.print("Títol: ");
        String titol = sc.next();
    
        System.out.print("ISBN: ");
        String isbn = sc.next();
    
        System.out.print("Any de publicació: ");
        int any = sc.nextInt();
    

        Llibres llibre = new Llibres(titol, isbn, any);

        llibresDAO.create(llibre);

        System.out.println();
    }


    // 1.2 Modificar llibre
    static void modificarLlibre() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Editar Llibre");
        System.out.println("------------------------------------");
        
        System.out.println();

        System.out.print("Introdueix l'ID del llibre a editar: ");
        int id = sc.nextInt();

        Llibres llibre = em.find(Llibres.class, id);

        if (llibre == null) {
            System.out.println("Llibre no trobat.");
            return;
        }

        System.out.println();
        System.out.println("NOTA: Si hi ha alguna dada que no vols editar, hauràs d'escriure les dades previes de forma manual.");
        System.out.println();

        System.out.print("Títol: ");
        String titol = sc.next();

        System.out.print("ISBN: ");
        String isbn = sc.next();

        System.out.print("Any de publicació: ");
        int any = sc.nextInt();

        System.out.println();

        llibre.setTitol(titol);
        llibre.setIsbn(isbn);
        llibre.setAny_publicacio(any);

        llibresDAO.update(id, llibre);

        System.out.println();
    }


    // 1.3 Eliminar llibre
    static void eliminarLlibre() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Eliminar Llibre");
        System.out.println("------------------------------------");
        
        System.out.println();

        System.out.print("Introdueix l'ID del llibre a eliminar: ");
        int id = sc.nextInt();

        System.out.println();

        llibresDAO.delete(id);

        System.out.println();
    }


    // 1.4 Llistar llibres
    static void llistarLlibres() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("------------------------------");
        System.out.println("Llistar Llibres");
        System.out.println("------------------------------");

        System.out.println();

        System.out.println("------------------------------");

        for (Llibres llibre : llibresDAO.LlistarLlibre()) {
            System.out.println("ID: " + llibre.getId());
            System.out.println("Titol: " + llibre.getTitol());
            System.out.println("ISBN: " + llibre.getIsbn());
            System.out.println("Data de publicació: " + llibre.getAny_publicacio());
            // System.out.println("Autor: " + llibre.getAutor());
            // System.out.println("Categoria: " + llibre.getCategoria());

            System.out.println("------------------------------");
        }

        System.out.println();

    }



    // 2. Autor
    static void autor() {}



    // 3. Categoria
    static void categoria() {}
}
