package com.okaka.pm.domain.prompt.entity;

import com.okaka.pm.Infrastructure.util.PMAssert;
import com.okaka.pm.domain.prompt.vo.Prompt;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 提示语组
 * @author okaka
 * @date 2023-09-06
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromptGroup {

    @Getter
    final String id;

    @Getter
    String groupName;

    final List<Prompt> prompts;

    public PromptGroup(String groupName, List<Prompt> prompts) {
        PMAssert.assertNotEmpty(groupName, "提示词组名称不能为空");
        this.groupName = groupName;
        this.prompts = prompts;
        this.id = UUID.randomUUID().toString();
    }

    public PromptGroup(String groupName) {
        this(groupName, new ArrayList<>());
    }

    public void addPrompt(Prompt prompt) {
        this.prompts.add(prompt);
    }

}
