package pl.strefainformacji.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.repository.UserDataRepository;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final MessageService messageService;

    private void throwIfIdIsInvalid(Long id) {
        if(id == null || id < 1) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.INVALID_USER_DATA_ID));        }
    }
}