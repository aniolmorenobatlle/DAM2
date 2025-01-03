package dam2.amoreno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAOImpl implements DAOCategories {

    private Connection conn;

    // Constructor per rebre la connexió
    public CategoriesDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categories> LlistarCategories() {

        List<Categories> categories = new ArrayList<>();

        String query = "SELECT * FROM Categoria WHERE id != 1"; // Per no mostrar el "sense categoria"

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Categories categoria = new Categories();

                categoria.setId(resultSet.getInt("id"));
                categoria.setNom(resultSet.getString("nom_categoria"));

                categories.add(categoria);
            }
            
        } catch (SQLException e) {
            System.out.println("Error!!");
        }

        return categories;
    }


    @Override
    public boolean create(Categories categoria) {
        if (existeixNom(categoria.getNom())) {
            System.out.println("Ja existeix aquest la categoria!!");
            return false;
        }

        String query = "INSERT INTO Categoria (nom_categoria) VALUE (?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, categoria.getNom());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Hi ha hagut algun problema en obtenir les dades de la taula de categoria!!");

            return false;
        }       
    }

    // Comprovar si ja existeix la categoria
    public boolean existeixNom(String nom) {
        String query = "SELECT COUNT(*) FROM Categoria WHERE nom_categoria = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nom);

            try (ResultSet rs = stmt.executeQuery()){

                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }

            } catch (Exception e) {
                System.out.println("Error!!");
            }

        } catch (SQLException e) {
            System.out.println("Error en buscar si la categoria existeix!!");
        }

        return false;
    }


    @Override
    public boolean update(Categories categoria) {
        String query = "UPDATE Categoria SET nom_categoria = ? WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, categoria.getNom());
            stmt.setInt(2, categoria.getId());

            return stmt.executeUpdate() > 0;

        } catch(SQLException e) {
            System.out.println("Error en actualitzar la categoria!!");
            return false;
        }
        
    }


    @Override
    public boolean delete(int id) {
        String updateLlibres = "UPDATE Llibre SET categoria_id = ? WHERE categoria_id = ?";
        String query = "DELETE FROM Categoria WHERE id = ?";

        try (PreparedStatement stmtUpdate = conn.prepareStatement(updateLlibres); 
             PreparedStatement stmtDelete = conn.prepareStatement(query)) {

            stmtUpdate.setInt(1, 1); // 1 es l'id de quan no esta assignat cap categoria
            stmtUpdate.setInt(2, id);
            stmtUpdate.executeUpdate();
        

            stmtDelete.setInt(1, id);
            int rowsAffected = stmtDelete.executeUpdate();

            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error en eliminar la categoria!!");
            return false;
        }
    }    
}
