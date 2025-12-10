package com.rahul.verma.movierental.util;

import com.rahul.verma.movierental.dto.MessageDto;
import com.rahul.verma.movierental.dto.ResponseDto;
import com.rahul.verma.movierental.dto.ResponseListDto;
import com.rahul.verma.movierental.exception.BaseException;
import com.rahul.verma.movierental.exception.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class AppControllerAdvice {

    // Handles a specific custom exception
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResponseDto> handleMyCustomNotFoundException(BaseException ex) {
        // Create a custom error response object
        // Return the response entity with a specific HTTP status
        return ResponseEntity.status(ex.getStatusCode())
                .body(ResponseDto.builder()
                        .messages(List.of(new MessageDto(ex.getDefaultMessage(), HttpStatus.OK.value())))
                        .build());
    }

}
