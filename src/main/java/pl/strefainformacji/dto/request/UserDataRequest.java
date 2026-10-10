package pl.strefainformacji.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
public class UserDataRequest {
    @Length(min = 3)
    private String city;
    @Length(min = 3)
    private String street;
    @NotNull
    private Integer streetNumber;
    @NotNull
    private Integer apartmentNumber;
    @NotBlank
    private String zipCode;
    @NotNull
    private Integer phoneNumber;
}