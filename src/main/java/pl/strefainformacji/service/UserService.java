package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.mapper.UserMapper;
import pl.strefainformacji.util.RegisterValidator;
import pl.strefainformacji.dto.request.UserRequest;
import pl.strefainformacji.dto.response.UserResponse;
import pl.strefainformacji.model.User;
import pl.strefainformacji.exception.UserNotFoundException;
import pl.strefainformacji.exception.ValidationException;
import pl.strefainformacji.repository.UserRepository;
import pl.strefainformacji.util.ServiceValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MessageService messageService;
    private final PasswordEncoder passwordEncoder;
    private final RegisterValidator registerValidator;
    private final ServiceValidator serviceValidator;

    @Transactional(readOnly = true)
    public UserResponse getUserResponse(Long userId){
        serviceValidator.throwIfIdIsNotValid(userId, ErrorMessages.INVALID_USER_ID);
        return UserMapper.toUserResponse(getUserOrThrowIfNotExist(userId));
    }

    @Transactional
    public UserResponse saveNewUser(UserRequest userRequest) throws ValidationException {
        serviceValidator.throwIfRequestIsNull(userRequest, ErrorMessages.USER_REQUEST_IS_NULL);
        User user = UserMapper.toUserModel(userRequest);

        List<String> validationFailures = registerValidator.validateErrors(user);

        if (validationFailures.isEmpty()) {
            return UserMapper.toUserResponse(userRepository.save(user));
        } else  {
            throw new ValidationException(validationFailures);
        }

    }

    @Transactional
    public void deleteUser(Long userId){
        serviceValidator.throwIfIdIsNotValid(userId, ErrorMessages.INVALID_USER_ID);
        userRepository.delete(getUserOrThrowIfNotExist(userId));
    }

    @Transactional
    public UserResponse updateUser(UserRequest userRequest){
        serviceValidator.throwIfRequestIsNull(userRequest, ErrorMessages.USER_REQUEST_IS_NULL);
        serviceValidator.throwIfIdIsNotValid(userRequest.getUserId(), ErrorMessages.INVALID_USER_ID);

        User existingUser = getUserOrThrowIfNotExist(userRequest.getUserId());
        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setEmail(userRequest.getEmail());

        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        return UserMapper.toUserResponse(userRepository.save(existingUser));

    }

    //Used in others classes
    public User getUserOrThrowIfNotExist(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        messageService.getMessage(ErrorMessages.USER_NOT_FOUND, id)));
    }
}
