package team.concurrency.mealproject.repository.meal;

import org.springframework.data.jpa.repository.Query;
import team.concurrency.mealproject.entity.Meal;
import team.concurrency.mealproject.repository.base.AbstractRepository;
import team.concurrency.mealproject.repository.base.BaseGenericRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends AbstractRepository<Meal, String>, BaseGenericRepository {

    @Query(value = "select * from meal m where m.name = ?1 and not m.deleted", nativeQuery = true)
    Meal findByNameDelete(String name);


    @Query(value = "select * from meal m where not m.deleted", nativeQuery = true)
    List<Meal> findAllMeals();

    @Query(value = "select * from meal m where m.id = ?1 and not m.deleted", nativeQuery = true)
    Meal findByIdNotDelete(String id);


    @Query(value = "select m.* from daily_meal dm inner join meal m on dm.meal_id = m.id where not dm.deleted and not m.deleted and cast(dm.date as date)= ?1", nativeQuery = true)
    List<Meal> getAllToday(LocalDate date);


    @Query(value = "select m.* from meal m inner join orders o on m.id = o.meal_id where not o.deleted and not m.deleted and o.user_id= ?1 and cast(o.date as date) = ?2", nativeQuery = true)
    Meal findByUserId(String id, LocalDate date);

    @Query(value = "select * from meal m where m.name = ?1 and not m.deleted and m.id <> ?2", nativeQuery = true)
    Object findByNameDeleteAndMealId(String name, String mealId);
}
