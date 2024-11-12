package dam2.amoreno;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App 
{
    static Scanner sc = new Scanner(System.in);

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    private static EntityManager em = emf.createEntityManager();


    private static EstudiantDAO estudiantDAO = new EstudiantDAOImpl(emf);
    private static AssignaturaDAO assignaturaDAO = new AssignaturaDAOImpl(emf);
    private static MatriculaDAO matriculaDAO = new MatriculaDAOImpl(emf);

    public static void main( String[] args )
    {
        System.out.println();


        boolean exit = false;

        while (!exit) {
            
            System.out.println("-----------------------------------");
            System.out.println();

            System.out.println("0. Sortir");
            System.out.println("1. Operacions Estudiants");
            System.out.println("2. Operacions Assignatures");
            System.out.println("3. Operacions Matrícula");
            System.out.println("4. Consultes");

            System.out.println();
            System.out.println("-----------------------------------");

            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    estudiants();
                    break;

                case 2:
                    assignatures();
                    break;

                case 3:
                    matricula();
                    break;

                case 0:
                    System.out.println("Sortin...");
                    exit = true;
                    break;
            
                default:
                    System.out.println("Opció no vàlida");
                    break;
            }

            System.out.println();

        }
    }


    // 1. Estudiants
    static void estudiants() {

        System.out.println();


        boolean exit = false;

        while (!exit) {
            
            System.out.println("-----------------------------------");
            System.out.println();

            System.out.println("0. Sortir");
            System.out.println("1. Afegir estudiant");
            System.out.println("2. Modificar estudiant");
            System.out.println("3. Eliminar estudiant");
            System.out.println("4. Llistar estudiants");

            System.out.println();
            System.out.println("-----------------------------------");

            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    afegirEstudiant();
                    break;

                case 2:
                    modificarEstudiant();
                    break;

                case 3:
                    eliminarEstudiant();
                    break;

                case 4:
                    llistarEstudiants();
                    break;

                case 0:
                    System.out.println("Sortin...");
                    exit = true;
                    break;
            
                default:
                    System.out.println("Opció no vàlida");
            }

            System.out.println();

        }
    }

    // 1.1 Afegir estudiant
    static void afegirEstudiant() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Afegir estudinat");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();


        System.out.print("Nom: ");
        String nom = sc.next();

        System.out.print("Cognoms: ");
        String cognoms = sc.next();

        LocalDate date = null;
        boolean dataValida = false;

        while (!dataValida) {
            System.out.print("Data de naixement (format YYYY-MM-DD): ");
            String dataStr = sc.next();

            try {
                date = LocalDate.parse(dataStr);
                dataValida = true;
            } catch (Exception e) {
                System.out.println("Format de data incorrecte.");
            }
        }

        System.out.print("Correu electrònic: ");
        String email = sc.next();

        System.out.println();

        Estudiant estudiant = new Estudiant(nom, cognoms, email, date);

        estudiantDAO.create(estudiant);

        System.out.println();
    }

    // 1.2 Modificar estudinat
    static void modificarEstudiant() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Modificar estudinat");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();

        System.out.print("ID de l'estudiant a editar: ");
        Long id = sc.nextLong();

        Estudiant estudiantId = em.find(Estudiant.class, id);

        if (estudiantId == null)  {
            System.out.println("Estudiant no trobat.");
            return;
        }

        System.out.println();
        System.out.println("NOTA: Així edites totes les dades, si hi ha alguna dada que no vols que canvi l'hauràs d'escriure com estava.");
        System.out.println();

        System.out.print("Nom: ");
        String nom = sc.next();
        estudiantId.setNom(nom);

        System.out.print("Cognoms: ");
        String cognoms = sc.next();
        estudiantId.setCognoms(cognoms);

        LocalDate date = null;
        boolean dataValida = false;

        while (!dataValida) {
            System.out.print("Data de naixement (format YYYY-MM-DD): ");
            String dataStr = sc.next();

            try {
                date = LocalDate.parse(dataStr);
                dataValida = true;
            } catch (Exception e) {
                System.out.println("Format de data incorrecte.");
            }
        }
        estudiantId.setDataNaixement(date);

        System.out.print("Correu electrònic: ");
        String email = sc.next();
        estudiantId.setEmail(email);

        System.out.println();

        estudiantDAO.update(estudiantId, id);

        System.out.println();

    }

    // 1.3 Eliminar estudiant
    static void eliminarEstudiant() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Eliminar estudinat");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();


        System.out.print("ID de l'estudiant: ");
        Long id = sc.nextLong();

        estudiantDAO.delete(id);

    }

    // 1.4 Llistar estudinats
    static void llistarEstudiants() {
        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Llistar estudinats");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();

        List<Estudiant> estudiants = estudiantDAO.LlistarEstudiant();

        System.out.println("-----------------------------------");

        for (Estudiant estudiant : estudiants) {
            System.out.println("ID: " + estudiant.getId());
            System.out.println("Nom: " + estudiant.getNom());
            System.out.println("Cognoms: " + estudiant.getCognoms());
            System.out.println("Data de naixement: " + estudiant.getDataNaixement());
            System.out.println("Correu electrònic: " + estudiant.getEmail());

            System.out.println("-----------------------------------");
        }


        System.out.println();
    }



    // 2. Assignatures
    static void assignatures() {
        System.out.println();


        boolean exit = false;

        while (!exit) {
            
            System.out.println("-----------------------------------");
            System.out.println();

            System.out.println("0. Sortir");
            System.out.println("1. Afegir assignatura");
            System.out.println("2. Modificar assignatura");
            System.out.println("3. Eliminar assignatura");
            System.out.println("4. Llistar assignatures");

            System.out.println();
            System.out.println("-----------------------------------");

            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    afegirAssignatura();
                    break;

                case 2:
                    modificarAssignatura();
                    break;

                case 3:
                    eliminarAssignatura();
                    break;

                case 4:
                    llistarAssignatures();
                    break;

                case 0:
                    System.out.println("Sortin...");
                    exit = true;
                    break;
            
                default:
                    System.out.println("Opció no vàlida");
            }

            System.out.println();

        }
    }
    
    // 2.1 Afegir assignatura
    static void afegirAssignatura() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Afegir assignatura");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();


        System.out.print("Nom: ");
        String nom = sc.next();

        System.out.print("Descripció: ");
        String descripcio = sc.next();

        System.out.print("Codi: ");
        String codi = sc.next();

        System.out.println();

        Assignatura assignatura = new Assignatura(nom, descripcio, codi);

        assignaturaDAO.create(assignatura);

        System.out.println();
    }

    // 2.2 Modificar assignatura
    static void modificarAssignatura() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Modificar assignatura");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();

        System.out.print("ID de la assignatura a editar: ");
        Long id = sc.nextLong();

        Assignatura assignaturaId = em.find(Assignatura.class, id);

        if (assignaturaId == null)  {
            System.out.println("Estudiant no trobat.");
            return;
        }

        System.out.println();
        System.out.println("NOTA: Així edites totes les dades, si hi ha alguna dada que no vols que canvi l'hauràs d'escriure com estava.");
        System.out.println();

        System.out.print("Nom: ");
        String nom = sc.next();
        assignaturaId.setNom(nom);

        System.out.print("Descripció: ");
        String descripcio = sc.next();
        assignaturaId.setDescripcio(descripcio);

        System.out.print("Codi: ");
        String codi = sc.next();
        assignaturaId.setCodi(codi);

        System.out.println();

        assignaturaDAO.update(assignaturaId, id);

        System.out.println();

    }

    // 2.3 Eliminar assginatura
    static void eliminarAssignatura() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Eliminar assignatura");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();


        System.out.print("ID de la assignatura: ");
        Long id = sc.nextLong();

        assignaturaDAO.delete(id);

    }

    // 2.4 Llistar assignatures
    static void llistarAssignatures() {
        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Llistar estudinats");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();

        List<Assignatura> assignatures = assignaturaDAO.llistarAssignatures();

        System.out.println("-----------------------------------");

        for (Assignatura assignatura : assignatures) {
            System.out.println("ID: " + assignatura.getId());
            System.out.println("Nom: " + assignatura.getNom());
            System.out.println("Descripció: " + assignatura.getDescripcio());
            System.out.println("Codi: " + assignatura.getCodi());

            System.out.println("-----------------------------------");
        }


        System.out.println();
    }



    // 3. Matricula
    static void matricula() {
        System.out.println();


        boolean exit = false;

        while (!exit) {
            
            System.out.println("-----------------------------------");
            System.out.println();

            System.out.println("0. Sortir");
            System.out.println("1. Afegir matricula");
            System.out.println("2. Modificar matricula");
            System.out.println("3. Eliminar matricula");
            System.out.println("4. Llistar matricules");

            System.out.println();
            System.out.println("-----------------------------------");

            System.out.println();

            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();

            switch (opcio) {
                case 1:
                    afegirMatricula();
                    break;

                case 2:
                    modificarMatricula();
                    break;

                case 3:
                    eliminarMatricula();
                    break;

                case 4:
                    llistarMatricules();
                    break;

                case 0:
                    System.out.println("Sortin...");
                    exit = true;
                    break;
            
                default:
                    System.out.println("Opció no vàlida");
                    break;
            }

            System.out.println();

        }
    }

    // 2.1 Afegir matricula
    static void afegirMatricula() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Afegir matricula");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();


        // LocalDate date = null;
        // boolean dataValida = false;

        // while (!dataValida) {
        //     System.out.print("Data (format YYYY-MM-DD): ");
        //     String dataStr = sc.next();

        //     try {
        //         date = LocalDate.parse(dataStr);
        //         dataValida = true;
        //     } catch (Exception e) {
        //         System.out.println("Format de data incorrecte.");
        //     }
        // }

        // System.out.print("ID de l'estudiant: ");
        // int id_estudiant = sc.nextInt();

        // System.out.print("ID de l'assignatura: ");
        // int id_assignatura = sc.nextInt();
        

        // System.out.println();

        // Matricula matricula = new Matricula(date, id_assignatura, id_estudiant);

        // matriculaDAO.create(matricula);

        System.out.println();
    }

    // 2.2 Modificar matricula
    static void modificarMatricula() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Modificar assignatura");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();

        System.out.print("ID de la assignatura a editar: ");
        Long id = sc.nextLong();

        Assignatura assignaturaId = em.find(Assignatura.class, id);

        if (assignaturaId == null)  {
            System.out.println("Estudiant no trobat.");
            return;
        }

        System.out.println();
        System.out.println("NOTA: Així edites totes les dades, si hi ha alguna dada que no vols que canvi l'hauràs d'escriure com estava.");
        System.out.println();

        System.out.print("Nom: ");
        String nom = sc.next();
        assignaturaId.setNom(nom);

        System.out.print("Descripció: ");
        String descripcio = sc.next();
        assignaturaId.setDescripcio(descripcio);

        System.out.print("Codi: ");
        String codi = sc.next();
        assignaturaId.setCodi(codi);

        System.out.println();

        assignaturaDAO.update(assignaturaId, id);

        System.out.println();

    }

    // 2.3 Eliminar matricula
    static void eliminarMatricula() {
        sc.useDelimiter("\\n");

        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Eliminar matricula");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();


        System.out.print("ID de la matricula: ");
        Long id = sc.nextLong();

        matriculaDAO.delete(id);

    }

    // 2.4 Llistar matricules
    static void llistarMatricules() {
        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Llistar matricula");

        System.out.println();
        System.out.println("-----------------------------------");
        
        System.out.println();

        List<Matricula> matricules = matriculaDAO.llistarMatricula();

        System.out.println("-----------------------------------");

        for (Matricula matricula : matricules) {
            System.out.println("ID: " + matricula.getId());
            System.out.println("Data de la matricula: " + matricula.getDataMatricula());
            System.out.println("Cognoms de l'estudiant: " + matricula.getEstudiant().getCognoms());

            System.out.println("-----------------------------------");
        }


        System.out.println();
    }
}
