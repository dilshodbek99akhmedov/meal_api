package team.concurrency.mealproject.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import team.concurrency.mealproject.enums.Department;
import team.concurrency.mealproject.enums.Gender;
import team.concurrency.mealproject.enums.Position;
import team.concurrency.mealproject.telegram.MealManagementBot;
import team.concurrency.mealproject.telegram.config.EntityHolder;
import team.concurrency.mealproject.telegram.processor.auth.AuthorizationProcessor;
import team.concurrency.mealproject.telegram.service.CallBackHandlerService;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {

    private final MealManagementBot BOT;
    private final AuthorizationProcessor authorizationProcessor;
    private final CallBackHandlerService callBackHandlerService;
    private final EntityHolder userHolder;

    public void handle(CallbackQuery callbackQuery) {

        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        String chatId = message.getChatId().toString();

        if ("uz".equals(data) ||
                "ru".equals(data) ||
                "en".equals(data)) {
            deleteMessage(message, chatId);
            callBackHandlerService.languageMessage(message, data);
        }
        else if (Arrays.stream(
                Department.values()).
                anyMatch(department -> department.getName().toLowerCase().equals(data))) {
            deleteMessage(message, chatId);
            callBackHandlerService.departmentMessage(message, data);
        }
        else if (Arrays.stream(
                Position.values()).
                anyMatch(position -> position.getName().toLowerCase().equals(data))) {
            deleteMessage(message, chatId);
            callBackHandlerService.positionMessage(message, data);
        }
        else if (Arrays.stream(
                Gender.values()).
                anyMatch(gender -> gender.getName().toLowerCase().equals(data))) {
            deleteMessage(message, chatId);
            callBackHandlerService.genderMessage(message, data);
        }
        else if (data.startsWith("accept") ||
                "notAccepted".equals(data)) {
            deleteMessage(message, chatId);
            callBackHandlerService.acceptMessage(message, data);
        }
    }
//
//    private void sendMessage(String chatId) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(Emoji.ADD + " " + LangConfig.get(chatId, "limit.changed") + "\n" +
//                LangConfig.get(chatId, "current.limit") + State.getLimitState(chatId));
//        BOT.executeMessage(sendMessage);
//    }

    private void deleteMessage(Message message, String chatId) {
        DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
        BOT.executeMessage(deleteMessage);
    }
}
