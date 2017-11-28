package Tidee;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("logIN.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Tidee");
        primaryStage.show();
    }
	
	public static void main (String [] args ) {
		
		try {
			// Get a connection
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocksystem?autoReconnect=true&useSSL=false", "root", "hello123");
			// Create a statement
			Statement myStmt = myConn.createStatement();
			//Execute Query
			ResultSet myResult = myStmt.executeQuery("select * from item");
			
			while (myResult.next())
			{
				System.out.println('$' + myResult.getString("price") + ", " +  myResult.getString("itemName"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		launch(args);
	}
}
