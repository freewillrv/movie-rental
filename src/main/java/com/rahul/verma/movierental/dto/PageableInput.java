package com.rahul.verma.movierental.dto;

import com.rahul.verma.movierental.util.Constants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageableInput {
    @Min(0)
    private Integer page = 0;
    @Min(1)
    @Max(100)
    private Integer size = 10;
    private String sortBy = Constants.ID;
    private String direction = Constants.ASC;
}
