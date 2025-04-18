package dam.amoreno.m7_a6_amoreno.Escenari2;

public class ClassificacioCursa {
  private String nomPilot;
  private int punts;

  public ClassificacioCursa(String nomPilot, int punts) {
    this.nomPilot = nomPilot;
    this.punts = punts;
  }

  public String getNomPilot() {
    return nomPilot;
  }

  public int getPunts() {
    return punts;
  }

  public void setPunts(int punts) {
    this.punts = punts;
  }
}
