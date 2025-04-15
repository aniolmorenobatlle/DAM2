package dam.amoreno.m7_a5_amoreno;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Compres.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Activitat 5");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        resetStockCarrito();
        launch();
    }

    private static void resetStockCarrito() {
        List<Producte> productesPerDefecte = List.of(
                new Producte("Arros", "12.50€", "/arros.jpg", 10),
                new Producte("Bimbo", "15.99€", "/bimbo.jpg", 10),
                new Producte("Cafe", "25.00€", "/cafe.jpg", 10),
                new Producte("Filet", "30.75€", "/filet.jpg", 10),
                new Producte("Gambes", "18.49€", "/gamba.jpg", 10),
                new Producte("Llet", "8.99€", "/llet.jpg", 10),
                new Producte("Macarrons", "3.99€", "/macarrons.jpg", 10),
                new Producte("Nutella", "7.49€", "/nutella.jpg", 10),
                new Producte("Peres", "5.99€", "/pera.jpg", 10));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stock.txt"))) {
            for (Producte p : productesPerDefecte) {
                writer.write(p.getNom() + " - " + p.getStock() + " - " + p.getPreu() + " - " + p.getImatgePath());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escrivint l'stock per defecte: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("carrito.txt"))) {
            // no escriure res aixi esta buit
        } catch (IOException e) {
            System.out.println("Error buidant el carrito: " + e.getMessage());
        }
    }
}