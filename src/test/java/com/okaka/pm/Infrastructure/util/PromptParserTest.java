package com.okaka.pm.Infrastructure.util;

import com.okaka.pm.domain.prompt.entity.aggregate.PromptAggregate;
import com.okaka.pm.domain.prompt.entity.vo.NormalPrompt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author okaka
 * @date 2023-09-13
 */
public class PromptParserTest {

    @Test
    public void testNormalPrompt() {
        String text = "sim,(flower), seem, [black], {day},(black flower, tower), [knife, desk], (((east,hail))), (food:1.3),(hot dog, apple, banana:1.2), <kitty>,<miky:0.6>";
        PromptParser promptParser = new PromptParser();
        PromptAggregate promptAggregate = promptParser.parse(text);
        System.out.println(promptAggregate.getId());
        System.out.println(promptAggregate.getPrompts());
        Assertions.assertEquals("sim, (flower), seem, [black], {day}, (black flower, tower), [knife, desk], (((east, hail))), (food:1.3), (hot dog, apple, banana:1.2), <kitty>, <miky:0.6>",
                promptAggregate.toPromptString());

    }

    @Test
    public void testWrongPrompt() {
        String text = "[(flower), [black], {day},(black flower, tower), [knife, desk], (((east,hail))), (food:1.3),(hot dog, apple, banana:1.2), <kitty>,<miky:0.6>";
        PromptParser promptParser = new PromptParser();
        PromptAggregate promptAggregate = promptParser.parse(text);
        System.out.println(promptAggregate.getPrompts());
    }

    @Test
    public void testWrongLeftBracket() {
        String text = "[(flower), <lora>";
        PromptParser promptParser = new PromptParser();
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> {
            PromptAggregate promptAggregate = promptParser.parse(text);
            System.out.println(promptAggregate.toPromptString());
        });
        System.out.println(runtimeException.getMessage());

    }

    @Test
    public void testWrongRightBracket() {
        String text = "(flower)], <lora>";
        PromptParser promptParser = new PromptParser();
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> {
            PromptAggregate promptAggregate = promptParser.parse(text);
            System.out.println(promptAggregate.toPromptString());
        });
        System.out.println(runtimeException.getMessage());
    }


}
