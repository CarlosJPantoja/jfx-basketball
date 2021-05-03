package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUIDriver {
	
	@FXML
    private VBox VBox;
	
	@FXML
    private TextField URL;
	
	@FXML
    private Label alertTF;

    
	
	private Stage stage;
	private Alert alert;
	
	public void loader(String fmxl) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fmxl));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		VBox.getChildren().clear();
		VBox.getChildren().add(root);
		stage.close();
		stage.show();
	}
	
	public void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
	
	private void launchAlert(String msg) throws IOException {
		alert = new Alert(AlertType.WARNING);
		alert.setTitle("Basketball Stat Filter");
		alert.getDialogPane().getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("alert.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		alert.getDialogPane().setHeader(root);
		alertTF.setText(msg);
		Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		alert.showAndWait();
	}
	
	@FXML
    public void alertTouch(ActionEvent event) {
		alert.close();
    }
	
	@FXML
    public void openFileChooser(ActionEvent event) {
		try{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
			URL.setText(fileChooser.showOpenDialog(stage).getAbsolutePath());
		} catch(NullPointerException e) {
			URL.setText("");
		}
    }
	
	
	@FXML
    public void loadCSVFilter(ActionEvent event) throws IOException {
		if(URL.getText()==null || URL.getText().equals("")) {
			launchAlert("Tonto");
		} else {
			loader("filter.fxml");
		}
    }
}
