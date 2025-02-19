package dam.amoreno.m7_a2_amoreno;

public class RegistreVehicle {
    private String marca;
    private String model;
    private String preuCompra;

    public RegistreVehicle(String marca, String model, String preuCompra) {
        this.marca = marca;
        this.model = model;
        this.preuCompra = preuCompra;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public String getPreu() {
        return preuCompra;
    }
}
