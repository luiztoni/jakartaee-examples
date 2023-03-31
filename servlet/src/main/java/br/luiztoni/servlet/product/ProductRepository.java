package br.luiztoni.servlet.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.luiztoni.servlet.config.Repository;

public class ProductRepository extends Repository<Product, Integer> {

	Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	
	@Override
	public void create(Product product) {
		this.openConnection();
		String update = "INSERT INTO PRODUCTS (NAME, DESCRIPTION, PRICE) VALUES (?, ?, ?)";
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.executeUpdate();
			this.closeConnection();
		} catch (SQLException e) {
			logger.severe("Error in create: "+e.getMessage());
		}	
	}

	@Override
	public Product read(Integer id) {
		String query = "SELECT * FROM PRODUCTS WHERE ID = ?";
		this.openConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				Product product = new Product();
				product.setId(resultSet.getInt("ID"));
				product.setName(resultSet.getString("NAME"));
				product.setDescription(resultSet.getString("DESCRIPTION"));
				product.setPrice(resultSet.getDouble("PRICE"));
				this.closeConnection();
				return product;
			}
		} catch (SQLException e) {
			logger.severe("Error in read: "+e.getMessage());
		}
		return null;
	}

	@Override
	public void update(Product product) {
		String update = "UPDATE PRODUCTS SET NAME = ?, DESCRIPTION = ?, PRICE = ?  WHERE ID = ?";
		this.openConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.setInt(4, product.getId());
			statement.executeUpdate();
			this.closeConnection();
		} catch (SQLException e) {
			logger.severe("Error in update: "+e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) {
		String update = "DELETE FROM PRODUCTS WHERE ID = ?";
		this.openConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, id);
			statement.executeUpdate();
			this.closeConnection();
		} catch (SQLException e) {
			logger.severe("Error in delete: "+e.getMessage());
		}
	}

	@Override
	public List<Product> index() {
		String query = "SELECT * FROM PRODUCTS";
		this.openConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			List<Product> products = new ArrayList<Product>();
			Product product;
			while (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt("ID"));
				product.setName(resultSet.getString("NAME"));
				product.setDescription(resultSet.getString("DESCRIPTION"));
				product.setPrice(resultSet.getDouble("PRICE"));
				products.add(product);
			}
			this.closeConnection();
			return products;
		} catch (SQLException e) {
			logger.severe("Error in index: "+e.getMessage());
		}
		return null;
	}

}