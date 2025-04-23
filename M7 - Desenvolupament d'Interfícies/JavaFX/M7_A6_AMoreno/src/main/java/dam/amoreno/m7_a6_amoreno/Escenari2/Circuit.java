package dam.amoreno.m7_a6_amoreno.Escenari2;

import java.time.LocalDate;

public class Circuit {
  private String nom;
  private LocalDate data;
  private String imatge;
  private boolean corregut = false;

  public Circuit(String nom, LocalDate data, String imatge, boolean corregut) {
    this.nom = nom;
    this.data = data;
    this.imatge = imatge;
    this.corregut = corregut;
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

  public boolean isCorregut() {
    return corregut;
  }

  public void setCorregut(boolean corregut) {
    this.corregut = corregut;
  }
}
