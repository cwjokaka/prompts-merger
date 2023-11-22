package com.okaka.pm.interfaces.controller;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.okaka.pm.Infrastructure.config.ApplicationContext;
import com.okaka.pm.Infrastructure.util.PromptParser;
import com.okaka.pm.domain.prompt.entity.aggregate.PromptAggregate;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;

/**
 * 提示词提取Controller
 * @author okaka
 * @date 2023-09-15
 */
public class TextExtractController {

    @FXML
    private TextArea promptTextInput;

    private PromptParser promptParser = new PromptParser();

    /**
     * 页面初始化时会自动回调此方法
     */
    @FXML
    public void initialize() {
        System.out.println("initialize!!!!");
    }

    @FXML
    protected void onCommitExtract() {
        String text = promptTextInput.getText();
        try {
            PromptAggregate parse = promptParser.parse(text);
            System.out.println(parse);
        } catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText(e.getMessage());
//            alert.show();
            JFXAlert<Void> alert = new JFXAlert<>(ApplicationContext.getStage());
            alert.setOverlayClose(true);
            alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
            alert.setContent(new Label(e.getMessage()));
            alert.initModality(Modality.NONE);
            alert.show();
        }
    }

}
