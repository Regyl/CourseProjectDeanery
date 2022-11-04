package com.deepspace.deanery.api;

import com.deepspace.dto.ExpulsionPercentageResponse;

import java.util.List;

public interface InstructionService {

    List<ExpulsionPercentageResponse> getExpulsionPercentage();
}
