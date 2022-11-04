package com.deepspace.deanery.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeaneryException extends RuntimeException {

    private Reason reason;

    public DeaneryException(Reason reason, String text) {
        super(text);
        this.reason = reason;
    }

    public enum Reason {
        STARTUP_EXCEPTION,
        NOT_IMPLEMENTED,
        INVALID_ARGUMENT
    }
}
