package team.concurrency.mealproject.telegram.processor.meal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import team.concurrency.mealproject.telegram.MealManagementBot;
import team.concurrency.mealproject.telegram.config.State;
import team.concurrency.mealproject.telegram.state.AddMealState;
import team.concurrency.mealproject.telegram.state.MealState;
import team.concurrency.mealproject.telegram.state.MenuState;

@Component
@RequiredArgsConstructor
public class MealProcessor {

    private final State state;
    private final MealManagementBot BOT;

    public void process(Message message, MealState mealState) {
        String chatId = message.getChatId().toString();
        String text = message.getText();

        if ("Add Meal".equals(text)) {
            state.setMenuState(chatId, MenuState.ADD_MEAL);
            state.setAddMealState(chatId, AddMealState.NAME);

            SendMessage sendMessage = new SendMessage(chatId, "Enter name of meal");
            BOT.executeMessage(sendMessage);
        }
    }
}
