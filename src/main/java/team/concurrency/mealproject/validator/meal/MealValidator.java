package team.concurrency.mealproject.validator.meal;

import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.meal.MealCreateDto;
import team.concurrency.mealproject.dto.meal.MealUpdateDto;
import team.concurrency.mealproject.exceptions.validation.ValidationException;
import team.concurrency.mealproject.validator.base.AbstractValidator;

@Component
public class MealValidator extends AbstractValidator<MealCreateDto, MealUpdateDto, String> {


    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(MealCreateDto mealCreateDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(MealUpdateDto cd) throws ValidationException {

    }
}
