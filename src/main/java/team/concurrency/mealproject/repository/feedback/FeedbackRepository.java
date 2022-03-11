package team.concurrency.mealproject.repository.feedback;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.concurrency.mealproject.entity.Feedback;
import team.concurrency.mealproject.repository.base.AbstractRepository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FeedbackRepository extends AbstractRepository<Feedback,String> {

    @Transactional
    @Modifying
    @Query(value = "update feedbacks set deleted=true where id=:id",nativeQuery = true)
    void setDeleted(String id);

    List<Feedback> getAllByUserId (String userId);

    List<Feedback> getAllByType(String type);

    @Query(value = "select * from feedbacks",nativeQuery = true)
    List<Feedback> getAll();
}
