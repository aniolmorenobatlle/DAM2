package dam.amoreno.exemple_validacio_interna.classes;
public abstract class Model {

protected String marca;
protected String model;

public String getMarca() {
	return marca;
}
public String getModel() {
	return model;
}
public abstract String getInfo();
public abstract String getTipus();
}
