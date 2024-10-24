package dam2.amoreno;

import java.util.List;

public interface DAOUsuaris {
    List<Usuaris> LlistarUsuaris();

    boolean create(Usuaris usuari);
    boolean update(int id, Usuaris usuari);
    boolean delete(int id);
}
