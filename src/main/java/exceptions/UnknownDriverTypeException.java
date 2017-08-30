package exceptions;

public class UnknownDriverTypeException extends RuntimeException {
    public UnknownDriverTypeException(String arg) {
        super(arg);
    }
}
