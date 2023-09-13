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

    public OpenBracketPrompt(int nestingTimes, String... promptNames) {
        super(nestingTimes, promptNames);
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
