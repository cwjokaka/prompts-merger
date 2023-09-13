package com.okaka.pm.domain.prompt.entity.vo;

/**
 * @author okaka
 * @date 2023-09-13
 */
public class AngleBracketPrompt extends BracketPrompt {

    public AngleBracketPrompt(String... promptNames) {
        super(promptNames);
    }

    @Override
    protected String leftBracket() {
        return "<";
    }

    @Override
    protected String rightBracket() {
        return ">";
    }
}
