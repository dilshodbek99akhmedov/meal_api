package team.concurrency.mealproject.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.concurrency.mealproject.dto.meal.MealCreateDto;
import team.concurrency.mealproject.dto.meal.MealDto;
import team.concurrency.mealproject.dto.meal.MealUpdateDto;
import team.concurrency.mealproject.service.meal.MealServiceImp;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MealController {

    private final MealServiceImp service;

    @PostMapping("api/meals")
    public ResponseEntity<String> create(@RequestBody MealCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("api/meals")
    public ResponseEntity<List<MealDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }


    @GetMapping("api/meals/{id}")
    public ResponseEntity<MealDto> get(@PathVariable String id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PutMapping("api/meals/{id}")
    public ResponseEntity<Boolean> update(@RequestBody MealUpdateDto dto, @PathVariable String id) {
        dto.setId(id);
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("api/meals/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/meals/today")
    public ResponseEntity<List<MealDto>> getAllToday(){
        return new ResponseEntity<>(service.getAllToday(),HttpStatus.OK);
    }


    @GetMapping("api/meals/user/{id}")
    public ResponseEntity<MealDto> getMealOfMeal(@PathVariable String id) {
        return new ResponseEntity<>(service.getMealOfMeal(id), HttpStatus.OK);
    }

}

