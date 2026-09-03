package pl.strefainformacji.dto.response;

public record UserDataResponse (
        String city,
        String street,
        Integer streetNumber,
        Integer apartmentNumber,
        String zipCode,
        Integer phoneNumber) {
}