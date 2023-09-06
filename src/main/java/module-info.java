module com.okaka.pm {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
//    requires fastjson;
//    requires hutool.all;

    opens com.okaka.pm to javafx.fxml;
    exports com.okaka.pm;
}