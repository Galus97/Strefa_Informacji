package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.request.UserRequest;
import pl.strefainformacji.dto.response.UserResponse;
import pl.strefainformacji.entity.User;
import pl.strefainformacji.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MessageService messageService;

    private void throwIfIdIsInvalid(Long id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.INVALID_USER_ID));
        }
    }
}
