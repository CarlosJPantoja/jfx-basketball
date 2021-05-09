package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Controller;
import model.Player;
import structures.AVLTree;

public class GUIDriver {
	
	@FXML
    private VBox mainVBox;
	@FXML
    private Label alertTF, stat1, stat2, stat3, stat4, stat5 , statValue1, statValue2, statValue3, statValue4, statValue5, playersName, playersAge, playersTeam;
	@FXML
    private TextField URL, data;
	@FXML
    private ComboBox<String> pickStat, pickSign;
	@FXML
	private GridPane grid;
	@FXML
    private Button	stat1Player1,stat1Player2,stat1Player3,stat1Player4,stat1Player5, 
    				stat2Player1,stat2Player2,stat2Player3,stat2Player4,stat2Player5, 
    				stat3Player1,stat3Player2,stat3Player3,stat3Player4,stat3Player5, 
    				stat4Player1,stat4Player2,stat4Player3,stat4Player4,stat4Player5, 
    				stat5Player1,stat5Player2,stat5Player3,stat5Player4,stat5Player5;
	
	private Controller controller;
	private Stage stage;
	private Alert alert;
	
	public GUIDriver() {
		controller = new Controller();
	}
	
	public void loader(String fmxl) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fmxl));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		mainVBox.getChildren().clear();
		mainVBox.getChildren().add(root);
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
			launchAlert("A file has not been selected yet");
		} else {
			String[] stats = controller.loadCSV(URL.getText());
			
			loader("filter.fxml");
			pickStat.getItems().addAll(stats[0],stats[1],stats[2],stats[3],stats[4]);
			pickSign.getItems().addAll("<", "<=", "=", ">=", ">");
			stat1.setText(stats[0]);
			stat2.setText(stats[1]);
			stat3.setText(stats[2]);
			stat4.setText(stats[3]);
			stat5.setText(stats[4]);
			Button[] st1 = {stat1Player1,stat1Player2,stat1Player3,stat1Player4,stat1Player5};
			Button[] st2 = {stat2Player1,stat2Player2,stat2Player3,stat2Player4,stat2Player5};
			Button[] st3 = {stat3Player1,stat3Player2,stat3Player3,stat3Player4,stat3Player5};
			Button[] st4 = {stat4Player1,stat4Player2,stat4Player3,stat4Player4,stat4Player5};
			Button[] st5 = {stat5Player1,stat5Player2,stat5Player3,stat5Player4,stat5Player5};
			
			
			for(int i=0; i<5; i++) {
				st1[i].setText(controller.getTopStat1().get(i).getName());
				st2[i].setText(controller.getTopStat2().get(i).getName());
				st3[i].setText(controller.getTopStat3().get(i).getName());
				st4[i].setText(controller.getTopStat4().get(i).getName());
				st5[i].setText(controller.getTopStat5().get(i).getName());
			}
			
		}
    }
	
	@FXML
	public void filterTree(ActionEvent event) {
		AVLTree<Double, Player> aux = null;
		int n = -2;
		boolean sentinel = false;
		for(int i=0; i<5 && !sentinel; i++) {
			if(controller.getOutput()[i].equals(pickStat.getValue())) 
				aux = controller.getStats().get(i);
			if(controller.getSigns()[i].equals(pickSign.getValue()))
				n = controller.getValues()[i];
		}
		if(pickSign.getValue().length()>1 && pickSign.getValue().charAt(1)=='=') {
			sentinel = true;
		}
		ArrayList<Player> list = new ArrayList<Player>();
		long t1 = System.currentTimeMillis();
		list = aux.filter(aux.getRoot(), Double.parseDouble(data.getText()), list, n, sentinel);
		System.out.println(System.currentTimeMillis()-t1);
		VBox ux = (VBox)mainVBox.getChildren().get(0);
		ux.getChildren().remove(2);
		
		GridPane gr = new GridPane();
		gr.setGridLinesVisible(true);
		ScrollPane sp = new ScrollPane();
		sp.setContent(gr);
		ux.getChildren().add(2, sp);
		addCenterGrid(new Label ("Top"), gr, 0, 0);
		
		addCenterGrid(new Label ("Player"), gr, 1, 0);
		addCenterGrid(new Label (pickStat.getValue()), gr, 2, 0);
		long t2 = System.currentTimeMillis();
		for(int i=0; i<list.size(); i++) {
			addCenterGrid(new Label("#"+(i+1)), gr, 0, i+1);
			addCenterGrid(new Label(list.get(i).getName()), gr, 1, i+1);
			addCenterGrid(new Label(list.get(i).getFtr()+""), gr, 2, i+1);
		}
		System.out.println(System.currentTimeMillis()-t2);
		
		
		
		//VBox.setMargin(sp, new Insets(20, 60, 20, 60));
		

		
		
		
		//gr.setAlignment(Pos.CENTER);
		
		
		sp.setPrefSize(600, 529);
		
		
		//stage.sizeToScene();
		
		//fl.setMaxWidth(gr.getWidth()+20);
		//fl.setPrefWidth(gr.getWidth()+20);
		//fl.setMinWidth(gr.getWidth()+20);

		//sp.setMaxWidth(gr.getWidth()+20);
		//sp.setPrefWidth(gr.getWidth()+20);
		//sp.setMinWidth(gr.getWidth()+20);
		long t3 = System.currentTimeMillis();
		System.out.println("finish");
		stage.close();
		stage.show();
		System.out.println("finish "+(System.currentTimeMillis()-t3));
	}
	
	private <Type extends Labeled> void addCenterGrid(Type element, GridPane matrix, int column, int row) {
		matrix.add(element, column, row);
		//GridPane.setHalignment(element, HPos.CENTER);
		//GridPane.setValignment(element, VPos.CENTER);
		//element.setPrefHeight(32);
		//element.setPadding(new Insets(5, 10, 5, 10));
	}
	
	@FXML
    public void showTopPlayer(ActionEvent event) throws IOException {
		Player aux = null;
		char[] a = event.getTarget().toString().toCharArray();
		if(Integer.parseInt(a[14]+"")==1) {
			aux = controller.getTopStat1().get(Integer.parseInt(a[21]+"")-1);
		} else if(Integer.parseInt(a[14]+"")==2) {
			aux = controller.getTopStat2().get(Integer.parseInt(a[21]+"")-1);
		} else if(Integer.parseInt(a[14]+"")==3) {
			aux = controller.getTopStat3().get(Integer.parseInt(a[21]+"")-1);
		} else if(Integer.parseInt(a[14]+"")==4) {
			aux = controller.getTopStat4().get(Integer.parseInt(a[21]+"")-1);
		} else if(Integer.parseInt(a[14]+"")==5) {
			aux = controller.getTopStat5().get(Integer.parseInt(a[21]+"")-1);
		}
		loader("player.fxml");
		playersName.setText(aux.getName());
		playersTeam.setText(aux.getTeam());
		playersAge.setText(aux.getAge()+"");
    }
}

//C:\Users\Carlos\OneDrive\Documentos\Eclipse IDE\Workspaces\eclipse-workspace\jfx-basketball\data\
