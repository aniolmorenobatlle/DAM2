package dam2.amoreno;

import java.util.List;

public interface DAOLlibres {
    List<Llibres> LlistarLlibres();

    boolean create(Llibres usuari);
    boolean update(int id, Llibres usuari);
    boolean delete(int id);
}
