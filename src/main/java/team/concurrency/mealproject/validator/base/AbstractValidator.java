package team.concurrency.mealproject.validator.base;

import team.concurrency.mealproject.exceptions.validation.ValidationException;

public abstract class AbstractValidator<CD, UD, K> implements BaseGenericValidator {

    public abstract void validateKey(K id) throws
            ValidationException;

    public abstract void validOnCreate(CD cd) throws ValidationException;

    public abstract void validOnUpdate(UD cd) throws ValidationException;


}