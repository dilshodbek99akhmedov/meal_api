package team.concurrency.mealproject.telegram.button;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import team.concurrency.mealproject.telegram.config.LangConfig;
import team.concurrency.mealproject.telegram.tools.Emoji;

import java.util.Arrays;
import java.util.List;

@Component
public class InlineBoard {


    public InlineKeyboardMarkup languageButtons() {
        InlineKeyboardMarkup board = new InlineKeyboardMarkup();
        InlineKeyboardButton uz = new InlineKeyboardButton(Emoji.UZ + " O'zbek");
        uz.setCallbackData("uz");

        InlineKeyboardButton ru = new InlineKeyboardButton(Emoji.RU + " Русский");
        ru.setCallbackData("ru");

        InlineKeyboardButton en = new InlineKeyboardButton(Emoji.EN + " English");
        en.setCallbackData("en");
        board.setKeyboard(Arrays.asList(getRow(uz), getRow(ru), getRow(en)));
        return board;
    }

    public InlineKeyboardMarkup departmentButtons() {
        InlineKeyboardMarkup board = new InlineKeyboardMarkup();
        InlineKeyboardButton sales = new InlineKeyboardButton("Sales");
        sales.setCallbackData("sales");

        InlineKeyboardButton kids = new InlineKeyboardButton("Kids");
        kids.setCallbackData("kids");

        InlineKeyboardButton english = new InlineKeyboardButton("English");
        english.setCallbackData("english");

        InlineKeyboardButton academic = new InlineKeyboardButton("Academic");
        academic.setCallbackData("academic");

        InlineKeyboardButton hr = new InlineKeyboardButton("HR");
        hr.setCallbackData("hr");
        board.setKeyboard(Arrays.asList(getRow(sales),
                getRow(kids),
                getRow(english),
                getRow(academic),
                getRow(hr)));
        return board;
    }

    public InlineKeyboardMarkup positionButtons() {
        InlineKeyboardMarkup board = new InlineKeyboardMarkup();
        InlineKeyboardButton administrator = new InlineKeyboardButton("Administrator");
        administrator.setCallbackData("administrator");

        InlineKeyboardButton teacher = new InlineKeyboardButton("Teacher");
        teacher.setCallbackData("teacher");

        InlineKeyboardButton employee = new InlineKeyboardButton("Employee");
        employee.setCallbackData("employee");

        InlineKeyboardButton assistant = new InlineKeyboardButton("Assistant");
        assistant.setCallbackData("assistant");

        board.setKeyboard(Arrays.asList(getRow(administrator),
                getRow(teacher),
                getRow(employee),
                getRow(assistant)));
        return board;
    }

    public InlineKeyboardMarkup gender(String chatId) {
        InlineKeyboardMarkup board = new InlineKeyboardMarkup();
        InlineKeyboardButton male = new InlineKeyboardButton(Emoji.MALE + " " + LangConfig.get(chatId, "male"));
        male.setCallbackData("male");

        InlineKeyboardButton female = new InlineKeyboardButton(Emoji.FEMALE + " " + LangConfig.get(chatId, "female"));
        female.setCallbackData("female");

        board.setKeyboard(Arrays.asList(getRow(male), getRow(female)));
        return board;
    }

    public InlineKeyboardMarkup accept(String target) {
        InlineKeyboardMarkup board = new InlineKeyboardMarkup();

        InlineKeyboardButton yes = new InlineKeyboardButton(Emoji.ADD + " Yes");
        yes.setCallbackData("accepted" + target);

        InlineKeyboardButton no = new InlineKeyboardButton(Emoji.REMOVE + " No");
        no.setCallbackData("notAccepted");

        board.setKeyboard(Arrays.asList(getRow(no), getRow(yes)));
        return board;
    }

    private List<InlineKeyboardButton> getRow(InlineKeyboardButton... buttons) {
        return Arrays.stream(buttons).toList();
    }


}
