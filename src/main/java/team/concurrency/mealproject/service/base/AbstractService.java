package team.concurrency.mealproject.service.base;

import team.concurrency.mealproject.mapper.base.BaseGenericMapper;
import team.concurrency.mealproject.repository.base.BaseGenericRepository;
import team.concurrency.mealproject.validator.base.BaseGenericValidator;

public abstract class AbstractService<
        R extends BaseGenericRepository,
        M extends BaseGenericMapper,
        V extends BaseGenericValidator
        > implements BaseGenericService {

    protected final M mapper;
    protected final V validator;
    protected final R repository;

    protected AbstractService(M mapper, V validator, R repository) {
        this.mapper = mapper;
        this.validator = validator;
        this.repository = repository;
    }
}