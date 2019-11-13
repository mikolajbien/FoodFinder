
package AppPack;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Driver extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("A Food Price Checker");
       

        
        StackPane root = new StackPane();

        primaryStage.setScene(new Scene(root, 750, 750));
        primaryStage.show();
    }
}
