package dam2.amoreno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LlibresDAOImpl implements DAOLlibres {

    private Connection conn;

    // Constructor per rebre la connexió
    public LlibresDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Llibres> LlistarLlibres() {
        List<Llibres> llibres = new ArrayList<>();
        String query = "SELECT * FROM Llibre";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Llibres llibre = new Llibres();

                llibre.setId(resultSet.getInt("id"));
                llibre.setTitol(resultSet.getString("titol"));
                llibre.setISBN(resultSet.getString("isbn"));
                llibre.setAny(resultSet.getString("any_publicacio"));
                llibre.setAutor(resultSet.getString("autor_id"));
                llibre.setCategoria(resultSet.getString("categoria_id"));

                llibres.add(llibre);
            }
        } catch (SQLException e) {
            System.out.println("Error!!");
        }

        return llibres;
    }


    @Override
    public boolean create(Llibres llibre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }


    @Override
    public boolean update(Llibres llibre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


    @Override
    public boolean delete(String titol) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
