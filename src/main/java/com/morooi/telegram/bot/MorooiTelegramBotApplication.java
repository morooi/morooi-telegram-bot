package com.morooi.telegram.bot;

import com.morooi.telegram.bot.config.BotConfigProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;

@SpringBootApplication
@EnableConfigurationProperties({BotConfigProperties.class})
@Import({TelegramBotStarterConfiguration.class})
@MapperScan("com.morooi.telegram.bot.db.mapper")
public class MorooiTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MorooiTelegramBotApplication.class, args);
    }

}
