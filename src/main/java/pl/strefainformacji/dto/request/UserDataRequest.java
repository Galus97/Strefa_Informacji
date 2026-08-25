package pl.strefainformacji.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDataRequest {
    private Long userDataId;
    @Size(min = 3)
    private String city;
    @Size(min = 3)
    private String street;
    @NotNull
    private Integer streetNumber;
    @NotNull
    private Integer apartmentNumber;
    @NotBlank
    private String zipCode;
    @NotNull
    private Integer phoneNumber;
    @NotNull
    private Long userId;
}