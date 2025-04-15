package dam.amoreno.m7_a5_amoreno.Carret;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dam.amoreno.m7_a5_amoreno.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CarretController {

  @FXML
  private Pane pane1;

  @FXML
  private GridPane producteGrid;

  private final String rutaFitxer = "carrito.txt";

  @FXML
  public void initialize() {
    carregarCarret();
  }

  private void carregarCarret() {
    Map<String, ProducteCarret> productes = new HashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(rutaFitxer))) {
      String linia;

      while ((linia = reader.readLine()) != null) {
        String[] parts = linia.split(" - ");

        if (parts.length == 4) {
          String nom = parts[0];
          int quantitat = Integer.parseInt(parts[1]);
          double preuUnitari = Double.parseDouble(parts[2].replace("€", ""));
          String imagePath = parts[3];

          productes.put(nom, new ProducteCarret(nom, preuUnitari, quantitat, imagePath));
        }
      }

      // Afegir productes al grid
      int row = 0;

      for (ProducteCarret producte : productes.values()) {
        HBox fila = new HBox(20);
        fila.setPadding(new Insets(20));

        ImageView imageView = new ImageView(new Image(producte.imatgePath));
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        Text nomText = new Text(producte.nom);
        Text preuText = new Text(producte.preuUnitari + "€");
        Text quantitatText = new Text("Quantitat: " + producte.quantitat);
        Text totalText = new Text("Total: " + producte.getPreuTotal() + "€");

        fila.getChildren().addAll(imageView, nomText, preuText, quantitatText, totalText);
        producteGrid.add(fila, 0, row++);
      }

    } catch (Exception e) {
      System.out.println("Error al carregar el carret: " + e.getMessage());
    }
  }

  @FXML
  void handleFactura(MouseEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Factura.fxml"));
    Pane carretRoot = fxmlLoader.load();

    Stage currentSatge = (Stage) producteGrid.getScene().getWindow();

    currentSatge.getScene().setRoot(carretRoot);
    currentSatge.show();
  }

  @FXML
  void handleEnrera(MouseEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Compres.fxml"));
    Parent root = fxmlLoader.load();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.getScene().setRoot(root);
  }
}
