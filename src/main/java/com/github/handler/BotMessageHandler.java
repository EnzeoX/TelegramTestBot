package com.github.handler;

import com.github.bot.Bot;
import com.github.controller.MessageController;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BotMessageHandler {

    private static final MessageController controller = new MessageController();

    public void messageHandler(Object data, Bot bot) {
        Update update = objectOfUpdate(data);
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("/start")) {
                    controller.startMessage(update, bot);
                } else {
                    controller.unsupportedOperationMessage(update, bot);
                }
            }
        } else if (update.hasCallbackQuery()) {
            String callBackMessage = update.getCallbackQuery().getData();
            switch (callBackMessage) {
                case "/help":
                    controller.helpMessage(update, bot);
                    break;
                case "/information":
                    controller.informationMessage(update, bot);
                    break;
                case "/registration":
                    controller.registrationMessage(update, bot);
                    break;
                case "/affiliateInfo":
                    controller.affiliateInfo(update, bot);
                    break;
                case "/kievInfo":
                    controller.kievInfo(update, bot);
                    break;
                case "/kcdInfo":
                    controller.kcdInfo(update, bot);
                    break;
                case "/cacInfo":
                    controller.cacInfo(update, bot);
                    break;
                case "/finalInfo":
                    controller.finalInfo(update, bot);
                    break;
                case "/helpInfo":
                    controller.helpInfo(update, bot);
                    break;
                default:
                    controller.defaultMessage(update, bot);
                    break;
            }
        }
    }

    private static boolean isCommand(String msg) {
        return msg.startsWith("/");
    }

    private Update objectOfUpdate(Object data) {
        if (data instanceof Update) {
            return (Update) data;
        } else {
            return null;
        }
    }
}
