package team.concurrency.mealproject.service.order;

import team.concurrency.mealproject.dto.order.OrderCreateDto;
import team.concurrency.mealproject.dto.order.OrderDto;
import team.concurrency.mealproject.dto.order.OrderUpdateDto;
import team.concurrency.mealproject.service.base.GenericCrudService;

public interface OrderService extends GenericCrudService<
        OrderDto,
        OrderCreateDto,
        OrderUpdateDto,
        String> {

}
