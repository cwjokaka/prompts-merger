package com.okaka.pm.domain.prompt.entity.vo;

import com.okaka.pm.Infrastructure.util.PMAssert;

/**
 * 括号提示词
 * @author okaka
 * @date 2023-09-06
 */
public abstract class BracketPrompt extends AbstractPrompt {

    /**
     * 括号嵌套的次数
     */
    final int nestingTimes;

    public BracketPrompt(String... promptNames) {
        this(1, promptNames);
    }

    public BracketPrompt(int nestingTimes, String... promptNames) {
        super(promptNames);
        PMAssert.assertTrue(nestingTimes >= 1, "括号嵌套次数不能小于1");
        this.nestingTimes = nestingTimes;
    }

    @Override
    public String toPromptString() {
        StringBuilder sb = new StringBuilder();
        sb.append(leftBracket().repeat(Math.max(0, nestingTimes)));
        sb.append(getPromptContent());
        sb.append(rightBracket().repeat(Math.max(0, nestingTimes)));
        return sb.toString();
    }

    protected abstract String leftBracket();
    protected abstract String rightBracket();

}
