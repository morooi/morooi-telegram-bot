package com.morooi.telegram.bot.bots;

import cn.hutool.json.JSONUtil;
import com.morooi.telegram.bot.config.BotConfigProperties;
import com.morooi.telegram.bot.constant.CommandEnum;
import com.morooi.telegram.bot.handler.AbstractCommandHandler;
import com.morooi.telegram.bot.handler.CommandHandlerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Zhou.Shaojing
 * @date 2023/1/29 15:14
 */
@Slf4j
@Component
public class MorooiBot extends TelegramLongPollingBot {

    private final BotConfigProperties botConfigProperties;

    public MorooiBot(BotConfigProperties botConfigProperties) {
        super(botConfigProperties.getBotToken());
        this.botConfigProperties = botConfigProperties;
    }

    @Override
    public String getBotUsername() {
        return botConfigProperties.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update == null) {
            log.error("更新信息为空");
            return;
        }

        Message message = update.getMessage() == null ? update.getEditedMessage() : update.getMessage();
        if (!message.isUserMessage()) {
            return;
        }

        if (message.isCommand()) {
            CommandEnum command = CommandEnum.getByCommand(message.getText());
            AbstractCommandHandler commandHandler = CommandHandlerFactory.getCommandHandler(command.getCommand());
            commandHandler.onMessage(this, message);
            return;
        }

        log.info("输入的不是命令, update: {}", JSONUtil.toJsonStr(update));

        SendMessage answer = new SendMessage();
        answer.setChatId(message.getChatId());
        answer.setText("只支持输入命令");

        try {
            execute(answer);
        } catch (TelegramApiException e) {
            log.error("发送消息失败, update: {}, answer: {}", JSONUtil.toJsonStr(update), JSONUtil.toJsonStr(answer), e);
        }
    }

}
