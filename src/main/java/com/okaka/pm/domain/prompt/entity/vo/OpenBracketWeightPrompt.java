package com.okaka.pm.domain.prompt.entity.vo;

import java.math.BigDecimal;

/**
 * @author okaka
 * @date 2023-09-13
 */
public class OpenBracketWeightPrompt extends BracketWeightPrompt {

    public OpenBracketWeightPrompt(BigDecimal weight, String... promptNames) {
        super(weight, promptNames);
    }

    @Override
    public String getPromptContent() {
        return String.join(", ", promptNames) + ":" + weight;
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
