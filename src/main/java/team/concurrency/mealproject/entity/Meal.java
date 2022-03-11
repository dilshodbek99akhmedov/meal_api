package team.concurrency.mealproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import team.concurrency.mealproject.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Meal extends Auditable {
    @Column(nullable = false)
    private String name;
    private String description;
    private String path;
}
