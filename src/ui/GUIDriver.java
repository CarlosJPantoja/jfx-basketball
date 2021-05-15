package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
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

	private Controller controller;
	private Stage stage;
	private Alert alert;
	private int pickValue;

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

			for(int i=1; i<=5; i++) {
				addCenterGrid(new Label(stats[i-1]), grid, i, 0);
				addCenterGrid(new Label(controller.getTopStat1().get(i-1).getName()), grid, 1, i);
				addCenterGrid(new Label(controller.getTopStat2().get(i-1).getName()), grid, 2, i);
				addCenterGrid(new Label(controller.getTopStat3().get(i-1).getName()), grid, 3, i);
				addCenterGrid(new Label(controller.getTopStat4().get(i-1).getName()), grid, 4, i);
				addCenterGrid(new Label(controller.getTopStat5().get(i-1).getName()), grid, 5, i);
			}

		}
	}

	public ArrayList<Player> filtered() {
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
		list = aux.filter(aux.getRoot(), Double.parseDouble(data.getText()), list, n, sentinel);
		addCenterGrid(new Label ("Player"), grid, 1, 0);
		addCenterGrid(new Label (pickStat.getValue()), grid, 2, 0);

		return list;

	}

	@FXML
	public void filterTree(ActionEvent event) {

		AVLTree<Double, Player> aux = null;
		int n = -2;
		boolean sentinel = false;
		for(int i=0; i<5 && !sentinel; i++) {
			if(controller.getOutput()[i].equals(pickStat.getValue())) {
				aux = controller.getStats().get(i);
				pickValue = i;
			}
			if(controller.getSigns()[i].equals(pickSign.getValue()))
				n = controller.getValues()[i];
		}
		if(pickSign.getValue().length()>1 && pickSign.getValue().charAt(1)=='=') {
			sentinel = true;
		}
		ArrayList<Player> list = new ArrayList<Player>();
		list = aux.filter(aux.getRoot(), Double.parseDouble(data.getText()), list, n, sentinel);
		

		controller.setList(list);
		
		

		Pagination p = new Pagination();
		VBox ux = (VBox)mainVBox.getChildren().get(0);
		ux.getChildren().remove(2);
		
		ux.getChildren().add(2, p);
		p.setMaxPageIndicatorCount((list.size()/16)+1);
		p.setPageCount((list.size()/16)+1);
		p.setPageFactory(new Callback<Integer, Node>() {
			public Node call(Integer pageIndex) {
				
				grid.getChildren().clear();
				grid.setGridLinesVisible(false);
				grid.setGridLinesVisible(true);
				addCenterGrid(new Label ("Top"), grid, 0, 0);
				addCenterGrid(new Label ("Player"), grid, 1, 0);
				addCenterGrid(new Label (pickStat.getValue()), grid, 2, 0);
				
				for(int i=((16*(pageIndex+1))-16), j=0; i<(16*(pageIndex+1))&&i<controller.getList().size(); i++, j++) {
					addCenterGrid(new Label("#"+(i+1)), grid, 0, j+1);
					Button bt = new Button(controller.getList().get(i).getName());
					
					bt.setStyle("-fx-background-color: transparent;");
					bt.setPadding(new Insets(5, 10, 5, 10));
					addCenterGrid(bt, grid, 1, j+1);
					addCenterGrid(new Label(controller.getList().get(i).getStats()[pickValue]+""), grid, 2, j+1);
					bt.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							Button bta = (Button)event.getSource();
							int row = GridPane.getRowIndex(bta)-1;
							int num = (16*(pageIndex+1))-16+row;
							Player aux = controller.getList().get(num);
							try {
								loader("player.fxml");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							playersName.setText(aux.getName());
							playersTeam.setText(aux.getTeam());
							playersAge.setText(aux.getAge()+"");
							
						}
						
					});
				}
				GridPane.setMargin(grid, new Insets(0, 0, 73, 0) );
				return grid;
				
			}
		});
		
		
		p.setPrefSize(grid.getWidth(), 603);
		//p.setMaxSize(grid.getWidth(), 529);
		//p.setMinSize(grid.getWidth(), 529);
		VBox.setMargin(p, new Insets(20, 60, 20, 60));

		//GridThread gt = new GridThread(controller, this);
		//gt.setDaemon(true);
		//gt.start();


		//GridPane gr = new GridPane();
		//gr.setGridLinesVisible(true);






		//VBox.setMargin(sp, new Insets(20, 60, 20, 60));

		//gr.setAlignment(Pos.CENTER);

		//sp.setPrefSize(600, 529);

		//stage.sizeToScene();

	}

	private <Type extends Labeled> void addCenterGrid(Type element, GridPane matrix, int column, int row) {
		matrix.add(element, column, row);
		GridPane.setHalignment(element, HPos.CENTER);
		GridPane.setValignment(element, VPos.CENTER);
		element.setPadding(new Insets(5, 10, 5, 10));
	}

	
}

/*
	//"-fx-background-color: transparent;"
	C:\Users\Carlos\OneDrive\Documentos\Eclipse IDE\Workspaces\eclipse-workspace\jfx-basketball\data\data.csv
*/
