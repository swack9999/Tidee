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
					"root", "MyNewPass");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public ResultSet execStatement(String stmt) {
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet results = statement.executeQuery(stmt);
			return results;
		} catch (Exception exc) { 
			exc.printStackTrace(); 
		}
		return null;
	}
}
