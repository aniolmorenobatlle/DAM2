package dam2.amoreno;

import java.util.Scanner;

public class App 
{

    static Scanner sc = new Scanner(System.in);

    
    public static void main( String[] args )
    {
        Conector conn = new Conector();

        ConectorImpl listener = new ConectorImpl();

        conn.setListener(listener);

        System.out.println();

        System.out.print("Gestor de base de dades: ");
        conn.setGestor(sc.nextLine());

        System.out.print("IP: ");
        conn.setIp(sc.nextLine());

        System.out.print("Port: ");
        conn.setPort(sc.nextInt());
        sc.nextLine();

        System.out.print("Nom d'usuari: ");
        conn.setUsuari(sc.nextLine());

        System.out.print("Contrasenya: ");
        conn.setContrasenya(sc.nextLine());

        System.out.print("Nom de la base de dades: ");
        conn.setNom_bbdd(sc.nextLine());


        System.out.println();

        if (conn.obrirConnexio()) {
            conn.connexioActiva();

            conn.tancarConnexio();
        }
    }
}
