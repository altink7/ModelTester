package at.altin.utils.exception;

public class MethodTesterRuntimeException extends RuntimeException {
    private static final String MESSAGE = "Error occurred while testing the method";

    public MethodTesterRuntimeException(String message) {
        super(MESSAGE + ": " + message);
    }

    public MethodTesterRuntimeException(String message, Throwable cause) {
        super(MESSAGE + ": " + message, cause);
    }
}