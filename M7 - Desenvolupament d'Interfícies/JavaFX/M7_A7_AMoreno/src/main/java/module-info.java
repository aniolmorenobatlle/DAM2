module dam.amoreno.m7_a7_amoreno {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens dam.amoreno.m7_a7_amoreno to javafx.fxml;

    opens dam.amoreno.m7_a7_amoreno.Pantalla1 to javafx.fxml;
    opens dam.amoreno.m7_a7_amoreno.Examen to javafx.fxml;
    opens dam.amoreno.m7_a7_amoreno.Carnet to javafx.fxml;

    exports dam.amoreno.m7_a7_amoreno;
    exports dam.amoreno.m7_a7_amoreno.Pantalla1;
    exports dam.amoreno.m7_a7_amoreno.Examen;
    exports dam.amoreno.m7_a7_amoreno.Carnet;

}