package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.api.InstructionController;
import com.deepspace.deanery.model.Instruction;
import com.deepspace.deanery.repository.AbstractJpaRepository;
import com.deepspace.deanery.repository.InstructionRepository;
import com.deepspace.dto.ExpulsionPercentageCourseResponse;
import com.deepspace.dto.ExpulsionPercentageYearResponse;
import com.deepspace.dto.projection.ShortInstructionDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/instruction")
public class InstructionControllerImpl extends AbstractCRUDController<Instruction> implements InstructionController {

    private final InstructionRepository repository;

    @GetMapping("/expulsion/percentage/course")
    @Operation(summary = "Percentage of expulsion group by courses")
    public String getResponse(Model model) {
        List<ExpulsionPercentageCourseResponse> expulsionPercentageByCourse = repository.getExpulsionPercentageByCourse();
        model.addAttribute("expulsionPercentageByCourse", expulsionPercentageByCourse);
        return "expulsion-list-by-course";
    }

    @GetMapping("/expulsion/percentage/year")
    public List<ExpulsionPercentageYearResponse> getExpulsionPercentageYearResponse(@RequestParam(required = false, defaultValue = "2015") int start,
                                                                                    @RequestParam(required = false, defaultValue = "2030") int end) {
        return repository.getExpulsionPercentageByYear(start, end);
    }

    @PostMapping("/search")
    public Page<ShortInstructionDTO> searchInstructions(@ParameterObject Pageable pageable) {
        return repository.findAllBy(pageable);
    }

    @Override
    protected AbstractJpaRepository<Instruction> getRepository() {
        return repository;
    }
}
