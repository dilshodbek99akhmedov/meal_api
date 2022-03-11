package team.concurrency.mealproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import team.concurrency.mealproject.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders", indexes = {@Index(name = "user_id_index", columnList = "user_id"), @Index(name = "meal_id_index", columnList = "meal_id")})
public class Order implements BaseEntity {
    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false, name = "user_id")
    private String userId;

    @Column(nullable = false, name = "meal_id")
    private String mealId;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @Column(columnDefinition = "boolean default false")
    private boolean taken;

}
