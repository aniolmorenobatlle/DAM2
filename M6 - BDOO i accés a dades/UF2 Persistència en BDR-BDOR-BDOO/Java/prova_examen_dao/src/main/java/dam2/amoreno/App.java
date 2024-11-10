package dam2.amoreno;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class App 
{

    static Scanner sc = new Scanner(System.in);
    static UsuarisDAO usuarisDAO = new UsuariDAOImpl();

    public static void main( String[] args )
    {
        boolean exit = false;

        while (!exit) {
            System.out.println();

            System.out.println("--------------------------");

            System.out.println("0. Sortir");
            System.out.println("1. Crear usuari");
            System.out.println("2. Modificar usuari");
            System.out.println("3. Eliminar usuari");
            System.out.println("4. Llistar usuaris");

            System.out.println("--------------------------");

            System.out.println();


            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            System.out.println();

            switch (opcio) {
                case 1:
                    crear();
                    break;

                case 2:
                    modificar();
                    break;

                case 3:
                    eliminar();
                    break;

                case 4:
                    llistar();
                    break;

                case 0:
                    System.out.println("Sortin...");
                    System.out.println();
                    exit = true;
                    break;
            
                default:
                    System.out.println("Opció no vàlida!!");
            }
        }
    }


    static void crear() {
        sc.useDelimiter("\\n");

        System.out.println("--------------------------");
        System.out.println("Crear usuari");
        System.out.println("--------------------------");
        System.out.println();

        Usuaris nouUsuari = new Usuaris();

        System.out.print("Nom de l'usuari: ");
        nouUsuari.setNom(sc.next());

        System.out.print("Cognoms de l'usuari: ");
        nouUsuari.setCognom(sc.next());

        LocalDate dataNaixement = null;
        boolean dataValida = false;

        while (!dataValida) {
            System.out.print("Data de naixement (format YYYY-MM-DD): ");
            String dataStr = sc.next();
            try {
                dataNaixement = LocalDate.parse(dataStr);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Format de data incorrecte. Torna a intentar-ho.");
            }
        }
        nouUsuari.setData_naixement(dataNaixement);

        System.out.print("Email de l'usuari: ");
        nouUsuari.setEmail(sc.next());

        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        boolean creat = usuarisDAO.create(nouUsuari);

        if (creat) System.out.println("Usuari creat correctament.");
    }

    static void modificar() {
        sc.useDelimiter("\\n");

        System.out.println("--------------------------");
        System.out.println("Modificar usuari");
        System.out.println("--------------------------");
        System.out.println();

        Usuaris editarUsuari = new Usuaris();

        System.out.print("ID de l'usuari a editar: ");
        editarUsuari.setId(sc.nextInt());

        System.out.println();
        System.out.println("NOTA: Si no vols actualitzar totes les dades hauràs d'escriure les dades previes.");
        System.out.println();

        System.out.print("Nou nom de l'usuari: ");
        editarUsuari.setNom(sc.next());

        System.out.print("Nous cognoms de l'usuari: ");
        editarUsuari.setCognom(sc.next());

        LocalDate dataNaixement = null;
        boolean dataValida = false;

        while (!dataValida) {
            System.out.print("Nova data de naixement (format YYYY-MM-DD): ");
            String dataStr = sc.next();
            try {
                dataNaixement = LocalDate.parse(dataStr);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Format de data incorrecte. Torna a intentar-ho.");
            }
        }
        editarUsuari.setData_naixement(dataNaixement);

        System.out.print("Nou correu de l'usuari: ");
        editarUsuari.setEmail(sc.next());

        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        boolean editat = usuarisDAO.update(editarUsuari);

        if (editat) System.out.println("Usuari editat correctament!!");
    }

    static void eliminar() {
        sc.useDelimiter("\\n");

        System.out.println("--------------------------");
        System.out.println("Eliminar usuari");
        System.out.println("--------------------------");
        System.out.println();

        System.out.print("ID de l'usuari a eliminar: ");
        int id = sc.nextInt();

        System.out.println();
        System.out.println("--------------------------");
        System.out.println();

        boolean eliminat = usuarisDAO.delete(id);

        if (eliminat) System.out.println("Usuair eliminat correctament!!");
    }

    static void llistar() {
        System.out.println("--------------------------");
        System.out.println("Llistar usuaris");
        System.out.println("--------------------------");
        System.out.println();

        List<Usuaris> usuaris = usuarisDAO.LlistarUsuaris();

        System.out.println("--------------------------");
        for (Usuaris usuari : usuaris) {
            System.out.println("ID: " + usuari.getId());
            System.out.println("Nom: " + usuari.getNom());
            System.out.println("Cognoms: " + usuari.getCognom());
            System.out.println("Data de naixement: " + usuari.getData_naixement());
            System.out.println("Email: " + usuari.getEmail());

            System.out.println("--------------------------");
        }
    }
}
