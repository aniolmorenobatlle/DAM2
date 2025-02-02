package dam.amoreno.m7_a1_amoreno;

public class Videojoc {
    String nom;
    String plataforma;
    String classificacio;
    String periferics;
    String tematica;
    String dataSortida;

    public Videojoc(String nom, String plataforma, String classificacio, String periferics, String tematica, String dataSortida) {
        this.nom = nom;
        this.plataforma = plataforma;
        this.classificacio = classificacio;
        this.periferics = periferics;
        this.tematica = tematica;
        this.dataSortida = dataSortida;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getClassificacio() {
        return classificacio;
    }

    public void setClassificacio(String classificacio) {
        this.classificacio = classificacio;
    }

    public String getPeriferics() {
        return periferics;
    }

    public void setPeriferics(String periferics) {
        this.periferics = periferics;
    }

    public String getDataSortida() {
        return dataSortida;
    }

    public void setDataSortida(String dataSortida) {
        this.dataSortida = dataSortida;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }
}
