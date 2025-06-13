package dam.amoreno.m7_a7_amoreno.Examen;

public class Pregunta {
  private String pregunta;
  private String resposta1;
  private String resposta2;
  private String resposta3;
  private String imatge;

  public Pregunta(String pregunta, String resposta1, String resposta2, String resposta3, String imatge) {
    this.pregunta = pregunta;
    this.resposta1 = resposta1;
    this.resposta2 = resposta2;
    this.resposta3 = resposta3;
    this.imatge = imatge;
  }

  public String getPregunta() {
    return pregunta;
  }

  public String getResposta1() {
    return resposta1;
  }

  public String getResposta2() {
    return resposta2;
  }

  public String getResposta3() {
    return resposta3;
  }

  public String getImatge() {
    return imatge;
  }

}
