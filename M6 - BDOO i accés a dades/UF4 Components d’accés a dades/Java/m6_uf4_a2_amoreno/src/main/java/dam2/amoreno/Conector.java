package dam2.amoreno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Conector implements ConectorInterface {
    static Scanner sc = new Scanner(System.in);


    private String gestor;
    private String ip;
    private String usuari;
    private String contrasenya;
    private String nom_bbdd;
    private int port;
    private Connection connexio;
    private ConectorListener listener;


    // Obrir connexio
    public boolean obrirConnexio() {
        try {

            String url = generarURLConnexio();
            connexio = DriverManager.getConnection(url, usuari, contrasenya);

            if (listener != null) {
                listener.onConnexioEstablerta();
            }

            return true;

        } catch (SQLException e) {

            if (listener != null) {
                listener.onErrorConnexio(e.getMessage());
            }

            return false;
        }
    }


    // Tancar connexio
       public boolean tancarConnexio() {
        try {

            if (connexio != null && !connexio.isClosed()) {
                connexio.close();

                if (listener != null) {
                    listener.onConnexioTancada();
                }

                return true;
            }

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
                return true;

            } else {

                if (listener != null) {
                    listener.onConnexioCaiguda();
                }

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


    public ConectorListener getListener() {
        return listener;
    }


    public void setListener(ConectorListener listener) {
        this.listener = listener;
    }

}
