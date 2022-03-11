package team.concurrency.mealproject.mapper.feedback;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.feedback.FeedbackCreateDto;
import team.concurrency.mealproject.dto.feedback.FeedbackDto;
import team.concurrency.mealproject.dto.feedback.FeedbackUpdateDto;
import team.concurrency.mealproject.entity.Feedback;
import team.concurrency.mealproject.mapper.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface FeedbackMapper extends AbstractMapper<Feedback, FeedbackDto, FeedbackCreateDto, FeedbackUpdateDto> {
    @Override
    FeedbackDto toDto(Feedback entity);

    @Override
    List<FeedbackDto> toDto(List<Feedback> entities);

    @Override
    Feedback fromCreateDto(FeedbackCreateDto createDto);

    @Override
    Feedback fromUpdateDto(FeedbackUpdateDto updateDto);
}
