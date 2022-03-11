package team.concurrency.mealproject.service.feedback;

import team.concurrency.mealproject.dto.feedback.FeedbackCreateDto;
import team.concurrency.mealproject.dto.feedback.FeedbackDto;
import team.concurrency.mealproject.dto.feedback.FeedbackUpdateDto;
import team.concurrency.mealproject.service.base.GenericCrudService;

public interface FeedbackService extends GenericCrudService<FeedbackDto, FeedbackCreateDto, FeedbackUpdateDto,String> {
}
