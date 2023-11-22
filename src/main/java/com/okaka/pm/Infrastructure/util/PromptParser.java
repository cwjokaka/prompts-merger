package com.okaka.pm.Infrastructure.util;

import com.okaka.pm.domain.prompt.entity.aggregate.PromptAggregate;
import com.okaka.pm.domain.prompt.entity.vo.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 提示词文本解析器
 * @author okaka
 * @date 2023-09-11
 */
public class PromptParser {

    private ParserMode parserMode = ParserMode.SKIPPING;
    private ProcessingPromptType processingPromptType = ProcessingPromptType.NONE;

    private final List<Prompt> promptsCache = new ArrayList<>();

    private final Deque<Character> bracketStack = new ArrayDeque<>();

    private int nestingCount = 0;
    private StringBuilder processingPromptStr = new StringBuilder();


    public PromptAggregate parse(String promptsText) {
        for (char c : promptsText.toCharArray()) {
            switch (c) {
                case ' ':
                    processSpace();
                    break;
                case '\n':
                case '\t':
                    break;
                case ',':
                    processComma();
                    break;
                case ':':
                    processColon();
                    break;
                case '|':
                    continue;
                case '<':
                case '(':
                case '[':
                case '{':
                    processLeftBracket(c);
                    break;
                case '>':
                case ')':
                case ']':
                case '}':
                    processRightBracket(c);
                    break;
                default:
                    processNormalChar(c);
            }
        }

        if (!bracketStack.isEmpty()) {
            throw new RuntimeException("语法错误:'" + bracketStack.peek() + "'没有闭合");
        }

        return PromptAggregate.builder()
                .id(UUID.randomUUID().toString())
                .prompts(promptsCache)
                .build();
    }

    private void validateRightBracket(char rightBracket) {
        Character leftBracket = bracketStack.peek();
        if (leftBracket == null) {
            throw new RuntimeException("括号语法错误, 有剩余的右括号" + rightBracket);
        }
        switch (rightBracket) {
            case '>' -> PMAssert.assertTrue(leftBracket == '<', "尖括号不一致:左" + leftBracket + "右" + rightBracket);
            case ')' -> PMAssert.assertTrue(leftBracket == '(', "圆括号不一致:左" + leftBracket + "右" + rightBracket);
            case ']' -> PMAssert.assertTrue(leftBracket == '[', "方括号不一致:左" + leftBracket + "右" + rightBracket);
            case '}' -> PMAssert.assertTrue(leftBracket == '{', "花括号不一致:左" + leftBracket + "右" + rightBracket);
        }
    }

    private boolean isFinalBracket() {
        return bracketStack.isEmpty();
    }

    private void processNormalChar(char c) {
        parserMode = ParserMode.CREATING_PROMPT;
        if (processingPromptType == ProcessingPromptType.NONE) {
            processingPromptType = ProcessingPromptType.NORMAL_PROMPT;
        }
        processingPromptStr.append(c);

    }

    private void processColon() {
        PMAssert.assertTrue(parserMode == ParserMode.CREATING_PROMPT, "出现未知的冒号':'");
        switch (processingPromptType) {
            case NONE -> throw new RuntimeException("出现未知的冒号':'");
            case NORMAL_PROMPT -> throw new RuntimeException("普通提示词不能出现冒号':'");
            case CURLY_BRACKET_PROMPT -> throw new RuntimeException("花括号示词内不能出现冒号':'");
            case SQUARE_BRACKET_PROMPT -> throw new RuntimeException("方括号提示词内不能出现冒号':'");
            case OPEN_BRACKET_PROMPT -> this.processingPromptType = ProcessingPromptType.OPEN_BRACKET_WEIGHT_PROMPT;
            case ANGLE_BRACKET_PROMPT -> this.processingPromptType = ProcessingPromptType.ANGLE_BRACKET_WEIGHT_PROMPT;
        }
        processingPromptStr.append(":");
    }

    private void processComma() {
        if (parserMode == ParserMode.SKIPPING) {
            parserMode = ParserMode.CREATING_PROMPT;
            return;
        }
        PMAssert.assertTrue(parserMode == ParserMode.CREATING_PROMPT, "未知的parserMode");
        switch (processingPromptType) {
            case NONE -> {}
            case NORMAL_PROMPT -> {
                createNormalPrompt();
                resetProcessingPrompt();
            }
            default -> {
                processingPromptStr.append(",");
            }
        }
    }

    private void processLeftBracket(char leftBracket) {
        Character preBracket = bracketStack.peek();
        if (preBracket != null && leftBracket != preBracket) {
            throw new RuntimeException("嵌套了不同的括号:" + preBracket + leftBracket);
        }
        switch (leftBracket) {
            case '<' -> processingPromptType = ProcessingPromptType.ANGLE_BRACKET_PROMPT;
            case '(' -> processingPromptType = ProcessingPromptType.OPEN_BRACKET_PROMPT;
            case '[' -> processingPromptType = ProcessingPromptType.SQUARE_BRACKET_PROMPT;
            case '{' -> processingPromptType = ProcessingPromptType.CURLY_BRACKET_PROMPT;
            default -> throw new RuntimeException("未知的括号类型:" + leftBracket);
        }
        bracketStack.push(leftBracket);
    }

    private void processRightBracket(char leftBracket) {
        validateRightBracket(leftBracket);
        bracketStack.pop();
        nestingCount++;
        if (isFinalBracket()) {
            createBracketPrompt();
            resetProcessingPrompt();
            parserMode = ParserMode.SKIPPING;
        }
    }

    private void processSpace() {
        if (parserMode == ParserMode.SKIPPING) {
            return;
        }
        if (parserMode == ParserMode.CREATING_PROMPT) {
            processingPromptStr.append(' ');
        }
    }

    private void createNormalPrompt() {
        String promptContent = processingPromptStr.toString();
        if (promptContent.isEmpty()) {
            return;
        }
        NormalPrompt normalPrompt = new NormalPrompt(promptContent);
        promptsCache.add(normalPrompt);
    }

    private void createBracketPrompt() {
        String promptContent = processingPromptStr.toString();
        if (promptContent.isEmpty()) {
            return;
        }
        String[] promptNames = promptContent.split(",");
        Prompt prompt;
        switch (processingPromptType) {
            case ANGLE_BRACKET_PROMPT -> prompt = new AngleBracketPrompt(promptNames);
            case OPEN_BRACKET_PROMPT -> prompt = new OpenBracketPrompt(nestingCount, promptNames);
            case SQUARE_BRACKET_PROMPT -> prompt = new SquareBracketPrompt(nestingCount, promptNames);
            case CURLY_BRACKET_PROMPT -> prompt = new CurlyBracketPrompt(nestingCount, promptNames);
            case ANGLE_BRACKET_WEIGHT_PROMPT -> {
                String[] split = promptNames[promptNames.length - 1].split(":");
                promptNames[promptNames.length - 1] = split[0];
                BigDecimal weight = new BigDecimal(split[1]);
                prompt = new AngleBracketWeightPrompt(weight, promptNames);
            }
            case OPEN_BRACKET_WEIGHT_PROMPT -> {
                String[] split = promptNames[promptNames.length - 1].split(":");
                promptNames[promptNames.length - 1] = split[0];
                BigDecimal weight = new BigDecimal(split[1]);
                prompt = new OpenBracketWeightPrompt(weight, promptNames);
            }
            default -> throw new RuntimeException();
        }
        promptsCache.add(prompt);
    }

    private void resetProcessingPrompt() {
        processingPromptStr = new StringBuilder();
        processingPromptType = ProcessingPromptType.NONE;
        nestingCount = 0;
    }

    private enum ParserMode {
        SKIPPING,
        CREATING_PROMPT,
    }

    private enum ProcessingPromptType {
        NONE,
        // level 1
        NORMAL_PROMPT,
        ANGLE_BRACKET_PROMPT,
        OPEN_BRACKET_PROMPT,
        SQUARE_BRACKET_PROMPT,
        CURLY_BRACKET_PROMPT,
        // Upgrade to weightPrompt (level 2)
        ANGLE_BRACKET_WEIGHT_PROMPT,
        OPEN_BRACKET_WEIGHT_PROMPT,

    }

}
