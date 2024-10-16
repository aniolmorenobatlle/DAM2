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
        String query = "SELECT Llibre.id, Llibre.titol, Llibre.isbn, Llibre.any_publicacio, "
                        + "CONCAT(Autor.nom, ' ', Autor.cognoms) AS autor_nom_complet, "
                        + "Categoria.nom_categoria "
                        + "FROM Llibre "
                        + "JOIN Autor ON Llibre.autor_id = Autor.id "
                        + "JOIN Categoria ON Llibre.categoria_id = Categoria.id";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Llibres llibre = new Llibres();

                llibre.setId(resultSet.getInt("id"));
                llibre.setTitol(resultSet.getString("titol"));
                llibre.setISBN(resultSet.getString("isbn"));
                llibre.setAny(resultSet.getString("any_publicacio"));
                llibre.setAutor(resultSet.getString("autor_nom_complet"));
                llibre.setCategoria(resultSet.getString("nom_categoria"));

                llibres.add(llibre);
            }
        } catch (SQLException e) {
            System.out.println("Error!!");
        }

        return llibres;
    }


    @Override
    public boolean create(Llibres llibre) {
        return false;
    }


    @Override
    public boolean update(Llibres llibre) {
        return false;
    }


    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Llibre WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error en eliminar el llibre!!");

            return false;
        }
    }
    
}
