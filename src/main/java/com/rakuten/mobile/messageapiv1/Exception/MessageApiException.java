package com.rakuten.mobile.messageapiv1.Exception;


public class ValidationException extends Exception {

    private final int errorCode;
    private final String errorMessage;


    public ValidationException(final int errorCode, final String errorMessage, final Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
