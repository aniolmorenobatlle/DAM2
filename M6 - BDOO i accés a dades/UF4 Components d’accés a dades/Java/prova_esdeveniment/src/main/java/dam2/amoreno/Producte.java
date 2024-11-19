package dam2.amoreno;

import java.util.ArrayList;
import java.util.List;

class Producte {
    private String nom;
    private int quantitat;
    private List<EstocEventListener> listeners = new ArrayList<>();

    public Producte(String nom, int quantitatInicial) {
        this.nom = nom;
        this.quantitat = quantitatInicial;
    }

    public void afegirEstocEventListener(EstocEventListener listener) {
        listeners.add(listener); // Afegeix un listener a la llista
    }

    public void eliminarEstocEventListener(EstocEventListener listener) {
        listeners.remove(listener); // Elimina un listener de la llista
    }

    public void establirQuantitat(int quantitat) {
        this.quantitat = quantitat;
        System.out.println("Quantitat actual de " + nom + ": " + quantitat);

        // Notifica tots els listeners del canvi d'estoc
        for (EstocEventListener listener : listeners) {

            // Notifica específicament si l'estoc és baix (per exemple, per sota de 5)
            if (quantitat < 5) {
                listener.enEstocBaix(this);
            }
        }
    }

    public int obtenirQuantitat() {
        return quantitat;
    }

    public String obtenirNom() {
        return nom;
    }
}