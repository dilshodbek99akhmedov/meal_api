package team.concurrency.mealproject.dto.feedback;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import team.concurrency.mealproject.dto.base.GenericDto;

@Setter
@Getter
public class FeedbackUpdateDto extends GenericDto {
    String message;
    String type;
    public FeedbackUpdateDto(String id) {
        super(id);
    }
    @Builder(builderMethodName = "childBuilder")
    public FeedbackUpdateDto(String id, String message, String type){
        this(id);
        this.message=message;
        this.type=type;
    }
}
