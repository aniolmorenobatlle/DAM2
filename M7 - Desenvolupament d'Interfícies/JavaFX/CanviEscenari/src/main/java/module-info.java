module dam.amoreno.canviescenari {
    requires javafx.controls;
    requires javafx.fxml;


    opens dam.amoreno.canviescenari to javafx.fxml;
    exports dam.amoreno.canviescenari;
}