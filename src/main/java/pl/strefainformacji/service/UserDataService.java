package pl.strefainformacji.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.entity.UserData;
import pl.strefainformacji.exception.UserDataNotFoundException;
import pl.strefainformacji.repository.UserDataRepository;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final MessageService messageService;

    // public UserDataResponse getUserData(Long id) {
    //     throwIfIdIsInvalid(id);
        
    // }

    private void throwIfIdIsInvalid(Long id) {
        if(id == null || id < 1) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.INVALID_USER_DATA_ID));        }
    }

    private UserData getUserDataOrThrow(Long id) {
        return userDataRepository.findById(id).orElseThrow(
            () -> new UserDataNotFoundException(messageService.getMessage(ErrorMessages.USER_DATA_NOT_FOUND)));
    }
}