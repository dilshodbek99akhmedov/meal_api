package team.concurrency.mealproject.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import team.concurrency.mealproject.entity.base.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractRepository<
        E extends BaseEntity,
        K extends Serializable
        > extends JpaRepository<E, K>, BaseGenericRepository {
}