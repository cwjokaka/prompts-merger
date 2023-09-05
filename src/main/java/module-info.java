module com.okaka.promptsmerger {
    requires javafx.controls;
    requires javafx.fxml;
            
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
            
    opens com.okaka.promptsmerger to javafx.fxml;
    exports com.okaka.promptsmerger;
}