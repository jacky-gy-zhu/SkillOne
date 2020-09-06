package com.skillone.common.logger;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class Log {

    static {
        //TODO local log file path
    }

    private static String errorPrompt() {
        return "[error]* ";
    }

    public static void error(String content) {
        println(errorPrompt() + content, false);
    }

    public static void error(String content, boolean storeToFile) {
        println(errorPrompt() + content, storeToFile);
    }

    public static void println(String content) {
        println(content, false);
    }

    public static void println(List<String> list) {
        println(list, false);
    }

    public static void println(List<String> list, boolean storeToFile) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(line -> println(line, storeToFile));
        }
    }

    public static void println(String content, boolean storeToFile) {
        System.out.println(content);
        if (storeToFile) {

        }
    }

}
