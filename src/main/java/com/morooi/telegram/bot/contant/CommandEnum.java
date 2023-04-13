package com.morooi.telegram.bot.contant;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Zhou.Shaojing
 * @date 2023/1/29 18:50
 */
@Getter
@AllArgsConstructor
public enum CommandEnum {

    START("/start", "开始"),
    INFO("/info", "查看信息"),
    BWG_BIND("/bwg_bind", "绑定搬瓦工信息"),
    BWG_INFO("/bwg_info", "查看搬瓦工信息"),
    DEFAULT("/default", "其他"),
    ;

    private final String command;
    private final String desc;

    public static CommandEnum getByCommand(String command) {
        if (StrUtil.isBlank(command)) {
            return DEFAULT;
        }
        command = command.split(" ")[0];
        for (CommandEnum value : CommandEnum.values()) {
            if (value.getCommand().equals(command)) {
                return value;
            }
        }
        return DEFAULT;
    }

}
