package pl.strefainformacji.dto.response;

import pl.strefainformacji.model.UserData;

public record UserDataResponse (
        Long userDataId,
        String city,
        String street,
        Integer streetNumber,
        Integer apartmentNumber,
        String zipCode,
        Integer phoneNumber,
        Long userId) {
}