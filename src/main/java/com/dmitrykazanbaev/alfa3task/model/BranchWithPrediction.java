package com.dmitrykazanbaev.alfa3task.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchWithPrediction {
    @JsonUnwrapped
    private Branch branch;

    private Integer dayOfWeek;

    private Integer hourOfDay;

    private Long predicting;
}
