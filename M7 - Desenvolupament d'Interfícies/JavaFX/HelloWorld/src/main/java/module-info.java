module dam.amoreno.helloworld {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens dam.amoreno.helloworld to javafx.fxml;
    exports dam.amoreno.helloworld;
}