module dam.amoreno.menubar {
    requires javafx.controls;
    requires javafx.fxml;


    opens dam.amoreno.menubar to javafx.fxml;
    exports dam.amoreno.menubar;
}