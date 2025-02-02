module dam.amoreno.m7_a1_amoreno {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens dam.amoreno.m7_a1_amoreno to javafx.fxml;
    exports dam.amoreno.m7_a1_amoreno;
}