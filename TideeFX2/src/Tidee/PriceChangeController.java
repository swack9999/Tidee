package Tidee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PriceChangeController {
	private Parent manager_home_parent;

	public void Return(ActionEvent event) throws IOException {
		/*
		 * Purpose: Send user back to homepage based on their employee type
		 */
		if (GlobalConstants.currentEmpType == 0)
			manager_home_parent = FXMLLoader.load(getClass().getResource("EmpHomePage.fxml"));
		if (GlobalConstants.currentEmpType == 1)
			manager_home_parent = FXMLLoader.load(getClass().getResource("DeptManagerHomePage.fxml"));
		if (GlobalConstants.currentEmpType == 2)
			manager_home_parent = FXMLLoader.load(getClass().getResource("storeManagerHomePage.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}

	
	
	public void submit(ActionEvent event) throws IOException {
		/*
		 * Purpose: 
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("CreatePriceChangeEvent2.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	public void initialize(URL location, ResourceBundle resources) {

	}
}
