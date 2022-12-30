package com.deepspace.deanery.service;

import com.deepspace.deanery.api.InstructionService;
import com.deepspace.deanery.exception.DeaneryException;
import com.deepspace.deanery.model.Instruction;
import com.deepspace.deanery.repository.InstructionRepository;
import com.deepspace.deanery.repository.StudentRepository;
import com.deepspace.dto.ExpulsionPercentageCourseResponse;
import com.deepspace.dto.ExpulsionPercentageYearResponse;
import com.deepspace.dto.projection.ShortInstructionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstructionServiceImpl implements InstructionService {

    private final InstructionRepository instructionRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<ExpulsionPercentageCourseResponse> getExpulsionPercentageByCourse() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ExpulsionPercentageYearResponse> getExpulsionPercentageByYear(int start, int end) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<ShortInstructionDTO> searchPage(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Instruction findById(UUID id) {
        return instructionRepository.findById(id)
                .orElseThrow(() -> new DeaneryException(DeaneryException.Reason.INVALID_ARGUMENT,
                "Не найдена сущность с идентификатором " + id));
    }
}
