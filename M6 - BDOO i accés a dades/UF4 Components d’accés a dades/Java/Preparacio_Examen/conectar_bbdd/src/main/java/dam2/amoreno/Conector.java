package dam2.amoreno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector implements ConectorInterface{

    private String tipus;
    private String ip;
    private int port;
    private String usuari;
    private String constrasenya;
    private String nom_bbdd;
    private Connection conexio;
    
    
    public Conector(String tipus, String ip, int port, String usuari, String constrasenya, String nom_bbdd) {
        this.tipus = tipus;
        this.ip = ip;
        this.port = port;
        this.usuari = usuari;
        this.constrasenya = constrasenya;
        this.nom_bbdd = nom_bbdd;
    }


    
    public boolean obrirConnexio() {
        try {

            String url = generarURLConexio();

            conexio = DriverManager.getConnection(url, usuari, constrasenya);

            return true;

        } catch (SQLException e) {
            System.out.println("ERROR en establir la conexió: " + e.getMessage());

            return false;
        }
    } 

    public boolean tancarConnexio() {
        try {

            if (conexio != null && !conexio.isClosed()) {
                conexio.close();

                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR en tancar la conexió: " + e.getMessage());
            return false;
        }
    }

    public boolean conexioActiva() {
        try {

            if (conexio != null && !conexio.isClosed()) {
                return true;
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR en obtenir la conexió: " + e.getMessage());
            return false;
        }
    }

    public String generarURLConexio() {
        if (tipus.equalsIgnoreCase("mysql")) {
            return "jdbc:mysql://" + ip + ":" + port + "/" + nom_bbdd;
        } else {
            return "Gestor de base de dades no trobada.";
        }
    }



    public String getTipus() {
        return tipus;
    }


    public void setTipus(String tipus) {
        this.tipus = tipus;
    }


    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
    }


    public String getUsuari() {
        return usuari;
    }


    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }


    public String getConstrasenya() {
        return constrasenya;
    }


    public void setConstrasenya(String constrasenya) {
        this.constrasenya = constrasenya;
    }


    public String getNom_bbdd() {
        return nom_bbdd;
    }


    public void setNom_bbdd(String nom_bbdd) {
        this.nom_bbdd = nom_bbdd;
    }


    public Connection getConexio() {
        return conexio;
    }


    public void setConexio(Connection conexio) {
        this.conexio = conexio;
    }
}
