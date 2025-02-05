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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPreu(String preuCompra) {
        this.preuCompra = preuCompra;
    }
}
