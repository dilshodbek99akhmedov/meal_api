package team.concurrency.mealproject.exceptions.meal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MealAlreadyAddedException extends RuntimeException{
    public MealAlreadyAddedException(String message){
        super(message);
    }
}
