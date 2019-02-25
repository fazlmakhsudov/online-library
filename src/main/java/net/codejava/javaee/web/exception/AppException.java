package net.codejava.javaee.web.exception;

public class AppException extends Exception {

    private static final long serialVersionUID = 8288779062647218916L;

    public AppException() {
    }

    public AppException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppException(final String message) {
        super(message);
    }

}