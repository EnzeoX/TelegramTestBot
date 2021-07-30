package com.github.controller;

import com.github.bot.Bot;
import com.github.repository.UsersRepository;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import com.github.utils.Buttons;

import java.util.ArrayList;
import java.util.List;

import static com.github.utils.Messages.*;
import static java.lang.Math.toIntExact;

public class MessageController {

    private static final UsersRepository repository = new UsersRepository();

    public void startMessage(Update update, Bot bot) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(getChatId(update)));
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getNextButton("/help"));
        if (!repository.isUserInDb(getChatId(update))) {
            repository.addUser(getChatId(update));
            sendMessage.setText(START_MESSAGE_FIRST_IN);
            sendMessage.setReplyMarkup(markup);
        } else {
            sendMessage.setText(START_MESSAGE_SECOND_IN);
            rowInLine.add(Buttons.getChangeDirectionButton());
            sendMessage.setReplyMarkup(markup);
        }
        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        bot.sendQueue.add(sendMessage);
    }

    public void helpMessage(Update update, Bot bot) {
        long chatId = getChatIdFromCallBack(update);
        int messageId = getMessageIdFromCallback(update);
        String message = update.getCallbackQuery().getMessage().getText();
        EditMessageText editedMessage = updateMessage(chatId, messageId, message + "\n" + HELP_MESSAGE);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getNextButton("/information"));
        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(markup);
        bot.sendQueue.add(editedMessage);
    }

    public void informationMessage(Update update, Bot bot) {
        long chatId = getChatIdFromCallBack(update);
        int messageId = getMessageIdFromCallback(update);
        EditMessageText editedMessage;
        String message = update.getCallbackQuery().getMessage().getText();
        String typeOf = update.getCallbackQuery().getData();
        String info = update.getCallbackQuery().getMessage().getReplyMarkup().getKeyboard().get(0).get(0).getText();
        if (info.equals("На початок") && typeOf.equals("/information")) {
            editedMessage = updateMessage(chatId, messageId, INFO_MESSAGE);
        } else {
            editedMessage = updateMessage(chatId, messageId, message + "\n" + INFO_MESSAGE);
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getNextButton("/registration"));
        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(markup);
        bot.sendQueue.add(editedMessage);
    }

    public void registrationMessage(Update update, Bot bot) {
        long chatId = getChatIdFromCallBack(update);
        int messageId = getMessageIdFromCallback(update);
        String message = update.getCallbackQuery().getMessage().getText();
        EditMessageText editedMessage = updateMessage(chatId, messageId, message + "\n" + REGISTRATION_MESSAGE);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getAffiliateButton());
        rowInLine.add(Buttons.getKievOfficeButton());
        rowInLine2.add(Buttons.getNextButton("/finalInfo"));
        rowsInLine.add(rowInLine);
        rowsInLine.add(rowInLine2);
        markup.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(markup);
        bot.sendQueue.add(editedMessage);
    }

    public void affiliateInfo(Update update, Bot bot) {
        long chatId = getChatIdFromCallBack(update);
        int messageId = getMessageIdFromCallback(update);
        String message = update.getCallbackQuery().getMessage().getText();
        EditMessageText editedMessage = updateMessage(chatId, messageId, message + "\n" + AFFILIATE_INFO);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getNextButton("/finalInfo"));
        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(markup);
        bot.sendQueue.add(editedMessage);
    }

    public void kievInfo(Update update, Bot bot) {
        long chatId = getChatIdFromCallBack(update);
        int messageId = getMessageIdFromCallback(update);
        String message = update.getCallbackQuery().getMessage().getText();
        EditMessageText editedMessage = updateMessage(chatId, messageId, message + "\n");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getKcdButton());
        rowInLine.add(Buttons.getCacButton());
        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(markup);
        bot.sendQueue.add(editedMessage);
    }

    public void cacInfo(Update update, Bot bot) {
        long chatId = getChatIdFromCallBack(update);
        int messageId = getMessageIdFromCallback(update);
        String message = update.getCallbackQuery().getMessage().getText();
        EditMessageText editedMessage = updateMessage(chatId, messageId, message + "\n" + KIEV_CAU);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getNextButton("/finalInfo"));
        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(markup);
        bot.sendQueue.add(editedMessage);
    }

    public void kcdInfo(Update update, Bot bot) {
        long chatId = getChatIdFromCallBack(update);
        int messageId = getMessageIdFromCallback(update);
        String message = update.getCallbackQuery().getMessage().getText();
        EditMessageText editedMessage = updateMessage(chatId, messageId, message + "\n" + KIEV_KMD);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getNextButton("/finalInfo"));
        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(markup);
        bot.sendQueue.add(editedMessage);
    }

    public void finalInfo(Update update, Bot bot) {
        long chatId = getChatIdFromCallBack(update);
        int messageId = getMessageIdFromCallback(update);
        String message = update.getCallbackQuery().getMessage().getText();
        EditMessageText editedMessage = updateMessage(chatId, messageId, message + "\n" + FINAL_INFO);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getNextButton("/helpInfo"));
        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(markup);
        bot.sendQueue.add(editedMessage);
    }

    public void helpInfo(Update update, Bot bot) {
        long chatId = getChatIdFromCallBack(update);
        int messageId = getMessageIdFromCallback(update);
        String message = update.getCallbackQuery().getMessage().getText();
        EditMessageText editedMessage = updateMessage(chatId, messageId, message + "\n" + HELP_INFO);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsInLine = new ArrayList<>();
        rowInLine.add(Buttons.getReturnToStartButton());
        rowsInLine.add(rowInLine);
        markup.setKeyboard(rowsInLine);
        editedMessage.setReplyMarkup(markup);
        bot.sendQueue.add(editedMessage);
    }

    public void welcomeMessage(Update update, Bot bot) {
        bot.sendQueue.add(createMessage(getChatId(update), WELCOME_MESSAGE));
    }

    public void unsupportedOperationMessage(Update update, Bot bot) {
        bot.sendQueue.add(createMessage(getChatId(update), UNSUPPORTED_MESSAGE));
    }

    public void defaultMessage(Update update, Bot bot) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        bot.sendQueue.add(createMessage(chatId, ERROR_MESSAGE));
    }

    private SendMessage createMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);
        return sendMessage;
    }

    private EditMessageText updateMessage(long chatId, long messageId, String message) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(String.valueOf(chatId));
        editMessageText.setMessageId(toIntExact(messageId));
        editMessageText.setText(message);
        return editMessageText;
    }

    private long getChatIdFromCallBack(Update data) {
        return data.getCallbackQuery().getMessage().getChatId();
    }

    private int getMessageIdFromCallback(Update data) {
        return data.getCallbackQuery().getMessage().getMessageId();
    }

    private long getChatId(Update data) {
        return data.getMessage().getChatId();
    }
}
