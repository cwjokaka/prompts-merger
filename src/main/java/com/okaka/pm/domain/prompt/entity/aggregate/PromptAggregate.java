package com.okaka.pm.domain.prompt.entity.aggregate;

import com.okaka.pm.domain.prompt.entity.vo.Prompt;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author okaka
 * @date 2023-09-06
 */
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class PromptAggregate implements Prompt {

    String id;

    List<Prompt> prompts;


    @Override
    public String toPromptString() {
        return prompts.stream().map(Prompt::toPromptString).collect(Collectors.joining(", "));
    }

    @Override
    public String getPromptContent() {
        return null;
    }
}
