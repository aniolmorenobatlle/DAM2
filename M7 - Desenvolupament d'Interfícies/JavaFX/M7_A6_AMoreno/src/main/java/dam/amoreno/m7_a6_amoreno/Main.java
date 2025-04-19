package dam.amoreno.m7_a6_amoreno;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Escenari1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Activitat 6");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        resetClassificacio();
        launch();
    }

    private static void resetClassificacio() {
        String fitxerClassificacio = "classificacioMundial.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerClassificacio))) {
            // Buidar el fitxer de classificacio al iniciar la cursa
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}