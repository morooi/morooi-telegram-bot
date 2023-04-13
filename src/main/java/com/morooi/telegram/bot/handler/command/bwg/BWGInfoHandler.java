package com.morooi.telegram.bot.handler.command.bwg;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.morooi.telegram.bot.contant.CommandEnum;
import com.morooi.telegram.bot.db.mapper.BwgApiKeyMapper;
import com.morooi.telegram.bot.db.model.BwgApiKeyDO;
import com.morooi.telegram.bot.handler.AbstractCommandHandler;
import com.morooi.telegram.bot.model.BwgApiDTO;
import com.morooi.telegram.bot.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * @author Zhou.Shaojing
 * @date 2023/4/13 11:31
 */
@Component
@RequiredArgsConstructor
public class BWGInfoHandler extends AbstractCommandHandler {

    private final BwgApiKeyMapper bwgApiKeyMapper;

    @Override
    public String command() {
        return CommandEnum.BWG_INFO.getCommand();
    }

    @Override
    public void handleMessage(Message message, SendMessage answer) {
        User user = message.getFrom();
        BwgApiKeyDO bwgApiKeyDO = bwgApiKeyMapper.selectByUserId(user.getId());
        if (bwgApiKeyDO == null) {
            answer.setText("请先使用 /bwg\\_bind 命令绑定 VEID 和 API KEY");
            return;
        }

        BwgApiDTO serviceInfo = getServiceInfo(bwgApiKeyDO.getVeid(), bwgApiKeyDO.getApiKey());
        if (serviceInfo == null || !Integer.valueOf(0).equals(serviceInfo.getError())) {
            answer.setText("获取服务器信息失败，请确认 VEID 和 API KEY 是否正确\n确认后重新使用 /bwg\\_bing 命令更新信息");
            return;
        }

        String hostname = MessageUtils.replaceForMarkDownV2(serviceInfo.getHostName());
        String nodeDatacenter = MessageUtils.replaceForMarkDownV2(serviceInfo.getNodeDataCenter());
        String ipAddresses = MessageUtils.replaceForMarkDownV2(String.join(", ", serviceInfo.getIpAddresses()));
        String planMonthlyData = MessageUtils.replaceForMarkDownV2(String.valueOf(serviceInfo.getPlanMonthlyData() >> 30));
        String dataCounter = MessageUtils.replaceForMarkDownV2(BigDecimal.valueOf(serviceInfo.getDataCounter() >> 20).divide(BigDecimal.valueOf(1024L), 2, RoundingMode.HALF_UP).toString());
        String dataPercent = MessageUtils.replaceForMarkDownV2(BigDecimal.valueOf(serviceInfo.getDataCounter()).multiply(BigDecimal.valueOf(100L)).divide(BigDecimal.valueOf(serviceInfo.getPlanMonthlyData()), 2, RoundingMode.HALF_UP).toString());
        String dataNextReset = MessageUtils.replaceForMarkDownV2(DateUtil.formatDateTime(new Date(serviceInfo.getDataNextReset() * 1000)));

        String info = StrUtil.format("*主机名*：{}\n*IP*：`{}`\n*数据中心*：{}\n*流量使用情况*：{} GB / {} GB \\({} %\\)\n*流量重置时间*：{}",
                hostname, ipAddresses, nodeDatacenter, dataCounter, planMonthlyData, dataPercent, dataNextReset);
        answer.setText(info);
    }

    private BwgApiDTO getServiceInfo(String veid, String apiKey) {
        String response = HttpUtil.get(StrUtil.format("https://api.64clouds.com/v1/getServiceInfo?veid={}&api_key={}", veid, apiKey));
        return JSON.parseObject(response, BwgApiDTO.class);
    }
}
