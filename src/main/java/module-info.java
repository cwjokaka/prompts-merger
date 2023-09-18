module com.okaka.pm {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;

    requires static lombok;
//    requires fastjson;
    requires cn.hutool;

    opens com.okaka.pm to javafx.fxml;
    exports com.okaka.pm;
    exports com.okaka.pm.interfaces.controller;
    opens com.okaka.pm.interfaces.controller to javafx.fxml;
}