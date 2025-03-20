package dam.amoreno.m7_a4_amoreno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
    private TableColumn<Travessa, String> ColumnaEquip;

    @FXML
    private TableColumn<Travessa, String> ColumnaPrediccio;

    private ObservableList<Travessa> dades = FXCollections.observableArrayList();
    private static final String FITXER_APOSTES = "apostes.txt";

    @FXML
    public void initialize() {
        table.setEditable(true);

        ColumnaEquip.setCellValueFactory(new PropertyValueFactory<>("equip"));

        ColumnaPrediccio.setCellValueFactory(new PropertyValueFactory<>("prediccio"));
        ColumnaPrediccio
                .setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList("1", "X", "2")));

        // En fer canvi a la columna de prediccions, guardar les apostes
        ColumnaPrediccio.setOnEditCommit(e -> {
            Travessa travessa = e.getRowValue();
            travessa.setPrediccio(e.getNewValue());
            guardarApostes();
        });

        carregarApostes();
        table.setItems(dades);
    }

    private void guardarApostes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FITXER_APOSTES))) {
            for (Travessa travessa : dades) {
                writer.write(travessa.getEquip() + ", " + travessa.getPrediccio());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarApostes() {
        dades.clear(); // Esborrar dades anteriors

        File fitxer = new File(FITXER_APOSTES);

        if (fitxer.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
                String linia;
                while ((linia = reader.readLine()) != null) {
                    String[] parts = linia.split(", ");
                    if (parts.length == 2) {
                        dades.add(new Travessa(parts[0], parts[1]));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Si no existeix afegir dades per defecte
            dades.add(new Travessa("Barcelona", "1"));
            dades.add(new Travessa("Real Madrid", "1"));
            dades.add(new Travessa("Atlético Madrid", "1"));
            dades.add(new Travessa("Valencia", "1"));
            dades.add(new Travessa("Sevilla", "1"));
            dades.add(new Travessa("Villarreal", "1"));
            dades.add(new Travessa("Real Sociedad", "1"));
            dades.add(new Travessa("Girona", "1"));
            dades.add(new Travessa("Athletic Club", "1"));
            dades.add(new Travessa("Granada", "1"));
            dades.add(new Travessa("Osasuna", "1"));
            dades.add(new Travessa("Levante", "1"));
            dades.add(new Travessa("Real Betis", "1"));
            dades.add(new Travessa("Alavés", "1"));
        }
    }
}
