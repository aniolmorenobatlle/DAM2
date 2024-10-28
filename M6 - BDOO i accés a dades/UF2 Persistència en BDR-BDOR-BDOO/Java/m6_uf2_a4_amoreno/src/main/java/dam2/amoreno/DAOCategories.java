package dam2.amoreno;

import java.util.List;

public interface DAOCategories {
    List<Categories> LlistarCategories();

    boolean create(Categories categoria);
    boolean update(int id, Categories categoria);
    boolean delete(int id);
}
