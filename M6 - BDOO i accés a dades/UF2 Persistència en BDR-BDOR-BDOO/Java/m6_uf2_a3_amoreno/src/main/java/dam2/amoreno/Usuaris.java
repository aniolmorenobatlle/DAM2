package dam2.amoreno;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="usuaris")
public class Usuaris implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "cognoms")
    private String cognoms;
    @Column(name = "data_naixement")
    private String data_naixement;
    @Column(name = "email")
    private String email;

    
    public Usuaris() {}
    public Usuaris(String nom, String cognoms, String data_naixement, String email) {
        super();
        this.nom = nom;
        this.cognoms = cognoms;
        this.data_naixement = data_naixement;
        this.email = email;
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
    
    public String getCognoms() {
        return cognoms;
    }
    
    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }
    
    public String getData_naixement() {
        return data_naixement;
    }
    
    public void setData_naixement(String data_naixement) {
        this.data_naixement = data_naixement;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
