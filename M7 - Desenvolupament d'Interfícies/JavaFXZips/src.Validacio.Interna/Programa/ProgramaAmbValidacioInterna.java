package Programa;
import Principal.Inventari;
import classes.Equip;
import classes.Model;
import classes.ModelImpresora;

public class ProgramaAmbValidacioInterna {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Inventari palamos=new Inventari();

		ModelImpresora KonikaMinolta=new ModelImpresora("Konika","Minolta","Wifi");
		
		if(palamos.afegirModel(KonikaMinolta)){
			System.out.println("Model Entrat");
		}
		else System.out.println("Model Repetit");
		
		Model modelAux=palamos.comprovarModel("Konika","Minolta");
		
		if(modelAux!=null) {
			Equip KonikaMinolta1=new Equip("123",modelAux,"Aula1","Actiu");
			int error=palamos.afegirEquip(KonikaMinolta1);
				switch (error) {
				case 0:System.out.println("Equip Entrat");break;
				case 1:System.out.println("Codi Repetit");break;
				case 2:System.out.println("Ubicacio Inexistent");break;
				case 3:System.out.println("Estat Inexistent");break;
			}
		}
		else System.out.println("model no entrat");
	
		System.out.println(palamos.getModels());
		System.out.println(palamos.getEquips());		
		
		//Model repetit
		
		ModelImpresora KonikaMinolta2=new ModelImpresora("Konika","Minolta","Wifi");
		
		if(palamos.afegirModel(KonikaMinolta2)){
			System.out.println("Model Entrat");
		}
		else System.out.println("Model Repetit");
		
		System.out.println(palamos.getModels());
		System.out.println(palamos.getEquips());	
		
		//Equip d'un model inexistent
		
		modelAux=palamos.comprovarModel("Epson","Deskjet");
		
		if(modelAux!=null) {
			Equip KonikaMinolta1=new Equip("124",modelAux,"Aula1","Actiu");
			int error=palamos.afegirEquip(KonikaMinolta1);
				switch (error) {
				case 0:System.out.println("Equip Entrat");break;
				case 1:System.out.println("Codi Repetit");break;
				case 2:System.out.println("Ubicacio Inexistent");break;
				case 3:System.out.println("Estat Inexistent");break;
			}
		}
		else System.out.println("model no entrat");
		
		System.out.println(palamos.getModels());
		System.out.println(palamos.getEquips());	
		
		//Equip de model existent
		modelAux=palamos.comprovarModel("Konika","Minolta");
		
		if(modelAux!=null) {
			Equip KonikaMinolta1=new Equip("125",modelAux,"Aula1","Actiu");
			int error=palamos.afegirEquip(KonikaMinolta1);
				switch (error) {
				case 0:System.out.println("Equip Entrat");break;
				case 1:System.out.println("Codi Repetit");break;
				case 2:System.out.println("Ubicacio Inexistent");break;
				case 3:System.out.println("Estat Inexistent");break;
			}
		}
		else System.out.println("model no entrat");
		
		System.out.println(palamos.getModels());
		System.out.println(palamos.getEquips());	

	}
	
}
