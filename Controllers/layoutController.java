package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class layoutController {
	private Parent manager_home_parent;
	
    public void Return(ActionEvent event) throws IOException {

        manager_home_parent = FXMLLoader.load(getClass().getResource("storeManagerHomePage.fxml"));
        Scene manager_home_scene = new Scene (manager_home_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(manager_home_scene);
        app_stage.show();
    }



    public void initialize(URL location, ResourceBundle resources) {

    }
}
