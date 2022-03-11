package team.concurrency.mealproject.service.meal;

import org.springframework.stereotype.Service;
import team.concurrency.mealproject.dto.meal.MealCreateDto;
import team.concurrency.mealproject.dto.meal.MealDto;
import team.concurrency.mealproject.dto.meal.MealUpdateDto;
import team.concurrency.mealproject.entity.Meal;
import team.concurrency.mealproject.exceptions.meal.MealAlreadyAddedException;
import team.concurrency.mealproject.exceptions.meal.MealNotFoundException;
import team.concurrency.mealproject.mapper.meal.MealMapper;
import team.concurrency.mealproject.repository.meal.MealRepository;
import team.concurrency.mealproject.service.base.AbstractService;
import team.concurrency.mealproject.validator.meal.MealValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MealServiceImp extends AbstractService<MealRepository, MealMapper, MealValidator> implements MealService {

    protected MealServiceImp(MealMapper mapper, MealValidator validator, MealRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(MealCreateDto createDto) {
        Meal meal = repository.findByNameDelete(createDto.name);
        if (Objects.nonNull(meal))
            throw new MealAlreadyAddedException("This meal already added");
        Meal meal1 = mapper.fromCreateDto(createDto);
        meal1.setId(UUID.randomUUID().toString());
        repository.save(meal1);
        return meal1.getId();
    }

    @Override
    public Boolean update(MealUpdateDto updateDto) {
        Meal meal = repository.findByIdNotDelete((updateDto.id));
        if (Objects.isNull(meal))
            throw new MealNotFoundException("Meal not found");
        if(Objects.nonNull(repository.findByNameDeleteAndMealId(updateDto.name,updateDto.id))){
            throw new MealNotFoundException("This meal already added");
        }
        meal.setName(updateDto.name);
        meal.setDescription(updateDto.description);
        meal.setPath(updateDto.path);
        repository.save(meal);
        return true;
    }


    @Override
    public MealDto get(String id) {
        Meal meal = repository.findByIdNotDelete(id);
        if (Objects.isNull(meal))
            throw new MealNotFoundException("No meal was found for the given id");
        return mapper.toDto(meal);
    }

    @Override
    public List<MealDto> getAll(String id) {
        return null;
    }

    @Override
    public List<MealDto> getAll() {
        List<Meal> allMeals = repository.findAllMeals();
        if (allMeals.isEmpty())
            throw new MealNotFoundException("No meal added");
        return mapper.toDto(allMeals);
    }

    @Override
    public void delete(String id) {
        Meal meal = repository.findByIdNotDelete(id);
        if (Objects.isNull(meal))
            throw new MealNotFoundException("No meal was found for the given id");
        meal.setDeleted(true);
        repository.save(meal);
    }

    public List<MealDto> getAllToday() {
        return mapper.toDto(repository.getAllToday(LocalDate.now()));
    }

    public MealDto getMealOfMeal(String id) {
        System.out.println("LocalDate.now() = " + LocalDate.now());
        Meal meal = repository.findByUserId(id, LocalDate.now());
        return mapper.toDto(meal);
    }
}
