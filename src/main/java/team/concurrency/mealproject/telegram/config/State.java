package team.concurrency.mealproject.telegram.config;

import org.springframework.stereotype.Component;
import team.concurrency.mealproject.telegram.state.AddMealState;
import team.concurrency.mealproject.telegram.state.MealState;
import team.concurrency.mealproject.telegram.state.MenuState;
import team.concurrency.mealproject.telegram.state.UserState;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class State {
    private final Map<String, UserState> userState = new HashMap<>();
    private final Map<String, MenuState> menuState = new HashMap<>();
    private final Map<String, MealState> mealState = new HashMap<>();
    private final Map<String, AddMealState> addMealState = new HashMap<>();

    public synchronized void setUserState(String chatId, UserState state) {
        this.userState.put(chatId, state);
    }

    public UserState getUserState(String chatId) {
        return userState.get(chatId);
    }

    public synchronized void setMenuState(String chatId, MenuState state) {
        this.menuState.put(chatId, state);
    }

    public MenuState getMenuState(String chatId) {
        if (Objects.isNull(menuState.get(chatId))) {
            setMenuState(chatId, MenuState.UNDEFINED);
        }
        return menuState.get(chatId);
    }

    public synchronized void setMealState(String chatId, MealState state) {
        this.mealState.put(chatId, state);
    }

    public MealState getMealState(String chatId) {
        if (Objects.isNull(mealState.get(chatId))) {
            setMealState(chatId, MealState.UNDEFINED);
        }
        return mealState.get(chatId);
    }

    public synchronized void setAddMealState(String chatId, AddMealState state) {
        this.addMealState.put(chatId, state);
    }

    public AddMealState getAddMealState(String chatId) {
        if (Objects.isNull(addMealState.get(chatId))) {
            setAddMealState(chatId, AddMealState.UNDEFINED);
        }
        return addMealState.get(chatId);
    }
}
