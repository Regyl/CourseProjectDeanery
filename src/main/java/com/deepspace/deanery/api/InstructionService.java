package com.deepspace.deanery.api;

import com.deepspace.deanery.model.Instruction;
import com.deepspace.dto.ExpulsionPercentageCourseResponse;
import com.deepspace.dto.ExpulsionPercentageYearResponse;
import com.deepspace.dto.projection.ShortInstructionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Deprecated
public interface InstructionService {

    List<ExpulsionPercentageCourseResponse> getExpulsionPercentageByCourse();

    List<ExpulsionPercentageYearResponse> getExpulsionPercentageByYear(int start, int end);

    Page<ShortInstructionDTO> searchPage(Pageable pageable);

    Instruction findById(UUID id);
}
