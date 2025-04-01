package dam.amoreno.m7_a5_amoreno.Compres;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import dam.amoreno.m7_a5_amoreno.Main;
import dam.amoreno.m7_a5_amoreno.Producte;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CompresController {

    @FXML
    private GridPane productGrid;

    // Llista productes
    private final List<Producte> productes = List.of(
            new Producte("Peres", "5.99€", "/pera.jpg"),
            new Producte("Arros", "12.50€", "/arros.jpg"),
            new Producte("Pan bimbo", "9.99€", "/bimbo.jpg"),
            new Producte("Cafe", "25.00€", "/cafe.jpg"),
            new Producte("Carn", "30.75€", "/filet.jpg"),
            new Producte("Gambes", "18.49€", "/gamba.jpg"),
            new Producte("Llet", "22.99€", "/llet.jpg"),
            new Producte("Macarrons", "14.30€", "/macarrons.jpg"),
            new Producte("Nutella", "19.95€", "/nutella.jpg"));

    @FXML
    public void initialize() {
        productGrid.setHgap(50);
        productGrid.setVgap(10);

        int column = 0;
        int row = 0;

        for (Producte producte : productes) {
            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(10));
            vbox.setMinWidth(220);

            vbox.setCursor(Cursor.HAND);

            ImageView imageView = new ImageView();
            try {
                Image image = new Image(producte.getImatgePath());
                imageView.setImage(image);
            } catch (Exception e) {
                System.out.println("No s'ha pogut carregar la imatge: " + producte.getImatgePath());
            }
            imageView.setFitWidth(190);
            imageView.setFitHeight(200);

            Text nomText = new Text(producte.getNom());
            Text preuText = new Text(producte.getPreu());

            vbox.setOnMouseClicked((MouseEvent _) -> afegirAlCarrito(producte));

            vbox.getChildren().addAll(imageView, nomText, preuText);

            productGrid.add(vbox, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    private void afegirAlCarrito(Producte producte) {
        String ruta = "carrito.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) {
            writer.write(producte.getNom() + " - 1 - " + producte.getPreu() + " - " + producte.getImatgePath());
            writer.newLine();
            System.out.println("Producte afegit al carrito: " + producte.getNom());

        } catch (Exception e) {
            System.out.println("Error al afegir al carrito: " + e.getMessage());
        }
    }

    @FXML
    private void handleCarrito(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Carret.fxml"));

        Pane carretRoot = fxmlLoader.load();

        Stage currentSatge = (Stage) productGrid.getScene().getWindow();

        currentSatge.getScene().setRoot(carretRoot);

        currentSatge.show();

    }
}
