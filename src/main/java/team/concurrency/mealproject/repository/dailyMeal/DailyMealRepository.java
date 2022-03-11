package team.concurrency.mealproject.repository.dailyMeal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.concurrency.mealproject.entity.DailyMeal;
import team.concurrency.mealproject.repository.base.AbstractRepository;
import team.concurrency.mealproject.repository.base.BaseGenericRepository;

import java.util.List;

@Repository
public interface DailyMealRepository extends AbstractRepository<DailyMeal,String> , BaseGenericRepository {

    @Query(value = "select * from daily_meal where not deleted and data= ?1",nativeQuery = true)
    List<DailyMeal> getAllByDate(String date);
}
