package dam2.amoreno;

import java.util.List;

public interface MatriculaDAO {

    List<Matricula> llistarMatricula();

    boolean create(Matricula matricula);
    boolean update(Matricula matricula, Long id);
    boolean delete(Long id);
}
