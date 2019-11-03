package Tidee;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class DeptManagerHomePageController {
	// Attributes
	private Parent manager_home_parent;

	// Operations
	public void viewDepInventory(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to department inventory viewing page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("storeInventory.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}

	public void changeLayout(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to department layout change page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("layout.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}

	public void viewPriceChgHist(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to department price event history page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("viewPriceChangeEvent.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}

	public void processReturns(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to return processing page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("Returns.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
}
