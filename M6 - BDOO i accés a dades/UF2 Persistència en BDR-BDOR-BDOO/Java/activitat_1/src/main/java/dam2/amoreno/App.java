package dam2.amoreno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App 
{

    private static final String URL = "jdbc:mysql://localhost:3306/activitat1";
    private static final String USER = "aniol";
    private static final String PASSWORD = "aniol1234";



    public static void main( String[] args )
    {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
        	System.out.println("Connexio completada");
        	
        } catch(SQLException e) {
        	System.out.println("Error en connectar-se a la base de dades!!");
        }
    }
}
