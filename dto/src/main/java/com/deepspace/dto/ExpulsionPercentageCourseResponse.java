package com.deepspace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpulsionPercentageCourseResponse {

    private Double percentage;

    private Integer course;
}
