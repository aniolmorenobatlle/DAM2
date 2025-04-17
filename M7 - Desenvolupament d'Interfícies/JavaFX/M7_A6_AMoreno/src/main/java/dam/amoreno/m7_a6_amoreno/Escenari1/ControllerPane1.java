package dam.amoreno.m7_a6_amoreno.Escenari1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import dam.amoreno.m7_a6_amoreno.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerPane1 {

    @FXML
    private Button btnComençar;

    @FXML
    private Button btnEntrarEscuderia;

    @FXML
    private Button btnGuardarEscuderia;

    @FXML
    private Button btnEntrarPilot;

    @FXML
    private Button btnGuardarPilot;

    @FXML
    private Button btnEntrarCircuit;

    @FXML
    private Button btnGuardarCircuit;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView imagePreview;

    @FXML
    private ImageView imagePreviewPilot;

    @FXML
    private ImageView imagePreviewCircuit;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;

    @FXML
    private TextField textFieldNomEscuderia;

    @FXML
    private ComboBox<String> comboEscuderies;

    @FXML
    private TextField textFieldDorsal;

    @FXML
    private TextField textFieldNomPilot;

    @FXML
    private TextField textFieldNomCircuit;

    @FXML
    private DatePicker datePickerDataCircuit;

    private File imatgeSeleccionadaCotxe;
    private File imatgeSeleccionadaPilot;
    private File imatgeSeleccionadaCircuit;

    @FXML
    public void initialize() {
        carregarEscuderies();
    }

    // ---------------------------- Escuderia
    @FXML
    private void entrarEscuderia(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imatge");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imatges", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imagePreview.setImage(image);
            imatgeSeleccionadaCotxe = file;
        }
    }

    @FXML
    private void guardarEscuderia(ActionEvent event) {
        String nomEscuderia = textFieldNomEscuderia.getText();

        if (nomEscuderia.isEmpty()) {
            alerts("WARNING", "Error", "Has d'introduir un nom d'escuderia.");
            return;
        }

        if (imatgeSeleccionadaCotxe == null) {
            alerts("WARNING", "Error", "Has de seleccionar una imatge.");
            return;
        }

        try {
            File ruta = new File("src/main/resources/dam/amoreno/m7_a6_amoreno/images/cotxes/");
            if (!ruta.exists()) {
                ruta.mkdirs();
            }

            String nomEscuderiaFitxer = nomEscuderia.replaceAll("\\s", "");
            File desti = new File(ruta, nomEscuderiaFitxer + ".jpg");

            Files.copy(imatgeSeleccionadaCotxe.toPath(), desti.toPath(), StandardCopyOption.REPLACE_EXISTING);

            File escuderiesFile = new File("escuderies.txt");
            if (!escuderiesFile.exists()) {
                escuderiesFile.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(escuderiesFile, true))) {
                writer.write(nomEscuderia + ", " + nomEscuderiaFitxer + ".jpg");
                writer.newLine();
            }

            alerts("INFORMATION", "Èxit", "Imatge guardada correctament i escuderia afegida al fitxer.");

            textFieldNomEscuderia.clear();
            imagePreview.setImage(null);
            imatgeSeleccionadaCotxe = null;

            comboEscuderies.getItems().add(nomEscuderia);
        } catch (Exception e) {
            e.printStackTrace();
            alerts("ERROR", "Error", "No s'ha pogut guardar la imatge.");
        }
    }

    private void carregarEscuderies() {
        File escuderiesFile = new File("escuderies.txt");

        if (escuderiesFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(escuderiesFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(", ");
                    if (parts.length == 2) {
                        String nomEscuderia = parts[0].trim();
                        comboEscuderies.getItems().add(nomEscuderia);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                alerts("ERROR", "Error", "No s'ha pogut llegir el fitxer d'escuderies.");
            }
        }
    }

    // ---------------------------- Pilot
    @FXML
    private void entrarFotoPilot(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imatge");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imatges", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imagePreviewPilot.setImage(image);
            imatgeSeleccionadaPilot = file;
        }
    }

    @FXML
    private void guardarPilot(ActionEvent event) {
        String nomPilot = textFieldNomPilot.getText();
        String dorsal = textFieldDorsal.getText();
        String escuderia = comboEscuderies.getValue();

        if (nomPilot.isEmpty() || dorsal.isEmpty()) {
            alerts("WARNING", "Error", "Has d'introduir un nom i un dorsal.");
            return;
        }

        if (imatgeSeleccionadaPilot == null) {
            alerts("WARNING", "Error", "Has de seleccionar una imatge.");
            return;
        }

        try {
            File ruta = new File("src/main/resources/dam/amoreno/m7_a6_amoreno/images/pilots/");
            if (!ruta.exists()) {
                ruta.mkdirs();
            }

            String nomPilotFitxer = nomPilot.replaceAll("\\s", "");
            File desti = new File(ruta, nomPilotFitxer + ".jpg");

            Files.copy(imatgeSeleccionadaPilot.toPath(), desti.toPath(), StandardCopyOption.REPLACE_EXISTING);

            File pilotsFile = new File("pilots.txt");
            if (!pilotsFile.exists()) {
                pilotsFile.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pilotsFile, true))) {
                writer.write(nomPilot + ", " + dorsal + ", " + escuderia + ", " + nomPilot + ".jpg");
                writer.newLine();
            }

            alerts("INFORMATION", "Èxit", "Imatge guardada correctament.");

            textFieldNomPilot.clear();
            textFieldDorsal.clear();
            comboEscuderies.getSelectionModel().clearSelection();
            imagePreviewPilot.setImage(null);
            imatgeSeleccionadaPilot = null;

        } catch (Exception e) {
            e.printStackTrace();
            alerts("ERROR", "Error", "No s'ha pogut guardar la imatge.");
        }
    }

    // ---------------------------- Circuit
    @FXML
    private void entrarCircuit(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imatge");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imatges", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imagePreviewCircuit.setImage(image);
            imatgeSeleccionadaCircuit = file;
        }
    }

    @FXML
    private void guardarCircuit(ActionEvent event) {
        String nomCircuit = textFieldNomCircuit.getText();
        String dataCircuit = datePickerDataCircuit.getValue() != null ? datePickerDataCircuit.getValue().toString()
                : null;

        if (nomCircuit.isEmpty()) {
            alerts("WARNING", "Error", "Has d'introduir un nom de circuit.");
            return;
        }

        if (imatgeSeleccionadaCircuit == null) {
            alerts("WARNING", "Error", "Has de seleccionar una imatge.");
            return;
        }

        try {
            File ruta = new File("src/main/resources/dam/amoreno/m7_a6_amoreno/images/circuit/");
            if (!ruta.exists()) {
                ruta.mkdirs();
            }

            String nomCircuitFitxer = nomCircuit.replaceAll("\\s", "");
            File desti = new File(ruta, nomCircuitFitxer + ".jpg");

            Files.copy(imatgeSeleccionadaCircuit.toPath(), desti.toPath(), StandardCopyOption.REPLACE_EXISTING);

            File circuitsFile = new File("circuits.txt");
            if (!circuitsFile.exists()) {
                circuitsFile.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(circuitsFile, true))) {
                writer.write(nomCircuit + ", " + dataCircuit + ", " + nomCircuitFitxer + ".jpg");
                writer.newLine();
            }

            alerts("INFORMATION", "Exit", "Imatge guardada correctament.");

            textFieldNomCircuit.clear();
            imagePreviewCircuit.setImage(null);
            imatgeSeleccionadaCircuit = null;

        } catch (Exception e) {
            e.printStackTrace();
            alerts("ERROR", "Error", "No s'ha pogut guardar la imatge.");
        }
    }

    // ---------------------------- Començar
    @FXML
    private void comencar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Escenari2.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    // ---------------------------- Global
    void alerts(String type, String title, String context) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(context);
        alert.showAndWait();
    }
}