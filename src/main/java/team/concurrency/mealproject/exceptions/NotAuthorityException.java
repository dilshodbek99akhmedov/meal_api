package team.concurrency.mealproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAuthorityException extends RuntimeException{

    public NotAuthorityException(String message) {
        super(message);
    }
}
