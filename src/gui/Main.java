package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	
	private MenuGUI menugui;
	
	public Main() {
		menugui = new MenuGUI();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("support.fxml"));
		fxmlLoader.setController(menugui);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bouncing Lasers");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		//menugui.set_stage(primaryStage);
		//menugui.loader("lobby.fxml");
		primaryStage.show();
	}

}