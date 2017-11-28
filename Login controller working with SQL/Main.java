package Tidee;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	//connectToDB();
        Parent root = FXMLLoader.load(getClass().getResource("LogIN.fxml"));
        primaryStage.setTitle("TIDEE");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
    /*public void connectToDB() {
   
    		try {
    			// Get a connection
    			Connection myConn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stocksystem?autoReconnect=true&useSSL=false", "root", "MyNewPass");
    			// Create a statement
    			Statement myStmt = (Statement) myConn.createStatement();
    			//Execute Query
    			ResultSet myResult = myStmt.executeQuery("select * from item");
    			while (myResult.next())
    			{
    				System.out.println('$' + myResult.getString("price") + ", " +  myResult.getString("itemName"));
    				System.out.println("Product Description: " + myResult.getString("productDescription"));
    			}
    			myResult = myStmt.executeQuery("select * from employee");
    			while (myResult.next())
    			{
    				System.out.println('$' + myResult.getString("fName") + ", " +  myResult.getString("empType"));
    			}
    			
    		}
    		catch (Exception exc) {
    			exc.printStackTrace();
    		}
    	}*/
    
}
