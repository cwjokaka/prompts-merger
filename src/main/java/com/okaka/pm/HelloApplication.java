package com.okaka.pm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

//        Panel panel = new Panel("This is the title");
//        panel.getStyleClass().add("panel-primary");                            //(2)
//        BorderPane content = new BorderPane();
//        content.setPadding(new Insets(20));
//        Button button = new Button("Hello BootstrapFX");
//        button.getStyleClass().setAll("btn","btn-danger");                     //(2)
//        content.setCenter(button);
//        panel.setBody(content);
//
//        Scene scene = new Scene(panel);
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());       //(3)
//
//        stage.setTitle("BootstrapFX");
//        stage.setScene(scene);
//        stage.sizeToScene();
//        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}