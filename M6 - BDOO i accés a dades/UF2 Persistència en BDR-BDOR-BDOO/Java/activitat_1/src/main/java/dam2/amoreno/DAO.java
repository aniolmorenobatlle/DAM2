package dam2.amoreno;

import java.util.List;

public interface DAO {

    List<Usuaris> LlistarUsuaris();

    boolean create(Usuaris usuari);
    boolean update(Usuaris usuari);
    boolean delete(String email);

    Usuaris cercaEmail(String email);
}
