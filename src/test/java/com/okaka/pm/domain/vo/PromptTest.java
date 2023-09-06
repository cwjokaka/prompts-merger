package com.okaka.pm.domain.vo;

import com.okaka.pm.domain.prompt.vo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author okaka
 * @date 2023-09-06
 */
public class PromptTest {

    @Test
    public void testNormalPrompt() {
        NormalPrompt prompt = new NormalPrompt("white flower");
        Assertions.assertEquals("white flower", prompt.toPromptString());
        Assertions.assertEquals("white flower", prompt.getPromptName());
    }

    @Test
    public void testCurlyBracketPrompt() {
        Prompt prompt = new CurlyBracketPrompt("white flower", 1);
        Assertions.assertEquals("{white flower}", prompt.toPromptString());
        Assertions.assertEquals("white flower", prompt.getPromptName());
    }

    @Test
    public void testManyCurlyBracketPrompt() {
        Prompt prompt = new CurlyBracketPrompt("white flower", 3);
        Assertions.assertEquals("{{{white flower}}}", prompt.toPromptString());
        Assertions.assertEquals("white flower", prompt.getPromptName());
    }

    @Test
    public void testOpenBracketPrompt() {
        Prompt prompt = new OpenBracketPrompt("white flower", 1);
        Assertions.assertEquals("(white flower)", prompt.toPromptString());
        Assertions.assertEquals("white flower", prompt.getPromptName());
    }

    @Test
    public void testManyOpenBracketPrompt() {
        Prompt prompt = new OpenBracketPrompt("white flower", 3);
        Assertions.assertEquals("(((white flower)))", prompt.toPromptString());
        Assertions.assertEquals("white flower", prompt.getPromptName());
    }

    @Test
    public void testSquareBracketPrompt() {
        Prompt prompt = new SquareBracketPrompt("white flower", 1);
        Assertions.assertEquals("[white flower]", prompt.toPromptString());
        Assertions.assertEquals("white flower", prompt.getPromptName());
    }

    @Test
    public void testManySquareBracketPrompt() {
        Prompt prompt = new SquareBracketPrompt("white flower", 3);
        Assertions.assertEquals("[[[white flower]]]", prompt.toPromptString());
        Assertions.assertEquals("white flower", prompt.getPromptName());
    }

}
