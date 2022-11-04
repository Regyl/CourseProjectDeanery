package com.deepspace.deanery.service;

import com.deepspace.deanery.api.DictionaryService;
import com.deepspace.deanery.api.InstructionService;
import com.deepspace.deanery.exception.DeaneryException;
import com.deepspace.deanery.model.Instruction;
import com.deepspace.deanery.model.Student;
import com.deepspace.deanery.model.dictionary.InstructionTypeDic;
import com.deepspace.deanery.repository.InstructionRepository;
import com.deepspace.deanery.repository.StudentRepository;
import com.deepspace.dto.ExpulsionPercentageCourseResponse;
import com.deepspace.dto.ExpulsionPercentageYearResponse;
import com.deepspace.dto.ShortInstructionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstructionServiceImpl implements InstructionService {

    private final InstructionRepository instructionRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<ExpulsionPercentageCourseResponse> getExpulsionPercentageByCourse() {
        Set<Student> expelledStudents = instructionRepository.findAllByInstructionTypeValue(InstructionTypeDic.Value.EXPULSION).stream()
                .flatMap(item -> item.getStudents().stream()).collect(Collectors.toSet());

        int maxCourse = 4;
        List<ExpulsionPercentageCourseResponse> responses = new ArrayList<>(maxCourse);
        for (int i=1; i <= maxCourse; i++) {
            Integer course = i;
            Long studentQuantity = studentRepository.countAllByCourse(i);
            long expelled = expelledStudents.stream()
                    .filter(item -> item.getCourse().equals(course))
                    .count();
            double percentage = expelled/studentQuantity.doubleValue();

            ExpulsionPercentageCourseResponse expulsionPercentage = new ExpulsionPercentageCourseResponse(percentage, course);
            responses.add(expulsionPercentage);
        }
        return responses;
    }

    @Override
    public List<ExpulsionPercentageYearResponse> getExpulsionPercentageByYear(int start, int end) {
        Set<Student> expelledStudents = instructionRepository.findAllByInstructionTypeValue(InstructionTypeDic.Value.EXPULSION).stream()
                .flatMap(item -> item.getStudents().stream()).collect(Collectors.toSet());

        int difference = end - start;
        List<ExpulsionPercentageYearResponse> responses = new ArrayList<>(difference);
        for (int i=start; i<=end; i++) {
            int currentYear = i;
            Long studentQuantity = studentRepository.countAllByEduStartYear(i);
            long expelled = expelledStudents.stream()
                    .filter(item -> item.getEduStart().getYear() <= currentYear && item.getEduEnd().getYear() >= currentYear)
                    .count();
            double percentage = expelled/studentQuantity.doubleValue();

            ExpulsionPercentageYearResponse response = new ExpulsionPercentageYearResponse(percentage, currentYear);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public Page<ShortInstructionDTO> searchPage(Pageable pageable) {
        return instructionRepository.findAllByDeletedIsFalse(pageable);
    }

    @Override
    public Instruction findById(UUID id) {
        return instructionRepository.findById(id)
                .orElseThrow(() -> new DeaneryException(DeaneryException.Reason.INVALID_ARGUMENT,
                "Не найдена сущность с идентификатором " + id));
    }
}
