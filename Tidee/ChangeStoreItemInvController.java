package Tidee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class ChangeStoreItemInvController implements Initializable {
	// Attributes
	private Parent manager_home_parent;
	private ObservableList<TableItems> data;
	@FXML
	private TableView<TableItems> itemView;
	@FXML
	private TableColumn<TableItems, String> itemIDColumn;
	@FXML
	private TableColumn<TableItems, String> deptColumn;
	@FXML
	private TableColumn<TableItems, String> itemColumn;
	@FXML
	private TableColumn<TableItems,Integer> storeInvColumn;
	@FXML
	private TableColumn<TableItems,Integer> stockInvColumn;
	@FXML
	private TableColumn<TableItems,Integer> inventoryColumn;
	@FXML
	private TextField SearchValue;
	@FXML
	private TextField newValue;
	@FXML
	private RadioButton radio1;
	@FXML
	private ToggleGroup check;
	@FXML
	private RadioButton radio2;

	// Operations
	@FXML
	public void Return(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user to homepage
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("storeManagerHomePage.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	
	@FXML
	public void Search(ActionEvent event) throws SQLException {
		/*
		 * Purpose: Reloads the table if it is empty and 
		 * 			searches for an item via entered keywords
		 */
		if (SearchValue.getText().equals("")) {
			try {
				LoadDatabase();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			Connector conn = new Connector();
			ResultSet rs = conn.execStatement(false, "select * from `item` where `itemName` like '" + SearchValue.getText() + "%'");
			data= FXCollections.observableArrayList();
			while (rs.next()) {
				data.add(new TableItems(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
						rs.getString(9),rs.getString(10)));
			}
			itemView.setItems(null);
			itemView.setItems(data);
		}
	}
	@FXML
	public void Submit(ActionEvent event) throws SQLException {
		/*
		 * Purpose: Change item inventory value of either store or stockroom
		 */
		TableItems tableItems = null;
		if (check.getSelectedToggle() != null) {
			String buttonChosen;
			if(radio1.isSelected())
				buttonChosen="store";
			else
				buttonChosen="stock";
			radio1.setSelected(false);
			radio2.setSelected(false);
			tableItems = itemView.getSelectionModel().getSelectedItem();
			if (tableItems != null) {
				Connector conn = new Connector();
				String statement;
				if (buttonChosen.equals("store")) {
				 statement = "UPDATE `item` SET `storeInventory`= " + Integer.parseInt(newValue.getText()) +
						" WHERE `itemID` = '"+ tableItems.getItemID() + "'";
				}
				else {
					statement = "UPDATE `item` SET `stockInventory`= " + Integer.parseInt(newValue.getText()) +
							" WHERE `itemID` = '" + tableItems.getItemID() + "'";
				}
				newValue.setText("");
				conn.execStatement(true,statement);
				LoadDatabase();	
			}
		}
	}
	
	@FXML
	public void LoadDatabase() throws SQLException {
		/*
		 * Purpose: Populates GUI table
		 */
		Connector conn = new Connector();
		data = FXCollections.observableArrayList();
		ResultSet rs = conn.execStatement(false,"select * from item");
		while (rs.next()) {
			data.add(new TableItems(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
					rs.getString(9),rs.getString(10)));
		}
		itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		deptColumn.setCellValueFactory(new PropertyValueFactory<>("depNum"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		storeInvColumn.setCellValueFactory(new PropertyValueFactory<>("storeInventory"));
		stockInvColumn.setCellValueFactory(new PropertyValueFactory<>("stockInventory"));
		inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
		itemView.setItems(null);
		itemView.setItems(data);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*
		 * Purpose: Populates GUI table on initialization
		 */
	try {
		LoadDatabase();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
}
