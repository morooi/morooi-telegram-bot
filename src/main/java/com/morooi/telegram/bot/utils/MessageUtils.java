package com.morooi.telegram.bot.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Zhou.Shaojing
 * @date 2023/4/13 11:46
 */
public class MessageUtils {

    public static String replaceForMarkDownV2(String input) {
        if (StringUtils.isBlank(input)) {
            return input;
        }
        return input.replaceAll("[_*\\[\\]()~`>#+=\\-|{}.!]", "\\\\$0");
    }

}
