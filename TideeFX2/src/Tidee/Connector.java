package Tidee;

import java.sql.*;
public class Connector {
	// Attributes
	private Connection connection;
	
	// Operations
	public Connector() {
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/stocksystem?autoReconnect=true&useSSL=false", "root", "MyNewPass");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	/*public ResultSet execStatement(String stmt) {
		Statement statement = connection.createStatement();
		
	}
	*/
}
