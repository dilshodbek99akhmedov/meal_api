package team.concurrency.mealproject.telegram.config;

import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.meal.MealCreateDto;
import team.concurrency.mealproject.dto.user.UserCreateDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class EntityHolder {
    private final Map<String, UserCreateDto> userCreateDtoMap = new HashMap<>();
    private final Map<String, MealCreateDto> mealCreateDtoMap = new HashMap<>();

    public UserCreateDto getUserCD(String chatId) {
        if (Objects.isNull(userCreateDtoMap.get(chatId))) {
            userCreateDtoMap.put(chatId, new UserCreateDto());
        }
        return userCreateDtoMap.get(chatId);
    }

    public void setUserCD(String chatId, UserCreateDto dto) {
        userCreateDtoMap.put(chatId, dto);
    }

    public void removeUserCD(String chatId) {
        userCreateDtoMap.remove(chatId);
    }

    public MealCreateDto getMealCD(String chatId) {
        if (Objects.isNull(mealCreateDtoMap.get(chatId))) {
            mealCreateDtoMap.put(chatId, new MealCreateDto());
        }
        return mealCreateDtoMap.get(chatId);
    }

    public void setMealCD(String chatId, MealCreateDto dto) {
        mealCreateDtoMap.put(chatId, dto);
    }

    public void removeMealCD(String chatId) {
        mealCreateDtoMap.remove(chatId);
    }
}
