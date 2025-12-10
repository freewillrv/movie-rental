package com.rahul.verma.movierental.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableInput {
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = "id";
    private String direction = "asc";
}
