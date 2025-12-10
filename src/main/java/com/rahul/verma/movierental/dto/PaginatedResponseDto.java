package com.rahul.verma.movierental.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginatedResponseDto<T> {
    private List<T> data;

    private Integer pageNumber;
    private Integer pageSize;
    private Long totalItems;
    private Integer totalPages;

    private List<MessageDto> messages;
    private Integer statusCode;
}
