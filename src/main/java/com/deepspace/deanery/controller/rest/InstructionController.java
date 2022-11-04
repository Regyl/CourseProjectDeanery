package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.api.InstructionService;
import com.deepspace.deanery.model.Instruction;
import com.deepspace.dto.ExpulsionPercentageCourseResponse;
import com.deepspace.dto.ExpulsionPercentageYearResponse;
import com.deepspace.dto.ShortInstructionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Instruction controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/instruction")
public class InstructionController {

    private final InstructionService instructionService;

    @GetMapping("/expulsion/percentage/course")
    @Operation(summary = "Percentage of expulsion group by courses")
    public List<ExpulsionPercentageCourseResponse> getResponse() {
        return instructionService.getExpulsionPercentageByCourse();
    }

    @GetMapping("/expulsion/percentage/year")
    public List<ExpulsionPercentageYearResponse> getExpulsionPercentageYearResponse(@RequestParam(defaultValue = "2015") int start,
                                                                                    @RequestParam(defaultValue = "2030") int end) {
        return instructionService.getExpulsionPercentageByYear(start, end);
    }

    @GetMapping("/search")
    public Page<ShortInstructionDTO> searchInstructions(@RequestParam Pageable pageable) {
        return instructionService.searchPage(pageable);
    }

    @GetMapping("/view/{id}")
    public Instruction view(@PathVariable UUID id) {
        return instructionService.findById(id);
    }


}
