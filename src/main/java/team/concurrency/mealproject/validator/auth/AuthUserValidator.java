package team.concurrency.mealproject.validator.auth;

import org.springframework.stereotype.Component;
import team.concurrency.mealproject.dto.user.UserCreateDto;
import team.concurrency.mealproject.dto.user.UserUpdateDto;
import team.concurrency.mealproject.exceptions.validation.ValidationException;
import team.concurrency.mealproject.validator.base.AbstractValidator;

@Component
public class AuthUserValidator extends AbstractValidator<UserCreateDto, UserUpdateDto, String > {

    @Override
    public void validateKey(String id) throws ValidationException {


    }

    @Override
    public void validOnCreate(UserCreateDto userCreateDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(UserUpdateDto cd) throws ValidationException {

    }
}
