package dam2.amoreno;

import java.util.List;
import java.util.Scanner;

public class App 
{
    static Scanner sc = new Scanner(System.in);

    static DAO usuarisDAO = new UsuarisDAOImpl();

    public static void main( String[] args )
    {

        System.out.println("1. Afegir nou usuari");
        System.out.println("2. Modificar usuari");
        System.out.println("3. Eliminar usuari");
        System.out.println("4. Llistar tots els usuaris");
        System.out.println();

        System.out.print("Selecciona una opció: ");
        int opcio = sc.nextInt();

        System.out.println();

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
                llistarUsuaris();
                break;
                
                
            default:
                break;
        }

    }


    // 1. Afegir nou usuari
    public static void afegirUsuari() {
        
        System.out.println("Afegir un usuari:");
        System.out.println();


        Usuaris nouUsuari = new Usuaris();
        sc.useDelimiter("\\n");


        System.out.print("Nom de l'usuari: ");
        nouUsuari.setNom(sc.next());

        System.out.print("Cognom de l'usuari: ");
        nouUsuari.setCognoms(sc.next());

        System.out.print("Data de naixement de l'usuari (format YYYY-MM-DD): ");
        nouUsuari.setData_naixement(sc.next());

        System.out.print("Email de l'usuari: ");
        nouUsuari.setEmail(sc.next());

        System.out.println();

        // Comprovar que tot s'hagi afegit correctament
        boolean afegit = usuarisDAO.create(nouUsuari);

        if (afegit) {
            System.out.println("Usuari creat correctament!!");
        } else {
            System.out.println("Hi ha hagut algun problema en crear l'usuari!!");
        }
    }


    // 2. Modificar usuari
    public static void modificarUsuari() {

        System.out.println("Modificar usuari:");
        System.out.println();


        Usuaris nouUsuari = new Usuaris();
        sc.useDelimiter("\\n");


        System.out.print("Escriu el correu de l'usuari que vols editar: ");
        String email = sc.next();
        System.out.println();

        // Comprovar si existeix l'usuari amb el correu introudit
        Usuaris usuariExistent = usuarisDAO.cercaEmail(email);

        if (usuariExistent == null) {
            System.out.println("No existeix cap usuari amb aquest correu!!");
        }


        System.out.print("Nou nom: ");
        nouUsuari.setNom(sc.next());

        System.out.print("Nous cognoms: ");
        nouUsuari.setCognoms(sc.next());

        System.out.print("Nova data de naixement (format YYYY-MM-DD): ");
        nouUsuari.setData_naixement(sc.next());

        System.out.print("Nou correu electrònic: ");
        nouUsuari.setEmail(sc.next());

        nouUsuari.setId(usuariExistent.getId());

        // Comprovar que tot hagi funcionat correctament
        boolean actualitzatTot = usuarisDAO.update(nouUsuari);

        if (actualitzatTot) {
            System.out.println();
            System.out.println("Totes les dades actualitzades correctament!!");
        } else {
            System.out.println("Error en actualitzar les dades.");
        }


    }


    // 3. Eliminar usuaris
    public static void eliminarUsuari() {
        
        sc.useDelimiter("\\n");

        System.out.println("Eliminar usuari:");
        System.out.println();

        System.out.print("Email de l'usuari a eliminar: ");
        String email = sc.nextLine(); 


        Usuaris usuari = usuarisDAO.cercaEmail(email);

        // Comprovar si existeix l'usuari amb el correu introduit
        if (usuari != null) {

            boolean eliminat = usuarisDAO.delete(email);

            if (eliminat) {
                System.out.println("Usuari eliminat correctament");
            } else {
                System.out.println("Error en eliminar l'usuari!!");
            }

        } else {
            System.out.println("No existeix cap usuari amb aquest correu!!");
        }

    }


    // 4. Llistar usuaris
    public static void llistarUsuaris() {
        
        System.out.println("Usuaris: ");
        System.out.println();

        // Crear llista dels usuaris
        List<Usuaris> usuaris = usuarisDAO.LlistarUsuaris();


        System.out.println("---------------------------------------------");
        
        for (Usuaris usuari : usuaris) {
            System.out.println("Nom i cognoms: " + usuari.getNom() + " " + usuari.getCognoms());
            System.out.println("Data de naixement: " + usuari.getData_naixement());
            System.out.println("Email: " + usuari.getEmail());

            System.out.println("---------------------------------------------");
        }
    }
}
