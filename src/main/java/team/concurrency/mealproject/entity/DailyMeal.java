package team.concurrency.mealproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import team.concurrency.mealproject.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = @Index(name = "measdgewq", columnList = "meal_id"))
public class DailyMeal implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "meal_id")
    private String mealId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;
}
