package com.okaka.pm.domain.prompt.entity.vo;

/**
 * 花括号提示词
 * @author okaka
 * @date 2023-09-06
 */
public class CurlyBracketPrompt extends BracketPrompt {

    public CurlyBracketPrompt(String... promptNames) {
        super(promptNames);
    }

    public CurlyBracketPrompt(int nestingTimes, String... promptName) {
        super(nestingTimes, promptName);
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
