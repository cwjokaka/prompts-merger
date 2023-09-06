package com.okaka.pm.domain.prompt.vo;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * 中括号提示词
 * @author okaka
 * @date 2023-09-06
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SquareBracketPrompt extends BracketPrompt {

    public SquareBracketPrompt(String promptName, int nestingTimes) {
        super(promptName, nestingTimes);
    }

    @Override
    protected String leftBracket() {
        return "[";
    }

    @Override
    protected String rightBracket() {
        return "]";
    }

}
