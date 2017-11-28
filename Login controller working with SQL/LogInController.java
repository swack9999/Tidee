package Tidee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
	// Attributes
	@FXML
	private TextField empIDtxt;
	@FXML
	private PasswordField passwordTxt;
	@FXML
	private Label errorLabel;

	// Operations

	@FXML
	public void logInButton(ActionEvent event) throws IOException, SQLException {
		try {
			// Get a connection
			boolean test=false;
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocksystem?autoReconnect=true&useSSL=false", "root", "hello123");
			// Create a statement
			Statement myStmt = myConn.createStatement();
			//Execute Query
			ResultSet myResult = myStmt.executeQuery("select * from employee");
			
			while (myResult.next())
			{
				if(empIDtxt.getText().equals(myResult.getString("empID"))&&passwordTxt.getText().equals(myResult.getString("empPass")))
				{
					if(myResult.getInt("empType")==1)
					{
						Parent manager_home_parent = FXMLLoader.load(getClass().getResource("storeManagerHomePage.fxml"));
						Scene manager_home_scene = new Scene(manager_home_parent);
						Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						app_stage.setScene(manager_home_scene);
						app_stage.show();
						test=true;
						break;
					}
				}
			}
			if (test==false)
			{
				errorLabel.setText("Incorrect credentials");
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
	}
}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
