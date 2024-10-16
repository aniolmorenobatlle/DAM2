package dam2.amoreno;

import java.util.List;

public interface DAOAutors {

    List<Autors> LlistarAutors();

    boolean create(Autors autor);
    boolean update(Autors autor);
    boolean delete(int id);
}
