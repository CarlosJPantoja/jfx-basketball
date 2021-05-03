package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	
	private GUIDriver gui;
	
	public Main() {
		gui = new GUIDriver();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setController(gui);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Basketball Stat Filter");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		gui.setStage(primaryStage);
		gui.loader("csvdata.fxml");
		primaryStage.show();
	}

}