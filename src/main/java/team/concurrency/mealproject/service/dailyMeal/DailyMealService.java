package team.concurrency.mealproject.service.dailyMeal;

import team.concurrency.mealproject.dto.dailyMeal.DailyMealCreateDto;
import team.concurrency.mealproject.dto.dailyMeal.DailyMealDto;
import team.concurrency.mealproject.dto.dailyMeal.DailyMealUpdateDto;
import team.concurrency.mealproject.service.base.GenericCrudService;

public interface DailyMealService extends GenericCrudService<DailyMealDto, DailyMealCreateDto, DailyMealUpdateDto,String> {
}
