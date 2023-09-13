package com.okaka.pm.domain.prompt.entity.vo;

import java.math.BigDecimal;

/**
 * 尖括号权重提示词
 * @author okaka
 * @date 2023-09-13
 */
public class AngleBracketWeightPrompt extends BracketWeightPrompt {

    public AngleBracketWeightPrompt(BigDecimal weight, String... promptNames) {
        super(weight, promptNames);
    }

    @Override
    public String getPromptContent() {
        return String.join(", ", promptNames) + ":" + weight;
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
