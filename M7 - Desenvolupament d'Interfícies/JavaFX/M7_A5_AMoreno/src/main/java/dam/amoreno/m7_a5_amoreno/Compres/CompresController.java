package dam.amoreno.m7_a5_amoreno.Compres;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dam.amoreno.m7_a5_amoreno.Main;
import dam.amoreno.m7_a5_amoreno.Producte;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CompresController {

    @FXML
    private GridPane producteGrid;

    private final List<Producte> productes = new ArrayList<>();

    @FXML
    public void initialize() {
        carregarStock();

        producteGrid.setHgap(50);
        producteGrid.setVgap(10);

        int column = 0;
        int row = 0;

        for (Producte producte : productes) {
            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(10));
            vbox.setMinWidth(220);

            ImageView imageView = new ImageView();
            try {
                Image image = new Image(producte.getImatgePath());
                imageView.setImage(image);
            } catch (Exception e) {
                System.out.println("No s'ha pogut carregar la imatge: " + producte.getImatgePath());
            }
            imageView.setFitWidth(150);
            imageView.setFitHeight(100);

            Text nomText = new Text(producte.getNom());
            Text preuText = new Text(producte.getPreu());

            // Obtenir la quantitat actual del producte al carrito
            int quantitatInicial = obtenirQuantitatAlCarrito(producte);
            Text quantitatText = new Text(String.valueOf(quantitatInicial));

            Button btnMenys = new Button("-");
            Button btnMes = new Button("+");

            btnMenys.setOnAction(_ -> {
                int quantitat = Integer.parseInt(quantitatText.getText());
                if (quantitat > 0) {
                    quantitat--;
                    quantitatText.setText(String.valueOf(quantitat));
                    actualitzarCarrito(producte, -1);
                    actualitzarStock(producte, +1);

                    if (quantitat == 0) {
                        btnMenys.setDisable(true);
                    }
                }
            });

            btnMes.setOnAction(_ -> {
                if (producte.getStock() > 0) {
                    int quantitat = Integer.parseInt(quantitatText.getText());
                    quantitat++;
                    quantitatText.setText(String.valueOf(quantitat));
                    actualitzarCarrito(producte, +1);
                    actualitzarStock(producte, -1);

                    if (producte.getStock() == 0) {
                        btnMes.setDisable(true);
                    }
                } else {
                    btnMes.setDisable(true);
                }
            });

            HBox hboxQuantitat = new HBox(2, btnMenys, quantitatText, btnMes);
            hboxQuantitat.setAlignment(Pos.CENTER);

            vbox.getChildren().addAll(imageView, nomText, preuText, hboxQuantitat);
            producteGrid.add(vbox, column, row);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    private void carregarStock() {
        try (BufferedReader reader = new BufferedReader(new FileReader("stock.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 4) {
                    String nom = parts[0];
                    int stock = Integer.parseInt(parts[1]);
                    String preu = parts[2];
                    String imatge = parts[3];
                    productes.add(new Producte(nom, preu, imatge, stock));
                }
            }
        } catch (IOException e) {
            System.out.println("Error llegint l'stock: " + e.getMessage());
        }
    }

    private int obtenirQuantitatAlCarrito(Producte producte) {
        try (BufferedReader br = new BufferedReader(new FileReader("carrito.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts[0].equals(producte.getNom())) {
                    return Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error llegint el carrito: " + e.getMessage());
        }
        return 0; // Si no es troba el producte, la quantitat és 0
    }

    private void actualitzarCarrito(Producte producte, int canviQuantitat) {
        Map<String, String[]> carritoMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("carrito.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 4) {
                    carritoMap.put(parts[0], new String[] { parts[1], parts[2], parts[3] });
                }
            }
        } catch (IOException ignored) {
            System.out.println("Error llegint el carrito: " + ignored.getMessage());
        }

        if (carritoMap.containsKey(producte.getNom())) {
            String[] dades = carritoMap.get(producte.getNom());
            int quantitat = Integer.parseInt(dades[0]) + canviQuantitat;
            if (quantitat <= 0) {
                carritoMap.remove(producte.getNom());
            } else {
                dades[0] = String.valueOf(quantitat);
                carritoMap.put(producte.getNom(), dades);
            }
        } else if (canviQuantitat > 0) {
            carritoMap.put(producte.getNom(), new String[] { "1", producte.getPreu(), producte.getImatgePath() });
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("carrito.txt"))) {
            for (Map.Entry<String, String[]> entry : carritoMap.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue()[0] + " - " + entry.getValue()[1] + " - "
                        + entry.getValue()[2]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escrivint al carrito: " + e.getMessage());
        }
    }

    private void actualitzarStock(Producte producte, int canvi) {
        List<String> stockLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("stock.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(producte.getNom() + " - ")) {
                    String[] parts = line.split(" - ");
                    int stock = Integer.parseInt(parts[1]) + canvi;
                    producte.setStock(stock);
                    line = producte.getNom() + " - " + stock + " - " + parts[2] + " - " + parts[3];
                }
                stockLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error llegint l'stock: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stock.txt"))) {
            for (String s : stockLines) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escrivint al stock: " + e.getMessage());
        }
    }

    @FXML
    private void handleCarrito(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Carret.fxml"));
        Pane carretRoot = fxmlLoader.load();
        Stage currentStage = (Stage) producteGrid.getScene().getWindow();
        currentStage.getScene().setRoot(carretRoot);
        currentStage.show();
    }
}
