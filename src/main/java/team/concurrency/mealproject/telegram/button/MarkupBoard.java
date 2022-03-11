package team.concurrency.mealproject.telegram.button;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import team.concurrency.mealproject.telegram.config.LangConfig;
import team.concurrency.mealproject.telegram.tools.Emoji;

import java.util.List;

@Component
public class MarkupBoard {

    public static ReplyKeyboardMarkup sharePhoneNumber(String chatId) {
        ReplyKeyboardMarkup board = new ReplyKeyboardMarkup();
        KeyboardButton phoneContact = new KeyboardButton(Emoji.PHONE + " " + LangConfig.get(chatId, "share.phone.number"));
        phoneContact.setRequestContact(true);
        board.setResizeKeyboard(true);
        board.setSelective(true);
        KeyboardRow row = new KeyboardRow();
        row.add(phoneContact);
        board.setKeyboard(List.of(row));
        return board;
    }

    public ReplyKeyboardMarkup mealCrud(String chatId) {
        ReplyKeyboardMarkup board = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Add Meal"));
        row1.add(new KeyboardButton("Delete Meal"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Update Meal"));
        row2.add(new KeyboardButton("List Meal"));

        KeyboardRow row4 = new KeyboardRow();
        row4.add(new KeyboardButton(Emoji.BACK + " " + LangConfig.get(chatId, "back")));
        board.setKeyboard(List.of(row1, row2, row4));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }

//    public static ReplyKeyboardMarkup settingsMenu(String chatId) {
//        KeyboardRow row1 = new KeyboardRow();
//        row1.add(new KeyboardButton(Emoji.NAME + " " + LangConfig.get(chatId, "change.name")));
//        row1.add(new KeyboardButton(Emojis.AGE + " " + LangConfig.get(chatId, "change.birthdate")));
//
//        KeyboardRow row2 = new KeyboardRow();
//        row2.add(new KeyboardButton(Emojis.PHONE + " " + LangConfig.get(chatId, "change.phone.number")));
//        row2.add(new KeyboardButton(Emojis.LANGUAGE + " " + LangConfig.get(chatId, "change.language")));
//
//        KeyboardRow row3 = new KeyboardRow();
//        row3.add(new KeyboardButton(Emojis.LIMIT + " " + LangConfig.get(chatId, "change.limit")));
//
//        KeyboardRow row4 = new KeyboardRow();
//        row4.add(new KeyboardButton(Emojis.BACK + " " + LangConfig.get(chatId, "back")));
//        board.setKeyboard(List.of(row1, row2, row3,row4));
//        board.setResizeKeyboard(true);
//        board.setSelective(true);
//        return board;
//    }

}
