package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.model.Instruction;
import com.deepspace.deanery.repository.AbstractJpaRepository;
import com.deepspace.deanery.repository.InstructionRepository;
import com.deepspace.dto.projection.ExpulsionPercentageCourseResponse;
import com.deepspace.dto.projection.ExpulsionPercentageYearResponse;
import com.deepspace.dto.projection.ShortInstructionDTO;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/instructions")
public class InstructionController extends AbstractCRUDController<Instruction> {

    private final InstructionRepository repository;

    @GetMapping("/expulsion/percentage/course")
    public String getResponse(Model model) {
        List<ExpulsionPercentageCourseResponse> expulsionPercentageByCourse = repository.getExpulsionPercentageByCourse();
        model.addAttribute("expulsionPercentageByCourse", expulsionPercentageByCourse);
        return "expulsion-list-by-course";
    }

    @GetMapping("/expulsion/percentage/year")
    public String getExpulsionPercentageYearResponse(Model model) {
        List<ExpulsionPercentageYearResponse> expulsionPercentageByCourse = repository.getExpulsionPercentageByYear();
        model.addAttribute("expulsionPercentageByYear", expulsionPercentageByCourse);
        return "expulsion-list-by-year";
    }

    @GetMapping("/quantity-by-type")
    public String quantityOfStudentsForEachInstructionTypeGroupByGroups(Model model) {
        var quantityOfStudents = repository.quantityOfStudentsForEachInstructionTypeGroupByGroups();
        model.addAttribute("entities", quantityOfStudents);
        return "quantity-of-students";
    }

    @GetMapping("/last-instruction-for-each-student")
    public String findLastInstructionForEachStudent(Model model) {
        var instructionList = repository.findLastInstructionForEachStudent();
        model.addAttribute("entities", instructionList);
        return "last-instruction-for-each-student";
    }

    @PostMapping("/search")
    public Page<ShortInstructionDTO> searchInstructions(@ParameterObject Pageable pageable) {
        return repository.findAllBy(pageable);
    }

    @Override
    protected String getTableFilename() {
        return "instruction-table";
    }

    @Override
    protected AbstractJpaRepository<Instruction> getRepository() {
        return repository;
    }
}
