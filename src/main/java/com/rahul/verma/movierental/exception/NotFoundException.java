package com.rahul.verma.movierental.exception;

public class NotFoundException extends BaseException {

    public NotFoundException(Throwable cause, String message) {
        super(cause, message, 404);
    }
}
