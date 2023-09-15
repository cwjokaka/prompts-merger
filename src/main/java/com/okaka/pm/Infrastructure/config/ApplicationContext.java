package com.okaka.pm.Infrastructure.config;

import javafx.stage.Stage;

/**
 * @author okaka
 * @date 2023-09-15
 */
public class ApplicationContext {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ApplicationContext.stage = stage;
    }

}
