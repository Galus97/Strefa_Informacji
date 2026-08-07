package pl.strefainformacji.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.strefainformacji.component.MessageService;

@Component
@RequiredArgsConstructor
public class ServiceValidator {
    private final MessageService messageService;

}
