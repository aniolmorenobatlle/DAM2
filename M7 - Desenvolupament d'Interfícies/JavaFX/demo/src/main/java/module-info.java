module dam.amoreno.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens dam.amoreno.demo to javafx.fxml;
    exports dam.amoreno.demo;
}