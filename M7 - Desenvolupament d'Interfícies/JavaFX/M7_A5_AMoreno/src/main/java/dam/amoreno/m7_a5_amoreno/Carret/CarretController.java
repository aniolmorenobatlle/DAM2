package dam.amoreno.m7_a5_amoreno.Carret;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
    Map<String, ProducteCarret> productesMap = new HashMap<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(rutaFitxer))) {
      String linia;

      while ((linia = reader.readLine()) != null) {
        String[] parts = linia.split(" - ");

        if (parts.length == 4) {
          String nom = parts[0];
          int quantitat = Integer.parseInt(parts[1]);
          double preuUnitari = Double.parseDouble(parts[2].replace("€", ""));
          String imagePath = parts[3];

          // Si ja existeix afegir la quantitat
          if (productesMap.containsKey(nom)) {
            productesMap.get(nom).afegirQuantitat(quantitat);
          } else {
            productesMap.put(nom, new ProducteCarret(nom, preuUnitari, quantitat, imagePath));
          }
        }
      }

      // Afegir productes al grid
      int row = 0;

      for (ProducteCarret producte : productesMap.values()) {
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

}
