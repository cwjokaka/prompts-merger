package com.okaka.pm.interfaces.controller;

import com.jfoenix.controls.JFXTreeView;
import com.okaka.pm.interfaces.dto.PMFile;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import static com.okaka.pm.Infrastructure.config.ApplicationConstant.ICON_FOLDER;

/**
 * @author okaka
 * @date 2023-09-18
 */
public class PromptGroupController {

    @FXML
    public JFXTreeView<PMFile> groupTreeView;

    public TreeItem<PMFile> rootNode = new TreeItem<>(new PMFile("root", "/", PMFile.FileType.FOLDER));

    /**
     * 页面初始化时会自动回调此方法
     */
    @FXML
    public void initialize() {
        System.out.println("PromptGroupController initialized!!!!");
        groupTreeView.setRoot(rootNode);
        groupTreeView.setShowRoot(false);
        rootNode.setExpanded(true);
        initRightMouseMenu();

    }

    private void initRightMouseMenu() {
        // 监听文件树鼠标右键点击事件
        groupTreeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                TreeItem selectItem = groupTreeView.getSelectionModel().getSelectedItem();
                showMenu(selectItem != null);
            }
        });

    }

    private void showMenu(boolean showDeleteMenu) {
        ContextMenu menu = new ContextMenu();
        MenuItem addItem = new MenuItem("添加节点");
        addItem.setOnAction(e -> {
            showNewFileNameDialog();
        });
        menu.getItems().add(addItem);
        MenuItem addFolder = new MenuItem("添加文件夹");
        addFolder.setOnAction(e -> {
            showNewFolderNameDialog();
        });
        menu.getItems().add(addFolder);
        if (showDeleteMenu) {
            MenuItem deleteItem = new MenuItem("删除");
            deleteItem.setOnAction(e -> {
                TreeItem selectItem = groupTreeView.getSelectionModel().getSelectedItem();
                rootNode.getChildren().remove(selectItem);
            });
            menu.getItems().add(deleteItem);
        }
        groupTreeView.setContextMenu(menu);
    }

    private void showNewFileNameDialog() {
        Alert inputDialog = new Alert(Alert.AlertType.NONE);
        Window window = inputDialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest((e) -> {
            window.hide();
        });
        inputDialog.setTitle("输入文件名");
        VBox vBox = new VBox();
        TextField fileNameInput = new TextField();
        vBox.getChildren().add(fileNameInput);
        Button cancelBtn = new Button("取消");
        cancelBtn.setOnAction((e) -> {
            inputDialog.getDialogPane().getScene().getWindow().hide();
        });
        Button saveBtn = new Button("确定");
        saveBtn.setOnAction(e -> {
            TreeItem<PMFile> selectedItem = groupTreeView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                selectedItem.getChildren().add(new TreeItem<>(new PMFile(fileNameInput.getText(), "/" + fileNameInput.getText(), PMFile.FileType.FILE)));
                selectedItem.setExpanded(true);
            } else {
                rootNode.getChildren().add(new TreeItem<>(new PMFile(fileNameInput.getText(), "/" + fileNameInput.getText(), PMFile.FileType.FILE)));
            }
            inputDialog.getDialogPane().getScene().getWindow().hide();
        });
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(saveBtn);
        borderPane.setRight(cancelBtn);
        vBox.getChildren().add(new Separator());
        vBox.getChildren().add(borderPane);
        inputDialog.getDialogPane().setContent(vBox);
        inputDialog.showAndWait();
    }

    private void showNewFolderNameDialog() {
        Alert inputDialog = new Alert(Alert.AlertType.NONE);
        Window window = inputDialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest((e) -> {
            window.hide();
        });
        inputDialog.setTitle("输入文件夹名");
        VBox vBox = new VBox();
        TextField fileNameInput = new TextField();
        vBox.getChildren().add(fileNameInput);
        Button cancelBtn = new Button("取消");
        cancelBtn.setOnAction((e) -> {
            inputDialog.getDialogPane().getScene().getWindow().hide();
        });
        Button saveBtn = new Button("确定");
        saveBtn.setOnAction(e -> {
            TreeItem<PMFile> selectedItem = groupTreeView.getSelectionModel().getSelectedItem();
            createFolder(fileNameInput.getText(), selectedItem);
            inputDialog.getDialogPane().getScene().getWindow().hide();
        });
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(saveBtn);
        borderPane.setRight(cancelBtn);
        vBox.getChildren().add(new Separator());
        vBox.getChildren().add(borderPane);
        inputDialog.getDialogPane().setContent(vBox);
        inputDialog.showAndWait();
    }

    private void createFolder(String folderName, TreeItem<PMFile> parent) {
        if (parent != null) {
            String parentPath = parent.getValue().getFilePath() + "/";
            TreeItem<PMFile> folderItem = new TreeItem<>(new PMFile(folderName, parentPath + folderName, PMFile.FileType.FOLDER));
            folderItem.setGraphic(new ImageView(ICON_FOLDER));
            parent.getChildren().add(folderItem);
            parent.setExpanded(true);
        }
        else {
            TreeItem<PMFile> folderItem = new TreeItem<>(new PMFile(folderName, "/" + folderName, PMFile.FileType.FOLDER));
            folderItem.setGraphic(new ImageView(ICON_FOLDER));
            rootNode.getChildren().add(folderItem);
            rootNode.setExpanded(true);
        }

    }

}
