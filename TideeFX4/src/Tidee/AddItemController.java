/*
 * 
 */
package Tidee;

import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.sql.PreparedStatement;

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
	private TextField storeLocSub;
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
	@FXML
	private TextField filePath;
	private FileChooser filechooser;
	private File file;
	private FileInputStream fis;
	
	
	// Operations
	public void Return(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user back to store inventory page
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("storeInventory.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	@FXML
	public void Browse(ActionEvent event) throws IOException, SQLException {
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		filechooser=new FileChooser();
		filechooser.setTitle("Choose image file");
		file=filechooser.showOpenDialog(app_stage);
		if(file!=null)
		{
			filePath.setText(file.getAbsolutePath());
		}
	}
	@FXML
	public void Submit(ActionEvent event) throws IOException, SQLException {
		/*
		 * Purpose: Add item with entered information into the database
		 */
		Connector conn = new Connector();
		// Check if item is already in database
		ResultSet result = conn.execStatement(false, "select `itemID` from `item` where `itemID` = '" + itemID.getText() + "'");
		if (result.next()) {
			errorLabel.setText("Item is already present in database");
			errorLabel.setVisible(true);
		}
		// Check if initial quantity is exceeded
		else if (Integer.parseInt(storeQuant.getText()) 
				+ Integer.parseInt(stockQuant.getText()) 
				> Integer.parseInt(initQuant.getText())){
			errorLabel.setText("Store floor and stockroom quantities "
					+ "exceed initial quantity");
			errorLabel.setVisible(true);
		}
		else {
			// Add the described item to the database
			errorLabel.setVisible(false);
			if(file!=null)
			{
			fis=new FileInputStream(file);
			try {
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/stocksystem?autoReconnect=true&useSSL=false",
						"root", "cis375");
				PreparedStatement statement=connection.prepareStatement("insert into `item` "
						+ "(`depNum`, `itemID`, `itemName`, `price`, "
						+ "`productDescription`, `stockInventory`, "
						+ "`stockSecLocation`, `storeInventory`, "
						+ "`storeSecLocation`, `storeSubLocation`, `itemPic`) values ('" + depID.getText() + "', '" 
						+ itemID.getText() + "', '" + itemName.getText() + "', "
						+ price.getText() + ", '" + itemDesc.getText() + "', " 
						+ stockQuant.getText() + ", " + stockLoc.getText() + ", "
						+ storeQuant.getText() + ", '" + storeLoc.getText() + "', '"
						+storeLocSub.getText()+"',?);");
				statement.setBinaryStream(1, fis);
				statement.execute();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			}
			else
			{
				errorLabel.setText("No file selected, Use browse button");
				errorLabel.setVisible(true);
			}
		}
	}
}
