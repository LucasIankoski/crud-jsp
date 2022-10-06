package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

	private static String urlBanco = "jdbc:postgresql://localhost:5432/crud-jsp?autoReconnect=true";
	private static String usuario = "postgres";
	private static String senha = "12345";
	private static Connection connection = null;

	public static Connection getConnection() {
		return connection;
	}

	static {
		conectar();
	}

	public DB() {
		conectar();
	}

	public static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(urlBanco, usuario, senha);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
