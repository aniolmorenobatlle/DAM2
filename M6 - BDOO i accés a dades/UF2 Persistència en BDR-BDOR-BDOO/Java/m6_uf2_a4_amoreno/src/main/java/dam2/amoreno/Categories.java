package dam2.amoreno;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class Categories implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom_categoria;

    @OneToMany(mappedBy = "categoria")
    private List<Llibres> llibres;


    public Categories() {}

    public Categories(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_categoria() {
        return nom_categoria;
    }

    public void setNom_categoria(String nom_categoria) {
        this.nom_categoria = nom_categoria;
    }

    public List<Llibres> getLlibres() {
        return llibres;
    }

    public void setLlibres(List<Llibres> llibres) {
        this.llibres = llibres;
    }
}
