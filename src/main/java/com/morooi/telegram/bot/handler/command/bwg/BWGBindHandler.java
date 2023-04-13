package com.morooi.telegram.bot.handler.command.bwg;

import com.morooi.telegram.bot.contant.CommandEnum;
import com.morooi.telegram.bot.db.mapper.BwgApiKeyMapper;
import com.morooi.telegram.bot.db.model.BwgApiKeyDO;
import com.morooi.telegram.bot.handler.AbstractCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * @author Zhou.Shaojing
 * @date 2023/4/13 11:31
 */
@Component
@RequiredArgsConstructor
public class BWGBindHandler extends AbstractCommandHandler {

    private final BwgApiKeyMapper bwgApiKeyMapper;

    @Override
    public String command() {
        return CommandEnum.BWG_BIND.getCommand();
    }

    @Override
    public void handleMessage(Message message, SendMessage answer) {
        String[] splitText = message.getText().split("\\s+");
        if (splitText.length != 3) {
            answer.setText("请在命令后指定您的 VEID 和 API KEY，用空格分隔\n如：`/bwg_bind VEID API_KEY`");
            return;
        }

        Long userId = message.getFrom().getId();
        String veid = splitText[1], apiKey = splitText[2];

        BwgApiKeyDO db = new BwgApiKeyDO();
        db.setUserId(userId);
        db.setVeid(veid);
        db.setApiKey(apiKey);

        if (bwgApiKeyMapper.selectByUserId(userId) == null) {
            bwgApiKeyMapper.insert(db);
        } else {
            bwgApiKeyMapper.updateByUserId(db);
        }
        answer.setText("*绑定成功*！\n请使用 /bwg\\_info 命令获取信息");
    }
}
