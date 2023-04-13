package com.morooi.telegram.bot.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zhou.Shaojing
 * @date 2023/4/13 10:25
 */
public class CommandHandlerFactory {

    private static final Map<String, AbstractCommandHandler> commandHandlerMap = new ConcurrentHashMap<>();

    public static AbstractCommandHandler getCommandHandler(String command) {
        return commandHandlerMap.get(command);
    }

    public static void registerCommandHandler(String command, AbstractCommandHandler messageHandler) {
        commandHandlerMap.put(command, messageHandler);
    }

}
