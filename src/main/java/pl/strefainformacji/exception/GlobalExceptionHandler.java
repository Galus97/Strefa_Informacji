package pl.strefainformacji.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.strefainformacji.component.ErrorMessages;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleArticleNotFoundException(ArticleNotFoundException e) {
        return getMapResponseEntity(e);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleImageNotFoundException(ImageNotFoundException e) {
        return getMapResponseEntity(e);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException e) {
        return getMapResponseEntity(e);
    }

    private static ResponseEntity<Map<String, String>> getMapResponseEntity(RuntimeException e) {
        Map<String, String> response = new HashMap<>();
        response.put(ErrorMessages.ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
