package team.concurrency.mealproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import team.concurrency.mealproject.exceptions.meal.MealAlreadyAddedException;
import team.concurrency.mealproject.exceptions.meal.MealNotFoundException;
import team.concurrency.mealproject.response.AppError;


@ControllerAdvice("concurrency.team.concurrency.mealproject")
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<AppError> handle500(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>(
                new AppError(e.getMessage(),
                        webRequest,
                        HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<AppError> handleNotFound(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>(
                new AppError(e.getMessage(),
                        webRequest,
                        HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MealNotFoundException.class})
    public ResponseEntity<AppError> mealNotFound(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>(
                new AppError(e.getMessage(),
                        webRequest,
                        HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MealAlreadyAddedException.class})
    public ResponseEntity<AppError> mealAlreadyAdded(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>(
                new AppError(e.getMessage(),
                        webRequest,
                        HttpStatus.BAD_GATEWAY),
                HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(value = {NotAuthorityException.class})
    public ResponseEntity<AppError> handleAuthority(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>(
                new AppError(e.getMessage(),
                        webRequest,
                        HttpStatus.FORBIDDEN),
                HttpStatus.FORBIDDEN);
    }



}
