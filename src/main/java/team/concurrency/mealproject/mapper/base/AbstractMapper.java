package team.concurrency.mealproject.mapper.base;

import team.concurrency.mealproject.dto.base.BaseGenericDto;
import team.concurrency.mealproject.dto.base.GenericDto;
import team.concurrency.mealproject.entity.base.BaseEntity;

import java.util.List;

public interface AbstractMapper<
        E extends BaseEntity,
        D extends GenericDto,
        CD extends BaseGenericDto,
        UP extends GenericDto
        > extends BaseGenericMapper {


    D toDto(E entity);

    List<D> toDto(List<E> entities);

    E fromCreateDto(CD createDto);

    E fromUpdateDto(UP updateDto);
}
