package team.concurrency.mealproject.dto.order;

import lombok.Getter;
import team.concurrency.mealproject.dto.base.GenericDto;

@Getter
public class OrderUpdateDto extends GenericDto {
    public String date;
    public boolean taken;

    public OrderUpdateDto(String id) {
        super(id);
    }
}
