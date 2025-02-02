package dam.amoreno.canvidecorat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CanviDecoratsMateixEscenari extends Application {


    public static void main(String[] args) {

        launch(args);

    }

    public void canviDecorat(Scene escena, Pane decorat ) {

        escena.setRoot(decorat);

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

        Button BotoCanviDecorat1=new Button("Canvi a Decorat1");

        Button botoCanviDecorat2=new Button("Canvi a Decorat2");

        //----------Decorat 1 ----------------------------

        Pane decorat1=new Pane();

        decorat1.setStyle("-fx-background-color: azure;");

        decorat1.getChildren().add(botoCanviDecorat2);

        decorat1.getChildren().add(quadreTexte1);

        //----------Decorat 2 ----------------------------

        Pane decorat2=new Pane();

        decorat1.setStyle("-fx-background-color: azure;");

        decorat2.getChildren().add(BotoCanviDecorat1);

        decorat2.getChildren().add(etiqueta1);

        //----------Escenari Inicial----------------------------

        Scene escenari=new Scene(decorat1, 1000, 500,Color.RED);

        teatre.setScene(escenari);

        teatre.show();

        //----Control d'Events

        BotoCanviDecorat1.setOnAction((ActionEvent f)->{

            canviDecorat(escenari,decorat1);

        });

        botoCanviDecorat2.setOnAction((ActionEvent f)->{

            canviDecorat(escenari,decorat2);

        });

    }

}