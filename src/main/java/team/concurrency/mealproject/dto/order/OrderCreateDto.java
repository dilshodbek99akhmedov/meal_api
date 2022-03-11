package team.concurrency.mealproject.dto.order;

import lombok.Getter;
import team.concurrency.mealproject.dto.base.BaseGenericDto;

@Getter
public class OrderCreateDto implements BaseGenericDto {
    public String userId;
    public String mealId;
    public String date;
}
