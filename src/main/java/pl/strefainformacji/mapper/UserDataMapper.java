package pl.strefainformacji.mapper;

import pl.strefainformacji.dto.request.UserDataRequest;
import pl.strefainformacji.dto.response.UserDataResponse;
import pl.strefainformacji.model.UserData;

public class UserDataMapper {
    public static UserDataResponse toUserDataResponse(UserData userData) {
        return new UserDataResponse(
                userData.getCity(),
                userData.getStreet(),
                userData.getStreetNumber(),
                userData.getApartmentNumber(),
                userData.getZipCode(),
                userData.getPhoneNumber()
        );
    }

    public static UserData toUserDataModel(UserDataRequest userDataRequest) {
        return UserData.builder()
                .userDataId(userDataRequest.getUserDataId())
                .city(userDataRequest.getCity())
                .street(userDataRequest.getStreet())
                .streetNumber(userDataRequest.getStreetNumber())
                .apartmentNumber(userDataRequest.getApartmentNumber())
                .zipCode(userDataRequest.getZipCode())
                .phoneNumber(userDataRequest.getPhoneNumber())
                .build();
    }
}
