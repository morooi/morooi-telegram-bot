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
public class InfoHandler extends AbstractCommandHandler {

    @Override
    public String command() {
        return CommandEnum.INFO.getCommand();
    }

    @Override
    public void handleMessage(Message message, SendMessage answer) {
        User user = message.getFrom();
        String firstName = user.getFirstName(), lastName = user.getLastName();
        answer.setText(StrUtil.format("*INFO*\nfirstName: {}\nlastName: {}\nuserId: {}", firstName, lastName, user.getId().toString()));
    }
}
