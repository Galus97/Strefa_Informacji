package pl.strefainformacji.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDataRequest {
    private Long UserDataId;
    private String city;
    private String street;
    private Integer streetNumber;
    private Integer apartmentNumber;
    private String zipCode;
    private Integer phoneNumber;
    private UserRequest userRequest;
}