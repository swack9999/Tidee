package Tidee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.RadioButton;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class createPriceController implements Initializable{
	@FXML
	private Label errorLabel;
	@FXML
	private RadioButton radio1;
	@FXML
	private ToggleGroup check;
	@FXML
	private RadioButton radio2;
	@FXML
	private TextField newPrice;
	@FXML
	private TextField eventID;
	@FXML
	private DatePicker calendar;
	@FXML
	private TableView<TableItems> itemView;
	@FXML
	private TableColumn<TableItems, String> itemIDColumn;
	@FXML
	private TableColumn<TableItems, Integer> deptColumn;
	@FXML
	private TableColumn<TableItems, String> itemColumn;
	@FXML
	private TableColumn<TableItems, Double> priceColumn;
	@FXML
	private TableColumn<TableItems, Integer> inventoryColumn;
	@FXML
	private TextField SearchValue;
	@FXML
	private TextField duration;
	@FXML
	private TextField field1;
	@FXML 
	private TextField field2;
	private Parent manager_home_parent;
	private ObservableList<TableItems> data;

	@FXML
	public void Submit(ActionEvent event) throws IOException,SQLException{
		TableItems tableItems = null;
		if (check.getSelectedToggle() != null) {
			String buttonChosen;
			if(radio1.isSelected())
				buttonChosen="clearance";
			else
				buttonChosen="sale";
			Connector conn = new Connector();
			String statement=null;
			tableItems = itemView.getSelectionModel().getSelectedItem();
			if (tableItems != null) {
				ResultSet result = conn.execStatement(false, "select `eventID` from `clearanceevent` where `eventID` = '" + eventID.getText() + "'");
				if (result.next()) {
					errorLabel.setText("EventID already exists in clearance");
					errorLabel.setVisible(true);
					return;
				}
				result=conn.execStatement(false, "select `eventID` from `salesevent` where `eventID` = '" + eventID.getText() + "'");
				if (result.next()) {
					errorLabel.setText("EventID already exists in sales");
					errorLabel.setVisible(true);
					return;
				}
				result=conn.execStatement(false, "select `itemID` from `clearanceevent` where `itemID` = '" + tableItems.getItemID() + "'");
				if (result.next()) {
					errorLabel.setText("ItemID is already in clearanceevent");
					errorLabel.setVisible(true);
					return;
				}
				result=conn.execStatement(false, "select `itemID` from `salesevent` where `itemID` = '" + tableItems.getItemID() + "'");
				if (result.next()) {
					errorLabel.setText("ItemID is already in salesevent");
					errorLabel.setVisible(true);
					return;
				}
				if (buttonChosen.equals("clearance")) {
					statement="insert into `clearanceevent` (`eventID`, `eventDate`, `itemID`, `secLoc`, `subLoc`, "
							+ "`markdownPrice`) values ('"+eventID.getText()+"', '"+calendar.getValue().toString()+
							"', '"+tableItems.getItemID()+"', '"+field1.getText()+"', '"+field2.getText()+"', '"+
							newPrice.getText()+"');";
					}
					
				else {
					statement="insert into `salesevent` (`eventID`, `eventDate`, `itemID`, `secLoc`, `subLoc`, "
							+ "`markdownPrice`, `duration`) values ('"+eventID.getText()+"', '"+calendar.getValue().toString()+
							"', '"+tableItems.getItemID()+"', '"+field1.getText()+"', '"+field2.getText()+"', '"+
							newPrice.getText()+"', '"+duration.getText()+"');";
				}
				conn.execStatement(true,statement);
				radio1.setSelected(false);
				radio2.setSelected(false);
				eventID.setText("");
				calendar.setValue(null);
				field1.setText("");
				field2.setText("");
				newPrice.setText("");
				duration.setText("");
				errorLabel.setText("");
				LoadDatabase();
				Alert msg=new Alert();
				msg.display("Added Price Event","Event was created");
				
				}
			else {
				errorLabel.setText("Need to choose an item");
				errorLabel.setVisible(true);
				return;
			}
		}
		else
		{
			errorLabel.setText("Need to choose sale or clearance");
			errorLabel.setVisible(true);
			return;
		}
	}
	@FXML
	public void Return(ActionEvent event) throws IOException{
		/*
		 * Purpose: Upon pressing the return button, returns user back to previous screen
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("viewPriceChangeEvent.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	@FXML
	public void Search(ActionEvent event) throws SQLException{
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
		 * Purpose: Populate GUI table with item data
		 */
		Connector conn = new Connector();
		data= FXCollections.observableArrayList();
		ResultSet rs = conn.execStatement(false,"select * from item");
		while (rs.next()) {
			data.add(new TableItems(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
					rs.getString(9),rs.getString(10)));
		}
		itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		deptColumn.setCellValueFactory(new PropertyValueFactory<>("depNum"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		inventoryColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
		itemView.setItems(null);
		itemView.setItems(data);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			LoadDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
