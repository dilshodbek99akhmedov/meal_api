package team.concurrency.mealproject.validator.order;

import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.order.OrderCreateDto;
import team.concurrency.mealproject.dto.order.OrderUpdateDto;
import team.concurrency.mealproject.exceptions.validation.ValidationException;
import team.concurrency.mealproject.validator.base.AbstractValidator;

@Component
public class OrderValidator extends AbstractValidator<OrderCreateDto, OrderUpdateDto, String> {
    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(OrderCreateDto orderCreateDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(OrderUpdateDto cd) throws ValidationException {

    }
}
