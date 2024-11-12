package dam2.amoreno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonBD {

    private static Connection conn;

    private SingletonBD() {
    }

    // Connexio a BBDD amb Singleton
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            
            try {

                // Configuració de la connexió
                String url = "jdbc:mysql://localhost:3306/activitat2";
                String user = "aniol";
                String password = "aniol1234";

                
                conn = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("No es pot establir la connexió amb la base de dades", e);
            }
        }
        
        return conn;
    }
}
