package com.okaka.pm;

import com.okaka.pm.Infrastructure.config.ApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        initStage(stage);
        ApplicationContext.setStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 初始化窗台
     */
    private void initStage(Stage stage) {
        // 设置有自带窗体栏的窗口样式
        stage.initStyle(StageStyle.DECORATED);
        // 标题
        stage.setTitle("PromptMerger");
        // 图标
        stage.getIcons().add(new Image(HelloApplication.class.getResource("icon/winIcon.png").toString()));
        // 窗体不可拉伸、缩小
//        stage.setResizable(false);
        // 窗体关闭回调
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(String[] args) {
        launch();
    }
}