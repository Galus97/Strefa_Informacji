package pl.strefainformacji.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.request.UserDataRequest;
import pl.strefainformacji.dto.response.UserDataResponse;
import pl.strefainformacji.entity.UserData;
import pl.strefainformacji.exception.UserDataNotFoundException;
import pl.strefainformacji.repository.UserDataRepository;
import pl.strefainformacji.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final UserRepository userRepository;
    private final MessageService messageService;

    public UserDataResponse getUserData(Long userDataId) {
        throwIfIdIsInvalid(userDataId);
        return UserDataResponse.fromEntity(getUserDataOrThrowIfNotExist(userDataId)); 
    }

    public void deleteUserData (Long userDataId) {
        throwIfIdIsInvalid(userDataId);
        userDataRepository.deleteById(userDataId);
    }

    public UserDataResponse saveUserData(UserDataRequest userDataRequest) {
       throwIfRequestIsNull(userDataRequest);
       
       return UserDataResponse.fromEntity(userDataRepository.save(buildUserDataFromRequest(userDataRequest)));
    }

    private void throwIfRequestIsNull(UserDataRequest userDataRequest) {
        if(userDataRequest == null){
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.USER_DATA_REQUEST_IS_NULL));
        }
    }

    private void throwIfIdIsInvalid(Long id) {
        if(id == null || id < 1) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.INVALID_USER_DATA_ID));        }
    }

    private UserData getUserDataOrThrowIfNotExist(Long id) {
        return userDataRepository.findById(id).orElseThrow(
            () -> new UserDataNotFoundException(messageService.getMessage(ErrorMessages.USER_DATA_NOT_FOUND)));
    }

    private UserData buildUserDataFromRequest(UserDataRequest userDataRequest) {
        return UserData.builder()
            .city(userDataRequest.getCity())
            .street(userDataRequest.getStreet())
            .streetNumber(userDataRequest.getStreetNumber())
            .apartmentNumber(userDataRequest.getApartmentNumber())
            .zipCode(userDataRequest.getZipCode())
            .phoneNumber(userDataRequest.getPhoneNumber())
            .user(userRepository.findById(userDataRequest.getUserRequest().getUserId())
                .orElseThrow(() -> new UserDataNotFoundException("")))
            .build();
        }
}