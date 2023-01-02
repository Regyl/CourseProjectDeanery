package com.deepspace.dto.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ExpulsionPercentageYearResponse {

    Double getPercentage();

    Integer getQueryYear();
}
