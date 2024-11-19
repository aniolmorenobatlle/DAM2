package dam2.amoreno;


public class App 
{
    public static void main( String[] args )
    {
        Conector conn = new Conector();

        ConectorImpl listener = new ConectorImpl();

        conn.setListener(listener);

        System.out.println();

        conn.obrirConnexio();
        conn.connexioActiva();
        conn.tancarConnexio();
    }
}
