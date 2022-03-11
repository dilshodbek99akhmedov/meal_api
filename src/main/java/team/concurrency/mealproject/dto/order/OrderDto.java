package team.concurrency.mealproject.dto.order;

import team.concurrency.mealproject.dto.base.GenericDto;

import java.time.LocalDateTime;

public class OrderDto extends GenericDto {
    public String userId;

    public String mealId;

    public LocalDateTime date;

    public OrderDto(String id) {
        super(id);
    }
}
