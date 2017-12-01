package Tidee;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
	
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestImageController implements Initializable {
	@FXML
	private ImageView imageHolder;
	
	public void LoadDatabase() throws SQLException, IOException {
		Connector conn = new Connector();
		ResultSet rs = conn.execStatement(false,"select * from `item` where `itemID` = '111'");
		rs.next();
		InputStream is= rs.getBinaryStream("itemPic");
		BufferedImage imBuff= ImageIO.read(is);
		Image image = SwingFXUtils.toFXImage(imBuff, null);
		imageHolder.setImage(image);
		imageHolder.setFitHeight(288);
		imageHolder.setFitWidth(426);
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources){
		try {
			LoadDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
