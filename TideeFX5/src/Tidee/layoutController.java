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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.Collections;
import java.util.List;

public class layoutController implements Initializable {
	// Attributes
	private Parent manager_home_parent;
	private ObservableList<TableItems> data;
	private ObservableList<String> IDs;
	List<String> secs = new ArrayList<String>(), layIDs = new ArrayList<String>(), subs = new ArrayList<String>();
	@FXML
	private TableView<TableItems> tableView;
	@FXML
	private TableColumn<TableItems, String> layoutID;
	@FXML
	private TableColumn<TableItems, String> sec;
	@FXML
	private TableColumn<TableItems, String> sub;
	@FXML
	private ChoiceBox<String> layBox, oldSec, oldSub, newSec, newSub;
	@FXML
	private Button secSwapButton;
	@FXML
	private Label oldSecLabel, newSecLabel;

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

	@FXML
	public void swapSecs() {
		/*
		 * Purpose: Swaps entered sections
		 */
		String rawSecs = "";
		Connector conn = new Connector();
		// Swap elements in local layout
		Collections.swap(secs, linear(secs, oldSec.getValue()),
				linear(secs, newSec.getValue()));
		// Populate rawSecs from secs
		for (int i = 0; i < secs.size(); i++) {
			// This prevents an extra space at the end of rawSecs
			if (i == secs.size() - 1)
				rawSecs += secs.get(i);
			else
				rawSecs += secs.get(i) + " ";
		}
		// Update database with rawSecs
		conn.execStatement(true, "update layout set secName = '" + rawSecs + "'");
		// reset ChoiceBoxes
		oldSec.setValue(secs.get(0));
		newSec.setValue(secs.get(0));
	}

	@FXML
	public void swapSubs() {
		/*
		 * Purpose: Swaps entered subsections
		 */
		Connector conn = new Connector();
		int oldSubVal;
		// Update relevant items' sublocations
		oldSubVal = Integer.parseInt(oldSub.getValue());
		conn.execStatement(true, "update item set storeSubLocation = '" + newSub.getValue()
				+ "' where storeSecLocation = '" + layBox.getValue() + "'");
		conn.execStatement(true, "update item set storeSubLocation = '" + oldSubVal + "' where storeSecLocation = '"
				+ layBox.getValue() + "'");
		// Reset ChoiceBoxes
		oldSub.setValue("1");
		newSub.setValue("1");
	}

	public int linear(List<String> ids, String target) {
		/*
		 * Purpose: Searches list for target id
		 */
		for (int i = 0; i < ids.size(); i++) {
			if (target.equals(ids.get(i)))
				return i;
		}
		return -1;
	}
	
	@FXML
	public void switchLayout(ActionEvent event) {
		int depPos;
		Alert msg = new Alert();
		// Check if dep manager attempts to change layout
		if (GlobalConstants.currentEmpType == 1) {
			// Display error message
			msg.display("Wrong Department", "Department Managers can't switch to another department's layout.");
			// Reset layBox
			depPos = Collections.binarySearch(secs, GlobalConstants.depNo);
			layBox.setValue(layIDs.get(depPos));
		} else {
			data = FXCollections.observableArrayList();
			// Repopulate GUI table
			if (layBox.getValue().equals("STORE")) {
				for (int i = 0; i < layIDs.size() - 1; i++) {
					for (int j = 1; j < Integer.parseInt(subs.get(i)) + 1; j++)
						data.add(new TableItems(secs.get(i), Integer.toString(j)));
				}
			} else {
				depPos = Integer.parseInt(layBox.getValue().substring(3, 5)) - 1;
				for (int i = 1; i < Integer.parseInt(subs.get(depPos)) + 1; i++)
					data.add(new TableItems(secs.get(depPos), Integer.toString(i)));
			}
			sec.setCellValueFactory(new PropertyValueFactory<>("secName"));
			sub.setCellValueFactory(new PropertyValueFactory<>("subSecName"));
			tableView.setItems(null);
			tableView.setItems(data);
		}

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
		String secRaw, subRaw;
		StringTokenizer secTok;
		int depPos;
		Connector conn = new Connector();
		List<String> subBoxList = new ArrayList<>();
		data = FXCollections.observableArrayList();
		// Get and store raw sec, sub, and layoutID data
		rs = conn.execStatement(false, "select secName, SubSectionName from layout");
		rs.next();
		secRaw = rs.getString("secName");
		subRaw = rs.getString("SubSectionName");
		// Populate section ArrayList
		secTok = new StringTokenizer(secRaw);
		while (secTok.hasMoreElements())
			secs.add(secTok.nextToken());
		// Populate subsection ArrayList
		secTok = new StringTokenizer(subRaw);
		while (secTok.hasMoreElements())
			subs.add(secTok.nextToken());
		// Populate layoutID ArrayList
		for (int i = 0; i < secs.size(); i++) {
			layIDs.add("DEP0" + (i + 1));
		}
		// Populate layBox and set default value
		layIDs.add("STORE");
		IDs = FXCollections.observableArrayList(layIDs);
		layBox.setItems(IDs);
		layBox.setValue(layIDs.get(0));
		// Populate oldSec
		IDs = FXCollections.observableArrayList(secs);
		oldSec.setItems(IDs);
		oldSec.setValue(secs.get(0));
		// Populate newSec
		newSec.setItems(IDs);
		newSec.setValue(secs.get(0));
		// Check for Dep Manager
		if (GlobalConstants.currentEmpType == 1) {
			// Restrict to only data from respective department
			depPos = linear(secs, GlobalConstants.depNo);
			for (int i = 1; i < Integer.parseInt(subs.get(depPos)) + 1; i++) {
				data.add(new TableItems(secs.get(depPos), Integer.toString(i)));
				subBoxList.add(Integer.toString(i));
			}
			// Hide options to swap departments
			oldSec.setVisible(false);
			newSec.setVisible(false);
			secSwapButton.setVisible(false);
			oldSecLabel.setVisible(false);
			newSecLabel.setVisible(false);
		} else {
			// Populate GUI table
			depPos = Integer.parseInt(layBox.getValue().substring(3, 5)) - 1;
			for (int i = 1; i < Integer.parseInt(subs.get(depPos)) + 1; i++) {
				data.add(new TableItems(secs.get(depPos), Integer.toString(i)));
				subBoxList.add(Integer.toString(i));
			}
		}
		// Populate oldSub and newSub
		IDs = FXCollections.observableArrayList(subBoxList);
		oldSub.setItems(IDs);
		newSub.setItems(IDs);
		// Set default values for oldSub and newSub
		oldSub.setValue("1");
		newSub.setValue("1");
		// Initialize the GUI table cells
		sec.setCellValueFactory(new PropertyValueFactory<>("secName"));
		sub.setCellValueFactory(new PropertyValueFactory<>("subSecName"));
		tableView.setItems(null);
		tableView.setItems(data);
	}
}