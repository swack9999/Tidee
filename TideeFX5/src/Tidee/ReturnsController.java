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

public class ReturnsController implements Initializable {
	// Attributes
	private Parent manager_home_parent;
	private ObservableList<TableItems> data;
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

	// Operations
	@FXML
	public void Submit(ActionEvent event) throws SQLException {
		/*
		 * Purpose: Make the entered inventory changes to the database
		 */
		TableItems item = itemView.getSelectionModel().getSelectedItem();
		if(item!=null) {
			if(item!=null) {
				Connector conn = new Connector();
				Alert msg = new Alert();
				String stmt;
				if(GlobalConstants.currentEmpType==1) {
					if(!item.getDepNum().equals(Integer.parseInt(GlobalConstants.depNo)))
						msg.display("Return not proccessed","This item is not avaliable in your department, please contact store manager");
					stmt = "UPDATE `item` SET `stockInventory`= `stockInventory` + "+ Integer.parseInt(QuantityReturned.getText()) +
					" WHERE `depNum` ='" + GlobalConstants.depNo + "' AND `itemID` = '" + item.getItemID() + "'";
			
					}
				else
					stmt = "UPDATE `item` SET `stockInventory`= `stockInventory` + "+ Integer.parseInt(QuantityReturned.getText()) +
						" WHERE `itemID` = '" + item.getItemID() + "'";
				conn.execStatement(true,stmt);
			}
		}
		LoadDatabase();
	}
	@FXML
	public void Return(ActionEvent event) throws IOException {
		/*
		 * Purpose: Send user to previous page
		 */
		if (GlobalConstants.currentEmpType == 1)
			manager_home_parent = FXMLLoader.load(getClass().getResource("DeptManagerHomePage.fxml"));
		if (GlobalConstants.currentEmpType == 2)
			manager_home_parent = FXMLLoader.load(getClass().getResource("storeManagerHomePage.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	@FXML
	public void Search(ActionEvent event) throws SQLException {
		/*
		 * Purpose: Search for entered item in database
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
			ResultSet rs;
			if(GlobalConstants.currentEmpType==1)
				rs = conn.execStatement(false,"select * from `item` where `depNum` = '" + GlobalConstants.depNo + "'AND  `itemName` like '" + SearchValue.getText() +"%'");
			else
				rs = conn.execStatement(false, "select * from `item` where `itemName` like '" + SearchValue.getText() + "%'");
			data= FXCollections.observableArrayList();
			while (rs.next())
			{
				data.add(new TableItems(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
						rs.getString(9),rs.getString(10)));
			}
			itemView.setItems(null);
			itemView.setItems(data);
		}
	}
	@FXML
	public void LoadDatabase() throws SQLException {
		/*
		 * Purpose: Load database into GUI table
		 */
		Connector conn = new Connector();
		data= FXCollections.observableArrayList();
		ResultSet rs = conn.execStatement(false,"select * from item");
		while (rs.next()) {
			data.add(new TableItems(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
					rs.getString(9),rs.getString(10)));
		}
		itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		deptColumn.setCellValueFactory(new PropertyValueFactory<>("depNum"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
		itemView.setItems(null);
		itemView.setItems(data);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*
		 * Purpose: Load database into GUI table on initialization
		 */
		try {
			LoadDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
