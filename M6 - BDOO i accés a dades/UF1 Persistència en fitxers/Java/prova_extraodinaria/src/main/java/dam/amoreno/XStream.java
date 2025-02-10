package dam.amoreno;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.thoughtworks.xstream.security.AnyTypePermission;

import dam.amoreno.classes.Persona;
import dam.amoreno.classes.Tasques;


public class XStream {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println();
        
            System.out.println("0. Sortir");
            System.out.println("1. Llegir");
            System.out.println("2. Escriure");
            System.out.println("3. Escriure sub");
        
            System.out.println();
            System.out.println("========================================");
            System.out.println();
        
        
            System.out.print("Selecciona una opció: ");
            int opcio = sc.nextInt();
            sc.nextLine();
        
            System.out.println();
        
            switch (opcio) {
                case 1:
                    Llegir();
                    break;

                case 2:
                    Escriure();
                    break;

                case 3:
                    EscriureSub();
                    break;
        
        
                case 0:
                    System.out.println("Sortint del programa...");
                    return;
        
        
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
        }
    }

    public static void Llegir() {
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

    public static void Escriure() {
        try {
            com.thoughtworks.xstream.XStream xstream = new com.thoughtworks.xstream.XStream();
            xstream.addPermission(AnyTypePermission.ANY);

            File ruta = new File("prova_extraodinaria/xmls/tasques.xml");

            xstream.alias("tasques", List.class);
            xstream.alias("tasca", Tasques.class);

            List<Tasques> tasques = new ArrayList<>();

            if (ruta.exists() && ruta.length() > 0) {
                try {
                    FileReader reader = new FileReader(ruta);
                    tasques = (List<Tasques>) xstream.fromXML(reader);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            tasques.add(new Tasques("prova3", "2020-10-10", "Moderada", "Personal"));
            
            try {
                FileWriter writer = new FileWriter(ruta);
                xstream.toXML(tasques, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void EscriureSub() {
        try {
            File ruta = new File("prova_extraodinaria/xmls/hotels.xml");

            com.thoughtworks.xstream.XStream xstream = new com.thoughtworks.xstream.XStream();
            xstream.addPermission(AnyTypePermission.ANY);

            xstream.alias("hotels", List.class);
            xstream.alias("hotel", Hotel.class);
            xstream.alias("habitacio", Habitacio.class);

            List<Hotel> hotels = new ArrayList<>();

            if (ruta.exists() && ruta.length() > 0) {
                try {
                    FileReader reader = new FileReader(ruta);
                    hotels = (List<Hotel>) xstream.fromXML(reader);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            List<Habitacio> habitacions = new ArrayList<>();
            habitacions.add(new Habitacio("301", "suite", 150));
            habitacions.add(new Habitacio("302", "suite", 200));

            hotels.add(new Hotel("Prova3", "prova3", habitacions));

            try {
                FileWriter writer = new FileWriter(ruta);
                xstream.toXML(hotels, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Hotel {
    private String nom;
    private String adreca;
    private List<Habitacio> habitacions;

    public Hotel(String nom, String adreca, List<Habitacio> habitacions) {
        this.nom = nom;
        this.adreca = adreca;
        this.habitacions = habitacions;
    }
}

class Habitacio {
    private String numero;
    private String tipus;
    private double preu;

    public Habitacio(String numero, String tipus, double preu) {
        this.numero = numero;
        this.tipus = tipus;
        this.preu = preu;
    }
}
