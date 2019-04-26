package com.jandi.integration.exception;

public class JandiException extends RuntimeException {

    private static final long serialVersionUID = 8220202394783226559L;

    public JandiException() {
    }

    public JandiException(String message) {
        super(message);
    }

    public JandiException(String message, Throwable cause) {
        super(message, cause);
    }

    public JandiException(Throwable cause) {
        super(cause);
    }

    public JandiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
