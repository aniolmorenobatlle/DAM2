package dam.amoreno.helloworld;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class prova1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Funcions i Procediments
    static String diaSetmana() {
        String[] auxNomDiaSetmana= {"dilluns","dimarts","dimecres","dijous","divendres","dissabte","diumenge"};
        LocalDate avui = LocalDate.now();
        int numDiaSet = avui.getDayOfWeek().getValue();
        String nomDiaSetmana = auxNomDiaSetmana[numDiaSet-1];
        return nomDiaSetmana;
    }

    static String mes() {
        String[] auxNomMes = {"gener","febrer","marc","abril","maig","juny","juliol","agost","setembre","octubre","novembre","desembre"};
        LocalDate avui = LocalDate.now();
        int numMes = avui.getMonthValue();
        String nomMes = auxNomMes[numMes - 1];
        return nomMes;
    }

    // Part Gràfica
    @Override
    public void start(Stage primaryStage) throws Exception{

        //-------------------------------------------------------
        Label etiqueta1 = new Label("prova");
        etiqueta1.setFont(Font.font("Arial",FontWeight.BOLD, 36));
        etiqueta1.setLayoutX(100);
        etiqueta1.setLayoutY(100);

        //-------------------------------------------------------
        Button boto1 = new Button("Dia de la setmana");
        boto1.setFont(Font.font("Arial",FontWeight.BOLD, 36));
        boto1.setLayoutX(500);
        boto1.setLayoutY(100);

        //-------------------------------------------------------
        Button boto2 = new Button("Mes");
        boto2.setFont(Font.font("Arial",FontWeight.BOLD, 36));
        boto2.setLayoutX(500);
        boto2.setLayoutY(300);

        //-------------------------------------------------------
        Pane contenidor1 = new Pane(etiqueta1,boto1,boto2);
        Scene escena = new Scene(contenidor1, 1000, 500,Color.AZURE);

        primaryStage.setTitle("Stage-Escena1");
        primaryStage.setScene(escena);
        primaryStage.show();

        //----------------------------------------------------------------------------

        // Control d'Events
        boto1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                etiqueta1.setText(diaSetmana());
            }

        });

        boto2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                etiqueta1.setText(mes());
            }
        });
    }
}