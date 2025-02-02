package dam.amoreno.m7_a1_amoreno;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.*;


public class Activitat1 extends Application {
    private VBox vboxAfegirVideojoc;
    private VBox vboxLlistatVideojocs;
    private StackPane decorat;

    @Override
    public void start(Stage teatre) {
        // Inicialitzar les dues pantalles
        vboxAfegirVideojoc = entradaVideojoc();
        vboxLlistatVideojocs = llistatVideojocs();

        // Crear un StackPane per a les dues pantalles
        decorat = new StackPane(vboxAfegirVideojoc, vboxLlistatVideojocs);

        // Mostrar la pantalla d'entrada de videojocs
        vboxAfegirVideojoc.setVisible(true);
        vboxLlistatVideojocs.setVisible(false);

        // Configurar escena
        Scene escena = new Scene(decorat, 800, 600, Color.AZURE);

        teatre.setTitle("M7 A1 Aniol Moreno");
        teatre.setScene(escena);
        teatre.show();
    }

    public static void main(String[] args) {
        launch();
    }


    public VBox entradaVideojoc() {
        VBox vboxPrincipal = new VBox(20); // Separació vertical entre elements
        vboxPrincipal.setPadding(new Insets(50));


        // ---- TITOL
        Text titol = new Text("Entrada de Videojocs");
        titol.setFont(new Font("Arial", 40));
        titol.setFill(Color.BLACK);


        // ---- TITOL DEL VIDEOJOC
        HBox hboxNomVideojoc = new HBox(10);
        hboxNomVideojoc.setAlignment(Pos.CENTER);
        Text textEsquerra = new Text("Nom Videojoc:");
        textEsquerra.setFont(new Font("Arial", 18));
        TextField inputText = new TextField();
        hboxNomVideojoc.getChildren().addAll(textEsquerra, inputText);


        // ---- CASELLA DE PLATAFORMA
        HBox hboxPlataforma = new HBox(10);
        hboxPlataforma.setAlignment(Pos.CENTER);
        Text textPlataforma = new Text("Plataforma:");
        textPlataforma.setFont(new Font("Arial", 18));

        RadioButton rbOrdinador = new RadioButton("Ordinador");
        RadioButton rbPlaystation = new RadioButton("PlayStation");
        RadioButton rbXbox = new RadioButton("Xbox");
        ToggleGroup tgPlataforma = new ToggleGroup();
        rbOrdinador.setToggleGroup(tgPlataforma);
        rbPlaystation.setToggleGroup(tgPlataforma);
        rbXbox.setToggleGroup(tgPlataforma);

        hboxPlataforma.getChildren().addAll(textPlataforma, rbOrdinador, rbPlaystation, rbXbox);


        // ---- CASELLA DE CLASSIFICACIÓ
        HBox hboxClassificacio = new HBox(10);
        hboxClassificacio.setAlignment(Pos.CENTER);
        Text textClassificacio = new Text("Classificació:");
        textClassificacio.setFont(new Font("Arial", 18));
        RadioButton rbTotPublic = new RadioButton("Tots els Públics");
        RadioButton rbMajors = new RadioButton("Majors de 18");
        ToggleGroup tgClassificacio = new ToggleGroup();
        rbTotPublic.setToggleGroup(tgClassificacio);
        rbMajors.setToggleGroup(tgClassificacio);

        hboxClassificacio.getChildren().addAll(textClassificacio, rbTotPublic, rbMajors);



        // ---- CASELLA DE PERIFERICS
        HBox hboxPeriferics = new HBox(10);
        hboxPeriferics.setAlignment(Pos.CENTER);
        Text textPeriferics = new Text("Perifèrics:");
        textPeriferics.setFont(new Font("Arial", 18));

        GridPane gridPeriferics = new GridPane();
        gridPeriferics.setHgap(10);
        gridPeriferics.setVgap(10);

        CheckBox cbVr = new CheckBox("Ulleres VR");
        CheckBox cbVolant = new CheckBox("Volant i Pedals");
        CheckBox cbEstora = new CheckBox("Estora Luminica");
        CheckBox cbJoystick = new CheckBox("Joystick Retro");

        gridPeriferics.add(cbVr, 0, 0);
        gridPeriferics.add(cbVolant, 1, 0);
        gridPeriferics.add(cbEstora, 0, 1);
        gridPeriferics.add(cbJoystick, 1, 1);

        hboxPeriferics.getChildren().addAll(textPeriferics, gridPeriferics);


        // ---- CASELLA DE TEMATICA I DATA DE SORTIDA
        HBox hboxTematicaData = new HBox(50);

        // Tematica
        VBox vboxTematica = new VBox(10);
        Text titolTematica = new Text("Temàtica del Joc");
        titolTematica.setFont(new Font("Arial", 18));

        ChoiceBox<String> cbTematica = new ChoiceBox<>();
        cbTematica.getItems().addAll("Aventura", "Carreres", "Esports", "FPS", "Simulació", "Survival", "Terror");

        vboxTematica.getChildren().addAll(titolTematica, cbTematica);


        // Data de sortida
        VBox vboxDataSortida = new VBox(10);
        Text titolDataSortida = new Text("Data de Sortida");
        titolDataSortida.setFont(new Font("Arial", 18));

        DatePicker dpDataSortida = new DatePicker();
        vboxDataSortida.getChildren().addAll(titolDataSortida, dpDataSortida);

        hboxTematicaData.getChildren().addAll(vboxTematica, vboxDataSortida);
        hboxTematicaData.setAlignment(Pos.CENTER);


        // ---- BOTO ENTRAR VIDEOJOC
        Button btnEntrarVideojoc = new Button("Entrar Videojoc");
        btnEntrarVideojoc.setFont(new Font("Arial", 20));


        btnEntrarVideojoc.setOnAction(e -> {
            String fitxer = "videojocs.txt";

            // Comprovar si hi han mes de 10 videojocs
            int numVideojocs = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
                String linia;

                while ((linia = reader.readLine()) != null) {
                    if (linia.startsWith("Nom del Videojoc: ")) numVideojocs++;
                }

            } catch (IOException ex) {
                System.out.println("Error al llegir el fitxer: " + ex.getMessage());
            }

            if (numVideojocs >= 10) {
                showError("Ja has arribat al límit de 10 videojocs!");

                btnEntrarVideojoc.setDisable(true);

                return;
            }


            if (inputText.getText().isEmpty()) {
                showError("El nom del videojoc és obligatori!");
                return;
            }
            if (!(rbOrdinador.isSelected() || rbPlaystation.isSelected() || rbXbox.isSelected())) {
                showError("Heu d'escollir una plataforma!");
                return;
            }
            if (!(rbTotPublic.isSelected() || rbMajors.isSelected())) {
                showError("Heu d'escollir una classificació!");
                return;
            }
            if (cbTematica.getValue() == null) {
                showError("Heu d'escollir una temàtica!");
                return;
            }
            if (dpDataSortida.getValue() == null) {
                showError("Heu d'escollir una data de sortida!");
                return;
            }


            String nomVideojoc = inputText.getText();

            // Comprovar si ja existeix el videojoc
            try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
                String linia;
                while ((linia = reader.readLine()) != null) {
                    if (linia.contains("Nom del Videojoc: " + nomVideojoc)) {
                        showError("Aquest nom de videojoc ja està entrat!");
                        return;
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error al llegir el fitxer: " + ex.getMessage());
            }


            // Guardar les dades
            try {
                String plataforma = "";
                if (rbOrdinador.isSelected()) {
                    plataforma = "Ordinador";
                } else if (rbPlaystation.isSelected()) {
                    plataforma = "PlayStation";
                } else if (rbXbox.isSelected()) {
                    plataforma = "Xbox";
                }

                String classificacio = "";
                if (rbTotPublic.isSelected()) {
                    classificacio = "Tots els Públics";
                } else if (rbMajors.isSelected()) {
                    classificacio = "Majors de 18";
                }

                StringBuilder periferics = new StringBuilder();
                if (cbVr.isSelected()) periferics.append("Ulleres VR, ");
                if (cbVolant.isSelected()) periferics.append("Volant i Pedals, ");
                if (cbEstora.isSelected()) periferics.append("Estora Luminica, ");
                if (cbJoystick.isSelected()) periferics.append("Joystick Retro, ");
                if (!periferics.isEmpty()) {
                    periferics.delete(periferics.length() - 2, periferics.length()); // Eliminar ultima coma
                }

                String tematica = cbTematica.getValue();
                String dataSortida = dpDataSortida.getValue().toString();

                // Guardar en el fitxer
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxer, true))) {
                    writer.write("Nom del Videojoc: " + nomVideojoc + "\n");
                    writer.write("Plataforma: " + plataforma + "\n");
                    writer.write("Classificació: " + classificacio + "\n");
                    writer.write("Perifèrics: " + periferics + "\n");
                    writer.write("Temàtica: " + tematica + "\n");
                    writer.write("Data de Sortida: " + dataSortida + "\n");
                    writer.write("---\n");
                }

                showOk();

                // Restablir els camps
                inputText.clear();
                rbOrdinador.setSelected(false);
                rbPlaystation.setSelected(false);
                rbXbox.setSelected(false);
                rbTotPublic.setSelected(false);
                rbMajors.setSelected(false);
                cbVr.setSelected(false);
                cbVolant.setSelected(false);
                cbEstora.setSelected(false);
                cbJoystick.setSelected(false);
                cbTematica.setValue(null);
                dpDataSortida.setValue(null);

            } catch (IOException ex) {
                System.out.println("Error al guardar el videojoc: " + ex.getMessage());
            }
        });


        // ---- BOTO DE LLISTAR VIDEOJOCS
        Button btnLlistarVideoJocs = new Button("Anar a Llistat de Videojocs");
        btnLlistarVideoJocs.setFont(new Font("Arial", 12));
        btnLlistarVideoJocs.setOnAction(e -> mostrarLlistatVideojocs());

        HBox hboxLlistar = new HBox(btnLlistarVideoJocs);
        hboxLlistar.setAlignment(Pos.BASELINE_RIGHT);

        // Afegir els components al VBox principal
        vboxPrincipal.getChildren().addAll(titol, hboxNomVideojoc, hboxPlataforma, hboxClassificacio, hboxPeriferics, hboxTematicaData, btnEntrarVideojoc, hboxLlistar);
        vboxPrincipal.setAlignment(Pos.CENTER);

        return vboxPrincipal;
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
        alert.setContentText("Videojoc guardat correctament!");
        alert.showAndWait();
    }


    public VBox llistatVideojocs() {
        // Contenidor vertical per tot
        VBox vboxPrincipal = new VBox(20);
        vboxPrincipal.setPadding(new Insets(50));


        // ---- TITOL
        Text titol = new Text("Llistat de Videojocs");
        titol.setFont(new Font("Arial", 40));
        titol.setFill(Color.BLACK);


        // ---- COLUMNES DE LA TAULA
        HBox capcalera = new HBox(20);
        capcalera.setPadding(new Insets(10));
        capcalera.setAlignment(Pos.CENTER);

        String[] columnes = {"Nom", "Plataforma", "Classificació", "Perifèrics", "Temàtica", "Data de Sortida"};

        Label[] labels = new Label[columnes.length];

        for (int i = 0; i < columnes.length; i++) {
            labels[i] = new Label(columnes[i]);
            labels[i].setFont(Font.font("Arial", FontWeight.BOLD, 20));
            labels[i].setPrefWidth(120);
            labels[i].setAlignment(Pos.CENTER);
            capcalera.getChildren().add(labels[i]);
        }


        // ---- CONTINGUT DEL LLISTAT
        VBox llistatContingut = new VBox(10);
        llistatContingut.setPadding(new Insets(10));

        String fitxer = "videojocs.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
            String linia;
            HBox entradaFila = null; // HBox per cada entrada

            while ((linia = reader.readLine()) != null) {
                if (linia.startsWith("Nom del Videojoc:")) {
                    entradaFila = new HBox(20);
                    entradaFila.setAlignment(Pos.CENTER);
                    llistatContingut.getChildren().add(entradaFila); // Afegir entrada
                }

                if (entradaFila != null) {
                    String[] dades = linia.split(": ", 2); // Dividir la linia per agafar el camp necessari

                    if (dades.length > 1) {
                        Label camp = new Label(dades[1]);
                        camp.setFont(new Font("Arial", 16));
                        camp.setPrefWidth(120);
                        camp.setAlignment(Pos.CENTER);
                        entradaFila.getChildren().add(camp);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al llegir el fitxer de videojocs: " + ex.getMessage());
        }


        // ---- BOTO PER TORNAR A ENTRADA DE VIDEOJOCS
        Button btnTornar = new Button("Tornar a Entrada de Videojocs");
        btnTornar.setFont(new Font("Arial", 12));
        btnTornar.setOnAction(e -> {
            mostrarEntradaVideojocs();
        });

        HBox hboxTornar = new HBox(btnTornar);
        hboxTornar.setAlignment(Pos.BASELINE_RIGHT);


        vboxPrincipal.getChildren().addAll(titol, capcalera, llistatContingut, hboxTornar);
        vboxPrincipal.setAlignment(Pos.CENTER);

        return vboxPrincipal;
    }


    private void mostrarLlistatVideojocs() {
        vboxAfegirVideojoc.setVisible(false);
        vboxLlistatVideojocs.setVisible(true);
    }

    private void mostrarEntradaVideojocs() {
        vboxAfegirVideojoc.setVisible(true);
        vboxLlistatVideojocs.setVisible(false);
    }

}
