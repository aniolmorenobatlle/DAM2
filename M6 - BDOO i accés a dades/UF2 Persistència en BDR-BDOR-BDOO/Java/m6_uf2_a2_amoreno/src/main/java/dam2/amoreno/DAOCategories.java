package dam2.amoreno;

import java.util.List;

public interface DAOCategories {

    List<Categories> LlistarCategories();

    boolean create(Categories Categoria);
    boolean update(Categories Categoria);
    boolean delete(int id);
}
