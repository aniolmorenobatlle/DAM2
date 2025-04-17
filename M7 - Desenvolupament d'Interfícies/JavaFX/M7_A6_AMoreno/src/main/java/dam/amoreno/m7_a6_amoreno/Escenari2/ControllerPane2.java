package dam.amoreno.m7_a6_amoreno.Escenari2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ControllerPane2 {

  @FXML
  private Button btnAccelerar;

  @FXML
  private Button btnClassificacio;

  @FXML
  private ImageView imageViewCircuit;

  @FXML
  private Pane pane2;

  @FXML
  private Pane paneCursa;

  private Random random = new Random();
  private List<ImageView> cars = new ArrayList<>();

  @FXML
  private void initialize() {
    llegirCircuits();
    llegirPilots();
  }

  void llegirCircuits() {
    List<Circuit> circuits = new ArrayList<>();

    String ruta = "circuits.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
      String linia;

      while ((linia = br.readLine()) != null) {
        String[] parts = linia.split(", ");

        if (parts.length == 3) {
          String nom = parts[0];
          LocalDate data = LocalDate.parse(parts[1]);
          String imatge = parts[2];
          circuits.add(new Circuit(nom, data, imatge));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    circuits.sort(Comparator.comparing(Circuit::getData));

    // Mostrar imatge del primer circuit
    if (!circuits.isEmpty()) {
      String nomImatge = circuits.get(0).getImatge();
      InputStream inputStream = getClass()
          .getResourceAsStream("/dam/amoreno/m7_a6_amoreno/images/circuit/" + nomImatge);
      if (inputStream == null) {
        System.out.println("No s'ha trobat el fitxer: " + nomImatge);
      } else {
        Image imatge = new Image(inputStream);
        imageViewCircuit.setImage(imatge);
      }
    }
  }

  void llegirPilots() {
    List<Pilot> pilots = new ArrayList<>();

    String rutaPilots = "pilots.txt";
    String rutaEscuderies = "escuderies.txt";

    // Llegir escuderies
    Map<String, String> escuderies = new HashMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(rutaEscuderies))) {
      String linia;

      while ((linia = br.readLine()) != null) {
        String[] parts = linia.split(", ");

        if (parts.length == 2) {
          escuderies.put(parts[0], parts[1]);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Llegir pilots
    try (BufferedReader br = new BufferedReader(new FileReader(rutaPilots))) {
      String linia;

      while ((linia = br.readLine()) != null) {
        String[] parts = linia.split(", ");

        if (parts.length == 4) {
          String nom = parts[0];
          int dorsal = Integer.parseInt(parts[1]);
          String escuderia = parts[2];
          String imatge = parts[3];
          pilots.add(new Pilot(nom, dorsal, escuderia, imatge));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    // Afegir imatge del cotxe al paneCursa
    for (Pilot p : pilots) {
      String imatgeCotxe = escuderies.get(p.getEscuderia());

      if (imatgeCotxe != null) {
        InputStream inputStream = getClass()
            .getResourceAsStream("/dam/amoreno/m7_a6_amoreno/images/cotxes/" + imatgeCotxe);

        if (inputStream == null) {
          System.out.println("No s'ha trobat la imatge del cotxe: " + imatgeCotxe);
          continue;
        }

        ImageView imageView = new ImageView(new Image(inputStream));
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        imageView.setSmooth(true);

        paneCursa.getChildren().add(imageView);
        cars.add(imageView); // Afegir el cotxe a la llista
      }
    }
  }

  @FXML
  void accelerar() {
    for (ImageView car : cars) {
      double randomDistance = random.nextInt(91) + 10; // Distància entre 10 i 100
      moureCotxe(car, randomDistance);
    }
  }

  private void moureCotxe(ImageView car, double distance) {
    TranslateTransition transition = new TranslateTransition();
    transition.setNode(car);
    transition.setDuration(Duration.seconds(0.5));
    transition.setByX(distance); // Mou el cotxe una distància aleatòria
    transition.setCycleCount(1);
    transition.setAutoReverse(false);

    // Un cop acabi l'animació, mostrar la posició final
    transition.setOnFinished(_ -> {
      System.out.println("Posició final del cotxe: " + car.getLayoutX());
    });

    transition.play();
  }

}
