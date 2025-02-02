module dam.amoreno.canvidecorat {
    requires javafx.controls;
    requires javafx.fxml;


    opens dam.amoreno.canvidecorat to javafx.fxml;
    exports dam.amoreno.canvidecorat;
}