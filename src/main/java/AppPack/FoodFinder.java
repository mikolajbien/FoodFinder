package AppPack;
import java.net.URI;
import java.awt.Desktop;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
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
public class FoodFinder{
    private BorderPane appPane;
    private Stage mainStage;
    private static final double BUTTON_WIDTH = 60;
    private static final double BUTTON_HEIGHT = 30;
    public FoodFinder(BorderPane pane, Stage primaryStage){
	this.appPane = pane;
	this.mainStage = primaryStage;
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
	about.setOnAction(new EventHandler<ActionEvent>(){//when the "about" menu item is pressed
		@Override
		public void handle(ActionEvent e){
		    System.out.println("pressed about");
		    VBox rootOfAbout = new VBox(20);//20 units of spacing
		    
		    rootOfAbout.setPadding(new Insets(5,10,15,10));
		    Text aboutText = new Text("A food finder app by Mikolaj Bien\nSource Code is available by clicking the button bellow");
		    aboutText.setFont(new Font(15));
		    Button closeButton = new Button("Close");
		    Button sourceCode = new Button("Source Code");
		    closeButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		    closeButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		    HBox Buttons = new HBox(30);//container for buttons
		    Buttons.setAlignment(Pos.CENTER);
		    Buttons.getChildren().addAll(closeButton, sourceCode);
		    rootOfAbout.getChildren().addAll(aboutText, Buttons);
		    
		    Stage aboutWindow = new Stage();
		    aboutWindow.setResizable(false);
		    aboutWindow.setTitle("About");
		    aboutWindow.initModality(Modality.WINDOW_MODAL);
		    aboutWindow.setScene(new Scene(rootOfAbout));
		    //make sure the new window opens on where the parent window is
		    aboutWindow.setX(mainStage.getX() + 50);
		    aboutWindow.setY(mainStage.getY() + 300);
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
	
	Text welcomeMsg = new Text("Welcome to FoodPriceComparer!");
	welcomeMsg.setFont(new Font(20));

	top.getChildren().addAll(topMenu, welcomeMsg);
	
	this.appPane.setTop(top);
    }
    private void SetupMid(){
	
    }
}
