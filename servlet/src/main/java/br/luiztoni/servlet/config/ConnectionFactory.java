package br.luiztoni.servlet.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionFactory {
	
	private static Connection connection;
	
	private ConnectionFactory() {}
	
	public static Connection getConnection() {
		String url ="jdbc:mariadb://localhost:3306/cocada";     
		String user = "root";    
		String password = "myroot";
		try {
			if (Objects.nonNull(connection) && !connection.isClosed()) {
				return connection;
			}
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			return connection;
		} catch(SQLException e) {		
			return null;
		}
	}
}