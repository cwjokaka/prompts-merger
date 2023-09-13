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
        String text = "(flower), [black], {day},(black flower, tower), [knife, desk], (((east,hail))), (food:1.3),(hot dog, apple, banana:1.2), <kitty>,<miky:0.6>";
        PromptParser promptParser = new PromptParser();
        PromptAggregate promptAggregate = promptParser.parse(text);
        System.out.println(promptAggregate.getId());
        System.out.println(promptAggregate.getPrompts());
//        Assertions.assertEquals("white flower", prompt.toPromptString());
    }


}
