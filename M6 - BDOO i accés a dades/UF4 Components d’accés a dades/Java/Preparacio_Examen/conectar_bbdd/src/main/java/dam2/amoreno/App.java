package dam2.amoreno;

import java.util.Scanner;

public class App 
{
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args )
    {
        Conector con = new Conector("mysql", "localhost", 3306, "aniol", "aniol1234", "test_connexio");

        if (con.obrirConnexio()) {
            System.out.println("Conexio oberta");

            con.tancarConnexio();
        }
    }
}
