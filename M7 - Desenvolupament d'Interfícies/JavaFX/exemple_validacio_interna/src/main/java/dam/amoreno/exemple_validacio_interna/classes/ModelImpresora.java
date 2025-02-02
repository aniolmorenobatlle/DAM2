package dam.amoreno.exemple_validacio_interna.classes;
public class ModelImpresora extends Model{

private String connexioActual;

public ModelImpresora(String marca,String model,String connexioActual){
	this.marca=marca;
	this.model=model;
	this.connexioActual=connexioActual;
	
}
public String getTipus() {
	return "Impressora";
	
}
public String getInfo() {
	return(this.marca+"-"+this.model+"-"+" impressora "+"-"+this.connexioActual);
}
}
