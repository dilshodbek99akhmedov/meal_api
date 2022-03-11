package team.concurrency.mealproject.telegram;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import team.concurrency.mealproject.telegram.handler.UpdateHandler;

@Component
public class MealManagementBot extends TelegramLongPollingBot {

    private final UpdateHandler updateHandler;

    // @Value("${bot.name}")
    private String botUsername = "@mealmanegmentbot";

    //  @Value("${bot.token}")
    private String botToken = "5243002339:AAEkjSRru3kFtAq_1QXwp7bxfj1tdDQVBYc";

    public MealManagementBot(@Lazy UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.handle(update);
    }

    @Override
    public String getBotToken() {
        return "5243002339:AAEkjSRru3kFtAq_1QXwp7bxfj1tdDQVBYc";
    }

    @Override
    public String getBotUsername() {
        return "@mealmanegmentbot";
    }


    public void executeMessage(BotApiMethod<?> msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(SendMessage msg) {
        msg.setParseMode("HTML");
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(SendDocument msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(EditMessageText msg) {
        msg.setParseMode("HTML");
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
