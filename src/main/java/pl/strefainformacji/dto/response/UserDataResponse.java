package pl.strefainformacji.dto.response;

import pl.strefainformacji.entity.UserData;

public record UserDataResponse (Long UserDataId, String city, String street, Integer streetNumber,
Integer apartmentNumber, String zipCode, Integer phoneNumber, UserResponse userResponse) {

    public static UserDataResponse fromEntity(UserData userData) {
        return new UserDataResponse (
            userData.getUserDataId(),
            userData.getCity(),
            userData.getStreet(),
            userData.getStreetNumber(),
            userData.getApartmentNumber(),
            userData.getZipCode(),
            userData.getPhoneNumber(),
            UserResponse.fromEntity(userData.getUser())
        );
    }
}