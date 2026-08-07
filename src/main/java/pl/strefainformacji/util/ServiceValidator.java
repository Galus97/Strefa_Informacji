package pl.strefainformacji.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.strefainformacji.component.MessageService;

@Component
@RequiredArgsConstructor
public class ServiceValidator {
    private final MessageService messageService;

    public void throwIfIdIsNotValid(Long id, String errorMessage) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException(messageService.getMessage(errorMessage, id));
        }
    }
}
