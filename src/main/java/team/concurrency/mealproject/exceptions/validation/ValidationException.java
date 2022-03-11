package team.concurrency.mealproject.exceptions.validation;


public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}