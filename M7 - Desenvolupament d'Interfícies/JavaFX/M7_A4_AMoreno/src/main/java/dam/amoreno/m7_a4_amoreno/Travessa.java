package dam.amoreno.m7_a4_amoreno;

public class Travessa {
    private String equipLocal;
    private String equipVisitant;
    private String prediccio;

    public Travessa(String equipLocal, String equipVisitant, String prediccio) {
        this.equipLocal = equipLocal;
        this.equipVisitant = equipVisitant;
        this.prediccio = prediccio;
    }

    public String getEquipLocal() {
        return equipLocal;
    }

    public void setEquipLocal(String equipLocal) {
        this.equipLocal = equipLocal;
    }

    public String getEquipVisitant() {
        return equipVisitant;
    }

    public void setEquipVisitant(String equipVisitant) {
        this.equipVisitant = equipVisitant;
    }

    public String getPrediccio() {
        return prediccio;
    }

    public void setPrediccio(String prediccio) {
        this.prediccio = prediccio;
    }
}
