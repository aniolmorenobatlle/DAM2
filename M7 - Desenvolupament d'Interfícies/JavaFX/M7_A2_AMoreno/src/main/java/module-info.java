module dam.amoreno.m7_a2_amoreno {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens dam.amoreno.m7_a2_amoreno to javafx.fxml;
    exports dam.amoreno.m7_a2_amoreno;
}