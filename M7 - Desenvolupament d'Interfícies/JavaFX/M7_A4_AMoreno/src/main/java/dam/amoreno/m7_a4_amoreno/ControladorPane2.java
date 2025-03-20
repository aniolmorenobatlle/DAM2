package dam.amoreno.m7_a4_amoreno;

import java.io.BufferedWriter;
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
    private TableColumn<Travessa, String> ColumnaEquip;

    @FXML
    private TableColumn<Travessa, String> ColumnaPrediccio;

    @FXML
    private ObservableList<Travessa> dades = FXCollections.observableArrayList();
    private static final String FITXER_APOSTES = "resultats.txt";

    private String[] equips = {
            "Barcelona", "Real Madrid", "Atlético Madrid", "Valencia", "Sevilla",
            "Villarreal", "Real Sociedad", "Girona", "Athletic Club", "Granada",
            "Osasuna", "Levante", "Real Betis", "Alavés"
    };

    private String[] opcions = { "1", "X", "2" };

    @FXML
    public void initialize() {
        table.setEditable(true);

        ColumnaEquip.setCellValueFactory(new PropertyValueFactory<>("equip"));
        ColumnaPrediccio.setCellValueFactory(new PropertyValueFactory<>("prediccio"));

        generarApostesAleatories();
        table.setItems(dades);
    }

    private void generarApostesAleatories() {
        dades.clear();
        Random random = new Random();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FITXER_APOSTES))) {
            for (String equip : equips) {
                String prediccio = opcions[random.nextInt(opcions.length)];
                dades.add(new Travessa(equip, prediccio));
                bw.write(equip + ", " + prediccio);
                bw.newLine();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No s'ha pogut escriure al fitxer");
            alert.setContentText("Error escrivint " + FITXER_APOSTES);
            alert.showAndWait();
        }
    }
}
