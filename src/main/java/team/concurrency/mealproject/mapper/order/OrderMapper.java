package team.concurrency.mealproject.mapper.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.order.OrderCreateDto;
import team.concurrency.mealproject.dto.order.OrderDto;
import team.concurrency.mealproject.dto.order.OrderUpdateDto;
import team.concurrency.mealproject.entity.Order;
import team.concurrency.mealproject.mapper.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper extends AbstractMapper<Order, OrderDto, OrderCreateDto, OrderUpdateDto> {

    @Override
    OrderDto toDto(Order entity);


    @Override
    List<OrderDto> toDto(List<Order> entities);


    @Override
    @Mapping(target = "date",ignore = true)
    Order fromCreateDto(OrderCreateDto createDto);


    @Override
    Order fromUpdateDto(OrderUpdateDto updateDto);

}
