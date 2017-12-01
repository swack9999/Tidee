package Tidee;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class storeInventoryController {
	// Attributes
	private Parent manager_home_parent;

	// Operations
	public void Return(ActionEvent event) throws IOException {
		/*
		 * Purpose: Send user to homepage based on their employee type
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

	public void AddItem(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to add item page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("addItem.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}

	public void RemoveItem(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to remove item page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("removeItem.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	
	public void Search(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to search inventory page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("SearchInv.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
}
