package dam2.amoreno;

import java.util.List;

public interface EstudiantDAO {

    List<Estudiant> LlistarEstudiant();

    boolean create(Estudiant estudiant);
    boolean update(Estudiant estudiant, Long id);
    boolean delete(Long id);
}
