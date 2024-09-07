package at.altin.utils.exception;

public class ModelTesterRuntimeException extends RuntimeException {
    private static final String MESSAGE = "Error occurred while testing the model";

    public ModelTesterRuntimeException(String message) {
        super(MESSAGE + ": " + message);
    }

    public ModelTesterRuntimeException(String message, Throwable cause) {
        super(MESSAGE + ": " + message, cause);
    }
}

