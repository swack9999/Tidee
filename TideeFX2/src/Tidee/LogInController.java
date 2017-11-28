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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
	// Attributes
	@FXML
	private TextField empIDtxt;
	@FXML
	private PasswordField passwordTxt;

	// Operations
	@FXML
	private String verifyPass() throws SQLException {
		Connector conn = new Connector();
		//String empPass;
		ResultSet results = conn.execStatement(
				"select empPass from employee where empID = '" +
				empIDtxt.getText() + "'");
		//empPass = results.getString("empPass");
		//System.out.println(empPass);
		results.next();
		return results.getString("empPass");
	}

	@FXML
	public void logInButton(ActionEvent event) throws IOException, SQLException {
		if (passwordTxt.getText().equals(verifyPass())) {
			Parent manager_home_parent = FXMLLoader.load(getClass().getResource("storeManagerHomePage.fxml"));
			Scene manager_home_scene = new Scene(manager_home_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(manager_home_scene);
			app_stage.show();
		} else {
			Alert alert = new Alert();
			alert.display("Alert", "Incorrect credentials");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
