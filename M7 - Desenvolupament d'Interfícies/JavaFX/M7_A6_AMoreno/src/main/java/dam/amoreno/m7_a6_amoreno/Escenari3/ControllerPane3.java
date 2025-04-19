package dam.amoreno.m7_a6_amoreno.Escenari3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControllerPane3 {

  @FXML
  private ImageView imageViewPodi1;

  @FXML
  private ImageView imageViewPodi2;

  @FXML
  private ImageView imageViewPodi3;

  @FXML
  private VBox llistaVBox;

  @FXML
  private ScrollPane scrollPane1;

  @FXML
  public void initialize() {
    llegirClassificacio();
  }

  private void llegirClassificacio() {
    Map<String, String> dadesPilots = new HashMap<>();

    // Llegir fitxer pilots
    try (BufferedReader br = new BufferedReader(new FileReader("pilots.txt"))) {
      String linia;

      while ((linia = br.readLine()) != null) {
        String[] parts = linia.split(", ");
        String nom = parts[0].trim();
        int dorsal = Integer.parseInt(parts[1].trim());
        String equip = parts[2].trim();
        String imatge = parts[3].trim();

        String dadesPilot = dorsal + ", " + equip + ", " + imatge;
        dadesPilots.put(nom.toLowerCase(), dadesPilot);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Llegir fitxer classificacio
    try (BufferedReader br = new BufferedReader(new FileReader("classificacioCursa.txt"))) {
      String linia;
      int posicio = 1;

      while ((linia = br.readLine()) != null) {
        String[] parts = linia.split("- ");
        String nom = parts[1].toLowerCase();

        if (dadesPilots.containsKey(nom)) {
          String dadesPilot = dadesPilots.get(nom);
          String[] dades = dadesPilot.split(", ");
          String dorsal = dades[0];
          String equip = dades[1];
          String imatge = dades[2];

          String imatgePath = "/dam/amoreno/m7_a6_amoreno/images/pilots/" + imatge;

          if (posicio == 1) {
            imageViewPodi1.setImage(
                new Image(getClass().getResource(imatgePath).toString()));
          } else if (posicio == 2) {
            imageViewPodi2.setImage(
                new Image(getClass().getResource(imatgePath).toString()));
          } else if (posicio == 3) {
            imageViewPodi3.setImage(
                new Image(getClass().getResource(imatgePath).toString()));
          }
          Label label = new Label(posicio + "- " + nom + " (" + dorsal + ") - " + equip);
          label.setStyle("-fx-font-size: 18px;");

          ImageView imageView = new ImageView(new Image(getClass().getResource(imatgePath).toString()));
          imageView.setFitHeight(50);
          imageView.setFitWidth(50);
          imageView.setPreserveRatio(true);

          HBox hbox = new HBox(10);
          hbox.setAlignment(Pos.CENTER_LEFT);
          hbox.getChildren().addAll(label, imageView);

          llistaVBox.getChildren().add(hbox);

          posicio++;
        } else {
          System.out.println("Pilot " + nom + " no trobat.");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
