package dam2.amoreno;


public class App 
{
    public static void main( String[] args )
    {
        Conector conn = new Conector();

        System.out.println();

        if (conn.obrirConnexio()) {
            conn.connexioActiva();


            conn.tancarConnexio();
        }
    }
}
