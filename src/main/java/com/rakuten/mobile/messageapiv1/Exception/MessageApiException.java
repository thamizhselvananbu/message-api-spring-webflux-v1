package com.rakuten.mobile.messageapiv1.Exception;


import lombok.Getter;

@Getter
public class MessageApiException extends RuntimeException {

    private final int errorCode;
    private final String errorMessage;


    public MessageApiException(final int errorCode, final String errorMessage, final Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
