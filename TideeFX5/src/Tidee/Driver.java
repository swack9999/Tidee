package Tidee;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {

		try {
			// Get a connection
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/stocksystem?autoReconnect=true&useSSL=false", "root", "R00tadmin");
			// Create a statement
			Statement myStmt = myConn.createStatement();
			// Execute Query
			ResultSet myResult = myStmt.executeQuery("select * from item");

			while (myResult.next()) {
				System.out.println('$' + myResult.getString("price") + ", " + myResult.getString("itemName"));
			}

			myResult = myStmt.executeQuery("select * from employee");

			while (myResult.next()) {
				System.out.println('$' + myResult.getString("fName") + ", " + myResult.getString("empID"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}