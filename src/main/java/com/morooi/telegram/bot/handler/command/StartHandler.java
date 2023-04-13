package com.morooi.telegram.bot.handler.command;

import cn.hutool.core.util.StrUtil;
import com.morooi.telegram.bot.contant.CommandEnum;
import com.morooi.telegram.bot.handler.AbstractCommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

/**
 * @author Zhou.Shaojing
 * @date 2023/4/13 10:26
 */
@Component
public class StartHandler extends AbstractCommandHandler {

    @Override
    public String command() {
        return CommandEnum.START.getCommand();
    }

    @Override
    public void handleMessage(Message message, SendMessage answer) {
        User user = message.getFrom();
        answer.setText(StrUtil.format("{} {}，欢迎使用 morooi's Bot", user.getFirstName(), user.getLastName()));
    }
}
