package dam2.amoreno;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Llibres implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titol;

    private String isbn;

    private int any_publicacio;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = true)
    private Autors autor;    

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = true)
    private Categories categoria;


    public Llibres() {}

    public Llibres(String titol, String isbn, int any_publicacio, Autors autor, Categories categoria) {
        this.titol = titol;
        this.isbn = isbn;
        this.any_publicacio = any_publicacio;
        this.autor = autor;
        this.categoria = categoria;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAny_publicacio() {
        return any_publicacio;
    }

    public void setAny_publicacio(int any_publicacio) {
        this.any_publicacio = any_publicacio;
    }

    public Autors getAutor() {
        return autor;
    }

    public void setAutor(Autors autor) {
        this.autor = autor;
    }

    public Categories getCategoria() {
        return categoria;
    }

    public void setCategoria(Categories categoria) {
        this.categoria = categoria;
    }
}
