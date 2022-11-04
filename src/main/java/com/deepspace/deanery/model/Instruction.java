package com.deepspace.deanery.model;

import com.deepspace.deanery.model.dictionary.InstructionBasisDic;
import com.deepspace.deanery.model.dictionary.InstructionTypeDic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Instruction extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String number;

    @NotNull
    @ManyToOne(optional = false)
    private InstructionBasisDic instructionBasis;

    @NotNull
    @ManyToOne(optional = false)
    private InstructionTypeDic instructionType;

    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @NotNull
    @ManyToMany
    private List<Student> students;
}


