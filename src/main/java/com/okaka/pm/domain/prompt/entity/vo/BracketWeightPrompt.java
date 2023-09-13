package com.okaka.pm.domain.prompt.entity.vo;

import java.math.BigDecimal;

/**
 * 权重提示词
 * @author okaka
 * @date 2023-09-13
 */
public abstract class BracketWeightPrompt extends BracketPrompt implements WeightPrompt {

    final BigDecimal weight;

    public BracketWeightPrompt(BigDecimal weight, String... promptNames) {
        super(1, promptNames);
        this.weight = weight;
    }

    @Override
    public BigDecimal getWeight() {
        return this.weight;
    }
}
