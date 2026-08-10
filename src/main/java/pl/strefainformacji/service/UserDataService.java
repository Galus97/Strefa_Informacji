package pl.strefainformacji.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.request.UserDataRequest;
import pl.strefainformacji.dto.response.UserDataResponse;
import pl.strefainformacji.entity.User;
import pl.strefainformacji.entity.UserData;
import pl.strefainformacji.exception.UserDataNotFoundException;
import pl.strefainformacji.exception.UserNotFoundException;
import pl.strefainformacji.repository.UserDataRepository;
import pl.strefainformacji.repository.UserRepository;
import pl.strefainformacji.util.ServiceValidator;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final UserService userService;
    private final MessageService messageService;
    private final ServiceValidator serviceValidator;

    @Transactional(readOnly = true)
    public UserDataResponse getUserData(Long userDataId) {
        serviceValidator.throwIfIdIsNotValid(userDataId, ErrorMessages.INVALID_USER_DATA_ID);
        return UserDataResponse.fromEntity(getUserDataOrThrowIfNotExist(userDataId)); 
    }

    public UserDataResponse getUserDataByUser(Long userId) {
        serviceValidator.throwIfIdIsNotValid(userId, ErrorMessages.INVALID_USER_ID);
        return UserDataResponse.fromEntity(userDataRepository
                .findByUser(userService.getUserOrThrowIfNotExist(userId)));
    }

    @Transactional
    public UserDataResponse saveUserData(UserDataRequest userDataRequest) {
        serviceValidator.throwIfRequestIsNull(userDataRequest, ErrorMessages.USER_DATA_REQUEST_IS_NULL);
        return UserDataResponse.fromEntity(userDataRepository.save(buildUserDataFromRequest(userDataRequest)));
    }

    @Transactional
    public UserDataResponse updateUserData(UserDataRequest userDataRequest) {
        serviceValidator.throwIfRequestIsNull(userDataRequest, ErrorMessages.USER_DATA_REQUEST_IS_NULL);
        serviceValidator.throwIfIdIsNotValid(userDataRequest.getUserDataId(), ErrorMessages.INVALID_USER_DATA_ID);

        UserData existingUserData = getUserDataOrThrowIfNotExist(userDataRequest.getUserDataId());
        existingUserData.setCity(userDataRequest.getCity());
        existingUserData.setStreet(userDataRequest.getStreet());
        existingUserData.setStreetNumber(userDataRequest.getStreetNumber());
        existingUserData.setApartmentNumber(userDataRequest.getApartmentNumber());
        existingUserData.setZipCode(userDataRequest.getZipCode());
        existingUserData.setPhoneNumber(userDataRequest.getPhoneNumber());
        existingUserData.setUser(userService.getUserOrThrowIfNotExist(userDataRequest.getUserDataId()));

        return UserDataResponse.fromEntity(userDataRepository.save(existingUserData));
    }

    @Transactional
    public void deleteUserData (Long userDataId) {
        serviceValidator.throwIfIdIsNotValid(userDataId, ErrorMessages.INVALID_USER_DATA_ID);
        userDataRepository.delete(getUserDataOrThrowIfNotExist(userDataId));
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
            .user(userService.getUserOrThrowIfNotExist(userDataRequest.getUserId()))
            .build();
        }
}