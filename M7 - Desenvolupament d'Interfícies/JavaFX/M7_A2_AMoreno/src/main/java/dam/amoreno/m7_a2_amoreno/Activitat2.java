package dam.amoreno.m7_a2_amoreno;

import javafx.application.Application;
import javafx.css.Match;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activitat2 extends Application {
    private int paginaActual = 0;
    private static final int ELEMENTS_PER_PAGINA = 4;
    private Text textPagina;
    private GridPane gridPane;

    private List<RegistreVehicle> vehicles;


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
        vehicles = new ArrayList<>();
        vehicles.add(new RegistreVehicle("Ford", "Focus", "20000"));
        vehicles.add(new RegistreVehicle("Audi", "A3", "25000"));
        vehicles.add(new RegistreVehicle("Audi", "A1", "15000"));
        vehicles.add(new RegistreVehicle("BMW", "X1", "30000"));
        vehicles.add(new RegistreVehicle("Mercedes", "GLC", "35000"));
        vehicles.add(new RegistreVehicle("Toyota", "Yaris", "20000"));
        vehicles.add(new RegistreVehicle("Mercedes", "ClasseA", "60000"));
        vehicles.add(new RegistreVehicle("Ferrari", "F40", "2000000"));


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

        models.setOnAction((ActionEvent _) -> vehicles.forEach(vehicle -> {
            if (vehicle.getMarca().equals(marques.getValue()) && vehicle.getModel().equals(models.getValue())) {
                preu.setText(vehicle.getPreu() + " €");

                File fitxerImatge = new File("imatges/" + vehicle.getMarca() + "." + vehicle.getModel() + ".jpg");
                if (fitxerImatge.exists()) {
                    imatgeCotxe.setImage(new Image(fitxerImatge.toURI().toString()));
                } else {
                    System.out.println("No s'ha trobat la imatge: " + fitxerImatge.getPath());
                }
            }
        }));

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
        imatgeCotxe2.setPreserveRatio(true);
        imatgeCotxe2.setSmooth(true);
        imatgeCotxe2.setFitHeight(200);
        imatgeCotxe2.setFitWidth(300);

        hboxMarcaModelPreuImatge.getChildren().addAll(vboxMarcaModelPreu, imatgeCotxe2);


        // ---------- Barra desplaçament i Index
        VBox vboxBarraIndex = new VBox(30);
        vboxBarraIndex.setAlignment(Pos.CENTER);

        // Barra desplaçament
        ScrollBar barraDesplacament = new ScrollBar();
        barraDesplacament.setMin(0);
        barraDesplacament.setMax(vehicles.size() - 1);
        barraDesplacament.setValue(0);
        barraDesplacament.setUnitIncrement(1);
        barraDesplacament.setBlockIncrement(1);
        barraDesplacament.setOrientation(Orientation.HORIZONTAL);
        barraDesplacament.setPrefWidth(300);

        // Index
        Text index = new Text();

        // Canviar el cotxe quan es mou la barra
        barraDesplacament.valueProperty().addListener((_, _, newValue) -> {
            int valor = newValue.intValue();
            mostrarCotxe(valor, marca2, model2, preu2, imatgeCotxe2, index);
        });

        // Cotxe per defecte
        mostrarCotxe(0, marca2, model2, preu2, imatgeCotxe2, index);

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

        gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Navegacio
        HBox hboxNavegacio = new HBox(30);
        hboxNavegacio.setAlignment(Pos.CENTER);
        Button botoPrimeraPagina = new Button("<<");
        Button botoPaginaAnterior = new Button("<");
        Button botoPaginaSeguent = new Button(">");
        Button botoUltimaPagina = new Button(">>");
        Arrays.asList(botoPrimeraPagina, botoPaginaAnterior, botoPaginaSeguent, botoUltimaPagina).forEach(boto -> boto.setStyle("-fx-font-size: 20;"));

        textPagina = new Text();
        textPagina.setStyle("-fx-font-size: 20;");

        hboxNavegacio.getChildren().addAll(botoPrimeraPagina, botoPaginaAnterior, botoPaginaSeguent, botoUltimaPagina, textPagina);

        // Funcionalitats dels botons
        botoPrimeraPagina.setOnAction(_ -> canviarPagina(0));
        botoPaginaAnterior.setOnAction(_ -> canviarPagina(paginaActual - 1));
        botoPaginaSeguent.setOnAction(_ -> canviarPagina(paginaActual + 1));
        botoUltimaPagina.setOnAction(_ -> canviarPagina((vehicles.size() - 1) / ELEMENTS_PER_PAGINA));

        vboxTercera.getChildren().addAll(gridPane, hboxNavegacio);
        botons3.getChildren().addAll(botoCanviEscena3, vboxTercera);

        escena3.getChildren().addAll(botons3);
        escena3.setStyle("-fx-background-color: lightgreen;");
        Scene escenari3 = new Scene(escena3, 600, 700);

        canviarPagina(0);


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

        Image cotxe4 = new Image(new FileInputStream("imatges/Default.jpg"));
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

        afegirImatge.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecciona una imatge");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imatges JPG", "*.jpg"));

            File fitxerImatge = fileChooser.showOpenDialog(teatre);
            if (fitxerImatge == null) return;

            try {
                // Comprovar que els camps no estiguin buits
                String marcaCotxe = marcaResultat.getText().trim();
                String modelCotxe = modelResultat.getText().trim();

                if (marcaCotxe.isEmpty() || modelCotxe.isEmpty()) {
                    showError("Has d'introduir marca i model abans d'afegir la imatge!");
                    return;
                }

                Image imatge = guardarImatge(marcaCotxe, modelCotxe, fitxerImatge);
                imatgeCotxe4.setImage(imatge);


            } catch (Exception ex) {
                showError("Error carregant la imatge: " + ex.getMessage());
            }

        });

        afegirCotxe.setOnAction(_ -> {
            String marcaCotxe = marcaResultat.getText().trim();
            String modelCotxe = modelResultat.getText().trim();
            String preuCotxe = preuResultat.getText().trim();

            System.out.println(marcaCotxe);

            if (marcaCotxe.isEmpty() || modelCotxe.isEmpty() || preuCotxe.isEmpty()) {
                showError("Has d'omplir tots els camps!");
                return;
            }

            vehicles.add(new RegistreVehicle(marcaCotxe, modelCotxe, preuCotxe));

            showOk();

            marcaResultat.clear();
            modelResultat.clear();
            preuResultat.clear();
            imatgeCotxe4.setImage(new Image("imatges/Default.jpg"));
        });


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
        teatre.setScene(escenari1);
        teatre.show();
    }

    private void mostrarCotxe(int index, Text marca, Text model, Text preu, ImageView imatge, Text indexText) {
        RegistreVehicle vehicle = vehicles.get(index);
        marca.setText(vehicle.getMarca());
        model.setText(vehicle.getModel());
        preu.setText(vehicle.getPreu() + " €");
        indexText.setText("Cotxe " + (index + 1) + " de " + vehicles.size());

        try {
            Image cotxe = new Image(new FileInputStream("imatges/" + vehicle.getMarca() + "." + vehicle.getModel() + ".jpg"));
            imatge.setImage(cotxe);
        } catch (Exception e) {
            System.out.println("Error carregant la imatge de " + vehicle.getMarca() + " " + vehicle.getModel());
        }
    }

    private static Image guardarImatge(String marcaCotxe, String modelCotxe, File fitxerImatge) throws IOException {
        File carpetaImatges = new File("imatges");

        // Generar el nom del fitxer
        String nomFitxer = marcaCotxe + "." + modelCotxe + ".jpg";
        File desti = new File(carpetaImatges, nomFitxer);

        // Copiar la imatge a la carpeta imatges/
        try (InputStream in = new FileInputStream(fitxerImatge);
             OutputStream out = new FileOutputStream(desti)) {

            byte[] buffer = new byte[1024];
            int llargada;
            while ((llargada = in.read(buffer)) > 0) {
                out.write(buffer, 0, llargada);
            }
        }

        // Mostrar la imatge en el ImageView
        return new Image(desti.toURI().toString());
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showOk() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("OK");
        alert.setHeaderText(null);
        alert.setContentText("Cotxe guardat correctament!");
        alert.showAndWait();
    }

    private void canviarPagina(int novaPagina) {
        int totalPagines = (int) Math.ceil((double) vehicles.size() / ELEMENTS_PER_PAGINA);
        if (novaPagina < 0 || novaPagina >= totalPagines) return;

        paginaActual = novaPagina;

        gridPane.getChildren().clear();

        // Titols
        Text marcaTitol = new Text("Marca");
        Text modelTitol = new Text("Model");
        Text preuTitol = new Text("Preu");
        Text imatgeTitol = new Text("Imatge");

        Arrays.asList(marcaTitol, modelTitol, preuTitol, imatgeTitol).forEach(text -> {
            text.setUnderline(true);
            text.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        });

        gridPane.add(marcaTitol, 0, 0);
        gridPane.add(modelTitol, 1, 0);
        gridPane.add(preuTitol, 2, 0);
        gridPane.add(imatgeTitol, 3, 0);

        // Llistar vehicles
        int fila = 1;
        int inici = paginaActual * ELEMENTS_PER_PAGINA;
        int ultima = Math.min(inici + ELEMENTS_PER_PAGINA, vehicles.size());

        for (int i = inici; i < ultima; i++) {
            RegistreVehicle vehicle = vehicles.get(i);

            Text marca = new Text(vehicle.getMarca());
            Text model = new Text(vehicle.getModel());
            Text preu = new Text(vehicle.getPreu() + " €");

            Arrays.asList(marca, model, preu).forEach(text -> text.setStyle("-fx-font-size: 20;"));

            gridPane.add(marca, 0, fila);
            gridPane.add(model, 1, fila);
            gridPane.add(preu, 2, fila);

            try {
                Image cotxe = new Image(new FileInputStream("imatges/" + vehicle.getMarca() + "." + vehicle.getModel() + ".jpg"));
                ImageView imatgeCotxe = new ImageView(cotxe);
                imatgeCotxe.setFitWidth(150);
                imatgeCotxe.setFitHeight(100);
                gridPane.add(imatgeCotxe, 3, fila);
            } catch (Exception e) {
                System.out.println("Error carregant la imatge de " + vehicle.getMarca() + " " + vehicle.getModel());
            }

            fila++;
        }

        // Actualitzar el text de la pàgina
        textPagina.setText("Plana " + (paginaActual + 1) + " de " + totalPagines);
    }

}
