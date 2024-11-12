package dam2.amoreno;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Matricula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Estudiant estudiant;
    
    @ManyToMany
    private List<Assignatura> assignatures;
    
    private LocalDate dataMatricula;


    
    public Matricula() {
    }
    public Matricula(Estudiant estudiant, List<Assignatura> assignatures, LocalDate dataMatricula) {
        this.estudiant = estudiant;
        this.assignatures = assignatures;
        this.dataMatricula = dataMatricula;
    }


    // Getters i setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiant getEstudiant() {
        return estudiant;
    }

    public void setEstudiant(Estudiant estudiant) {
        this.estudiant = estudiant;
    }

    public List<Assignatura> getAssignatures() {
        return assignatures;
    }

    public void setAssignatures(List<Assignatura> assignatures) {
        this.assignatures = assignatures;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }
}
