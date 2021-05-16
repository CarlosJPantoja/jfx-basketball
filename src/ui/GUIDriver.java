package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Controller;
import model.Player;

public class GUIDriver {

	@FXML
	private VBox mainVBox;
	@FXML
	private HBox pageIndex;
	@FXML
	private Label notice, time, warning, name, age, team;
	@FXML
	private TextField URL, data, page;
	@FXML
	private ComboBox<String> trees, stats, signs;
	@FXML
	private GridPane matrix;

	private Controller driver;
	private Pagination pages;
	private Stage stage;
	private Alert alert;

	public void loader(String fmxl) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fmxl));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		mainVBox.getChildren().clear();
		mainVBox.getChildren().add(root);
	}

	private void launchAlert(String msg) throws IOException {
		alert = new Alert(AlertType.WARNING);
		alert.setTitle("Basketball Stat Filter");
		alert.getDialogPane().getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("alert.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		alert.getDialogPane().setHeader(root);
		warning.setText(msg);
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
		if(URL.getText()==null || URL.getText().trim().equals("")) {
			launchAlert("A file has not been selected yet");
		} else {
			driver = new Controller();
			driver.loadCSV(URL.getText());
			loader("filter.fxml");
			long ms = System.currentTimeMillis();
			driver.top();
			time.setText("Filter time: "+(System.currentTimeMillis()-ms)+" milliseconds");
			pageIndex.setVisible(false);
			configureComboBox();
			for(int i=0; i<5; i++) {
				Label top = new Label("#"+(i+1));
				Label header = new Label(driver.getHeaders()[i]);
				placeInMatrix(top, matrix, 0, i+1);
				placeInMatrix(header, matrix, i+1, 0);
				for(int j=0; j<5; j++) {
					Label name = new Label(driver.getTop().get(i).get(j).getName());
					placeInMatrix(name, matrix, i+1, j+1);
				}
			}
		}
	}
	
	private void configureComboBox() {
		String[] headers = driver.getHeaders();
		stats.getItems().addAll(headers[0],headers[1],headers[2],headers[3],headers[4]);
		String[] sign = driver.getSigns();
		signs.getItems().addAll(sign[0],sign[1],sign[2],sign[3],sign[4]);
		trees.getItems().addAll("AVL Tree", "ABB Tree");
		trees.setValue("AVL Tree");
	}

	@FXML
	public void filterTree(ActionEvent event) throws IOException {
		if(stats.getValue()==null || signs.getValue()==null || data.getText()==null || data.getText().trim().equals("")) {
			launchAlert("Selet stat, sign and enter a number");
		} else {
			try {
				Double.parseDouble(data.getText());
			} catch(NumberFormatException e){
				launchAlert("Enter a number");
				return;
			}
			pageIndex.setVisible(true);
			String[] current = {stats.getValue(), signs.getValue(), data.getText(), "", trees.getValue()};
			driver.setCurrent(current);
			Long time = System.currentTimeMillis();
			if(trees.getValue().equals("AVL Tree"))
				driver.filter();
			else
				driver.filterABB();
			driver.getCurrent()[3] = (System.currentTimeMillis()-time)+"";
			configurePagination();
		}
	}
	
	@FXML
	public void back(ActionEvent event) throws IOException {
		configurePagination();
	}
	
	private void configurePagination() throws IOException {
		loader("filter.fxml");
		configureComboBox();
		stats.setValue(driver.getCurrent()[0]);
		signs.setValue(driver.getCurrent()[1]);
		data.setText(driver.getCurrent()[2]);
		notice.setText("Players with "+driver.getCurrent()[0]+" "+driver.getCurrent()[1]+" "+driver.getCurrent()[2]);
		time.setText("Filter time: "+driver.getCurrent()[3]+" milliseconds");
		trees.setValue(driver.getCurrent()[4]);
		pages = new Pagination();
		VBox current = (VBox)mainVBox.getChildren().get(0);
		current.getChildren().remove(4);
		current.getChildren().add(4, pages);
		pages.setMaxPageIndicatorCount((driver.getList().size()/16)+1);
		pages.setPageCount((driver.getList().size()/16)+1);
		pages.setPageFactory(new Callback<Integer, Node>() {
			public Node call(Integer pageIndex) {
				matrix = new GridPane();
				matrix.setGridLinesVisible(false);
				matrix.setGridLinesVisible(true);

				matrix.setAlignment(Pos.CENTER);
				
				placeInMatrix(new Label("Top"), matrix, 0, 0);
				placeInMatrix(new Label("Player"), matrix, 1, 0);
				placeInMatrix(new Label(driver.getCurrent()[0]), matrix, 2, 0);
				for(int i=((16*(pageIndex+1))-16), j=0; i<(16*(pageIndex+1))&&i<driver.getList().size(); i++, j++) {
					placeInMatrix(new Label("#"+(i+1)), matrix, 0, j+1);
					Button bt = new Button(driver.getList().get(i).getName());
					
					bt.setStyle("-fx-background-color: transparent;");
					bt.setPadding(new Insets(5, 10, 5, 10));
					placeInMatrix(bt, matrix, 1, j+1);
					placeInMatrix(new Label(driver.getList().get(i).getStats()[driver.getIndex()]+""), matrix, 2, j+1);
					
					configureButtonAction(bt);
					
				}
				GridPane.setMargin(matrix, new Insets(0, 0, 73, 0) );
				page.setText((pages.getCurrentPageIndex()+1)+"");
				return matrix;
			}
		});
		pages.setPrefSize(matrix.getWidth(), 603);
		VBox.setMargin(pages, new Insets(20, 60, 20, 60));
		
	}
	
	private void configureButtonAction(Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Button current = (Button)event.getSource();
				int row = GridPane.getRowIndex(current)-1;
				int num = (16*(pages.getCurrentPageIndex()+1))-16+row;
				Player player = driver.getList().get(num);
				try {
					loader("player.fxml");
				} catch (IOException e) {
				}
				name.setText(player.getName());
				team.setText(player.getTeam());
				age.setText(player.getAge()+"");
				for(int i=0; i<5; i++) {
					placeInMatrix(new Label(driver.getHeaders()[i]), matrix, 0, i);
					placeInMatrix(new Label(player.getStats()[i]+""), matrix, 1, i);
				}
			}
		});
	}

	private <Type extends Labeled> void placeInMatrix(Type element, GridPane matrix, int column, int row) {
		matrix.add(element, column, row);
		GridPane.setHalignment(element, HPos.CENTER);
		GridPane.setValignment(element, VPos.CENTER);
		element.setPadding(new Insets(5, 10, 5, 10));
	}
	
	@FXML
    public void locatePage(ActionEvent event) throws IOException {
		try {
			Integer index = Integer.parseInt(page.getText());
			if(index>pages.getPageCount())
				index = pages.getPageCount();
			else if(index<1)
				index = 1;
			pages.setCurrentPageIndex(index-1);
			page.setText(index+"");
		} catch(NumberFormatException e){
			launchAlert("Enter a number in Index");
		}
    }
	
	public void setStage(Stage s) {
		stage = s;
	}
}
