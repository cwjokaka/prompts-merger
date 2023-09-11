package com.okaka.pm.domain.prompt.entity.vo;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * 普通提示词
 * @author okaka
 * @date 2023-09-05
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NormalPrompt extends AbstractPrompt {

    public NormalPrompt(String promptName) {
        super(promptName);
    }

    @Override
    public String toPromptString() {
        return super.getPromptContent();
    }

}
