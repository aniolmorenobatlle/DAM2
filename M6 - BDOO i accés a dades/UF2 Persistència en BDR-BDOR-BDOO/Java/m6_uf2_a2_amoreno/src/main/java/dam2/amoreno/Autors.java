package dam2.amoreno;

import java.sql.Connection;

public class Autors {

    private Connection conn;
    private int id;
    private String nom;
    private String cognoms;
    private String data;


    public Autors(Connection conn) {
        this.conn = conn;
    }

    public Autors() {}


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
    
    public String getCognoms() {
        return cognoms;
    }
    
    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
}
