package team.concurrency.mealproject.service.meal;

import team.concurrency.mealproject.dto.meal.MealCreateDto;
import team.concurrency.mealproject.dto.meal.MealDto;
import team.concurrency.mealproject.dto.meal.MealUpdateDto;
import team.concurrency.mealproject.service.base.GenericCrudService;

public interface MealService extends GenericCrudService<MealDto, MealCreateDto, MealUpdateDto,String> {
}
