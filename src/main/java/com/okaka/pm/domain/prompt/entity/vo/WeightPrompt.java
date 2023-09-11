package com.okaka.pm.domain.prompt.entity.vo;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * 数字权重提示词
 * @author okaka
 * @date 2023-09-06
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeightPrompt extends BracketPrompt {

    final BigDecimal weight;

    public WeightPrompt(BigDecimal weight, String... promptNames) {
        super(1, promptNames);
        this.weight = weight;
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
