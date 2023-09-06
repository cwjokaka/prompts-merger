package com.okaka.pm.Infrastructure.util;


/**
 * @author okaka
 * @date 2023-09-06
 */
public class PMAssert {

    public static void assertTrue(boolean condition, String failMessage) {
        if (condition) {
            return;
        }
        throw new RuntimeException(failMessage);
    }

    public static void assertNotEmpty(String text, String failMessage) {
        if (text != null && !text.trim().equals("")) {
            return;
        }
        throw new RuntimeException(failMessage);
    }

}
