package com.okaka.pm.interfaces.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField inputArea;

    @FXML
    protected void onHelloButtonClick() {
        String text = inputArea.getText();
//        welcomeText.setText("Welcome to JavaFX Application!");
    }
}