package com.rahul.verma.movierental.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {

    private final int statusCode;

    public BaseException(Throwable cause, String message, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public BaseException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
