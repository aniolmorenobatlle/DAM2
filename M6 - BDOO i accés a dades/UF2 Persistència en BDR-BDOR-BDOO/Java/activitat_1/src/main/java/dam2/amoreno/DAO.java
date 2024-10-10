package dam2.amoreno;

import java.util.List;

public interface DAO {

    List<Usuaris> LlistarUsuaris();

    boolean create(Usuaris usu);
    boolean update(Usuaris usu);
    boolean delete(int id);
}
