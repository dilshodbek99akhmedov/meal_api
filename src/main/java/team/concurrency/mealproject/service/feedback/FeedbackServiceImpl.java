package team.concurrency.mealproject.service.feedback;

import org.springframework.stereotype.Service;
import team.concurrency.mealproject.dto.feedback.FeedbackCreateDto;
import team.concurrency.mealproject.dto.feedback.FeedbackDto;
import team.concurrency.mealproject.dto.feedback.FeedbackUpdateDto;
import team.concurrency.mealproject.entity.Feedback;
import team.concurrency.mealproject.mapper.feedback.FeedbackMapper;
import team.concurrency.mealproject.repository.feedback.FeedbackRepository;
import team.concurrency.mealproject.service.base.AbstractService;
import team.concurrency.mealproject.validator.feedback.FeedbackValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl extends AbstractService<FeedbackRepository, FeedbackMapper, FeedbackValidator> implements FeedbackService {
    protected FeedbackServiceImpl(FeedbackMapper mapper, FeedbackValidator validator, FeedbackRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(FeedbackCreateDto createDto) {
        Feedback f= mapper.fromCreateDto(createDto);
        f.setId(UUID.randomUUID().toString());
        return repository.save(f).getId();
    }

    @Override
    public void delete(String id) {
         repository.setDeleted(id);
    }

    @Override
    public Boolean update(FeedbackUpdateDto updateDto) {
        Feedback f=repository.getById(updateDto.id);
        f.setMessage(updateDto.getMessage());
        f.setType(updateDto.getType());
        repository.save(f);
        return true;
    }

    @Override
    public FeedbackDto get(String id) {
        return mapper.toDto(repository.getById(id));
    }

    @Override
    //TODO: create method to return list of feedbackdtos
    public List<FeedbackDto> getAll(String id) {
     return new ArrayList<>();
    }

    @Override
    public List<FeedbackDto> getAll() {
        return mapper.toDto(repository.getAll());
    }

    public List<FeedbackDto> getAllBySenderId(String id){
        return mapper.toDto(repository.getAllByUserId(id));
    }

    public List<FeedbackDto> getAllByType(String type){
        return mapper.toDto(repository.getAllByType(type));
    }

}
