package com.morooi.telegram.bot.handler.command;

import cn.hutool.core.util.StrUtil;
import com.morooi.telegram.bot.constant.CommandEnum;
import com.morooi.telegram.bot.handler.AbstractCommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Zhou.Shaojing
 * @date 2023/4/13 11:32
 */
@Component
public class DefaultHandler extends AbstractCommandHandler {

    @Override
    public String command() {
        return CommandEnum.DEFAULT.getCommand();
    }

    @Override
    public void handleMessage(Message message, SendMessage answer) {
        answer.setText(StrUtil.format("未知的命令: {}", message.getText()));
    }
}
