package team.concurrency.mealproject.service.dailyMeal;



import org.springframework.stereotype.Service;
import team.concurrency.mealproject.dto.dailyMeal.DailyMealCreateDto;
import team.concurrency.mealproject.dto.dailyMeal.DailyMealDto;
import team.concurrency.mealproject.dto.dailyMeal.DailyMealUpdateDto;
import team.concurrency.mealproject.entity.DailyMeal;
import team.concurrency.mealproject.mapper.dailyMeal.DailyMealMapper;
import team.concurrency.mealproject.repository.dailyMeal.DailyMealRepository;
import team.concurrency.mealproject.service.base.AbstractService;
import team.concurrency.mealproject.validator.DailyMealValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DailyMealServiceImp extends AbstractService<DailyMealRepository, DailyMealMapper, DailyMealValidator> implements DailyMealService{
    public DailyMealServiceImp(DailyMealMapper mapper, DailyMealValidator validator, DailyMealRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(DailyMealCreateDto createDto) {
        DailyMeal dailyMeal = mapper.fromCreateDto(createDto);
        dailyMeal.setDate(LocalDate.parse(createDto.date.substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return repository.save(dailyMeal).getMealId();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Boolean update(DailyMealUpdateDto updateDto) {
        return null;
    }

    @Override
    public DailyMealDto get(String id) {
        return null;
    }

    @Override
    public List<DailyMealDto> getAll(String id) {
        return null;
    }

    @Override
    public List<DailyMealDto> getAll() {
        return mapper.toDto(repository.getAllByDate(LocalDate.now().toString()));
    }
}
