package com.deepspace.deanery.model;

import com.deepspace.deanery.model.embedded.FullName;
import com.deepspace.deanery.model.embedded.PassportInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Human extends AbstractEntity {

    @NotNull
    @Embedded
    private FullName fullName;

    @NotNull
    @Column(nullable = false)
    private String gradeBookNumber;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Embedded
    private PassportInfo passportInfo;
}
