package dam.amoreno.m7_a7_amoreno;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Pantalla1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Activitat 7");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        resetUsuari();
        resetPreguntes();
        resetRespostes();
        launch();
    }

    private static void resetUsuari() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuari.txt"))) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void resetPreguntes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("preguntes.txt"))) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void resetRespostes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("respostes.txt"))) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}