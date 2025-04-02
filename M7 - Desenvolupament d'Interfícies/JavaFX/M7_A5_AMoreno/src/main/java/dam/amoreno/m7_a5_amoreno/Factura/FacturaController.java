package dam.amoreno.m7_a5_amoreno.Factura;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FacturaController {

  @FXML
  private GridPane gridpaneFactura;

  @FXML
  private Pane pane1;

  @FXML
  private Text resultDNI;

  @FXML
  private Text resultData;

  @FXML
  private Text resultNom;

  @FXML
  private Text resultNumero;

  @FXML
  private Text textDNI;

  @FXML
  private Text textData;

  @FXML
  private Text textNom;

  @FXML
  private Text textNumero;

  Random random = new Random();

  private final String rutaFitxer = "carrito.txt";

  private String nomClient = "";
  private String dniClient = "";
  private int numeroFactura = 1000000 + random.nextInt(9000000);
  private String dataFactura = LocalDate.now().toString();

  @FXML
  public void initialize() {
    finestraEmergent();
    generarFactura();

    resultNom.setText(nomClient);
    resultDNI.setText(dniClient);
    resultNumero.setText(String.valueOf(numeroFactura));
    resultData.setText(dataFactura);
  }

  private void finestraEmergent() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    alert.setHeaderText(null);
    alert.setTitle("Dades de la factura");

    TextField nomField = new TextField();
    nomField.setPromptText("Nom del client");

    TextField dniField = new TextField();
    dniField.setPromptText("DNI del client");

    VBox vbox = new VBox(10);
    vbox.getChildren().addAll(nomField, dniField);

    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.setContent(vbox);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      String nom = nomField.getText();
      String dni = dniField.getText();

      nomClient = nom;
      dniClient = dni;
    }
  }

  private void generarFactura() {
    Map<String, ProducteFactura> productesMap = carregarProductes();

    gridpaneFactura.setHgap(20);
    gridpaneFactura.setVgap(30);

    String[] titols = { "Nom", "Preu Unitari", "Quantitat", "Preu Total" };
    for (int col = 0; col < titols.length; col++) {
      Text titolText = new Text(titols[col]);
      titolText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
      gridpaneFactura.add(titolText, col, 0);
    }

    double totalFactura = 0;
    int row = 1;

    for (ProducteFactura producte : productesMap.values()) {
      double preuTotal = producte.getPreuTotal();
      totalFactura += preuTotal;

      Text nomText = new Text(producte.getNom());
      nomText.setFont(Font.font("Arial", 18));

      Text preuText = new Text(producte.getPreuUnitari() + "€");
      preuText.setFont(Font.font("Arial", 18));

      Text quantitatText = new Text(String.valueOf(producte.getQuantitat()));
      quantitatText.setFont(Font.font("Arial", 18));

      Text totalText = new Text(preuTotal + "€");
      totalText.setFont(Font.font("Arial", 18));

      gridpaneFactura.add(nomText, 0, row);
      gridpaneFactura.add(preuText, 1, row);
      gridpaneFactura.add(quantitatText, 2, row);
      gridpaneFactura.add(totalText, 3, row);
      row++;
    }

    gridpaneFactura.add(
        new Text("-----------------------------------------------------------------------------------------------"), 0,
        row, 4, 1);

    row++;
    Text totalText = new Text("Total: " + totalFactura + "€");
    totalText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    gridpaneFactura.add(totalText, 0, row, 4, 1);

    GridPane.setHalignment(totalText, HPos.RIGHT);
  }

  private Map<String, ProducteFactura> carregarProductes() {
    Map<String, ProducteFactura> productesMap = new HashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(rutaFitxer))) {
      String linia;

      while ((linia = reader.readLine()) != null) {
        String[] parts = linia.split(" - ");

        if (parts.length == 4) {
          String nom = parts[0];
          int quantitat = Integer.parseInt(parts[1]);
          double preuUnitari = Double.parseDouble(parts[2].replace("€", ""));
          String imatgePath = parts[3];

          // Suma quanitat si existeix
          if (productesMap.containsKey(nom)) {
            ProducteFactura existent = productesMap.get(nom);
            productesMap.put(nom,
                new ProducteFactura(nom, preuUnitari, existent.getQuantitat() + quantitat, imatgePath));
          } else {
            productesMap.put(nom, new ProducteFactura(nom, preuUnitari, quantitat, imatgePath));
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Error al llegir el fitxer: " + e.getMessage());
    }

    return productesMap;
  }

}