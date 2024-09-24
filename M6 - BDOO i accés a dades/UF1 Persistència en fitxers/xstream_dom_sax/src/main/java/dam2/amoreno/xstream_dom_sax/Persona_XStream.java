package dam2.amoreno.xstream_dom_sax;

public class Persona_XStream {
    private String nom;
    private String cognom;
    private String mail;
    private String telefon;

    // Constructor
    public Persona_XStream(String nom, String cognom, String mail, String telefon) {
        this.nom = nom;
        this.cognom = cognom;
        this.mail = mail;
        this.telefon = telefon;
    }
    

    // Getters i Setters
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "nom=" + nom + ", cognom=" + cognom + ", mail=" + mail + ", telefon=" + telefon;
    }
}
