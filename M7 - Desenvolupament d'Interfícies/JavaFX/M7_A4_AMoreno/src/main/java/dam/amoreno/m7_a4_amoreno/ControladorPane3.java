package dam.amoreno.m7_a4_amoreno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ControladorPane3 {

  @FXML
  private Pane pane3;

  @FXML
  private TableView<Travessa> table1;

  @FXML
  private TableColumn<Travessa, String> columnaEquip1;

  @FXML
  private TableColumn<Travessa, String> columnaPrediccio1;

  @FXML
  private TableView<Travessa> table2;

  @FXML
  private TableColumn<Travessa, String> columnaEquip2;

  @FXML
  private TableColumn<Travessa, String> columnaPrediccio2;

  @FXML
  private ObservableList<Travessa> dades1 = FXCollections.observableArrayList();
  private static final String FITXER_APOSTES = "apostes.txt";

  @FXML
  private ObservableList<Travessa> dades2 = FXCollections.observableArrayList();
  private static final String FITXER_RESULTATS = "resultats.txt";

  @FXML
  private Label labelEncerts;

  @FXML
  public void initialize() {
    columnaEquip1.setCellValueFactory(new PropertyValueFactory<>("equip"));
    columnaPrediccio1.setCellValueFactory(new PropertyValueFactory<>("prediccio"));

    columnaEquip2.setCellValueFactory(new PropertyValueFactory<>("equip"));
    columnaPrediccio2.setCellValueFactory(new PropertyValueFactory<>("prediccio"));

    // comprovar si la prediccio es correcte canviant el color de fons de la fila
    table1.setRowFactory(_ -> new TableRow<Travessa>() {
      @Override
      protected void updateItem(Travessa item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
          setStyle("");
        } else {
          // Bucar el resultat per comparar
          Travessa resultat = dades2.stream()
              .filter(t -> t.getEquip()
                  .equals(item.getEquip()))
              .findFirst()
              .orElse(null);

          if (resultat != null) {
            if (item.getPrediccio().equals(resultat.getPrediccio())) {
              setStyle("-fx-background-color: lightgreen;");
            } else {
              setStyle("-fx-background-color: lightcoral;");
            }
          } else {
            setStyle("");
          }
        }
      }
    });

    carregarApostes();
    carregarResultats();

    table1.setItems(dades1);
    table2.setItems(dades2);

    comptarEncerts();
  }

  private void carregarApostes() {
    dades1.clear();

    File fitxer = new File(FITXER_APOSTES);

    if (fitxer.exists()) {
      try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
        String linia;
        while ((linia = reader.readLine()) != null) {
          String[] parts = linia.split(", ");
          if (parts.length == 2) {
            dades1.add(new Travessa(parts[0], parts[1]));
          }
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void carregarResultats() {
    dades2.clear();

    File fitxer = new File(FITXER_RESULTATS);

    if (fitxer.exists()) {
      try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
        String linia;
        while ((linia = reader.readLine()) != null) {
          String[] parts = linia.split(", ");
          if (parts.length == 2) {
            dades2.add(new Travessa(parts[0], parts[1]));
          }
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void comptarEncerts() {
    int encerts = 0;

    for (Travessa t : dades1) {
      for (Travessa r : dades2) {
        if (t.getEquip().equals(r.getEquip()) && t.getPrediccio().equals(r.getPrediccio())) {
          encerts++;
        }
      }
    }

    labelEncerts.setText("Nombre d'encerts: " + encerts);
  }
}
