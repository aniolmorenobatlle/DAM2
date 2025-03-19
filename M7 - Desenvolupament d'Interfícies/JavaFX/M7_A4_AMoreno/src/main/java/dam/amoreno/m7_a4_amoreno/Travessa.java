package dam.amoreno.m7_a4_amoreno;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Travessa {
    private final StringProperty equip;
    private final StringProperty prediccio;

    public Travessa(String equip, String prediccio) {
        this.equip = new SimpleStringProperty(equip);
        this.prediccio = new SimpleStringProperty(prediccio);
    }

    public String getEquip() {
        return equip.get();
    }

    public String getPrediccio() {
        return prediccio.get();
    }

    public void setPrediccio(String prediccio) {
        this.prediccio.set(prediccio);
    }
}
