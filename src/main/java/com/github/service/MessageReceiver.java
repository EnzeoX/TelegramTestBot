package com.github.service;

import com.github.bot.Bot;
import com.github.handler.BotMessageHandler;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageReceiver implements Runnable {

    private boolean isActive = true;

    private final Bot bot;

    private final BotMessageHandler messageHandler = new BotMessageHandler();

    public MessageReceiver(Bot data) {
        this.bot = data;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    @Override
    public void run() {
        while (isActive) {
            for (Update update = bot.receiveQueue.poll(); update != null; update = bot.receiveQueue.poll()) {
                messageHandler.messageHandler(update, bot);
            }
        }
    }
}
