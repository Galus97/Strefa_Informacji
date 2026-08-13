package pl.strefainformacji.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.entity.User;
import pl.strefainformacji.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RegisterValidator {
    private final UserRepository userRepository;
    private final MessageService messageService;

    public List<String> validateErrors(User user) {
        List<String> errors = new ArrayList<>();

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            errors.add(messageService.getMessage(ErrorMessages.EMAIL_IS_ALREADY_USED));
        }
        return errors;
    }
}
