package dam2.amoreno;

import java.sql.Connection;

public class Categories {

    private Connection conn;
    private int id;
    private String nom;


    public Categories(Connection conn) {
        this.conn = conn;
    }

    public Categories() {}


    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }    
}
