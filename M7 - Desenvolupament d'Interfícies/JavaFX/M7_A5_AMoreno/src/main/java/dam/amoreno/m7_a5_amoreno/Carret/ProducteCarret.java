package dam.amoreno.m7_a5_amoreno.Carret;

public class ProducteCarret {
  String nom;
  double preuUnitari;
  int quantitat;
  String imatgePath;

  public ProducteCarret(String nom, double preuUnitari, int quantitat, String imatgePath) {
    this.nom = nom;
    this.preuUnitari = preuUnitari;
    this.quantitat = quantitat;
    this.imatgePath = imatgePath;
  }

  public void afegirQuantitat(int extra) {
    this.quantitat += extra;
  }

  public double getPreuTotal() {
    return this.preuUnitari * this.quantitat;
  }
}
