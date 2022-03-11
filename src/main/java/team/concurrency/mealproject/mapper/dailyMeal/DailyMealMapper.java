package team.concurrency.mealproject.mapper.dailyMeal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.dailyMeal.DailyMealCreateDto;
import team.concurrency.mealproject.dto.dailyMeal.DailyMealDto;
import team.concurrency.mealproject.dto.dailyMeal.DailyMealUpdateDto;
import team.concurrency.mealproject.entity.DailyMeal;
import team.concurrency.mealproject.mapper.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DailyMealMapper extends AbstractMapper<DailyMeal, DailyMealDto, DailyMealCreateDto, DailyMealUpdateDto> {

    @Override
    DailyMealDto toDto(DailyMeal entity);

    @Override
    List<DailyMealDto> toDto(List<DailyMeal> entities);

    @Mapping(target = "date",ignore = true)
    DailyMeal fromCreateDto(DailyMealCreateDto dto);

    @Override
    DailyMeal fromUpdateDto(DailyMealUpdateDto updateDto);

}
