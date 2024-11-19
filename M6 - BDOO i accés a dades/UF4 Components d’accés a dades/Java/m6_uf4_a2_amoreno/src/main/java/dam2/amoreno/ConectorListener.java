package dam2.amoreno;

public interface ConectorListener {

    void onConnexioEstablerta();
    void onErrorConnexio(String missatge);
    void onConnexioTancada();
    void onConnexioCaiguda();

}
