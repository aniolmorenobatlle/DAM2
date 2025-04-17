package dam.amoreno.m7_a6_amoreno.Escenari2;

import java.time.LocalDate;

public class Circuit {
  private String nom;
  private LocalDate data;
  private String imatge;

  public Circuit(String nom, LocalDate data, String imatge) {
    this.nom = nom;
    this.data = data;
    this.imatge = imatge;
  }

  public String getNom() {
    return nom;
  }

  public LocalDate getData() {
    return data;
  }

  public String getImatge() {
    return imatge;
  }
}
