package dam2.amoreno;

import java.util.List;

public interface LlibresDAO {

    List<Llibres> LlistarLlibre();

    boolean create(Llibres llibres);
    boolean update(int id, Llibres llibres);
    boolean delete(int id);
}
