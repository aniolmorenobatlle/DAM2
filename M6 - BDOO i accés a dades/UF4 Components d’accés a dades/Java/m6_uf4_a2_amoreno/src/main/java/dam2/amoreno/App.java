package dam2.amoreno;

import java.util.Scanner;

public class App 
{

    static Scanner sc = new Scanner(System.in);

    
    public static void main( String[] args )
    {

        ConectorImpl listener = new ConectorImpl();

        System.out.println();
        
        System.out.print("Gestor de base de dades: ");
        String gestor = sc.nextLine();

        System.out.print("IP: ");
        String ip = sc.nextLine();

        System.out.print("Port: ");
        int port = sc.nextInt();
        sc.nextLine();

        System.out.print("Nom d'usuari: ");
        String usuari = sc.nextLine();

        System.out.print("Contrasenya: ");
        String contrasenya = sc.nextLine();

        System.out.print("Nom de la base de dades: ");
        String nomdb = sc.nextLine();

        ConectorInterface conn = new Conector(gestor, ip, usuari, contrasenya, nomdb, port);
        conn.setListener(listener);


        System.out.println();

        if (conn.obrirConnexio()) {
            conn.connexioActiva();

            conn.tancarConnexio();
        }
    }
}
