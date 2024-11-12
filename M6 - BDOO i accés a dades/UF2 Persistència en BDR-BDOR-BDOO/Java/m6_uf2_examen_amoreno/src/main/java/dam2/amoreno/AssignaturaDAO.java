package dam2.amoreno;

import java.util.List;

public interface AssignaturaDAO {

    List<Assignatura> llistarAssignatures();

    boolean create(Assignatura assignatura);
    boolean update(Assignatura assignatura, Long id);
    boolean delete(Long id);
}
