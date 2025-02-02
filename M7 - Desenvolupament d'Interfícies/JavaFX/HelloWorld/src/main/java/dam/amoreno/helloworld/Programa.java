package dam.amoreno.helloworld;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Programa extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage teatre) throws Exception{
        // Declaració controls
        Pane decorat1 = new Pane();

        // Afegir controls al decorat
        Scene escenari1 = new Scene(decorat1, 1000, 500,Color.AZURE);

        teatre.setTitle("Teatre-Escenari1");
        teatre.setScene(escenari1);
        teatre.show();
    }
}