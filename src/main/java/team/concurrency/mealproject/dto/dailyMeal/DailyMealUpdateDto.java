package team.concurrency.mealproject.dto.dailyMeal;

import team.concurrency.mealproject.dto.base.GenericDto;

import java.time.LocalDate;

public class DailyMealUpdateDto extends GenericDto {
    public String mealId;
    public LocalDate date;

    public DailyMealUpdateDto(String id) {
        super(id);
    }
}
