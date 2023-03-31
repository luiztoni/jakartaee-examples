package br.luiztoni.servlet.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Repository<T, ID> {
	protected Connection connection;
	
	protected void openConnection() {
		connection = ConnectionFactory.getConnection();
	}
	
	protected void closeConnection() throws SQLException {
		connection.commit();
		connection.close();
	}
	public abstract void create(T t);
	public abstract T read(ID id);
	public abstract void update(T t);
	public abstract void delete(ID id);
	public abstract List<T> index();
	
}