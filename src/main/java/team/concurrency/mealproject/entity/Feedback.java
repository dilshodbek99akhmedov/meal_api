package team.concurrency.mealproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import team.concurrency.mealproject.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback extends Auditable {

    @Column(nullable = false)
    private String message;

    private String type;

    @Column(nullable = false,name="user_id")
    private String userId;
}
