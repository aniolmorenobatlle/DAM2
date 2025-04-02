module dam.amoreno.m7_a5_amoreno {
    requires javafx.controls;
    requires javafx.fxml;

    opens dam.amoreno.m7_a5_amoreno to javafx.fxml;
    opens dam.amoreno.m7_a5_amoreno.Compres to javafx.fxml;
    opens dam.amoreno.m7_a5_amoreno.Carret to javafx.fxml;
    opens dam.amoreno.m7_a5_amoreno.Factura to javafx.fxml;

    exports dam.amoreno.m7_a5_amoreno;
}