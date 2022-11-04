package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Instruction;
import com.deepspace.deanery.model.dictionary.InstructionTypeDic;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface InstructionRepository extends AbstractJpaRepository<Instruction> {

    Set<Instruction> findAllByInstructionTypeValue(InstructionTypeDic.Value instructionType);
}
