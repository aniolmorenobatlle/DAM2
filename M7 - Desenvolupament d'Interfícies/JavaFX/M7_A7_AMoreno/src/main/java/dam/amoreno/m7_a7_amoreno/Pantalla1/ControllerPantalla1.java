package dam.amoreno.m7_a7_amoreno.Pantalla1;

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

public class ControllerPantalla1 {

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
    private RadioButton radioBtnResposta1;

    @FXML
    private RadioButton radioBtnResposta2;

    @FXML
    private RadioButton radioBtnResposta3;

    @FXML
    private TextField textFieldPregunta;

    @FXML
    private TextField textFieldResposta1;

    @FXML
    private TextField textFieldResposta2;

    @FXML
    private TextField textFieldResposta3;

    private String rutaFitxer = "preguntes.txt";
    private String rutaImatge = "preguntes/";
    private File imatgeViewSeleccionada;

    @FXML
    private void initialize() {
        opcionsDesplegable();
    }

    @FXML
    private void opcionsDesplegable() {
        desplegable1.getItems().add("Examen moto");
        desplegable1.getItems().add("Examen cotxe");

        ToggleGroup group = new ToggleGroup();
        radioBtnResposta1.setToggleGroup(group);
        radioBtnResposta2.setToggleGroup(group);
        radioBtnResposta3.setToggleGroup(group);
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
    private void guardarPregunta() {
        String tipus = desplegable1.getValue();
        String preguntaText = textFieldPregunta.getText();
        String resposta1 = textFieldResposta1.getText();
        String resposta2 = textFieldResposta2.getText();
        String resposta3 = textFieldResposta3.getText();

        if (tipus == null || tipus.isEmpty()) {
            mostraError("Si us plau, selecciona un tipus d'examen.");
            return;
        }

        if (preguntaText == null || preguntaText.isEmpty()) {
            mostraError("Si us plau, escriu una pregunta.");
            return;
        }

        if (resposta1 == null || resposta1.isEmpty()) {
            mostraError("Si us plau, escriu la primera resposta.");
            return;
        }

        if (resposta2 == null || resposta2.isEmpty()) {
            mostraError("Si us plau, escriu la segona resposta.");
            return;
        }

        if (resposta3 == null || resposta3.isEmpty()) {
            mostraError("Si us plau, escriu la tercera resposta.");
            return;
        }

        if (imatgeViewSeleccionada == null) {
            mostraError("Si us plau, selecciona una imatge.");
            return;
        }

        if (!radioBtnResposta1.isSelected() && !radioBtnResposta2.isSelected() && !radioBtnResposta3.isSelected()) {
            mostraError("Si us plau, selecciona quina resposta és la correcta.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("---\n");
        sb.append("tipus: ").append(tipus).append("\n");
        sb.append("pregunta: ").append(preguntaText).append("\n");

        if (radioBtnResposta1.isSelected()) {
            sb.append("resposta1: ").append(resposta1).append(" - correcta\n");
        } else {
            sb.append("resposta1: ").append(resposta1).append("\n");
        }

        if (radioBtnResposta2.isSelected()) {
            sb.append("resposta2: ").append(resposta2).append(" - correcta\n");
        } else {
            sb.append("resposta2: ").append(resposta2).append("\n");
        }

        if (radioBtnResposta3.isSelected()) {
            sb.append("resposta3: ").append(resposta3).append(" - correcta\n");
        } else {
            sb.append("resposta3: ").append(resposta3).append("\n");
        }

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

                    System.out.println("Imatge copiada a: " + fitxerImatge.getAbsolutePath());
                } catch (IOException e) {
                    mostraError("No s'ha pogut copiar la imatge.");
                    e.printStackTrace();
                    return;
                }
            }

            sb.append("imatge: ").append(imatgeViewSeleccionada.getName()).append("\n");
        } catch (Exception e) {
            mostraError("No s'ha pogut guardar la imatge.");
            e.printStackTrace();
            return;
        }

        sb.append("---\n");

        try (FileWriter writer = new FileWriter(rutaFitxer, true)) {
            writer.write(sb.toString());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informació");
            alert.setContentText("La pregunta s'ha guardat correctament.");
            alert.showAndWait();

            desplegable1.setValue(null);
            textFieldPregunta.clear();
            textFieldResposta1.clear();
            textFieldResposta2.clear();
            textFieldResposta3.clear();
            radioBtnResposta1.setSelected(false);
            radioBtnResposta2.setSelected(false);
            radioBtnResposta3.setSelected(false);
            imageView1.setImage(null);

            imatgeViewSeleccionada = null;
        } catch (IOException e) {
            mostraError("No s'ha pogut guardar la pregunta.");
            e.printStackTrace();
        }
    }

    private void mostraError(String missatge) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(missatge);
        alert.showAndWait();
    }

    @FXML
    void anarExamen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Examen1.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
}
