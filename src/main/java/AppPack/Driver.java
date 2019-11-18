
package AppPack;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Insets; 
public class Driver extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("A Food Price Checker");
	primaryStage.setResizable(false);

        
        BorderPane root = new BorderPane();
	
	
	FoodFinder app = new FoodFinder(root, primaryStage);
        primaryStage.setScene(new Scene(root, 750, 750));
        primaryStage.show();
    }
}
