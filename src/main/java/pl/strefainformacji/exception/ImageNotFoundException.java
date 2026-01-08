package pl.strefainformacji.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException (String message) {
        super(message);
    }
}
