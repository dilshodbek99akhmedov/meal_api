package team.concurrency.mealproject.dto.feedback;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import team.concurrency.mealproject.dto.base.GenericDto;

@Setter
@Getter
public class FeedbackDto extends GenericDto {
    String message;
    String type;
    String userId;
    public FeedbackDto(String id) {
        super(id);
    }

    @Builder(builderMethodName = "childBuilder")
    public FeedbackDto(String id, String message, String type, String userId){
        this(id);
        this.message=message;
        this.type=type;
        this.userId=userId;
    }
}
