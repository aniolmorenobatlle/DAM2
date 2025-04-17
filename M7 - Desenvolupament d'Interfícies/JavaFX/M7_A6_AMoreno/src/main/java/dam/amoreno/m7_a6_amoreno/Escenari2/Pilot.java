package dam.amoreno.m7_a6_amoreno.Escenari2;

public class Pilot {
  private String nom;
  private int dorsal;
  private String escuderia;
  private String imatge;

  public Pilot(String nom, int dorsal, String escuderia, String imatge) {
    this.nom = nom;
    this.dorsal = dorsal;
    this.escuderia = escuderia;
    this.imatge = imatge;
  }

  public String getNom() {
    return nom;
  }

  public int getDorsal() {
    return dorsal;
  }

  public String getEscuderia() {
    return escuderia;
  }

  public String getImatge() {
    return imatge;
  }
}
