package Tidee;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SearchInvController implements Initializable {
	// Attributes
	private Parent manager_home_parent;
	private ObservableList<TableItems> data;
	@FXML
	private TextField SearchValue;
	@FXML
	private TableView<TableItems> itemView;
	@FXML
	private TableColumn<TableItems, String> itemIDColumn;
	@FXML
	private TableColumn<TableItems,Integer> deptColumn;
	@FXML
	private TableColumn<TableItems, String> itemColumn;
	@FXML
	private TableColumn<TableItems, Double> priceColumn;
	@FXML
	private TableColumn<TableItems,Integer> inventoryColumn;

	// Operations
	@FXML
	public void ReturnInven(ActionEvent event) throws IOException{
		/*
		 * Purpose: Upon pressing the return button, returns user back to previous screen
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("storeInventory.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	@FXML
	public void GoToItem(ActionEvent event) throws IOException {
		/*
		 * Purpose: Displays item page corresponding to selected item
		 */
		TableItems item = itemView.getSelectionModel().getSelectedItem();
		if(item!=null) {
			GlobalConstants.displayedItem=item;
			manager_home_parent = FXMLLoader.load(getClass().getResource("ItemPage.fxml"));
			Scene manager_home_scene = new Scene(manager_home_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(manager_home_scene);
			app_stage.show();
		}
	}
	@FXML
	public void Search(ActionEvent event) throws SQLException {
		/*
		 * Purpose: Search for a specified item in the database
		 */
		if(SearchValue.getText().equals(""))
		{
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
			while (rs.next())
				data.add(new TableItems(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
						rs.getString(9),rs.getString(10)));
			itemView.setItems(null);
			itemView.setItems(data);
		}
	}
	@FXML
	public void LoadDatabase() throws SQLException {
		/*
		 * Purpose: Load items in database into GUI tables
		 */
		Connector conn = new Connector();
		data= FXCollections.observableArrayList();
		ResultSet rs = conn.execStatement(false,"select * from item");
		while (rs.next())
			data.add(new TableItems(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
					rs.getString(9),rs.getString(10)));
		itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		deptColumn.setCellValueFactory(new PropertyValueFactory<>("depNum"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
		itemView.setItems(null);
		itemView.setItems(data);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources){
		/*
		 * Purpose: Populate the GUI tables on initialization
		 */
		try {
			LoadDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}