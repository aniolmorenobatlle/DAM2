package dam.amoreno.helloworld;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Text text = new Text("Hello World!!");
        text.setVisible(false);

        Button button = new Button("Fes clic");

        // Fes acció per mostrar missatge
        button.setOnAction(e -> {
            text.setVisible(true);
        });

        // Crear Pane per organitzar el text i el boto verticalment
        Pane contenidor = new Pane();

        // Crear escena i configurar-la
        Scene scene = new Scene(contenidor, 500, 800);

        // Configurar escena
        primaryStage.setTitle("Hello World JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
