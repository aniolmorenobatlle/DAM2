package dam2.amoreno;

import java.util.Scanner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class App 
{

    static Scanner sc = new Scanner(System.in);

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    private static EntityManager em = emf.createEntityManager();
    
    private static DAOLlibres llibresDAO = new LlibresDAOImpl(emf);
    private static DAOCategories categoriesDAO = new CategoriesDAOImpl(emf);
    private static DAOAutors autorsDAO = new AutorsDAOImpl(emf);

    public static void main( String[] args )
    {
        System.out.println();

        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("------------------------------------");

            System.out.println("0. Sortir");
            System.out.println("1. Operacions llibres");
            System.out.println("2. Operacions autors");
            System.out.println("3. Operacions categories");

            System.out.println("------------------------------------");
            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    operacionsLlibre();
                    break;

                case 2:
                    operacionsAutor();
                    break;

                case 3:
                    operacionsCategoria();
                    break;

                case 0:
                    exit = true;
                    emf.close();
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }



    // 1. Operacions llibres
    public static void operacionsLlibre() {
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("------------------------------------");


            System.out.println("0. Tornar");
            System.out.println("1. Afegir llibre");
            System.out.println("2. Editar llibre");
            System.out.println("3. Llistar llibres");
            System.out.println("4. Eliminar llibre");

            System.out.println("------------------------------------");
            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    afegirLlibre();
                    break;

                case 2:
                    editarLlibre();
                    break;
                
                case 3:
                    llistarLlibres();
                    break;

                case 4:
                    eliminarLlibre();
                    break;

                case 0:
                    exit = true;
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }


    // 1.1 Afegir llibre
    public static void afegirLlibre() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Afegir Llibre");
        System.out.println("------------------------------------");
        
        System.out.println();

    
        System.out.println("Introdueix les dades del llibre:");
    
        System.out.print("Títol: ");
        String titol = sc.next();
    
        System.out.print("ISBN: ");
        String isbn = sc.next();
    
        System.out.print("Any de publicació: ");
        int any = sc.nextInt();
    
        System.out.print("ID de l'autor: ");
        int autorId = sc.nextInt();
    
        System.out.print("ID de la categoria: ");
        int categoriaId = sc.nextInt();
    

        Autors autor = em.find(Autors.class, autorId);
        if (autor == null) {
            System.out.println("Autor no trobat.");
            return;
        }
    
        Categories categoria = em.find(Categories.class, categoriaId);
        if (categoria == null) {
            System.out.println("Categoria no trobada.");
            return;
        }


        System.out.println();


        Llibres llibre = new Llibres(titol, isbn, any, autor, categoria);

        llibresDAO.create(llibre);

    
        System.out.println();
    }
    

    // 1.2 Editar llibre
    public static void editarLlibre() {
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

        System.out.println("Introdueix les dades del llibre:");
        System.out.println("NOTA: Si hi ha alguna dada que no vols editar, hauràs d'escriure les dades previes de forma manual.");
        System.out.println();

        System.out.print("Títol: ");
        String titol = sc.next();

        System.out.print("ISBN: ");
        String isbn = sc.next();

        System.out.print("Any de publicació: ");
        int any = sc.nextInt();

        System.out.print("ID de l'autor: ");
        int autorId = sc.nextInt();

        System.out.print("ID de la categoria: ");
        int categoriaId = sc.nextInt();

        System.out.println();

        // Obtenir l'objecte Autor a partir de l'id
        Autors autor = em.find(Autors.class, autorId);
        if (autor == null) {
            System.out.println("Autor no trobat.");
            return;
        }

        // Obtenir l'objecte Categoria a partir de l'id
        Categories categoria = em.find(Categories.class, categoriaId);
        if (categoria == null) {
            System.out.println("Categoria no trobada.");
            return;
        }

        llibre.setTitol(titol);
        llibre.setIsbn(isbn);
        llibre.setAny_publicacio(any);
        llibre.setAutor(autor);
        llibre.setCategoria(categoria);

        llibresDAO.update(id, llibre);

        System.out.println();
    }
    

    // 1.3 Llistar llibres
    public static void llistarLlibres() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Afegir Llibre");
        System.out.println("------------------------------------");
        
        System.out.println();

        for (Llibres llibre : llibresDAO.LlistarLlibres()) {

            System.out.println("Titol: " + llibre.getTitol());
            System.out.println("ISBN: " + llibre.getIsbn());
            System.out.println("Any de publicació: " + llibre.getAny_publicacio());


            if (llibre.getAutor() != null) {
                System.out.println("Autor: " + llibre.getAutor().getNom());
            } else {
                System.out.println("Autor: Autor desconegut");
            }

            if (llibre.getCategoria() != null) {
                System.out.println("Categoria: " + llibre.getCategoria().getNom_categoria());
            } else {
                System.out.println("Categoria: No té cap categoria assignada");
            }

            System.out.println("------------------------------------");
        }
    }


    // 1.4 Eliminar llibre
    public static void eliminarLlibre() {
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



    // 2. Operacions autors
    public static void operacionsAutor() {
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("------------------------------------");


            System.out.println("0. Tornar");
            System.out.println("1. Afegir autor");
            System.out.println("2. Editar autor");
            System.out.println("3. Llistar autors");
            System.out.println("4. Eliminar autor");

            System.out.println("------------------------------------");

            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    afegirAutor();
                    break;

                case 2:
                    editarAutor();
                    break;

                case 3:
                    llistarAutors();
                    break;

                case 4:
                    eliminarAutor();
                    break;

                case 0:
                    exit = true;
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }


    // 2.1 Afegir autor
    public static void afegirAutor() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Afegir Autor");
        System.out.println("------------------------------------");
        
        System.out.println();


        System.out.println("Introdueix les dades de l'autor:");

        System.out.print("Nom: ");
        String nom = sc.next();

        System.out.print("Cognom: ");
        String cognom = sc.next();

        System.out.print("Data de naixement (format YYYY-MM-DD): ");
        String data = sc.next();

        // Comprovar format data
        if (!data.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Format de data incorrecte. Comprova que el format sigui YYYY-MM-DD.");
        }


        System.out.println();


        Autors autor = new Autors(nom, cognom, data);

        if (autorsDAO.create(autor)) System.out.println("Autor creat correctament!!");
        
        System.out.println();
    }


    // 2.2 Editar autor
    public static void editarAutor() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Editar Autor");
        System.out.println("------------------------------------");
        
        System.out.println();

        System.out.print("Introdueix l'ID de l'autor a editar: ");
        int id = sc.nextInt();

        System.out.println();

        Autors autor = em.find(Autors.class, id);

        if (autor == null) {
            System.out.println("No s'ha trobat cap autor amb aquest id.");
            return;
        }


        System.out.println("Introdueix les dades de l'autor:");
        System.out.println("NOTA: Si hi ha alguna dada que no vols editar, hauràs d'escriure la/es dada prèvia de forma manual.");
        System.out.println();


        System.out.print("Nom de l'autor: ");
        String nom = sc.next();

        System.out.print("Cognoms de l'autor: ");
        String cognoms = sc.next();

        System.out.print("Data de naixement (format YYYY-MM-DD): ");
        String data = sc.next();


        // Comprovar format data
        if (!data.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Format de data incorrecte. Comprova que el format sigui YYYY-MM-DD.");
        }
        
        System.out.println();


        autor.setNom(nom);
        autor.setCognoms(cognoms);
        autor.setData_naixement(data);

        if (autorsDAO.update(id, autor)) System.out.println("Autor actualitzat!!");

        System.out.println();
    }


    // 2.3 Llistar autors
    public static void llistarAutors() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Llistar Autors");
        System.out.println("------------------------------------");
        
        System.out.println();

        for (Autors autor : autorsDAO.LlistarAutors()) {
            System.out.println("ID: " + autor.getId());
            System.out.println("Nom: " + autor.getNom());
            System.out.println("Cognom: " + autor.getCognoms());
            System.out.println("Data de naixement: " + autor.getData_naixement());

            System.out.println("------------------------------------");
        }
    }


    // 2.4 Eliminar autor
    public static void eliminarAutor() {
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Eliminar Autor");
        System.out.println("------------------------------------");
        
        System.out.println();

        System.out.print("Introdueix l'ID de l'autor a eliminar: ");
        int id = sc.nextInt();

        System.out.println();


        if (autorsDAO.delete(id)) System.out.println("Eliminació completada.");

        System.out.println();
    }



    // 3. Operacions categories
    public static void operacionsCategoria() {
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("------------------------------------");

            System.out.println("0. Tornar");
            System.out.println("1. Afegir categoria");
            System.out.println("2. Editar categoria");
            System.out.println("3. Llistar categories");
            System.out.println("4. Eliminar categoria");

            System.out.println("------------------------------------");
            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    afegirCategoria();
                    break;

                case 2:
                    editarCategoria();
                    break;

                case 3:
                    llistarCategories();
                    break;

                case 4:
                    eliminarCategoria();
                    break;

                case 0:
                    exit = true;
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }


    // 3.1 Afegir categoria
    public static void afegirCategoria() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Afegir Categoria");
        System.out.println("------------------------------------");
        
        System.out.println();


        System.out.println("Introdueix el nom de la categoria:");

        System.out.print("Nom: ");
        String nom = sc.next();

        System.out.println();

        Categories categoria = new Categories(nom);

        categoriesDAO.create(categoria);
        
        System.out.println();
    }


    // 3.2 Editar categoria
    public static void editarCategoria() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Editar Categoria");
        System.out.println("------------------------------------");
        
        System.out.println();


        System.out.print("Introdueix l'ID de la categoria a editar: ");
        int id = sc.nextInt();

        System.out.println();

        Categories categoria = em.find(Categories.class, id);

        if (categoria == null) {
            System.out.println("No s'ha trobat cap categoria amb aquest ID");
            return;
        }

        System.out.println("Introdueix les dades de l'autor:");
        System.out.println("NOTA: Si hi ha alguna dada que no vols editar, hauràs d'escriure les dades previes de forma manual.");
        System.out.println();


        System.out.print("Nom de la categoria: ");
        String nom = sc.next();

        System.out.println();

        categoria.setNom_categoria(nom);

        categoriesDAO.update(id, categoria);

        System.out.println();
    }

        
    // 3.3 Llistar categories
    public static void llistarCategories() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Llistar Categories");
        System.out.println("------------------------------------");
        
        System.out.println();


        for (Categories categoria : categoriesDAO.LlistarCategories()) {
            System.out.println("ID: " + categoria.getId());
            System.out.println("Nom: " + categoria.getNom_categoria());

            System.out.println("------------------------------------");
        }
    }


    // 3.4 Eliminar categoria
    public static void eliminarCategoria() {
        sc.useDelimiter("\\n");

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Eliminar Categoria");
        System.out.println("------------------------------------");
        
        System.out.println();


        System.out.print("Introdueix l'ID de la categoria a eliminar: ");
        int id = sc.nextInt();

        System.out.println();

        categoriesDAO.delete(id);

        System.out.println();
    }

}