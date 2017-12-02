package Tidee;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class StoreManagerController {
	private Parent manager_home_parent;


	public void changeLayout(ActionEvent event) throws IOException {
		/*
		 * Purpose: Send user to layout view page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("layout.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}

	public void viewPriceChgHist(ActionEvent event) throws IOException {
		/*
		 * Purpose: Send user to price change event viewer page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("viewPriceChangeEvent.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}

	
	public void viewStoreInventory(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to price change event creation page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("storeInventory.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	public void processReturns(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to process returns page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("Returns.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	public void updateStoreInventory(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to update store inventory page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("ChangeStoreItemInv.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
}
