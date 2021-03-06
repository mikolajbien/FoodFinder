
package AppPack;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.Image;

public class Driver extends Application {
    public static void main(String[] args) {
	
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("A Nutriton Facts Checker");
	primaryStage.setResizable(false);
	java.net.URL s = Driver.class.getResource("/icons8-apple-64.png");
	System.out.println(s);
	primaryStage.getIcons().add(new Image(Driver.class.getResourceAsStream("/icons8-apple-64.png")));
        
        BorderPane root = new BorderPane();
	
	
	FoodFinder app = new FoodFinder(root, primaryStage);
        primaryStage.setScene(new Scene(root, 675, 675));
        primaryStage.show();
    }
}
