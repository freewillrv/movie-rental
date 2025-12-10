package com.rahul.verma.movierental.exception;

public class ConflictException extends BaseException {

    public ConflictException(Throwable cause, String message) {
        super(cause, message, 409);
        this.defaultMessage = "Duplicate resource found for the same. Conflict occurred.";
    }
    public ConflictException(String message) {
        super(message, 409);
        this.defaultMessage = "Duplicate resource found for the same. Conflict occurred.";
    }
}
