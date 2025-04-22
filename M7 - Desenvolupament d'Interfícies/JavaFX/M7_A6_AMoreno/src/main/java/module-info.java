module dam.amoreno.m7_a6_amoreno {
    requires javafx.controls;
    requires javafx.fxml;

    opens dam.amoreno.m7_a6_amoreno to javafx.fxml;

    exports dam.amoreno.m7_a6_amoreno;
    exports dam.amoreno.m7_a6_amoreno.Escenari1;
    exports dam.amoreno.m7_a6_amoreno.Escenari2;
    exports dam.amoreno.m7_a6_amoreno.Escenari3;
    exports dam.amoreno.m7_a6_amoreno.Escenari4;
    exports dam.amoreno.m7_a6_amoreno.Escenari5;

    opens dam.amoreno.m7_a6_amoreno.Escenari1 to javafx.fxml;
    opens dam.amoreno.m7_a6_amoreno.Escenari2 to javafx.fxml;
    opens dam.amoreno.m7_a6_amoreno.Escenari3 to javafx.fxml;
    opens dam.amoreno.m7_a6_amoreno.Escenari4 to javafx.fxml;
    opens dam.amoreno.m7_a6_amoreno.Escenari5 to javafx.fxml;
}