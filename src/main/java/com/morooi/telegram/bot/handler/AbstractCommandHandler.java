package com.morooi.telegram.bot.handler;

import cn.hutool.json.JSONUtil;
import com.morooi.telegram.bot.contant.CommandEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Zhou.Shaojing
 * @date 2023/1/29 16:41
 */
@Slf4j
@Component
public abstract class AbstractCommandHandler implements InitializingBean {

    public abstract String command();

    public void onMessage(DefaultAbsSender defaultAbsSender, Message message) {
        log.info("message: {}", JSONUtil.toJsonStr(message));

        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());
        answer.setParseMode(ParseMode.MARKDOWNV2);

        this.handleMessage(message, answer);

        try {
            defaultAbsSender.execute(answer);
        } catch (TelegramApiException e) {
            log.error("发送消息失败, message: {}, answer: {}", JSONUtil.toJsonStr(message), JSONUtil.toJsonStr(answer), e);
        }
    }

    public abstract void handleMessage(Message message, SendMessage answer);

    @Override
    public void afterPropertiesSet() {
        CommandHandlerFactory.registerCommandHandler(this.command(), this);
    }
}
