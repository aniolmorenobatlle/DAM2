package dam.amoreno.m7_a4_amoreno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ControladorPane2 {

    @FXML
    private Pane pane1;
    @FXML
    private Text title;
    @FXML
    private TableView<Travessa> table;
    @FXML
    private TableColumn<Travessa, String> ColumnaEquipLocal;
    @FXML
    private TableColumn<Travessa, String> ColumnaEquipVisitant;
    @FXML
    private TableColumn<Travessa, String> ColumnaPrediccio;

    private ObservableList<Travessa> dades = FXCollections.observableArrayList();
    private static final String FITXER_RESULTATS = "resultats.txt";
    private static final String FITXER_APOSTES = "apostes.txt";
    private String[] opcions = { "1", "X", "2" };
    private Random random = new Random();

    @FXML
    public void initialize() {
        ColumnaEquipLocal.setCellValueFactory(new PropertyValueFactory<>("equipLocal"));
        ColumnaEquipVisitant.setCellValueFactory(new PropertyValueFactory<>("equipVisitant"));
        ColumnaPrediccio.setCellValueFactory(new PropertyValueFactory<>("prediccio"));

        generarResultatsAleatorisBasatsEnApostes();
        table.setItems(dades);
    }

    private void generarResultatsAleatorisBasatsEnApostes() {
        dades.clear();

        File fitxerApostes = new File(FITXER_APOSTES);
        if (!fitxerApostes.exists()) {
            mostrarError("El fitxer d'apostes no existeix", "Has de generar primer les apostes.");
            return;
        }

        try (
                BufferedReader reader = new BufferedReader(new FileReader(fitxerApostes));
                BufferedWriter writer = new BufferedWriter(new FileWriter(FITXER_RESULTATS))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String[] parts = linia.split(", ");
                if (parts.length >= 2) {
                    String local = parts[0];
                    String visitant = parts[1];
                    String resultat = opcions[random.nextInt(opcions.length)];

                    dades.add(new Travessa(local, visitant, resultat));
                    writer.write(local + ", " + visitant + ", " + resultat);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            mostrarError("Error de fitxer", "No s'han pogut generar els resultats.");
            e.printStackTrace();
        }
    }

    private void mostrarError(String header, String missatge) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(missatge);
        alert.showAndWait();
    }
}
