package team.concurrency.mealproject.mapper.meal;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.meal.MealCreateDto;
import team.concurrency.mealproject.dto.meal.MealDto;
import team.concurrency.mealproject.dto.meal.MealUpdateDto;
import team.concurrency.mealproject.entity.Meal;
import team.concurrency.mealproject.mapper.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MealMapper extends AbstractMapper<Meal, MealDto, MealCreateDto, MealUpdateDto> {
    @Override
    MealDto toDto(Meal entity);

    @Override
    List<MealDto> toDto(List<Meal> entities);

    @Override
    Meal fromCreateDto(MealCreateDto createDto);

    @Override
    Meal fromUpdateDto(MealUpdateDto updateDto);
}
