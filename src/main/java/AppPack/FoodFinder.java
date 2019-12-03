package AppPack;
import java.net.URI;
import java.awt.Desktop;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.application.HostServices;
import javafx.stage.StageStyle;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import java.util.ListIterator;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This Class represents an instance of a FoodFinder application
 * It displays nutrition facts of certain foods
 */
public class FoodFinder{
    public class Nutrient{
	private String name;
	private String dailyVal;
	
	public Nutrient(String name, String dailyVal){
	    this.name = name;
	    this.dailyVal = dailyVal;
	}
	public Nutrient(String name){
	    this.name = name;
	    this.dailyVal = "N/A";
	}
	public String getName(){
	    return this.name;
	}
	public String getDailyVal(){
	    return this.dailyVal;
	}
	
	public StringProperty nameProperty(){
	    StringProperty property = new SimpleStringProperty();
	    property.set(this.name);
	    return property;
	}
	public StringProperty dailyValProperty(){
	    StringProperty property = new SimpleStringProperty();
	    property.set(this.dailyVal);
	    return property;
	}
	@Override
	public boolean equals(Object o){
	    Nutrient n = (Nutrient)o;
	    return ((this.name.equals(n.getName())) && (this.dailyVal.equals(n.getDailyVal())));
	}
    }
    private BorderPane appPane;
    private Stage mainStage;
    private static final double BUTTON_WIDTH = 60;
    private static final double BUTTON_HEIGHT = 30;
   
    protected FoodFinder(BorderPane pane, Stage primaryStage){
	this.appPane = pane;
	this.mainStage = primaryStage;
	setupTop();
	setupMid();
	
    }
    private void setupTop(){
	VBox top = new VBox(15);//container for the top part of the application
	top.setAlignment(Pos.CENTER);
	Menu fileMenu = new Menu("File");
	MenuItem close = new MenuItem("Close");
	close.setOnAction(e ->{
		System.out.println("close");
		Platform.exit();
		System.exit(0);
	    });
	MenuItem about = new MenuItem("About");
	about.setOnAction(new EventHandler<ActionEvent>(){//when the "about" menu item is pressed
		@Override
		public void handle(ActionEvent e){
	    System.out.println("pressed about");
		    VBox rootOfAbout = new VBox(20);//20 units of spacing
		    
		    rootOfAbout.setPadding(new Insets(5,10,15,10));
		    Text aboutText = new Text("An app to retrieve nutrition facts of foods\nSource Code is available by clicking the button bellow\nAuthor: Mikolaj Bien");
		    aboutText.setFont(new Font(15));
		    Button closeButton = new Button("Close");
		    Button sourceCode = new Button("Source Code");
		    //size for the buttons
		    closeButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		    closeButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		    HBox Buttons = new HBox(30);//container for buttons
		    Buttons.setAlignment(Pos.CENTER);
		    Buttons.getChildren().addAll(closeButton, sourceCode);
		    rootOfAbout.getChildren().addAll(aboutText, Buttons);

		    //settings for the new window
		    Stage aboutWindow = new Stage();
		    aboutWindow.setResizable(false);
		    aboutWindow.setTitle("About");
		    aboutWindow.initModality(Modality.WINDOW_MODAL);
		    aboutWindow.initOwner(mainStage);
		    aboutWindow.setScene(new Scene(rootOfAbout));
		    //make sure the new window opens on where the parent window is
		    aboutWindow.setX(mainStage.getX() + 50);
		    aboutWindow.setY(mainStage.getY() + 300);
		    //end window settings
		    
		    sourceCode.setOnAction(e1 ->{//open github
			    try{
				Desktop.getDesktop().browse(new URI("https://github.com/mikolajbien/FoodFinder"));}
			    catch(java.net.URISyntaxException ex){
				ex.printStackTrace();
			    }
			    catch(java.io.IOException ex){
				ex.printStackTrace();
			    }
			});
		    closeButton.setOnAction(e1 ->{//close the popup
			    System.out.println("pressed closed");
			    aboutWindow.close();
			});//button lambda
		    aboutWindow.show();
		    
		}
	    });
	fileMenu.getItems().addAll(about, close);//adds options for the file menu
	MenuBar topMenu = new MenuBar(fileMenu);
	
	Text welcomeMsg = new Text("Welcome to Nutrition Facts Checker!");
	welcomeMsg.setFont(new Font(20));

	top.getChildren().addAll(topMenu, welcomeMsg);
	
	this.appPane.setTop(top);
    }
    private void setupMid(){
	GridPane mid = new GridPane();//root for middle
	mid.setPadding(new Insets(15,10,25,20));


	Text groceryText = new Text("Grocery Item: ");
	ComboBox<String> foodSelection = new ComboBox<String>();
	foodSelection.getItems().addAll("Onions", "Green Pepper", "Apples");

	Button searchButton = new Button("Search");
	TableView<Nutrient> table = new TableView<Nutrient>();
	TableColumn<Nutrient, String> nutrientCol = new TableColumn<Nutrient, String>("Nutrient");
	nutrientCol.setCellValueFactory(new PropertyValueFactory<Nutrient, String>("name"));
	TableColumn<Nutrient, String> percentCol = new TableColumn("% dv");
	percentCol.setCellValueFactory(new PropertyValueFactory<Nutrient, String>("dailyVal"));
	table.getColumns().addAll(nutrientCol, percentCol);//adding column names to the table
	ObservableList<Nutrient> tableList = FXCollections.observableArrayList();
	searchButton.setOnAction(e ->{//search
		Scraper scraper = new Scraper();
		ArrayList<String> facts = scraper.queryDatabase("red bell pepper");//raw data to be organized

		for(String s: facts)
		    System.out.println(s);
		for (ListIterator<String> i = facts.listIterator(6); i.nextIndex() < facts.size() - 1; ){
		    String nutrientType = i.next();
		    String percent = i.next();
		    // System.out.println(nutrientType);
		    //System.out.println(facts.get(i.nextIndex()));
		    Nutrient potNut = new Nutrient(nutrientType, percent);
		    if (!tableList.contains(potNut)){
			tableList.add(potNut);
			}
		  
		}
		
		
		
		table.setItems(tableList);
	    } );
	    
	//TODO
	
	mid.setVgap(10);
	mid.setHgap(5);
	mid.add(groceryText, 0, 4);
	mid.add(foodSelection, 1, 4);
	mid.add(searchButton,4, 4);
	mid.add(table,0,10);
	this.appPane.setCenter(mid);
    }
}
