package com.okaka.pm.domain.prompt.entity.vo;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author okaka
 * @date 2023-09-06
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractPrompt implements Prompt {

    final List<String> promptNames;

    public AbstractPrompt(String... promptNames) {
        this.promptNames = Arrays.asList(promptNames);
    }

    @Override
    public String getPromptContent() {
        return String.join(", ", promptNames);
    }

}
