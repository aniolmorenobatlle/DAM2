package dam.amoreno.m7_a2_amoreno;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Activitat2 extends Application {

    // Variable global per guardar els vehicles
    ArrayList<RegistreVehicle> vehicles = new ArrayList<>();


    // Metode per canviar d'escena
    public void canviEscenari(Stage teatre, Scene escena) {
        teatre.setScene(escena);
        teatre.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage teatre) throws Exception {
        // Afegir vehicles prova
        vehicles.add(new RegistreVehicle("Ford", "Focus", 20000));
        vehicles.add(new RegistreVehicle("Audi", "A3", 25000));
        vehicles.add(new RegistreVehicle("Audi", "A1", 15000));
        vehicles.add(new RegistreVehicle("BMW", "X1", 30000));
        vehicles.add(new RegistreVehicle("Mercedes", "GLC", 35000));


        // ---------- Escena 1 ----------------------------
        Button botoCanviEscena1 = new Button("Escena 2");
        HBox botons = new HBox();

        StackPane escena1 = new StackPane();
        StackPane.setAlignment(botons, Pos.TOP_LEFT);

        // ----------- General
        VBox vboxPrimera = new VBox(30);
        vboxPrimera.setAlignment(Pos.CENTER);


        // ---------- Marca i Model
        HBox hboxMarcaModel = new HBox(50);
        hboxMarcaModel.setAlignment(Pos.CENTER);

        // Marca
        HBox hboxMarca = new HBox(20);
        hboxMarca.setAlignment(Pos.CENTER);
        Text marca = new Text("Marca");
        marca.setUnderline(true);
        marca.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        ChoiceBox<String> marques = new ChoiceBox<>();
        marques.setPrefWidth(100);

        vehicles.forEach(vehicle -> {
            if (!marques.getItems().contains(vehicle.getMarca())) {
                marques.getItems().add(vehicle.getMarca());
            }
        });

        hboxMarca.getChildren().addAll(marca, marques);

        // Model
        HBox hboxModel = new HBox(20);
        hboxModel.setAlignment(Pos.CENTER);
        Text model = new Text("Model");
        model.setUnderline(true);
        model.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        ChoiceBox<String> models = new ChoiceBox<>();
        models.setPrefWidth(100);

        marques.setOnAction((ActionEvent _) -> {
            models.getItems().clear();
            vehicles.forEach(vehicle -> {
                if (vehicle.getMarca().equals(marques.getValue())) {
                    models.getItems().add(vehicle.getModel());
                }
            });
        });

        hboxModel.getChildren().addAll(model, models);

        hboxMarcaModel.getChildren().addAll(hboxMarca, hboxModel);


        // ---------- Imatge i Preu
        VBox vboxImatgePreu = new VBox(20);
        vboxImatgePreu.setAlignment(Pos.CENTER);

        // Imatge
        Image cotxe = new Image(new FileInputStream("imatges/Ford.Focus.jpg"));
        ImageView imatgeCotxe = new ImageView(cotxe);
        imatgeCotxe.setFitHeight(200);
        imatgeCotxe.setFitWidth(300);

        // Preu
        HBox hboxPreu = new HBox(20);
        hboxPreu.setAlignment(Pos.CENTER);
        Text preuTitol = new Text("Preu");
        preuTitol.setUnderline(true);
        preuTitol.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        Text preu = new Text();

        models.setOnAction((ActionEvent _) -> {
            vehicles.forEach(vehicle -> {
                if (vehicle.getMarca().equals(marques.getValue()) && vehicle.getModel().equals(models.getValue())) {
                    preu.setText(vehicle.getPreu() + " €");
                }
            });
        });

        preu.setStyle("-fx-font-size: 20;");

        hboxPreu.getChildren().addAll(preuTitol, preu);

        vboxImatgePreu.getChildren().addAll(imatgeCotxe, hboxPreu);


        vboxPrimera.getChildren().addAll(hboxMarcaModel, vboxImatgePreu);

        botons.getChildren().addAll(botoCanviEscena1, vboxPrimera);

        escena1.getChildren().addAll(botons);
        escena1.setStyle("-fx-background-color: azure;");
        Scene escenari1 = new Scene(escena1, 560, 500);



        // ---------- Escena 2 ----------------------------
        Button botoCanviEscena2 = new Button("Escena 3");
        HBox botons2 = new HBox();

        StackPane escena2 = new StackPane();
        StackPane.setAlignment(botons2, Pos.TOP_LEFT);

        // ----------- General
        VBox vboxSegona = new VBox(30);
        vboxSegona.setAlignment(Pos.CENTER);


        // ---------- Marca Model Preu i Imatge
        HBox hboxMarcaModelPreuImatge = new HBox(50);
        hboxMarcaModelPreuImatge.setAlignment(Pos.CENTER);


        // ---------- Marca Model i Preu
        VBox vboxMarcaModelPreu = new VBox(30);
        vboxMarcaModelPreu.setAlignment(Pos.CENTER);

        // Marca
        HBox hboxMarca2 = new HBox(20);
        Text marcaTitol2 = new Text("Marca");
        marcaTitol2.setUnderline(true);
        marcaTitol2.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Text marca2 = new Text("Ford");
        marca2.setStyle("-fx-font-size: 20;");

        hboxMarca2.getChildren().addAll(marcaTitol2, marca2);


        // Model
        HBox hboxModel2 = new HBox(20);
        Text modelTitol2 = new Text("Model");
        modelTitol2.setUnderline(true);
        modelTitol2.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Text model2 = new Text("Focus");
        model2.setStyle("-fx-font-size: 20;");

        hboxModel2.getChildren().addAll(modelTitol2, model2);


        // Preu
        HBox hboxPreu2 = new HBox(20);
        Text preuTitol2 = new Text("Preu");
        preuTitol2.setUnderline(true);
        preuTitol2.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Text preu2 = new Text("20.000 €");
        preu2.setStyle("-fx-font-size: 20;");

        hboxPreu2.getChildren().addAll(preuTitol2, preu2);

        vboxMarcaModelPreu.getChildren().addAll(hboxMarca2, hboxModel2, hboxPreu2);


        // Imatge
        Image cotxe2 = new Image(new FileInputStream("imatges/Ford.Focus.jpg"));
        ImageView imatgeCotxe2 = new ImageView(cotxe2);
        imatgeCotxe2.setFitHeight(200);
        imatgeCotxe2.setFitWidth(300);

        hboxMarcaModelPreuImatge.getChildren().addAll(vboxMarcaModelPreu, imatgeCotxe2);


        // ---------- Barra desplaçament i Index
        VBox vboxBarraIndex = new VBox(30);
        vboxBarraIndex.setAlignment(Pos.CENTER);

        // Barra desplaçament
        Slider barraDesplacament = new Slider(0, 100, 50);
        barraDesplacament.setPrefWidth(100);
        barraDesplacament.setOrientation(Orientation.HORIZONTAL);
        int valor = (int) barraDesplacament.getValue();

        // Index
        Text index = new Text("Cotxe " + valor);

        vboxBarraIndex.getChildren().addAll(barraDesplacament, index);


        vboxSegona.getChildren().addAll(hboxMarcaModelPreuImatge, vboxBarraIndex);

        botons2.getChildren().addAll(botoCanviEscena2, vboxSegona);

        escena2.getChildren().addAll(botons2);
        escena2.setStyle("-fx-background-color: lightblue;");
        Scene escenari2 = new Scene(escena2, 640, 500);



        // ---------- Escena 3 ----------------------------
        Button botoCanviEscena3 = new Button("Escena 4");
        HBox botons3 = new HBox();

        StackPane escena3 = new StackPane();
        StackPane.setAlignment(botons3, Pos.TOP_LEFT);


        // ----------- General
        VBox vboxTercera = new VBox(20);
        vboxTercera.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Titols
        Text marcaTitol3 = new Text("Marca");
        Text modelTitol3 = new Text("Model");
        Text preuTitol3 = new Text("Preu");
        Text imatgeTitol3 = new Text("Imatge");

        Arrays.asList(marcaTitol3, modelTitol3, preuTitol3, imatgeTitol3).forEach(text -> {
            text.setUnderline(true);
            text.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        });

        gridPane.add(marcaTitol3, 0, 0);
        gridPane.add(modelTitol3, 1, 0);
        gridPane.add(preuTitol3, 2, 0);
        gridPane.add(imatgeTitol3, 3, 0);

        // Llistar vehicles
        int fila = 1; // Començar des de la fila 1 per després dels títols
        for (RegistreVehicle vehicle : vehicles) {
            Text marca3 = new Text(vehicle.getMarca());
            Text model3 = new Text(vehicle.getModel());
            Text preu3 = new Text(vehicle.getPreu() + " €");

            Arrays.asList(marca3, model3, preu3).forEach(text -> text.setStyle("-fx-font-size: 20;"));

            gridPane.add(marca3, 0, fila);
            gridPane.add(model3, 1, fila);
            gridPane.add(preu3, 2, fila);

            Image cotxe3 = new Image(new FileInputStream("imatges/" + vehicle.getMarca() + "." + vehicle.getModel() + ".jpg"));
            ImageView imatgeCotxe3 = new ImageView(cotxe3);
            imatgeCotxe3.setFitHeight(60);
            imatgeCotxe3.setFitWidth(100);

            gridPane.add(imatgeCotxe3, 3, fila);

            fila++;
        }


        // Navegacio
        HBox hboxNavegacio = new HBox(30);
        hboxNavegacio.setAlignment(Pos.CENTER);

        Button botoPrimeraPagina = new Button("<<");
        Button botoPaginaAnterior = new Button("<");
        Button botoPaginaSeguent = new Button(">");
        Button botoUltimaPagina = new Button(">>");

        Arrays.asList(botoPrimeraPagina, botoPaginaAnterior, botoPaginaSeguent, botoUltimaPagina).forEach(boto -> boto.setStyle("-fx-font-size: 20;"));

        Text textPagina = new Text("Plana 2 de 4");
        textPagina.setStyle("-fx-font-size: 20;");

        hboxNavegacio.getChildren().addAll(botoPrimeraPagina, botoPaginaAnterior, botoPaginaSeguent, botoUltimaPagina, textPagina);


        vboxTercera.getChildren().addAll(gridPane, hboxNavegacio);

        botons3.getChildren().addAll(botoCanviEscena3, vboxTercera);


        escena3.getChildren().addAll(botons3);
        escena3.setStyle("-fx-background-color: lightgreen;");
        Scene escenari3 = new Scene(escena3, 600, 700);



        // ---------- Escena 4 ----------------------------
        Button botoCanviEscena4 = new Button("Escena 1");
        HBox botons4 = new HBox();

        StackPane escena4 = new StackPane();
        StackPane.setAlignment(botons4, Pos.TOP_LEFT);


        // ----------- General
        VBox vboxQuarta = new VBox(20);
        vboxQuarta.setAlignment(Pos.CENTER);

        // Superior
        HBox hboxSuperior = new HBox(50);

        // Dades
        HBox hboxDades = new HBox(20);

        // Titols
        VBox vboxTitol = new VBox(20);

        Text marcaTitol4 = new Text("Marca");
        Text modelTitol4 = new Text("Model");
        Text preuTitol4 = new Text("Preu");

        Arrays.asList(marcaTitol4, modelTitol4, preuTitol4).forEach(text -> {
            text.setUnderline(true);
            text.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        });

        vboxTitol.getChildren().addAll(marcaTitol4, modelTitol4, preuTitol4);


        // TextFields
        VBox vboxTextFields = new VBox(20);

        TextField marcaResultat = new TextField();

        TextField modelResultat = new TextField();

        TextField preuResultat = new TextField();

        Arrays.asList(marcaResultat, modelResultat, preuResultat).forEach(textField -> {
            textField.setPrefWidth(200);
            textField.setStyle("-fx-font-size: 14;");
        });

        vboxTextFields.getChildren().addAll(marcaResultat, modelResultat, preuResultat);

        hboxDades.getChildren().addAll(vboxTitol, vboxTextFields);


        // Imatge
        VBox vboxImatge = new VBox(20);

        Text imatgeTitol4 = new Text("Imatge");
        imatgeTitol4.setUnderline(true);
        imatgeTitol4.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Image cotxe4 = new Image(new FileInputStream("imatges/Ford.Focus.jpg"));
        ImageView imatgeCotxe4 = new ImageView(cotxe4);
        imatgeCotxe4.setFitHeight(120);
        imatgeCotxe4.setFitWidth(200);

        Button afegirImatge = new Button("Afegir Imatge");
        afegirImatge.setStyle("-fx-font-size: 15;");

        vboxImatge.getChildren().addAll(imatgeTitol4, imatgeCotxe4, afegirImatge);

        hboxSuperior.getChildren().addAll(hboxDades, vboxImatge);


        // Afegir
        Button afegirCotxe = new Button("Afegir Cotxe al catàleg");
        afegirCotxe.setStyle("-fx-font-size: 20;");
        afegirCotxe.setAlignment(Pos.CENTER);


        vboxQuarta.getChildren().addAll(hboxSuperior, afegirCotxe);

        botons4.getChildren().addAll(botoCanviEscena4, vboxQuarta);

        escena4.getChildren().addAll(botons4);
        escena4.setStyle("-fx-background-color: lightgreen;");
        Scene escenari4 = new Scene(escena4, 680, 500);



        // ---------- Control d'Events ----------------------------
        botoCanviEscena1.setOnAction((ActionEvent _) -> canviEscenari(teatre, escenari2));
        botoCanviEscena2.setOnAction((ActionEvent _) -> canviEscenari(teatre, escenari3));
        botoCanviEscena3.setOnAction((ActionEvent _) -> canviEscenari(teatre, escenari4));
        botoCanviEscena4.setOnAction((ActionEvent _) -> canviEscenari(teatre, escenari1));



        // ---------- Escenari Inicial ----------------------------
        teatre.setScene(escenari4);
        teatre.show();
    }
}
