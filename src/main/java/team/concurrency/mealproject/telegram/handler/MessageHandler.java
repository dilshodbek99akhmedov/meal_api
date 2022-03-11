package team.concurrency.mealproject.telegram.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import team.concurrency.mealproject.entity.User;
import team.concurrency.mealproject.enums.Role;
import team.concurrency.mealproject.service.auth.AuthUserService;
import team.concurrency.mealproject.telegram.MealManagementBot;
import team.concurrency.mealproject.telegram.config.State;
import team.concurrency.mealproject.telegram.processor.auth.AuthorizationProcessor;
import team.concurrency.mealproject.telegram.processor.meal.AddMealProcessor;
import team.concurrency.mealproject.telegram.processor.meal.MealProcessor;
import team.concurrency.mealproject.telegram.processor.menu.MenuProcessor;
import team.concurrency.mealproject.telegram.state.AddMealState;
import team.concurrency.mealproject.telegram.state.MealState;
import team.concurrency.mealproject.telegram.state.MenuState;
import team.concurrency.mealproject.telegram.state.UserState;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final AuthUserService authUserService;
    private final MealManagementBot bot;
    private final AuthorizationProcessor authorizationProcessor;
    private final MealProcessor mealProcessor;
    private final AddMealProcessor addMealProcessor;
    private final MenuProcessor menuProcessor;
    private final State state;

    public void handle(Message message) {
        String chatId = message.getChatId().toString();
        String text = message.getText();

        MenuState menuState = state.getMenuState(chatId);
        UserState userState = state.getUserState(chatId);
        MealState mealState = state.getMealState(chatId);
        AddMealState addMealState = state.getAddMealState(chatId);

        User user = authUserService.getByChatId(chatId);

        if (("/start".equals(text) || MenuState.UNDEFINED.equals(menuState))) {
            if (!Objects.isNull(user)) {
                if (Role.USER.getName().equals(user.getRole()) && user.getStatus() == 0)
                    menuProcessor.userMenuProcess(message, menuState);
                else if (Role.MANAGER.getName().equals(user.getRole()) && user.getStatus() == 0)
                    menuProcessor.managerProcess(message, menuState);
                else if (Role.ADMIN.getName().equals(user.getRole()) && user.getStatus() == 0)
                    menuProcessor.adminMenuProcess(message, menuState);
                else if (Role.SUPER_ADMIN.getName().equals(user.getRole()) && user.getStatus() == 0)
                    menuProcessor.superMenuProcess(message, menuState);
            } else {
                authorizationProcessor.process(message, userState);
            }

        }

        if (MenuState.MEAL.equals(menuState)) {
            mealProcessor.process(message, mealState);
        } else if (MenuState.ADD_MEAL.equals(menuState)) {
            addMealProcessor.process(message, addMealState);
        }
    }
}
