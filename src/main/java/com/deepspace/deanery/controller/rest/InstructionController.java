package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.api.InstructionService;
import com.deepspace.dto.ExpulsionPercentageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Instruction controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/instruction")
public class InstructionController {

    private final InstructionService instructionService;

    @GetMapping("/expulsion/percentage")
    @Operation(summary = "Percentage of expulsion group by courses")
    public List<ExpulsionPercentageResponse> getResponse() {
        return instructionService.getExpulsionPercentage();
    }
}
