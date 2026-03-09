package pl.strefainformacji.exception;

public class UserDataNotFoundException extends RuntimeException {
    
    public UserDataNotFoundException (String message) {
        super(message);
    }
}