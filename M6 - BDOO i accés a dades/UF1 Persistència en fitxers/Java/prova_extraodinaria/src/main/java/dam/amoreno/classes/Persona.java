package dam.amoreno.classes;

public class Persona {
    private String nom;
    private String cognom;
    private String mail;
    private String telefon;

    public Persona() {}

    public Persona(String nom, String cognom, String mail, String telefon) {
        this.nom = nom;
        this.cognom = cognom;
        this.mail = mail;
        this.telefon = telefon;
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
        return "nom: " + nom + "\n"
        + "cognom: " + cognom + "\n"
        + "mail: " + mail + "\n"
        + "telefon: " + telefon + "\n"
        + "------------";
    }

}
