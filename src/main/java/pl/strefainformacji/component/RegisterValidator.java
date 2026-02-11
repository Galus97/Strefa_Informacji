package pl.strefainformacji.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.strefainformacji.entity.User;
import pl.strefainformacji.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegisterValidator {
    private final UserRepository userRepository;
    private final MessageService messageService;

    public List<String> validateErrors(User user) {
        List<String> errors = new ArrayList<>();

        Optional<User> userExistByEmail = userRepository.findByEmail(user.getEmail());
        if(userExistByEmail.isPresent()) {
            errors.add(messageService.getMessage(ErrorMessages.EMAIL_IS_ALREADY_USED));
        }
        return errors;
    }
}
