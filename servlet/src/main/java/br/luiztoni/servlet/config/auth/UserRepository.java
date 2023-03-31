package br.luiztoni.servlet.config.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import br.luiztoni.servlet.config.ConnectionFactory;

public class UserRepository {

	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	
	private Connection connection;

	private void openConnection() {
		connection = ConnectionFactory.getConnection();
	}

	private void closeConnection() throws SQLException {
		connection.commit();
		connection.close();
	}

	public void create(User user) {
		this.openConnection();
		String update = "INSERT INTO USERS(EMAIL, PASSWORD) VALUES (?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.executeUpdate();
			this.closeConnection();
		} catch (SQLException e) {
			logger.severe("Error in create: "+e.getMessage());
		}	
	}

	public User findByEmail(String email) {
		String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
		this.openConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				User user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setEmail(resultSet.getString("EMAIL"));
				user.setPassword(resultSet.getString("PASSWORD"));
				this.closeConnection();
				return user;
			}
		} catch (SQLException e) {
			logger.severe("Error in findByEmail: "+e.getMessage());
		}
		return null;

	}
}