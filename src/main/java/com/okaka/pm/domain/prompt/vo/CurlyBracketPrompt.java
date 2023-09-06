package com.okaka.pm.domain.prompt.vo;

/**
 * 花括号提示词
 * @author okaka
 * @date 2023-09-06
 */
public class CurlyBracketPrompt extends BracketPrompt {

    public CurlyBracketPrompt(String promptName, int nestingTimes) {
        super(promptName, nestingTimes);
    }

    @Override
    protected String leftBracket() {
        return "{";
    }

    @Override
    protected String rightBracket() {
        return "}";
    }

}
