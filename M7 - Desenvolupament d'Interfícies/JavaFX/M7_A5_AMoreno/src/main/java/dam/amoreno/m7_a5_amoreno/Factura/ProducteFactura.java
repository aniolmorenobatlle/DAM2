package dam.amoreno.m7_a5_amoreno.Factura;

public class ProducteFactura {
  private String nom;
  private double preuUnitari;
  private int quantitat;
  private String imatgePath;

  public ProducteFactura(String nom, double preuUnitari, int quantitat, String imatgePath) {
    this.nom = nom;
    this.preuUnitari = preuUnitari;
    this.quantitat = quantitat;
    this.imatgePath = imatgePath;
  }

  public String getNom() {
    return nom;
  }

  public double getPreuUnitari() {
    return preuUnitari;
  }

  public int getQuantitat() {
    return quantitat;
  }

  public String getImatgePath() {
    return imatgePath;
  }

  public double getPreuTotal() {
    return preuUnitari * quantitat;
  }
}
