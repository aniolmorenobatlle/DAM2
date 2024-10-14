package dam2.amoreno;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarisDAOImpl implements DAO {

	private String URL = "jdbc:mysql://localhost:3306/activitat1";
    private String USER = "aniol";
    private String PASSWORD = "aniol1234";

	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

	@Override
	public List<Usuaris> LlistarUsuaris() {
        // Crear una ArrayList per emmagatzemar els usuaris
        List<Usuaris> usuaris = new ArrayList<>();

        String sql = "SELECT * FROM usuaris";

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            // Recorre els resultats de la consulta
            while (resultSet.next()) {
                // Crea un objecte de la classe Usuaris
                Usuaris usuari = new Usuaris();
                
                // Assigna els valors del resultat a l'objecte Usuaris
                usuari.setId(resultSet.getInt("id"));
                usuari.setNom(resultSet.getString("nom"));
                usuari.setCognoms(resultSet.getString("cognoms"));
                usuari.setData_naixement(resultSet.getString("data_naixement"));
                usuari.setEmail(resultSet.getString("email"));

                // Afegeix l'usuari a la llista
                usuaris.add(usuari);
            }
        } catch (SQLException e) {
            System.out.println("Hi ha hagut algun problema en obtenir les dades de la taula usuaris!!");
        }

        // Retorna la llista d'usuaris
        return usuaris;
    }

	@Override
	public boolean create(Usuaris usuari) {
		// Comprovem si el correu existeix
		if (existeixEmail(usuari.getEmail())) {
			System.out.println("Ja existeix un usuari amb aquest correu electrònic.");
			// No crear l'usuari si ja existeix el correu
			return false;
		}

		String sql = "INSERT INTO usuaris (nom, cognoms, data_naixement, email) VALUE (?, ?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			
			statement.setString(1, usuari.getNom());
			statement.setString(2, usuari.getCognoms());
			statement.setString(3, usuari.getData_naixement());
			statement.setString(4, usuari.getEmail());

			// Retornar true si s'ha fet tot correctament
			return statement.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("Hi ha hagut algun problema en obtenir les dades de la taula usuaris!!");

			return false;
		}
	}

	public boolean existeixEmail(String email) {
		String sql = "SELECT COUNT(*) FROM usuaris WHERE email = ?";
		
		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, email);
			
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next() && rs.getInt(1) > 0) {
					// El correu ja existeix
					return true; 
				}
			}
		} catch (Exception e) {
			System.out.println("Hi ha hagut un problema al comprovar l'email.");
			e.printStackTrace();
		}
		
		// El correu no existeix
		return false; 
	}


	@Override
	public boolean update(Usuaris usuari) {
		String sql = "UPDATE usuaris SET nom = ?, cognoms = ?, data_naixement = ?, email = ? WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			
			statement.setString(1, usuari.getNom());
			statement.setString(2, usuari.getCognoms());
			statement.setString(3, usuari.getData_naixement());
			statement.setString(4, usuari.getEmail());
			statement.setInt(5, usuari.getId());

			// Retornar true si s'ha fet tot correctament
			return statement.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("Hi ha hagut algun problema en obtenir les dades de la taula usuaris!!");

			return false;
		}
	}

	@Override
	public boolean delete(String email) {
		String sql = "DELETE FROM usuaris WHERE email = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

			statement.setString(1, email);

			// Retornar true si s'ha fet tot correctament
            return statement.executeUpdate() > 0;

		} catch (Exception e) {
			System.out.println("Hi ha hagut algun problema en obtenir les dades de la taula usuaris!!");

			return false;
		}
	}

	@Override
	public Usuaris cercaEmail(String email) {
		Usuaris usuari = null;

		String sql = "SELECT * FROM usuaris WHERE email = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
        
			// Obtenir les dades de l'usuari
			if (resultSet.next()) {
				usuari = new Usuaris();

				usuari.setId(resultSet.getInt("id"));
				usuari.setNom(resultSet.getString("nom"));
				usuari.setCognoms(resultSet.getString("cognoms"));
				usuari.setData_naixement(resultSet.getString("data_naixement"));
				usuari.setEmail(resultSet.getString("email"));
			}

		} catch (Exception e) {
			System.out.println("Error");
		}

		return usuari;
	}
}
