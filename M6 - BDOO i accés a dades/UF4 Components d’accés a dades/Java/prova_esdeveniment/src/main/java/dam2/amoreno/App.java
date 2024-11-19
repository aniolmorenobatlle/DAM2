package dam2.amoreno;


public class App 
{
    public static void main( String[] args )
    {
        // Creació del producte amb una quantitat inicial
        Producte producte = new Producte("Ordinador portàtil", 10);

        // Creació dels listeners
        EstocEventListener listenerCompres = new DepartamentCompres();

        // Registre dels listeners
        producte.afegirEstocEventListener(listenerCompres);

        // Simulació de canvis d'estoc
        producte.establirQuantitat(8);  // No activa l'alerta de compres perquè l'estoc no és baix
        producte.establirQuantitat(4);  // Activa les alertes perquè l'estoc és baix
        producte.establirQuantitat(2);  // Activa de nou les alertes amb l'estoc encara més baix
    }
}
