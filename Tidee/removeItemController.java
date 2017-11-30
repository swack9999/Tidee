package Tidee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class removeItemController implements Initializable{
	@FXML
	private TableView<items> itemview;
	@FXML
	private TableColumn<items, String> columnItemID;
	@FXML
	private TableColumn<items, Integer> columnDept;
	@FXML
	private TableColumn<items, String> columnItem;
	@FXML
	private TableColumn<items, Integer> columnInventory;
	@FXML
	private Button btnReturn;
	private Parent manager_home_parent;
	private ObservableList<items> data;

	@FXML
	public void ReturnInven(ActionEvent event) throws IOException {
	       manager_home_parent = FXMLLoader.load(getClass().getResource("storeInventory.fxml"));
	        Scene manager_home_scene = new Scene (manager_home_parent);
	        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        app_stage.setScene(manager_home_scene);
	        app_stage.show();

	}
	@FXML
	public void LoadDatabase() throws SQLException {
		Connector conn = new Connector();
		data= FXCollections.observableArrayList();
		ResultSet rs = conn.execStatement(false,"select * from item");
		while (rs.next())
		{
			data.add(new items(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
					rs.getInt(9)));
		}
		columnItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		columnDept.setCellValueFactory(new PropertyValueFactory<>("depNum"));
		columnItem.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		columnInventory.setCellValueFactory(new PropertyValueFactory<>("sum"));
		itemview.setItems(null);
		itemview.setItems(data);
	}
	
	@FXML
	public void RemoveItem(ActionEvent event) throws IOException {
		items item = itemview.getSelectionModel().getSelectedItem();
		Connector conn = new Connector();
		conn.execStatement(true,"DELETE FROM `item` WHERE `itemID` = '"+item.getItemID()+"'");
		try {
			LoadDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		try {
			LoadDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
