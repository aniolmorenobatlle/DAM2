package dam2.amoreno;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Estudiant implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String nom;
    private String cognoms;

    @JoinColumn(unique = true)
    private String email;

    private LocalDate dataNaixement;

    
    public Estudiant() {
    }

    public Estudiant(String nom, String cognoms, String email, LocalDate dataNaixement) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.email = email;
        this.dataNaixement = dataNaixement;
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

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(LocalDate dataNaixement) {
        this.dataNaixement = dataNaixement;
    }
}
