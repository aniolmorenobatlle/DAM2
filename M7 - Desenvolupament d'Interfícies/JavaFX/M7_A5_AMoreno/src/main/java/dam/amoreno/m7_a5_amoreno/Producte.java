package dam.amoreno.m7_a5_amoreno;

public class Producte {
  private String nom;
  private String preu;
  private String imatgePath;
  private int stock;

  public Producte(String nom, String preu, String imatgePath, int stock) {
    this.nom = nom;
    this.preu = preu;
    this.imatgePath = imatgePath;
    this.stock = stock;
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

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
