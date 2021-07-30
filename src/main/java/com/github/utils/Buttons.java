package com.github.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Buttons {

    public static InlineKeyboardButton getNextButton(String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Далі");
        button.setCallbackData(callbackData);
        return button;
    }

    public static InlineKeyboardButton getButtonsForHelp() {
        InlineKeyboardButton mainButton = new InlineKeyboardButton();
        mainButton.setText("Допомога");
        mainButton.setCallbackData("/help");
        return mainButton;
    }

    public static InlineKeyboardButton getChangeDirectionButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Змінити дирекцію");
        button.setCallbackData("/registration");
        return button;
    }

    public static InlineKeyboardButton getReturnToStartButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("На початок");
        button.setCallbackData("/information");
        return button;
    }

    public static InlineKeyboardButton getAffiliateButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Вибір регіональної філії");
        button.setCallbackData("/affiliateInfo");
        return button;
    }

    public static InlineKeyboardButton getKievOfficeButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData("/kievInfo");
        button.setText("Київ");
        return button;
    }

    public static InlineKeyboardButton getCacButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("ЦАУ");
        button.setCallbackData("/cacInfo");
        return button;
    }

    public static InlineKeyboardButton getKcdButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("КМД");
        button.setCallbackData("/kcdInfo");
        return button;
    }
}
