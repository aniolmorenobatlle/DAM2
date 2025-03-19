package dam.amoreno.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorPane1 {
    @FXML
    private Label Eti01;

    @FXML
    private Button Boto01;

    @FXML
    void canviTexteEtiqueta(ActionEvent event) {
        Eti01.setText("Canvi de Texte");
    }
}