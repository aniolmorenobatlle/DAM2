package dam2.amoreno;

import java.sql.Connection;

public class Llibres {

    private Connection conn;
    private int id;
    private String titol;
    private String ISBN;
    private String any;
    private String autor;
    private String categoria;

    public Llibres() {}
    
    public Llibres(Connection conn) {
        this.conn = conn;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitol() {
        return titol;
    }
    
    public void setTitol(String titol) {
        this.titol = titol;
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    
    public String getAny() {
        return any;
    }
    
    public void setAny(String any) {
        this.any = any;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
