package com.deepspace.deanery.service;

import com.deepspace.deanery.api.DictionaryService;
import com.deepspace.deanery.api.InstructionService;
import com.deepspace.deanery.model.Instruction;
import com.deepspace.deanery.model.dictionary.InstructionTypeDic;
import com.deepspace.deanery.repository.InstructionRepository;
import com.deepspace.deanery.repository.StudentRepository;
import com.deepspace.dto.ExpulsionPercentageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstructionServiceImpl implements InstructionService {

    private final InstructionRepository instructionRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<ExpulsionPercentageResponse> getExpulsionPercentage() {
        Set<Instruction> instructions = instructionRepository.findAllByInstructionTypeValue(InstructionTypeDic.Value.EXPULSION);

        int maxCourse = 4;
        List<ExpulsionPercentageResponse> responses = new ArrayList<>(maxCourse);
        for (int i=1; i <= maxCourse; i++) {
            Integer course = i;
            Long studentQuantity = studentRepository.countAllByCourse(i);
            long expelled = instructions.stream()
                    .flatMap(item -> item.getStudents().stream())
                    .filter(item -> item.getCourse().equals(course))
                    .count();
            double percentage = expelled/studentQuantity.doubleValue();

            ExpulsionPercentageResponse expulsionPercentage = new ExpulsionPercentageResponse(percentage, course);
            responses.add(expulsionPercentage);
        }
        return responses;
    }
}
