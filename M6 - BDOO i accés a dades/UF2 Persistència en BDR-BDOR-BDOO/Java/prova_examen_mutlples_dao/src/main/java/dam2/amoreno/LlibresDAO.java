package dam2.amoreno;

import java.util.List;

public interface LlibresDAO {
    List<Llibres> LlistarLlibres();

    boolean create(Llibres llibre);
    boolean update(Llibres llibre);
    boolean delete(int id);
}
