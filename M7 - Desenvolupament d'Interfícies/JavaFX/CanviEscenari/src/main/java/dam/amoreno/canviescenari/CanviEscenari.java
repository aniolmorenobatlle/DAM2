package dam.amoreno.canviescenari;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CanviEscenari extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    public void canviEscenari(Stage teatre, Scene escena) {

        teatre.setScene(escena);

        teatre.show();

    }

    //---------------------------------------------------------------------------------------

    @Override
    public void start(Stage teatre) throws Exception{

        //---------Creació dels controls individuals-------------------------------------------------------

        TextField quadreTexte1=new TextField();

        quadreTexte1.setLayoutX(200);

        quadreTexte1.setLayoutY(300);

        Label etiqueta1=new Label("Valor inicial");

        etiqueta1.setLayoutX(200);

        etiqueta1.setLayoutY(300);

        Button BotoCanviScene1=new Button("Canvi a Escena1");

        Button botoCanviScene2=new Button("Canvi a Escena2");

        //----------Escena 1 ----------------------------

        Pane decorat1=new Pane();

        decorat1.setStyle("-fx-background-color: red;");

        decorat1.getChildren().add(botoCanviScene2);

        decorat1.getChildren().add(quadreTexte1);

        Scene escenari1=new Scene(decorat1, 1000, 500,Color.RED);

        //----------Escena 2 ----------------------------

        Pane decorat2=new Pane();

        decorat1.setStyle("-fx-background-color: blue;");

        decorat2.getChildren().add(BotoCanviScene1);

        decorat2.getChildren().add(etiqueta1);

        Scene escenari2=new Scene(decorat2, 200, 300,Color.BLUE);

        //----------Escenari Inicial----------------------------

        teatre.setScene(escenari1);

        teatre.show();

        //----Control d'Events

        BotoCanviScene1.setOnAction((ActionEvent f)->{

            canviEscenari(teatre,escenari1);

        });

        botoCanviScene2.setOnAction((ActionEvent f)->{

            canviEscenari(teatre,escenari2);

        });

    }

}