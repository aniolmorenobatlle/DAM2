package dam.amoreno.m7_a7_amoreno.Examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dam.amoreno.m7_a7_amoreno.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerExamen2 {

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
    private Text textPregunta;

    @FXML
    private Text textResposta1;

    @FXML
    private Text textResposta2;

    @FXML
    private Text textResposta3;

    private String rutaFitxerPreguntes = "preguntes.txt";
    private String rutaFitxerRespostes = "respostes.txt";
    private String rutaFitxerUsuari = "usuari.txt";
    private String rutaImatge = "preguntes/";

    private String tipusExamen = "";

    private List<Pregunta> preguntes = new ArrayList<>();

    private int indexActual = 0;

    private Map<Integer, Character> respostesUsuari = new HashMap<>();

    @FXML
    private void initialize() {
        opcionsDesplegable();
        carregarTipusExamen();
        carregarPreguntes();
        mostrarPreguntaActual();
    }

    @FXML
    private void opcionsDesplegable() {
        ToggleGroup group = new ToggleGroup();
        radioBtnResposta1.setToggleGroup(group);
        radioBtnResposta2.setToggleGroup(group);
        radioBtnResposta3.setToggleGroup(group);

        radioBtnResposta1.setOnAction(_ -> guardarResposta('a'));
        radioBtnResposta2.setOnAction(_ -> guardarResposta('b'));
        radioBtnResposta3.setOnAction(_ -> guardarResposta('c'));

    }

    private void carregarTipusExamen() {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFitxerUsuari))) {
            String linia;

            while ((linia = br.readLine()) != null) {
                linia = linia.strip();
                if (linia.startsWith("tipus:")) {
                    tipusExamen = linia.replace("tipus:", "").strip();
                    break;
                }
            }
        } catch (Exception e) {
            mostraError("Error al carregar el tipus d'examen");
            e.printStackTrace();
        }
    }

    private void carregarPreguntes() {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFitxerPreguntes))) {
            String linia;
            String tipus = "";
            String pregunta = "", resposta1 = "", resposta2 = "", resposta3 = "", imatge = "";

            while ((linia = br.readLine()) != null) {
                linia = linia.strip();
                if (linia.startsWith("tipus:")) {
                    tipus = linia.replace("tipus:", "").strip();
                } else if (linia.startsWith("pregunta:")) {
                    pregunta = linia.replace("pregunta:", "").strip();
                } else if (linia.startsWith("resposta1:")) {
                    resposta1 = linia.replace("resposta1:", "").replace("- correcta", "").strip();
                } else if (linia.startsWith("resposta2:")) {
                    resposta2 = linia.replace("resposta2:", "").replace("- correcta", "").strip();
                } else if (linia.startsWith("resposta3:")) {
                    resposta3 = linia.replace("resposta3:", "").replace("- correcta", "").strip();
                } else if (linia.startsWith("imatge:")) {
                    imatge = linia.replace("imatge:", "").strip();

                    if (tipus.equalsIgnoreCase(tipusExamen)) {
                        preguntes.add(new Pregunta(pregunta, resposta1, resposta2, resposta3, imatge));
                    }

                    // Reset per la següent pregunta
                    tipus = pregunta = resposta1 = resposta2 = resposta3 = imatge = "";
                }
            }
        } catch (Exception e) {
            mostraError("Error al carregar les preguntes.");
            e.printStackTrace();
        }
    }

    private void mostrarPreguntaActual() {
        if (indexActual >= preguntes.size()) {
            return;
        }

        Pregunta p = preguntes.get(indexActual);
        textPregunta.setText(p.getPregunta());
        textResposta1.setText(p.getResposta1());
        textResposta2.setText(p.getResposta2());
        textResposta3.setText(p.getResposta3());

        try {
            File file = new File(rutaImatge + p.getImatge());

            Image image = new Image(file.toURI().toString());

            imageView1.setImage(image);

        } catch (Exception e) {
            mostraError("No s'ha pogut carregar la imatge.");
            e.printStackTrace();
        }

        radioBtnResposta1.setSelected(false);
        radioBtnResposta2.setSelected(false);
        radioBtnResposta3.setSelected(false);

        if (respostesUsuari.containsKey(indexActual)) {
            char resposta = respostesUsuari.get(indexActual);
            switch (resposta) {
                case 'a':
                    radioBtnResposta1.setSelected(true);
                    break;
                case 'b':
                    radioBtnResposta2.setSelected(true);
                    break;
                case 'c':
                    radioBtnResposta3.setSelected(true);
                    break;
            }
        }

    }

    private void guardarResposta(char opcio) {
        respostesUsuari.put(indexActual, opcio);

        try {
            List<String> linies = new ArrayList<>();

            for (int i = 0; i < preguntes.size(); i++) {
                String respostaText = "No contestada";

                if (respostesUsuari.containsKey(i)) {
                    Pregunta p = preguntes.get(i);
                    char resposta = respostesUsuari.get(i);

                    switch (resposta) {
                        case 'a':
                            respostaText = p.getResposta1();
                            break;
                        case 'b':
                            respostaText = p.getResposta2();
                            break;
                        case 'c':
                            respostaText = p.getResposta3();
                            break;
                    }
                }

                linies.add("Pregunta " + (i + 1) + ": " + respostaText);
            }

            Files.write(Paths.get(rutaFitxerRespostes), linies);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSeguent() {
        if (indexActual < preguntes.size() - 1) {
            indexActual++;
            mostrarPreguntaActual();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Final");
            alert.setHeaderText("Examen completat");
            alert.setContentText("Has arribat al final del test.");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnAnterior() {
        if (indexActual > 0) {
            indexActual--;
            mostrarPreguntaActual();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inici");
            alert.setHeaderText("Estàs a la primera pregunta");
            alert.setContentText("No pots tornar enrere.");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnFinalitzar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Carnet.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    private void mostraError(String missatge) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(missatge);
        alert.showAndWait();
    }
}
