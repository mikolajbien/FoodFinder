package AppPack;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
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
public class FoodFinder{
    private BorderPane appPane;
    
    public FoodFinder(BorderPane pane){
	this.appPane = pane;
	setupTop();
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
	about.setOnAction(new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent e){
		    System.out.println("pressed about");
		    BorderPane rootOfAbout = new BorderPane();
		    VBox textBox = new VBox();
		    Label label1 = new Label("A food finder app by Mikolaj Bien");
		    textBox.getChildren().add(label1);
		    rootOfAbout.setTop(textBox);
		    Stage aboutWindow = new Stage();
		    aboutWindow.initModality(Modality.APPLICATION_MODAL);
		    aboutWindow.setScene(new Scene(rootOfAbout, 250, 250));
		    aboutWindow.show();
		}
	    });
	fileMenu.getItems().addAll(about, close);//adds options for the file menu
	MenuBar topMenu = new MenuBar(fileMenu);
	
	Text welcomeMsg = new Text("Welcome to FoodPriceComparer!");
	welcomeMsg.setFont(new Font(20));

	top.getChildren().addAll(topMenu, welcomeMsg);
	
	this.appPane.setTop(top);
    }
    private void SetupMid(){
	
    }
}
