package Principal;
import java.util.ArrayList;

import classes.*;

public class Inventari {
ArrayList<Model> models=new ArrayList<Model>();
{
	ModelImpresora a=new ModelImpresora("HP","Pro","USB");
	models.add(a);
}
ArrayList<Equip> equips=new ArrayList<Equip>();

ArrayList<String> estats=new ArrayList<String>();
{
	estats.add("Actiu");
	estats.add("Avariat");
}
ArrayList<String> ubicacions=new ArrayList<String>();
{
	ubicacions.add("Biblioteca");
	ubicacions.add("Aula1");
}
//-----Models

public Model comprovarModel(String marca,String model) {
	boolean trobat=false;
	Model auxModel=null;
	int pos=0;
	while(pos<models.size()&& !trobat){	
		if(models.get(pos).getModel().equals(model) && models.get(pos).getMarca().equals(marca)) {
			auxModel=models.get(pos);
			trobat= true;
		}
		else {
			pos++;	
		}
	}
	return auxModel;
}

public boolean afegirModel(Model model) {
	boolean afegit=true;
	if(comprovarModel(model.getMarca(),model.getModel())==null) models.add(model);		
	else afegit=false;
	return afegit;
}

//----Equips
public int afegirEquip(Equip equip) {
	int codi;
	if(!codiRepetit(equip.getCodi())) {
		if(comprovarUbicacio("Aula1")) {
			if(comprovarEstat("Actiu")) {
				equips.add(equip);
				codi=0;			
			}
			else codi=3;
		}
		else codi=2;
	}
	else codi=1;
	return codi;
}
private boolean codiRepetit(String codi) {
	boolean repetit=false;
	int pos=0;
	while(pos<equips.size()&& !repetit){	
		if(equips.get(pos).getCodi().equals(codi)) {
			repetit= true;
		}
		else {
			pos++;	
		}
	}
	return repetit;
}
private boolean comprovarUbicacio(String ubicacio) {
	boolean trobat=false;
	int pos=0;
	while(pos<ubicacions.size()&& !trobat){	
		if(ubicacions.get(pos).equals(ubicacio)) {
			trobat= true;
		}
		else {
			pos++;	
		}
	}
	return trobat;
}
private boolean comprovarEstat(String estat) {
	boolean trobat=false;
	int pos=0;
	while(pos<estats.size()&& !trobat){	
		if(estats.get(pos).equals(estat)) {
			trobat= true;
		}
		else {
			pos++;	
		}
	}
	return trobat;
}
public String getModels() {
	int pos=0;
	String auxModels="Models\n";
	while(pos<models.size()){	
		auxModels+= models.get(pos).getInfo()+"\n";pos++;
	}
	return auxModels;
	
}
public String getEquips() {
	int pos=0;
	String auxEquips="Equips\n";
	while(pos<equips.size()){	
		auxEquips+= equips.get(pos).getInfo()+"\n";pos++;
	}
	return auxEquips;
}


}
