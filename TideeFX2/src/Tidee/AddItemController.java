package Tidee;

import javafx.scene.control.TextArea;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemController {
	// Attributes
	private Parent manager_home_parent;
	@FXML
	private TextField itemID;
	@FXML
	private TextField depID;
	@FXML
	private TextField stockQuant;
	@FXML
	private TextField stockLoc;
	@FXML
	private TextField storeQuant;
	@FXML
	private TextField storeLoc;
	@FXML
	private TextField itemName;
	@FXML
	private TextField initQuant;
	@FXML
	private TextField price;
	@FXML
	private TextArea itemDesc;
	@FXML
	private Label errorLabel;
	
	// Operations
	public void Return(ActionEvent event) throws IOException {
		manager_home_parent = FXMLLoader.load(getClass().getResource("storeInventory.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	@FXML
	public void Submit(ActionEvent event) throws IOException, SQLException {
		Connector conn = new Connector();
		ResultSet result = conn.execStatement(false, "select `itemID` from `item` where `itemID` = '" + itemID.getText() + "'");
		if (result.next()) {
			errorLabel.setText("Item is already present in database");
			errorLabel.setVisible(true);
		}
		else if (Integer.parseInt(storeQuant.getText()) 
				+ Integer.parseInt(stockQuant.getText()) 
				> Integer.parseInt(initQuant.getText())){
			errorLabel.setText("Store floor and stockroom quantities "
					+ "exceed initial quantity");
			errorLabel.setVisible(true);
		}
		else {
			errorLabel.setVisible(false);
			conn.execStatement(true, "insert into `item` "
					+ "(`depNum`, `itemID`, `itemName`, `price`, "
					+ "`productDescription`, `stockInventory`, "
					+ "`stockSecLocation`, `storeInventory`, "
					+ "`storeSecLocation`) values ('" + depID.getText() + "', '" 
					+ itemID.getText() + "', '" + itemName.getText() + "', "
					+ price.getText() + ", '" + itemDesc.getText() + "', " 
					+ stockQuant.getText() + ", " + stockLoc.getText() + ", "
					+ storeQuant.getText() + ", " + storeLoc.getText() + ");");
		}
	}

	public void initialize(URL location, ResourceBundle resources) {

	}
}
