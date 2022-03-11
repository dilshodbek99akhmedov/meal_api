package team.concurrency.mealproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.concurrency.mealproject.dto.dailyMeal.DailyMealCreateDto;
import team.concurrency.mealproject.service.dailyMeal.DailyMealServiceImp;

@RestController
@RequiredArgsConstructor
public class DailyMealController {
private final DailyMealServiceImp service;

    @PostMapping("api/daily-meal")
    public ResponseEntity<String> create(@RequestBody DailyMealCreateDto dto){
        return new ResponseEntity<>(service.create(dto) , HttpStatus.CREATED);
    }



}
