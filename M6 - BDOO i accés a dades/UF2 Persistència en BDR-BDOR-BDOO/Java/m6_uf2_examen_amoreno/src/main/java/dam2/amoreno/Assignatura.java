package dam2.amoreno;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Assignatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nom;

    @JoinColumn(unique = true)
    private String codi;
    
    private String descripcio;


    public Assignatura() {
    }
    
    public Assignatura(String nom, String codi, String descripcio) {
        this.nom = nom;
        this.codi = codi;
        this.descripcio = descripcio;
    }



    // Getters i setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }
}
