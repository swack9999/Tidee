package Tidee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInController {
	// Attributes
	@FXML
	private TextField empIDtxt;
	@FXML
	private PasswordField passwordTxt;
	@FXML
	private Label errorLabel;

	// Operations
	@FXML
	private String verifyPass() throws SQLException {
		/*
		 * Purpose: Verify that the entered password matches with entered employee ID
		 */
		Connector conn = new Connector();
		// Query database for employee corresponding to entered employee ID
		ResultSet results = conn.execStatement(false,
				"select `empPass` from `employee` where `empID` = '" +
				empIDtxt.getText() + "'");
		// Check if employee is registered in database
		if(results.next())
			return results.getString("empPass");
		else
			return "";
	}
	@FXML
	private int verifyType() throws SQLException {
		/*
		 * Purpose: Determine which homepage system must load based on employee type
		 */
		Connector conn = new Connector();
		// Query database for type of employee corresponding to employee ID
		ResultSet results = conn.execStatement(false, "select `empType` "
				+ "from `employee` where `empID`= '" 
				+ empIDtxt.getText() + "'");
		results.next();	// No need to test for employee being in database. VerifyPass already has that check
		return results.getInt("empType");
	}
	@FXML
	public void logInButton(ActionEvent event) throws IOException, SQLException {
		/*
		 * Purpose: Take user credentials and log in as necessary
		 */
		// Validate entered credentials
		if (passwordTxt.getText().equals(verifyPass())) {
			Parent manager_home_parent;
			// Send to respective homepage based on employee type
			if (verifyType() == 0) {
				manager_home_parent = FXMLLoader.load(getClass().getResource("EmpHomePage.fxml"));
				// Store current employee type
				GlobalConstants.currentEmpType = 0;
			}
			else if (verifyType() == 1) {
				manager_home_parent = FXMLLoader.load(getClass().getResource("DeptManagerHomePage.fxml"));
				// Store current employee type
				GlobalConstants.currentEmpType = 1;
			}
			else {
				manager_home_parent = FXMLLoader.load(getClass().getResource("storeManagerHomePage.fxml"));
				// Store current employee type
				GlobalConstants.currentEmpType = 2;
			}
			Scene manager_home_scene = new Scene(manager_home_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(manager_home_scene);
			app_stage.show();
		} else
			// Display error if login credentials are incorrect
			errorLabel.setText("Incorrect credentials");
	}
}
