package classes;
public class Equip {
	/*
	 * Codi
Marca
Model
Ubicació
Estat (Actiu,Avariat, Reserva, Reparacio)
Metode Abstracte -> tipusEquip();
Metode Abstracte -> descripcioEquip();
	 */
private String codi;
private Model model;
private String ubicacio;
private String estat;

public Equip(String codi,Model model,String ubicacio,String estat){
	this.codi=codi;
	this.model=model;
	this.ubicacio=ubicacio;
	this.estat=estat;
}
public String getCodi() {
	return this.codi;
}
public String getUbicacio() {
	return this.ubicacio;
}
public String getEstat() {
	return this.estat;
}
public String getInfo() {
	String info=this.codi+"-"+model.getInfo()+"-"+this.ubicacio+"-"+this.estat;
	return info;
};
public String getTipus() {
	return model.getTipus();
};
}
