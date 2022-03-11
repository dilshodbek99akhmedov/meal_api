package team.concurrency.mealproject.dto.dailyMeal;

import team.concurrency.mealproject.dto.base.GenericDto;

public class DailyMealDto  extends GenericDto {
    public String mealId;
    public String name;
    public DailyMealDto(String id) {
        super(id);
    }
}
