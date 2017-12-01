package Tidee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
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

import javafx.scene.control.TableView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

public class ReturnsController implements Initializable{
	@FXML
	private TableView<TableItems> itemView;
	@FXML
	private TableColumn<TableItems, String> itemIDColumn;
	@FXML
	private TableColumn<TableItems,Integer> deptColumn;
	@FXML
	private TableColumn<TableItems, String> itemColumn;
	@FXML
	private TableColumn<TableItems,Integer> inventoryColumn;
	@FXML
	private TextField QuantityReturned;
	@FXML
	private TextField SearchValue;
	private Parent manager_home_parent;
	private ObservableList<TableItems> data;

	@FXML
	public void Submit(ActionEvent event) throws SQLException{
		TableItems item = itemView.getSelectionModel().getSelectedItem();
		if(item!=null)
		{
			Connector conn = new Connector();
			String stmt = "UPDATE `item` SET `stockInventory`= `stockInventory` + "+Integer.parseInt(QuantityReturned.getText())+
					" WHERE `itemID` = '"+item.getItemID()+"'";
			conn.execStatement(true,stmt);
		}
		LoadDatabase();
		
	}
	@FXML
	public void Return(ActionEvent event) throws IOException{
		manager_home_parent = FXMLLoader.load(getClass().getResource("storeInventory.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	@FXML
	public void Search(ActionEvent event) throws SQLException{
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
		{
			data.add(new TableItems(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
					rs.getInt(9)));
		}
		itemView.setItems(null);
		itemView.setItems(data);
		}
	}
	@FXML
	public void LoadDatabase() throws SQLException {
		Connector conn = new Connector();
		data= FXCollections.observableArrayList();
		ResultSet rs = conn.execStatement(false,"select * from item");
		while (rs.next())
		{
			data.add(new TableItems(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
					rs.getInt(9)));
		}
		itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		deptColumn.setCellValueFactory(new PropertyValueFactory<>("depNum"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
		itemView.setItems(null);
		itemView.setItems(data);
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
