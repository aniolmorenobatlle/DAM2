package dam2.amoreno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class AutorsDAOImpl implements DAOAutors {

    private Connection conn;

    // Constructor per rebre la connexió
    public AutorsDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Autors> LlistarAutors() {

        List<Autors> autors = new ArrayList<>();

        String query = "SELECT * FROM Autor";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Autors autor = new Autors();

                autor.setId(resultSet.getInt("id"));
                autor.setNom(resultSet.getString("nom"));
                autor.setCognoms(resultSet.getString("cognoms"));
                autor.setData(resultSet.getString("data_naixement"));

                autors.add(autor);
            }
            
        } catch (SQLException e) {
            System.out.println("Error!!");
        }

        return autors;
    }


    @Override
    public boolean create(Autors autor) {
        
        if (existeixNom(autor.getNom(), autor.getCognoms())) {
            System.out.println("Ja existeix aquest autor!!");
            return false;
        }
        

        String query = "INSERT INTO Autor (nom, cognoms, data_naixement) VALUE (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, autor.getNom());
            stmt.setString(2, autor.getCognoms());
            stmt.setString(3, autor.getData());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Hi ha hagut algun problema en obtenir les dades de la taula d'autor!!");

            return false;
        }
    }

    // Comprovar si hi ha un autor amb el mateix nom i cognom
    public boolean existeixNom(String nom, String cognoms) {
        String query = "SELECT COUNT(*) FROM Autor WHERE nom = ? and cognoms = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nom);
            stmt.setString(2, cognoms);

            try (ResultSet rs = stmt.executeQuery()){

                if (rs.next() && rs.getInt(1) > 0 && rs.getInt(2) > 0) {
                    return true;
                }

            } catch (Exception e) {
                System.out.println("Error!!");
            }

        } catch (SQLException e) {
            System.out.println("Error en buscar si l'autor existeix!!");
        }

        return false;
    }


    @Override
    public boolean update(Autors autor) {
        return false;
    }


    @Override
    public boolean delete(Integer id) {
        return false;
    }
    
}
