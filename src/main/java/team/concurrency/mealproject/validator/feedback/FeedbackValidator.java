package team.concurrency.mealproject.validator.feedback;

import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.feedback.FeedbackCreateDto;
import team.concurrency.mealproject.dto.feedback.FeedbackUpdateDto;
import team.concurrency.mealproject.exceptions.validation.ValidationException;
import team.concurrency.mealproject.validator.base.AbstractValidator;

@Component
public class FeedbackValidator extends AbstractValidator<FeedbackCreateDto, FeedbackUpdateDto,String> {
    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(FeedbackCreateDto feedbackCreateDto) throws ValidationException {
    }

    @Override
    public void validOnUpdate(FeedbackUpdateDto cd) throws ValidationException {

    }
}
