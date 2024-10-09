package dam.amoreno;

import java.util.Scanner;
import java.io.File;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args )
    {
        System.out.println("1. Afegir noves tasques.");
        System.out.println("2. Llegir el fitxer.");

        System.out.print("Selecciona una opció: ");
        int opcio = sc.nextInt();

        System.out.println();

        switch (opcio) {
            case 1:
                afegir();
                break;
            
            case 2:
                llegir();
                break;
        

            default:
                break;
        }
    }

    // Funció de afegir nous elements al document
    public static void afegir() {
        System.out.print("Escriu la ruta del fitxer (incloent el nom.xml): ");
        String ruta = sc.next();

        System.out.println();

        try {

            // Crear un XStream
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);

            // Crear un nou fitxer a partir de la ruta obtinguda
            File file = new File(ruta);

            // Crear els alias
            xstream.alias("tasques", List.class);
            xstream.alias("tasca", XStream_Prova1.class);

            // Crear array buida per en un futur posar les noves dades a inserir
            List<XStream_Prova1> llista = new ArrayList<>();

            if (file.exists()) {
                try {
                    FileReader reader = new FileReader(file);
                    llista = (List<XStream_Prova1>) xstream.fromXML(reader);

                    reader.close();
                    
                } catch (Exception e) {
                    System.out.println("No s'ha trobat el fitxer!!");
                }
            }

            // Demanar les noves dades
            System.out.print("Escriu el nou nom que vols afegir: ");
            String nou_nom = sc.next();
            System.out.println();

            System.out.print("Escriu la nova data que vols afegir (en format YYYY-MM-DD): ");
            String nou_data = sc.next();
            System.err.println();

            System.out.print("Escriu la prioritat que serà (Alta, Moderada, Baixa): ");
            String nou_prioritat = sc.next();
            System.out.println();

            System.out.print("Escriu la nova categoria, (Personal, Feina): ");
            String nou_categoria = sc.next();
            System.out.println();

            // Afegir aquestes noves dades i guarda-les a la llista
            llista.add(new XStream_Prova1(nou_nom, nou_data, nou_prioritat, nou_categoria));

            try {
                // Escriure les noves dades
                FileWriter writer = new FileWriter(file);

                // Tornar a convertir el fitxer en XML
                xstream.toXML(llista, writer);

                writer.close();

            } catch (Exception e) {
                System.out.println("Hi ha hagut algun problema en afegir les noves dades :( !!");
            }


        } catch(Exception e) {
            System.out.println("Hi ha hagut algun problema en processar les dades :( !!");
        }
    }


    // Funció de llegir el document
    public static void llegir() {
        System.out.print("Escriu la ruta del fitxer (incloent el nom.xml): ");
        String ruta = sc.next();

        System.out.println();

        try {

            // Crear un XStream
            XStream xstream = new XStream();
            xstream.addPermission(AnyTypePermission.ANY);

            // Crear un nou fitxer a partir de la ruta obtinguda
            File file = new File(ruta);

            // Crear els alias
            xstream.alias("tasques", List.class);
            xstream.alias("tasca", XStream_Prova1.class);

            // Crear un FileReader per poder llegir el contingut
            FileReader reader = new FileReader(file);

            // Crear una llista per poder llegir el contingut del XML
            @SuppressWarnings("unchecked")
            List<XStream_Prova1> llista = (List<XStream_Prova1>) xstream.fromXML(reader);

            // Iterar les llista per imprimir els resultat del XML
            for (int i = 0; i < llista.size(); i++) {
                XStream_Prova1 etiquetes = llista.get(i);

                System.out.println(etiquetes);
                System.out.println();
            }

        } catch(Exception e) {
            System.out.println("Hi ha hagut algun problema en processar les dades :( !!");
        }
    }

}
