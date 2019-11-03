package Tidee;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.Collections;

public class layoutController implements Initializable{
	// Attributes
	private Parent manager_home_parent;
	private ObservableList<TableItems> data;
	private ObservableList<String> IDs;
	@FXML
	private TableView<TableItems> tableView;
	@FXML
	private TableColumn<TableItems, String> layoutID;
	@FXML
	private TableColumn<TableItems, String> sec;
	@FXML
	private TableColumn<TableItems, String> sub;
	@FXML
	private ChoiceBox<String> layBox;
	
	// Operations
	public void Return(ActionEvent event) throws IOException {
		/*
		 * Purpose: Sends user bake to homepage
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*
		 * Purpose: Populates GUI table at initialization
		 */
		try {
			LoadDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void LoadDatabase() throws SQLException {
		/*
		 * Purpose: Populates GUI table
		 */
		ResultSet rs;
		String secRaw, subRaw, layRaw;
		StringTokenizer secTok;
		int depPos;
		ArrayList<String> secs = new ArrayList<String>(), layIDs = new ArrayList<String>();
		ArrayList<Integer> subs = new ArrayList<Integer>();
		Connector conn = new Connector();
		data = FXCollections.observableArrayList();
		// Get and store raw sec, sub, and layoutID data
		rs = conn.execStatement(false, "select * from layout");
		rs.next();
		secRaw = rs.getString("secName");
		subRaw = rs.getString("SubSectionName");
		layRaw = rs.getString("layoutID");
		// Populate section ArrayList
		secTok = new StringTokenizer(secRaw);
		while (secTok.hasMoreElements())
			secs.add(secTok.nextToken());
		// Populate subsection ArrayList
		secTok = new StringTokenizer(subRaw);
		while (secTok.hasMoreElements())
			subs.add(Integer.parseInt(secTok.nextToken()));
		// Populate layoutID ArrayList
		for (int i = 0; i < secs.size(); i++) {
            layIDs.add("DEP0" + (i + 1));
        }
		IDs = FXCollections.observableArrayList(layIDs);
		layBox.setItems(IDs);
		layBox.setValue(layIDs.get(0));
		
		if (GlobalConstants.currentEmpType == 1) {
			depPos = Collections.binarySearch(secs, GlobalConstants.depNo);
			for (int i = 1; i < subs.get(depPos) + 1; i++) {
				data.add(new TableItems(secs.get(depPos), Integer.toString(i)));
			}
		}
		else {
			
		}
		/*while(rs.next())
			data.add(new TableItems(rs.getString(1),rs.getString(2),
			rs.getString(3)));*/
		sec.setCellValueFactory(new PropertyValueFactory<>("secName"));
		sub.setCellValueFactory(new PropertyValueFactory<>("subSecName"));
		tableView.setItems(null);
		tableView.setItems(data);
		
	}
	
}