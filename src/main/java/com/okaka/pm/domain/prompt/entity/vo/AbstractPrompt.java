package com.okaka.pm.domain.prompt.entity.vo;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * @author okaka
 * @date 2023-09-06
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractPrompt implements Prompt {

    final String promptName;

    public AbstractPrompt(String promptName) {
        this.promptName = promptName;
    }

    @Override
    public String getPromptName() {
        return promptName;
    }

}
