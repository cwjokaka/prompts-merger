package com.okaka.pm.Infrastructure.util;

import com.okaka.pm.domain.prompt.entity.aggregate.PromptAggregate;

/**
 * 提示词解析器
 * @author okaka
 * @date 2023-09-11
 */
public class PromptParser {

    private ParserMode parserMode = ParserMode.NORMAL;
    private int nestingTimes = 0;

    public PromptAggregate parse(String promptsText) {
        for (char currentChar : promptsText.toCharArray()) {

        }
        return null;
    }

    private enum ParserMode {
        NORMAL
    }

}
