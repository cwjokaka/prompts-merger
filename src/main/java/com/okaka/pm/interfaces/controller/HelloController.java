package com.okaka.pm.interfaces.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.jfoenix.controls.JFXTabPane;
import com.okaka.pm.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class HelloController {

    @FXML
    private JFXTabPane tabPane;
//    @FXML
//    private AnchorPane content;
    @FXML
    private VBox buttonGroup;

    @FXML
    protected void onHelloButtonClick() {
//        String text = inputArea.getText();
//        welcomeText.setText("Welcome to JavaFX Application!");
    }

    /**
     * 页面初始化时会自动回调此方法
     */
    @FXML
    public void initialize() {
        System.out.println("initialize!!!!");
        bindButtonGroupEvent();
    }

    private void bindButtonGroupEvent() {
        for (Node node: buttonGroup.getChildren()){
            if (node.getAccessibleText() != null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    onClick(node.getAccessibleText());
                });
            }
        }
    }

    private void onClick(String jumpPage) {
        try {
            List<Tab> tabs = tabPane.getTabs().stream().filter(tab -> tab.getText().equals(jumpPage)).toList();
            if (CollectionUtil.isNotEmpty(tabs)) {
                tabPane.getSelectionModel().select(tabs.get(0));
                return;
            }
//            this.content.getChildren().clear();
            Node contentPage = FXMLLoader.load(HelloApplication.class.getResource("view/" + jumpPage + ".fxml"));
//            this.content.getChildren().add(contentPage);
            Tab tab = new Tab(jumpPage, contentPage);
            tab.setClosable(true);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}