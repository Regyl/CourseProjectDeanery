package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Instruction;
import com.deepspace.deanery.model.dictionary.InstructionTypeDic;
import com.deepspace.dto.ShortInstructionDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface InstructionRepository extends AbstractJpaRepository<Instruction> {

    Set<Instruction> findAllByInstructionTypeValue(InstructionTypeDic.Value instructionType);

    Page<ShortInstructionDTO> findAllByDeletedIsFalse(Pageable pageable);
}
