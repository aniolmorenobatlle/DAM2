package dam.amoreno.m7_a4_amoreno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String FITXER_ESTAT = "estat.txt";

    @Override
    public void start(Stage teatre) {
        try {
            String vista = llegirUltimaPantalla();

            FXMLLoader fxmlLoader;
            if (vista.equals("pane1")) {
                fxmlLoader = new FXMLLoader(Main.class.getResource("EntrarPrediccions.fxml"));
            } else {
                fxmlLoader = new FXMLLoader(Main.class.getResource("VeureResultats.fxml"));
            }

            Scene scene = new Scene(fxmlLoader.load());
            teatre.setTitle("Travessa");
            teatre.setScene(scene);
            teatre.setOnCloseRequest(_ -> guardarProperaPantalla(vista));
            teatre.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String llegirUltimaPantalla() {
        File fitxer = new File(FITXER_ESTAT);
        if (fitxer.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
                return reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "pane1";
    }

    private void guardarProperaPantalla(String vistaActual) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FITXER_ESTAT))) {
            if (vistaActual.equals("pane1")) {
                writer.write("pane2");
            } else {
                writer.write("pane1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}