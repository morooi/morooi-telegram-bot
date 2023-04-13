## morooi's Telegram Bot

https://t.me/morooi_bot

### 设置 Telegram API 信息

`application.properties` 文件

```properties
morooi.bot.bot-token=${telegram-api}
morooi.bot.bot-name=${botName}
```

### 获取镜像

```shell
docker pull morooi/morooi-telegram-bot:latest
```

### 运行

```shell
docker compose up -d 
```