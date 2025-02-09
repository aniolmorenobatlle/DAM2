package dam.amoreno;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.thoughtworks.xstream.security.AnyTypePermission;

import dam.amoreno.classes.Persona;


public class XStream {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {

            com.thoughtworks.xstream.XStream xstream = new com.thoughtworks.xstream.XStream();
            xstream.addPermission(AnyTypePermission.ANY);

            xstream.alias("agenda", List.class);
            xstream.alias("persona", Persona.class);

            // Llegir fitxer XML
            File fitxer = new File("prova_extraodinaria/xmls/dom.xml");
            FileReader reader = new FileReader(fitxer);

            // Convertir XML a Llista de persones
            List<Persona> persones = (List<Persona>) xstream.fromXML(reader);

            // Iterar i mostrar persones
            for (Persona p : persones) {
                System.out.println(p);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
