package com.okaka.pm.domain.prompt.entity.vo;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * 小括号提示词
 * @author okaka
 * @date 2023-09-06
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpenBracketPrompt extends BracketPrompt {

    public OpenBracketPrompt(String promptName, int nestingTimes) {
        super(promptName, nestingTimes);
    }

    @Override
    protected String leftBracket() {
        return "(";
    }

    @Override
    protected String rightBracket() {
        return ")";
    }
}
