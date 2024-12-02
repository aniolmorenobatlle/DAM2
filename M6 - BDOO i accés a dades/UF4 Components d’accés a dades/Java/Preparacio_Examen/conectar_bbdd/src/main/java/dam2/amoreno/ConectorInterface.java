package dam2.amoreno;

public interface ConectorInterface {

    boolean obrirConnexio();
    boolean tancarConnexio();
    boolean conexioActiva();
    String generarURLConexio(); 

}
