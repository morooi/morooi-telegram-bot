FROM eclipse-temurin:17-jre-alpine
MAINTAINER SJ Zhou <morooiu@gmail.com>

WORKDIR /bot
ARG JAR_FILE
ADD target/${JAR_FILE} morooi-telegram-bot.jar

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java", "-jar", "morooi-telegram-bot.jar"]