package Tidee;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {
	public void display(String title, String message) {
		Scene scene;
		Stage window = new Stage();
		Label msg = new Label();
		Button closeButton;
		VBox layout = new VBox(10);
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(600);
		window.setMinHeight(400);
		
		msg.setText(message);
		
		closeButton = new Button("Close");
		closeButton.setOnAction(e -> window.close());
		
		layout.getChildren().addAll(msg, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		scene = new Scene(layout,600,400);
		window.setScene(scene);
		window.show();
	}
}
