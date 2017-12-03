package Tidee;

import java.sql.*;
public class Connector {
	// Attributes
	private Connection connection;
	
	// Operations
	public Connector() {
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/stocksystem?autoReconnect=true&useSSL=false",
					"root", "cis375");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public ResultSet execStatement(boolean updateOrQuery, String stmt) {
		try {
			ResultSet results = null;
			Statement statement = (Statement) connection.createStatement();
			if (!updateOrQuery)
				results = statement.executeQuery(stmt);
			else
				statement.executeUpdate(stmt);
			return results;
		} catch (Exception exc) { 
			exc.printStackTrace(); 
		}
		return null;
	}
	public Connection getconnection()
	{
		return connection;
	}
}
