package com.okaka.pm.domain.prompt.vo;

public interface Prompt {

    String toPromptString();

    /**
     * 获取提示词名称
     * @return 提示词名称
     */
    String getPromptName();

}
