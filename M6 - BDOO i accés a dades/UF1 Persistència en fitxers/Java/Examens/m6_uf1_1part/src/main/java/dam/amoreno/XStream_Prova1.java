package dam.amoreno;

public class XStream_Prova1 {
    private String nom;
    private String data;
    private String prioritat;
    private String categoria;

    
    public XStream_Prova1(String nom, String data, String prioritat, String categoria) {
        this.nom = nom;
        this.data = data;
        this.prioritat = prioritat;
        this.categoria = categoria;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }


    public String getPrioritat() {
        return prioritat;
    }


    public void setPrioritat(String prioritat) {
        this.prioritat = prioritat;
    }


    public String getCategoria() {
        return categoria;
    }


    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
    @Override
    public String toString() {
        String format_nom = "Nom: " + nom;
        String format_data = "Data: " + data;
        String format_categoria = "Categoria: " + categoria;
        String format_prioritat = "Prioritat: " + prioritat;

        return format_nom + "\n" + format_data + "\n" + format_categoria + "\n" + format_prioritat;
    }
}
