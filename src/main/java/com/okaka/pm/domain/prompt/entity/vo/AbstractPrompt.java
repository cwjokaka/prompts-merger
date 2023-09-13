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

    protected final List<String> promptNames;

    public AbstractPrompt(String... promptNames) {
        ArrayList<String> list = new ArrayList<>();
        for (String promptName : promptNames) {
            list.add(promptName.trim());
        }
        this.promptNames = list;
    }

    @Override
    public String getPromptContent() {
        return String.join(", ", promptNames);
    }

    @Override
    public String toString() {
        return toPromptString();
    }
}
