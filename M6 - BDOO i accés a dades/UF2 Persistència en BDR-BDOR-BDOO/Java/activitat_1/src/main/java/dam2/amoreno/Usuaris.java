package dam2.amoreno;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Usuaris implements DAO {

	private static final String URL = "jdbc:mysql://localhost:3306/activitat1";
    private static final String USER = "aniol";
    private static final String PASSWORD = "aniol1234";

	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

	@Override
	public List<Usuaris> LlistarUsuaris() {

		
		String sql = "SELECT * FROM usuaris";
	}

	@Override
	public boolean create(Usuaris usuaris) {
		return false;
	}

	@Override
	public boolean update(Usuaris usuaris) {
		return false;
	}

	@Override
	public boolean delete(int id) {
		return false;
	}
}
