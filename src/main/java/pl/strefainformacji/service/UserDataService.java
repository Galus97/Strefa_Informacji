package pl.strefainformacji.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.request.UserDataRequest;
import pl.strefainformacji.dto.response.UserDataResponse;
import pl.strefainformacji.mapper.UserDataMapper;
import pl.strefainformacji.model.User;
import pl.strefainformacji.model.UserData;
import pl.strefainformacji.exception.UserDataNotFoundException;
import pl.strefainformacji.repository.UserDataRepository;
import pl.strefainformacji.util.ServiceValidator;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final MessageService messageService;
    private final ServiceValidator serviceValidator;


    @Transactional(readOnly = true)
    public UserDataResponse getUserDataByUser(User user) {
        UserData userData = userDataRepository.findByUser(user)
                .orElseThrow(() -> new UserDataNotFoundException(ErrorMessages.USER_DATA_NOT_FOUND_BY_USER));

        return UserDataMapper.toUserDataResponse(userData);
    }

    @Transactional
    public UserDataResponse saveUserData(UserDataRequest userDataRequest, User user) {
        serviceValidator.throwIfRequestIsNull(userDataRequest, ErrorMessages.USER_DATA_REQUEST_IS_NULL);

        UserData userData = userDataRepository.save(UserDataMapper.toUserDataModel(userDataRequest, user));

        return UserDataMapper.toUserDataResponse(userData);
    }

    @Transactional
    public UserDataResponse updateUserData(UserDataRequest userDataRequest, User user) {
        serviceValidator.throwIfRequestIsNull(userDataRequest, ErrorMessages.USER_DATA_REQUEST_IS_NULL);

        UserData existingUserData = getUserDataByUserOrThrow(user);

        existingUserData.setCity(userDataRequest.getCity());
        existingUserData.setStreet(userDataRequest.getStreet());
        existingUserData.setStreetNumber(userDataRequest.getStreetNumber());
        existingUserData.setApartmentNumber(userDataRequest.getApartmentNumber());
        existingUserData.setZipCode(userDataRequest.getZipCode());
        existingUserData.setPhoneNumber(userDataRequest.getPhoneNumber());

        return UserDataMapper.toUserDataResponse(userDataRepository.save(existingUserData));
    }

    @Transactional
    public void deleteUserData (User user) {
        userDataRepository.delete(getUserDataByUserOrThrow(user));
    }

    private UserData getUserDataByUserOrThrow(User user) {
        return userDataRepository.findByUser(user)
                .orElseThrow(() -> new UserDataNotFoundException(
                        messageService.getMessage(ErrorMessages.USER_DATA_NOT_FOUND_BY_USER)));
    }
}