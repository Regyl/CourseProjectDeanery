package com.deepspace.deanery.model;

import com.deepspace.deanery.model.dictionary.StudentStatusDic;
import com.deepspace.deanery.model.embedded.FullName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends AbstractEntity {

    @NotNull
    @Embedded
    private FullName fullName;

    @NotNull
    @ManyToOne
    private StudentStatusDic studentStatus;

    @NotNull
    @Column(nullable = false)
    private String gradeBookNumber;

    @NotNull
    @Column(nullable = false)
    private LocalDate eduStart;

    @Column
    private LocalDate eduEnd;

    @ManyToOne(optional = false)
    private StudentGroup studentGroup;

    @NotNull
    @Column(nullable = false)
    private Integer course;
}
