package dam.amoreno.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public void start(Stage teatre) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Decorat1.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            teatre.setTitle("Hello!");
            teatre.setScene(scene);
            teatre.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}