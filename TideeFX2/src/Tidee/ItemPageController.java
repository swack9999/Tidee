package Tidee;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ItemPageController implements Initializable{
		@FXML
		private ImageView itemImage;
		@FXML
		private Label itemName;
		@FXML
		private Label price;
		@FXML
		private Label itemID;
		@FXML
		private Text description;
		@FXML
		private Text storeSec;
		@FXML
		private Text stockSec;
		@FXML
		private Label depNum;
		@FXML
		private Text storeInv;
		@FXML
		private Text stockInv;
	private Parent manager_home_parent;
	public void Return(ActionEvent event) throws IOException {

		manager_home_parent = FXMLLoader.load(getClass().getResource("SearchInv.fxml"));
		Scene manager_home_scene = new Scene(manager_home_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(manager_home_scene);
		app_stage.show();
	}
	public void LoadDatabase() throws SQLException, IOException {
		Connector conn = new Connector();
		ResultSet rs = conn.execStatement(false,"select * from `item` where `itemID` = '"+GlobalConstants.displayedItem.getItemID()+"'");
		rs.next();
		InputStream is= rs.getBinaryStream("itemPic");
		BufferedImage imBuff= ImageIO.read(is);
		Image image = SwingFXUtils.toFXImage(imBuff, null);
		itemImage.setImage(image);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources){
		itemName.setText(GlobalConstants.displayedItem.getItemName());
		price.setText("$"+String.format("%.2f",GlobalConstants.displayedItem.getPrice()));
		itemID.setText(GlobalConstants.displayedItem.getItemID());
		description.setText(GlobalConstants.displayedItem.getProductDescription());
		storeSec.setText(String.valueOf(GlobalConstants.displayedItem.getStoreSecLocation()));
		stockSec.setText(String.valueOf(GlobalConstants.displayedItem.getStockSecLocation()));
		depNum.setText(String.valueOf(GlobalConstants.displayedItem.getDepNum()));
		storeInv.setText(String.valueOf(GlobalConstants.displayedItem.getStoreInventory()));
		stockInv.setText(String.valueOf(GlobalConstants.displayedItem.getStockInventory()));
		try {
			LoadDatabase();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

}
