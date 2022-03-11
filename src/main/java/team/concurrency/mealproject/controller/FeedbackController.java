package team.concurrency.mealproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.concurrency.mealproject.dto.feedback.FeedbackCreateDto;
import team.concurrency.mealproject.dto.feedback.FeedbackDto;
import team.concurrency.mealproject.dto.feedback.FeedbackUpdateDto;
import team.concurrency.mealproject.service.feedback.FeedbackServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedbacks/*")
public class FeedbackController {

    private final FeedbackServiceImpl service;

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody FeedbackCreateDto createDto) {
        String id = service.create(createDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<FeedbackDto>> getAll() {
        List<FeedbackDto> feedbacks = service.getAll();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable(name = "id") String id) {
        FeedbackDto feedbackDto = service.get(id);
        return new ResponseEntity<>(feedbackDto, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable(name = "id") String id, @RequestBody FeedbackUpdateDto dto) {
        dto.setId(id);
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
