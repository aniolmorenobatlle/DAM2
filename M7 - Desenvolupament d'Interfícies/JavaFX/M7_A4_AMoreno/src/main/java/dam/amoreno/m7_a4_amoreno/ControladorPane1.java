package dam.amoreno.m7_a4_amoreno;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ControladorPane1 {

    @FXML
    private Pane pane1;
    @FXML
    private Text title;
    @FXML
    private TableView<Travessa> table;
    @FXML
    private TableColumn<Travessa, String> ColumnaPrediccio;
    @FXML
    private TableColumn<Travessa, String> ColumnaEquipLocal;
    @FXML
    private TableColumn<Travessa, String> ColumnaEquipVisitant;

    private ObservableList<Travessa> dades = FXCollections.observableArrayList();
    private static final String FITXER_APOSTES = "apostes.txt";

    @FXML
    public void initialize() {
        ColumnaEquipLocal.setCellValueFactory(new PropertyValueFactory<>("equipLocal"));
        ColumnaEquipVisitant.setCellValueFactory(new PropertyValueFactory<>("equipVisitant"));
        ColumnaPrediccio.setCellValueFactory(new PropertyValueFactory<>("prediccio"));

        ColumnaPrediccio.setCellFactory(ComboBoxTableCell.forTableColumn("1", "X", "2"));
        table.setEditable(true);
        ColumnaPrediccio.setEditable(true);

        ColumnaPrediccio.setOnEditCommit(e -> {
            Travessa travessa = e.getRowValue();
            travessa.setPrediccio(e.getNewValue());
            guardarApostes();
        });

        generarPartitsAleatoris();
        table.setItems(dades);
    }

    private void guardarApostes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FITXER_APOSTES))) {
            for (Travessa travessa : dades) {
                writer.write(travessa.getEquipLocal() + ", " +
                        travessa.getEquipVisitant() + ", " +
                        travessa.getPrediccio());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generarPartitsAleatoris() {
        String[] equips = {
                "FC Barcelona", "Real Madrid", "Atlético de Madrid",
                "Athletic Club", "Villarreal CF", "Betis",
                "Celta de Vigo", "Real Sociedad", "Rayo Vallecano",
                "Mallorca", "Getafe", "Sevilla FC", "Girona FC",
                "Osasuna", "Valencia CF", "Espanyol",
                "Alavés", "Leganés", "Las Palmas",
                "Valladolid"
        };

        List<String> llistaEquips = new ArrayList<>();
        Collections.addAll(llistaEquips, equips);
        Collections.shuffle(llistaEquips);

        for (int i = 0; i < llistaEquips.size() - 1; i += 2) {
            String local = llistaEquips.get(i);
            String visitant = llistaEquips.get(i + 1);
            dades.add(new Travessa(local, visitant, ""));
        }
    }
}
