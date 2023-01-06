package com.deepspace.deanery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@OnDelete(action = OnDeleteAction.CASCADE)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private UUID id;

    /*@EqualsAndHashCode.Exclude
    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime createDateTime;

    @EqualsAndHashCode.Exclude
    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updateDateTime;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted;*/
}
