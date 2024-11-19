package dam2.amoreno;

class DepartamentCompres implements EstocEventListener {
    @Override
    public void enEstocBaix(Producte producte) {
        System.out.println("Compres: Alerta! L'estoc de " + producte.obtenirNom() + " és baix. Considerar la compra de més producte.");
    }
}