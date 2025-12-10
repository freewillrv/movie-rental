package com.rahul.verma.movierental.exception;

public class InternalServerException extends BaseException {

    public InternalServerException(final Throwable cause, final String message) {
        super(cause, message, 500);
    }

    public InternalServerException(final String message) {
        super(message, 500);
    }
}
