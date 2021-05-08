package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.45.100:3306/dbasonpaul";
	private String user = "dba";
	private String password = "Senac@123";

	/**
	 * conexão com o banco de dados
	 * 
	 * @autur con
	 */
	// Método de conexão

	public Connection conectar() {
		// con -> objeto
		Connection con = null;
		// tratamento exceções
		try {
			Class.forName(driver);
			// estabelecendo
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
