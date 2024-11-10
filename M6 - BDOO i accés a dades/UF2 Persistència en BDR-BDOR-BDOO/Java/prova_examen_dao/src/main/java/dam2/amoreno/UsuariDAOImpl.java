package dam2.amoreno;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariDAOImpl implements UsuarisDAO {

    private String URL = "jdbc:mysql://localhost:3306/activitat1";
    private String USER = "aniol";
    private String PASSWORD = "aniol1234";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public List<Usuaris> LlistarUsuaris() {
        List<Usuaris> usuaris = new ArrayList<>();

        String query = "SELECT * FROM usuaris";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Usuaris usuari = new Usuaris();

                usuari.setId(resultSet.getInt("id"));
                usuari.setNom(resultSet.getString("nom"));
                usuari.setCognom(resultSet.getString("cognoms"));
                Date sqlDate = resultSet.getDate("data_naixement");
                if (sqlDate != null) {
                    usuari.setData_naixement(sqlDate.toLocalDate());
                }
                usuari.setEmail(resultSet.getString("email"));

                usuaris.add(usuari);

            }
            
        } catch (Exception e) {
            System.out.println("Error en llistar els usuaris");
        }

        return usuaris;
    }


    @Override
    public boolean create(Usuaris usuari) {
        String query = "INSERT INTO usuaris (nom, cognoms, data_naixement, email) VALUES (?, ?, ?, ?)";


        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuari.getNom());
            stmt.setString(2, usuari.getCognom());
            stmt.setDate(3, Date.valueOf(usuari.getData_naixement()));
            stmt.setString(4, usuari.getEmail());

            return stmt.executeUpdate() > 0;            

        } catch (Exception e) {
            System.out.println("Error en crear l'usuari, comprova que totes les dades sigui correctes.");
            return false;
        }

    }


    public boolean update(Usuaris usuari) {
        String query = "UPDATE usuaris SET nom = ?, cognoms = ?, data_naixement = ?, email = ? where id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuari.getNom());
            stmt.setString(2, usuari.getCognom());
            stmt.setDate(3, Date.valueOf(usuari.getData_naixement()));
            stmt.setString(4, usuari.getEmail());
            stmt.setInt(5, usuari.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Hi ha hagut algun error en actualitzar l'usuari. Comprova que totes les dades siguin correctes.");
            return false;
        }
    }

    
    public boolean delete(int id) {
        String query = "DELETE FROM usuaris WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error en eliminar l'usuari. Comprova que totes les dades siguin correctes.");
            return false;
        }
    }
}
