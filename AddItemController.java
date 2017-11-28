package Tidee;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemController {
	// Attributes
	private Parent manager_home_parent;
	@FXML
	private TextField depField;
	@FXML
	private TextField stockQuant;
	@FXML
	private TextField stockLoc;
	@FXML
	private TextField storeQuant;
	@FXML
	private TextField storeLoc;
	
	// Operations
	public void Return(ActionEvent event) throws IOException {
		manager_home_parent = FXMLLoader.load(getClass().getResource("storeInventory.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	public void Submit(ActionEvent event) throws IOException, SQLException {
		Connector conn = new Connector();
		//ResultSet result = conn.execStatement("insert into item");
	}

	public void initialize(URL location, ResourceBundle resources) {

	}
}
