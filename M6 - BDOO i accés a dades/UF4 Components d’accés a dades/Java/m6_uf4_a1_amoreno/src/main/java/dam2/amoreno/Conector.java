package dam2.amoreno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector implements ConectorInterface {


    private String gestor;
    private String ip;
    private String usuari;
    private String contrasenya;
    private String nom_bbdd;
    private int port;
    private Connection connexio;

    
    public Conector(String gestor, String ip, String usuari, String contrasenya, String nom_bbdd, int port) {
        this.gestor = gestor;
        this.ip = ip;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.nom_bbdd = nom_bbdd;
        this.port = port;
    }


    // Obrir connexio
    public boolean obrirConnexio() {
        try {

            String url = generarURLConnexio();

            connexio = DriverManager.getConnection(url, usuari, contrasenya);

            System.out.println("Connexió establerta!!");

            return true;

        } catch (SQLException e) {

            if (e.getSQLState().equals("08001")) {
                System.out.println("Error de connexió: Gestor de base de dades no trobada.");
            } else if (e.getSQLState().equals("28000")) {
                System.out.println("Usuari o contrasenya incorrecte.");
            } else if (e.getSQLState().equals("42000")) {
                System.out.println("Nom de la base de dades no trobat.");
            } else {
                System.out.println("Error en establir la connexió: " + e.getMessage());
            }

            return false;
        }
    }


    // Tancar connexio
    public boolean tancarConnexio() {
        try {

            if (connexio !=  null && !connexio.isClosed()) {
                connexio.close();

                System.out.println("Connexió tancada.");
                
                return true;
            }

            System.out.println("Connexio ja tancada.");
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error en tancar la connexió.");
            return false;
        }
    }


    // Comprovar si la connexió esta activa
    public boolean connexioActiva() {
        try {
            if (connexio != null && !connexio.isClosed()) {
                System.out.println("La connexió ja està activa.");
                return true;
            } else {
                System.out.println("La connexió no està activa.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al comprovar l'estat de la connexió: " + e.getMessage());
            return false;
        }
    }


    // Generar URL
    public String generarURLConnexio() {
        if (gestor.equalsIgnoreCase("mysql")) {
            return "jdbc:mysql://" + ip + ":" + port + "/" + nom_bbdd;
        } else if (gestor.equalsIgnoreCase("postgresql")) {
            return "jdbc:postgresql://" + ip + ":" + port + "/" + nom_bbdd;
        } else {
            return "Gestor de base de dades no trobada.";
        }
    }



    public String getGestor() {
        return gestor;
    }


    public void setGestor(String gestor) {
        this.gestor = gestor;
    }


    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getUsuari() {
        return usuari;
    }


    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }


    public String getContrasenya() {
        return contrasenya;
    }


    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }


    public String getNom_bbdd() {
        return nom_bbdd;
    }


    public void setNom_bbdd(String nom_bbdd) {
        this.nom_bbdd = nom_bbdd;
    }


    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
    }


    public Connection getConnexio() {
        return connexio;
    }


    public void setConnexio(Connection connexio) {
        this.connexio = connexio;
    }
}
