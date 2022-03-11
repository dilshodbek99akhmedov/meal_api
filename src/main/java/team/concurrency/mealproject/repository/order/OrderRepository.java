package team.concurrency.mealproject.repository.order;

import org.springframework.data.jpa.repository.Query;
import team.concurrency.mealproject.dto.TaomlarSoni;
import team.concurrency.mealproject.entity.Order;
import team.concurrency.mealproject.repository.base.AbstractRepository;
import team.concurrency.mealproject.repository.base.BaseGenericRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends AbstractRepository<Order, String>, BaseGenericRepository {

    @Query(value = "select * from orders o where o.user_id like ?1 and" +
            " not o.deleted and (to_char(current_timestamp, 'yyyy-MM-dd')=to_char(o.date, 'yyyy-MM-dd'))", nativeQuery = true)
    Optional<Order> thisUserOrderMeal(String userId);

    @Query(value = "select * from orders o where o.id like ?1 and not o.deleted", nativeQuery = true)
    Order thisUserOrderNoDeleted(String id);

    @Query(value = "select  m.name, count(*) from meal m inner join orders o on m.id = o.meal_id group by  m.name having (current_date  = ('2022-03-04' as date ))",nativeQuery = true)
    List<TaomlarSoni> orderNumber();

    @Query("select t.name, count(o.id) from Meal t inner join Order o on o.mealId = t.id group by t.name")
    List<Object[]> orderNumbers();




//    void orderNumber(LocalDate now);
}
