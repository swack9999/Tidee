package application;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class storeManagerController {
	private  Parent manager_home_parent;
	
	
    public void ViewStoreInventory(ActionEvent event) throws IOException {

        manager_home_parent = FXMLLoader.load(getClass().getResource("storeInventory.fxml"));
        Scene manager_home_scene = new Scene (manager_home_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(manager_home_scene);
        app_stage.show();
    }

    public void changeLayout(ActionEvent event) throws IOException {

        manager_home_parent = FXMLLoader.load(getClass().getResource("layout.fxml"));
        Scene manager_home_scene = new Scene (manager_home_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(manager_home_scene);
        app_stage.show();
    }

    public void Clearence(ActionEvent event) throws IOException {

        manager_home_parent = FXMLLoader.load(getClass().getResource("layout.fxml"));
        Scene manager_home_scene = new Scene (manager_home_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(manager_home_scene);
        app_stage.show();
    }
    
    public void initialize(URL location, ResourceBundle resources) {

    }
}
