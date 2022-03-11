package team.concurrency.mealproject.entity.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable implements BaseEntity{

    @Id
    @Column(unique = true)
    private String id;

    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt;


    private String createdBy;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(nullable = true, columnDefinition = "timestamp default now()")
    private LocalDateTime updatedAt;

    private String updatedBy;

    @Column(columnDefinition = "integer default 0")
    private int status; //status enum'dan qanaqa statusdaligini frontga berib yuboramiz

    @Column(nullable = true, columnDefinition = "boolean default false")
    private boolean deleted;
}
