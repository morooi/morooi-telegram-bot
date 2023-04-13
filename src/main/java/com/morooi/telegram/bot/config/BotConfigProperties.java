package com.morooi.telegram.bot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Zhou.Shaojing
 * @date 2023/1/29 15:19
 */
@Data
@ConfigurationProperties(prefix = "morooi.bot")
public class BotConfigProperties {

    private String botName;

    private String botToken;

    private Long creatorId;

}
