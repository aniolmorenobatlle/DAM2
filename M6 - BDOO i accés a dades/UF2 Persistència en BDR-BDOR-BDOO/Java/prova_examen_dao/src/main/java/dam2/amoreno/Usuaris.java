package dam2.amoreno;

import java.time.LocalDate;

public class Usuaris {

    private int id;
    private String nom;
    private String cognom;
    private LocalDate data_naixement;
    private String email;


    public Usuaris() {
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


    public String getCognom() {
        return cognom;
    }


    public void setCognom(String cognom) {
        this.cognom = cognom;
    }


    public LocalDate getData_naixement() {
        return data_naixement;
    }


    public void setData_naixement(LocalDate data_naixement) {
        this.data_naixement = data_naixement;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
