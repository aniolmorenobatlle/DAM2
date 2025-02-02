package dam.amoreno.exemple_validacio_interna.principal;

import java.util.ArrayList;
import dam.amoreno.exemple_validacio_interna.classes.*;

public class Inventari {
    ArrayList<Model> models = new ArrayList<Model>();

    {
        ModelImpresora a = new ModelImpresora("HP", "Pro", "USB");
        models.add(a);
    }

    ArrayList<Equip> equips = new ArrayList<Equip>();

    ArrayList<String> estats = new ArrayList<String>();

    {
        estats.add("Actiu");
        estats.add("Avariat");
    }

    ArrayList<String> ubicacions = new ArrayList<String>();

    {
        ubicacions.add("Biblioteca");
        ubicacions.add("Aula1");
    }

    //-----Models
    public Model comprovarModel(String marca, String model) {
        boolean trobat = false;
        Model auxModel = null;

        int pos = 0;

        while (pos < models.size() && !trobat) {
            if (models.get(pos).getModel().equals(model) && models.get(pos).getMarca().equals(marca)) {
                auxModel = models.get(pos);
                trobat = true;
            } else {
                pos++;
            }
        }

        return auxModel;
    }

}