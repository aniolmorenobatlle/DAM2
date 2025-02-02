module dam.amoreno.exemple_validacio_interna {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens dam.amoreno.exemple_validacio_interna to javafx.fxml;
    exports dam.amoreno.exemple_validacio_interna;
    exports dam.amoreno.exemple_validacio_interna.programa;
    opens dam.amoreno.exemple_validacio_interna.programa to javafx.fxml;
    exports dam.amoreno.exemple_validacio_interna.principal;
    opens dam.amoreno.exemple_validacio_interna.principal to javafx.fxml;
}