package dam.amoreno.m7_a5_amoreno;

public class Producte {
  private String nom;
  private String preu;
  private String imatgePath;

  public Producte(String nom, String preu, String imatgePath) {
    this.nom = nom;
    this.preu = preu;
    this.imatgePath = imatgePath;
  }

  public String getNom() {
    return nom;
  }

  public String getPreu() {
    return preu;
  }

  public String getImatgePath() {
    return imatgePath;
  }
}
