package pl.strefainformacji.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;

@Component
@RequiredArgsConstructor
public class ServiceValidator {
    private final MessageService messageService;

    public void throwIfIdIsNotValid(Long id, String errorMessage) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(messageService.getMessage(errorMessage, id));
        }
    }

    public void throwIfRequestIsNull(Object request, String errorMessage) {
        if (request == null) {
            throw new IllegalArgumentException(messageService.getMessage(errorMessage));
        }
    }

    public void throwIfEmailIsInvalid(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.EMAIL_IS_INVALID, email));
        }
    }
}
