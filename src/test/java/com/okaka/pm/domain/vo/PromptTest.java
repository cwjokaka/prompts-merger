package com.okaka.pm.domain.vo;

import com.okaka.pm.domain.prompt.entity.vo.*;
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
    }

    @Test
    public void testCurlyBracketPrompt() {
        Prompt prompt = new CurlyBracketPrompt(1, "white flower");
        Assertions.assertEquals("{white flower}", prompt.toPromptString());
    }

    @Test
    public void testManyCurlyBracketPrompt() {
        Prompt prompt = new CurlyBracketPrompt(3, "white flower");
        Assertions.assertEquals("{{{white flower}}}", prompt.toPromptString());
    }

    @Test
    public void testOpenBracketPrompt() {
        Prompt prompt = new OpenBracketPrompt(1, "white flower");
        Assertions.assertEquals("(white flower)", prompt.toPromptString());
    }

    @Test
    public void testManyOpenBracketPrompt() {
        Prompt prompt = new OpenBracketPrompt(3, "white flower");
        Assertions.assertEquals("(((white flower)))", prompt.toPromptString());
    }

    @Test
    public void testSquareBracketPrompt() {
        Prompt prompt = new SquareBracketPrompt(1, "white flower");
        Assertions.assertEquals("[white flower]", prompt.toPromptString());
    }

    @Test
    public void testManySquareBracketPrompt() {
        Prompt prompt = new SquareBracketPrompt(3, "white flower");
        Assertions.assertEquals("[[[white flower]]]", prompt.toPromptString());
    }
    @Test
    public void testManySquareBracketPrompts() {
        Prompt prompt = new SquareBracketPrompt(3, "white flower", "black flower");
        Assertions.assertEquals("[[[white flower, black flower]]]", prompt.toPromptString());
    }

}
