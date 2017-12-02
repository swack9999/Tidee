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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PriceChangeController implements Initializable{
	private Parent manager_home_parent;
	private ObservableList<TableItems> data;
	@FXML
	private TableView<TableItems> itemView;
	@FXML
	private TableColumn<TableItems, String> itemIDColumn;
	@FXML
	private TableColumn<TableItems,String> itemColumn;
	@FXML
	private TableColumn<TableItems,String> eventIDColumn;
	@FXML
	private TableColumn<TableItems,Double> markdownPriceColumn;
	@FXML
	private TableColumn<TableItems,String> storeLocColumn;
	@FXML
	private TableColumn<TableItems,String> storeSubLocColumn;
	@FXML
	private TableColumn<TableItems,String> startDateColumn;
	@FXML
	private TableColumn<TableItems,Integer> durationColumn;

	public void Return(ActionEvent event) throws IOException {
		/*
		 * Purpose: Send user back to homepage based on their employee type
		 */
		if (GlobalConstants.currentEmpType == 0)
			manager_home_parent = FXMLLoader.load(getClass().getResource("EmpHomePage.fxml"));
		if (GlobalConstants.currentEmpType == 1)
			manager_home_parent = FXMLLoader.load(getClass().getResource("DeptManagerHomePage.fxml"));
		if (GlobalConstants.currentEmpType == 2)
			manager_home_parent = FXMLLoader.load(getClass().getResource("storeManagerHomePage.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}

	
	
	public void createPriceEvent(ActionEvent event) throws IOException {
		/*
		 * Purpose: 
		 */
		manager_home_parent = FXMLLoader.load(getClass().getResource("CreatePriceChangeEvent1.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	public void LoadDatabase() throws SQLException{
		/*
		 * Purpose: Populate GUI table with item data
		 */
		Connector conn = new Connector();
		data= FXCollections.observableArrayList();
		ResultSet rs = conn.execStatement(false,"SELECT `salesevent`.`eventID`,`salesevent`.`eventDate`,`salesevent`.`itemID`,"
				+ "`salesevent`.`secLoc`,`salesevent`.`subLoc`,`salesevent`.`markdownPrice`,`salesevent`.`duration`,`item`.`itemName` "
				+ "FROM `salesevent` INNER JOIN item ON `salesevent`.`itemID`=`item`.`itemID`");
		while(rs.next()) {
			data.add(new TableItems(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getInt(7),rs.getString(8)));
		}
		 rs = conn.execStatement(false,"SELECT `clearanceevent`.`eventID`,`clearanceevent`.`eventDate`,`clearanceevent`.`itemID`,"
				+ "`clearanceevent`.`secLoc`,`clearanceevent`.`subLoc`,`clearanceevent`.`markdownPrice`,`item`.`itemName` "
				+ "FROM `clearanceevent` INNER JOIN item ON `clearanceevent`.`itemID`=`item`.`itemID`");
		while(rs.next()) {
			data.add(new TableItems(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),-1,rs.getString(7)));
		}
		itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		eventIDColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));
		markdownPriceColumn.setCellValueFactory(new PropertyValueFactory<>("markdownPrice"));
		storeLocColumn.setCellValueFactory(new PropertyValueFactory<>("storeSecLocation"));
		storeSubLocColumn.setCellValueFactory(new PropertyValueFactory<>("storeSubLocation"));
		startDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
		durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
		itemView.setItems(null);
		itemView.setItems(data);
	}
	public void initialize(URL location, ResourceBundle resources) {
		try {
			LoadDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
