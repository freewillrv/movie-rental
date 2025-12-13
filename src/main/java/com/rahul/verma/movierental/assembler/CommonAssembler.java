package com.rahul.verma.movierental.assembler;

import com.rahul.verma.movierental.dto.MessageDto;
import com.rahul.verma.movierental.dto.PaginatedResponseDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;

import java.util.List;

public class CommonAssembler {

    public <R> PaginatedResponseDto<R> paginate(Page<@NonNull R> page, List<MessageDto> messages) {
        PaginatedResponseDto<R> response = new PaginatedResponseDto<>();
        response.setData(page.getContent());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalItems(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setMessages(messages);
        return response;
    }
}
