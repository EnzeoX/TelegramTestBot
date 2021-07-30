package com.github.bot;

import com.github.config.BotConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Bot extends TelegramLongPollingBot {

    private static String botName;

    private static String botToken;

    private final BotConfig config = new BotConfig();

    public final Queue<Update> receiveQueue = new ConcurrentLinkedQueue<>();

    public final Queue<Object> sendQueue = new ConcurrentLinkedQueue<>();

    public Bot() {
        setBotName();
        setBotToken();
    }

    public void setBotName() {
        botName = this.config.getName();
    }

    public void setBotToken() {
        botToken = this.config.getToken();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        receiveQueue.add(update);
    }

    public void botConnect() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
