package dam2.amoreno;

public interface ConectorInterface {

    boolean obrirConnexio();
    boolean tancarConnexio();
    boolean connexioActiva();
    String generarURLConnexio();

    void setListener(ConectorListener listener);
}
