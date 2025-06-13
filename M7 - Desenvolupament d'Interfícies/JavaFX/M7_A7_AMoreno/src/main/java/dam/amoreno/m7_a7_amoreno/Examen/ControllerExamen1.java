package dam.amoreno.m7_a7_amoreno.Examen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import dam.amoreno.m7_a7_amoreno.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerExamen1 {

    @FXML
    private ChoiceBox<String> desplegable1;

    @FXML
    private ImageView imageView1;

    @FXML
    private Button btnImageView;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnExamen;

    @FXML
    private Pane pane1;

    @FXML
    private TextField textFieldDNI;

    @FXML
    private TextField textFieldNom;

    private String rutaFitxer = "usuari.txt";
    private String rutaImatge = "usuari/";
    private File imatgeViewSeleccionada;

    @FXML
    private void initialize() {
        opcionsDesplegable();
    }

    @FXML
    private void opcionsDesplegable() {
        desplegable1.getItems().add("Examen moto");
        desplegable1.getItems().add("Examen cotxe");
    }

    @FXML
    private void seleccionarImatge() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imatge");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imatges", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView1.setImage(image);
            imatgeViewSeleccionada = file;
        }
    }

    @FXML
    private boolean guardarUsuari() {
        String tipus = desplegable1.getValue();
        String dni = textFieldDNI.getText();
        String nom = textFieldNom.getText();

        if (tipus == null || tipus.isEmpty()) {
            mostraError("Si us plau, selecciona un tipus d'examen.");
            return false;
        }

        if (dni == null || dni.isEmpty()) {
            mostraError("Si us plau, introdueix el DNI.");
            return false;
        }

        if (nom == null || nom.isEmpty()) {
            mostraError("Si us plau, introdueix el nom.");
            return false;
        }

        if (imatgeViewSeleccionada == null) {
            mostraError("Si us plau, selecciona una imatge del DNI.");
            return false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("tipus: ").append(tipus).append("\n");
        sb.append("dni: ").append(dni).append("\n");
        sb.append("nom: ").append(nom).append("\n");

        try {
            File ruta = new File(rutaImatge);
            if (!ruta.exists()) {
                ruta.mkdirs();
            }

            File fitxerImatge = new File(rutaImatge, imatgeViewSeleccionada.getName());
            if (!fitxerImatge.exists()) {
                try {
                    Files.copy(imatgeViewSeleccionada.toPath(), fitxerImatge.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    mostraError("No s'ha pogut copiar la imatge.");
                    e.printStackTrace();
                }
            }

            sb.append("imatge: ").append(imatgeViewSeleccionada.getName()).append("\n");
        } catch (Exception e) {
            mostraError("No s'ha pogut guardar la imatge.");
            e.printStackTrace();
            return false;
        }

        try (FileWriter writer = new FileWriter(rutaFitxer)) {
            writer.write(sb.toString());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informació");
            alert.setContentText("La pregunta s'ha guardat correctament.");
            alert.showAndWait();

            desplegable1.setValue(null);
            textFieldDNI.clear();
            textFieldNom.clear();
            imageView1.setImage(null);

            imatgeViewSeleccionada = null;

            return true;
        } catch (IOException e) {
            mostraError("No s'ha pogut guardar l'usuari.");
            e.printStackTrace();
            return false;
        }
    }

    private void mostraError(String missatge) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(missatge);
        alert.showAndWait();
    }

    @FXML
    void comencarExamen(ActionEvent event) throws IOException {
        if (!guardarUsuari()) {
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Examen2.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

}
