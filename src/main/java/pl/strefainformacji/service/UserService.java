package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.response.UserResponse;
import pl.strefainformacji.entity.User;
import pl.strefainformacji.exception.UserNotFoundException;
import pl.strefainformacji.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MessageService messageService;

    public UserResponse getUserResponse(Long userId){
        throwIfIdIsInvalid(userId);
        return UserResponse.fromEntity(getUserOrThrowIfNotExist(userId));
    }

    private void throwIfIdIsInvalid(Long id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.INVALID_USER_ID));
        }
    }

    private User getUserOrThrowIfNotExist(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(messageService.getMessage(ErrorMessages.USER_NOT_FOUND, id)));
    }
}
