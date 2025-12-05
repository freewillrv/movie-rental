package com.rahul.verma.movierental.exception;

public class ValidationFailedException extends BaseException {

    public ValidationFailedException(final Throwable cause, final String message) {
        super(cause, message, 400);
    }

    public ValidationFailedException(final String message) {
        super(message, 400);
    }
}
