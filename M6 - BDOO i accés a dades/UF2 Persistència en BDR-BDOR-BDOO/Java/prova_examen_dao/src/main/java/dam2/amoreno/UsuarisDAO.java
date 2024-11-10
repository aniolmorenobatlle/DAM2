package dam2.amoreno;

import java.util.List;

public interface UsuarisDAO {
    List<Usuaris> LlistarUsuaris();

    boolean create(Usuaris usuari);
    boolean update(Usuaris usuari);
    boolean delete (int id);
}
