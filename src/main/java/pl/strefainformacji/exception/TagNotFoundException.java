package pl.strefainformacji.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException (String message) {
        super(message);
    }
}
