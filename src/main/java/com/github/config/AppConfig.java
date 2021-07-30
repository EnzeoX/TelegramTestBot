package com.github.config;

import com.github.bot.Bot;
import com.github.service.MessageReceiver;
import com.github.service.MessageSender;

public class AppConfig {

    private static final int RECEIVER_THREAD_PRIORITY = 3;

    private static final int SENDER_THREAD_PRIORITY = 1;

    public void startApp() {
        Bot bot = new Bot();
        bot.botConnect();

        Thread receive = new Thread(new MessageReceiver(bot));
        receive.setDaemon(true);
        receive.setName("ReceiverThread " + receive.getId());
        receive.setPriority(RECEIVER_THREAD_PRIORITY);
        receive.start();

        Thread send = new Thread(new MessageSender(bot));
        send.setDaemon(true);
        send.setName("SenderThread " + send.getId());
        send.setPriority(SENDER_THREAD_PRIORITY);
        send.start();
    }
}
