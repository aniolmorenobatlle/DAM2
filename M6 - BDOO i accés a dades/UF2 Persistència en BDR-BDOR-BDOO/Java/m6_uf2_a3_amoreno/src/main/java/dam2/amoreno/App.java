package dam2.amoreno;

import java.util.Scanner;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class App {
    static Scanner sc = new Scanner(System.in);

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    private static EntityManager em = emf.createEntityManager();
    private static DAOUsuaris usuarisDAO = new UsuarisDAOImpl(emf);


    public static void main(String[] args) {
        
        System.out.println();

        boolean exit = false;

        while (!exit) {
            System.out.println("0. Sortir");
            System.out.println("1. Afegir usuari");
            System.out.println("2. Modificar usuari");
            System.out.println("3. Eliminar usuaris");
            System.out.println("4. Llistar usuaris");

            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    afegirUsuari();
                    break;

                case 2:
                    modificarUsuari();
                    break;

                case 3:
                    eliminarUsuari();
                    break;

                case 4:
                    boolean mostrarId = false;
                    llistarUsuaris(mostrarId);
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


    // 1. Afegir nou usuari
    public static void afegirUsuari() {
        sc.useDelimiter("\\n");
        
        System.out.println("Afegir un usuari:");
        System.out.println();

        try {

            System.out.print("Nom de l'usuari: ");
            String nom = sc.next();

            System.out.print("Cognom de l'usuari: ");
            String cognom = sc.next();

            System.out.print("Data de naixement de l'usuari (format YYYY-MM-DD): ");
            String data = sc.next();

            System.out.print("Email de l'usuari: ");
            String mail = sc.next();

            System.out.println();

            Usuaris usuari = new Usuaris(nom, cognom, data, mail);

            em.getTransaction().begin();
            em.persist(usuari);
            em.getTransaction().commit();
            
            System.out.println("Usuari afegit amb èxit!");
            System.out.println();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.out.println("Error en afegir l'usuari.");
            System.out.println();

        }
    }


    // 2. Modificar usuari
    public static void modificarUsuari() {
        sc.useDelimiter("\\n");
        
        System.out.println("Modificar un usuari");
        System.out.println();

        boolean mostrarId = true;

        llistarUsuaris(mostrarId);

        
        System.out.print("Introdueix l'ID de l'usuari que vols editar: ");
        int id = sc.nextInt();

        System.out.println();

        System.out.println("NOTA: Si vols editar nomes una dades hauràs d'escriure manualment la dada ja existen.");

        System.out.println();


        System.out.print("Nou nom: ");
        String nouNom = sc.next();

        System.out.print("Nous cognoms: ");
        String nousCognoms = sc.next();

        System.out.print("Nova data de naixement (format YYYY-MM-DD): ");
        String novaData = sc.next();

        System.out.print("Nou email: ");
        String nouEmail = sc.next();
    

        System.out.println();

        Usuaris usuariActualitzat = new Usuaris(nouNom, nousCognoms, novaData, nouEmail);

        boolean editat = usuarisDAO.update(id, usuariActualitzat);

        
        if (editat) System.out.println("Usuari editat correctament.");

        System.out.println();
    }


    // 3. Eliminar usuaris
    public static void eliminarUsuari() {
        sc.useDelimiter("\\n");
    
        System.out.println("Eliminar usuari:");
        System.out.println();

        boolean mostrarId = true;

        llistarUsuaris(mostrarId);
    
        
        System.out.print("Introdueix l'ID de l'usuari que vols eliminar: ");
        int id = sc.nextInt();
    
        boolean eliminat = usuarisDAO.delete(id);
    
        if (eliminat) {
            System.out.println("Usuari eliminat correctament.");
            System.out.println();
        } else {
            System.out.println("No s'ha pogut eliminar l'usuari.");
            System.out.println();
        }
    }   


    // 4. Llistar usuaris
    public static void llistarUsuaris(boolean mostrarId) {
        
        System.out.println("Usuaris: ");
        System.out.println();


        DAOUsuaris daoUsuaris = new UsuarisDAOImpl(emf);

        List<Usuaris> usuaris = daoUsuaris.LlistarUsuaris();

        System.out.println("-------------------------------------------");

        for (Usuaris usuari : usuaris) {
            if (mostrarId) {
                System.out.println("ID: " + usuari.getId());
            }

            System.out.println("Nom: " + usuari.getNom());
            System.out.println("Cognoms: " + usuari.getCognoms());
            System.out.println("Data de naixement: " + usuari.getData_naixement());
            System.out.println("Email: " + usuari.getEmail());


            System.out.println("-------------------------------------------");
        }

        System.out.println();

    }
}
