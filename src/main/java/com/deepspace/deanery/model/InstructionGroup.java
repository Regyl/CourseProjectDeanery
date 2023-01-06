package com.deepspace.deanery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InstructionGroup extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String groupNumber;

    @OneToMany(mappedBy = "instructionGroup", fetch = FetchType.LAZY)
    private List<Instruction> instructions;
}
