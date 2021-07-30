package com.github.service;

import com.github.bot.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageSender implements Runnable {

    private final Bot bot;

    private boolean isActive = true;

    public MessageSender(Bot data) {
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
            try {
                for (Object message = bot.sendQueue.poll(); message != null; message = bot.sendQueue.poll()) {
                    if (message instanceof EditMessageText) {
                        bot.execute((EditMessageText) message);
                    } else if (message instanceof SendMessage) {
                        bot.execute((SendMessage) message);
                    }
                }
            } catch (TelegramApiException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
