package team.concurrency.mealproject.dto.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.concurrency.mealproject.dto.base.BaseGenericDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackCreateDto implements BaseGenericDto {
    String message;
    String type;
    String userId;
}
