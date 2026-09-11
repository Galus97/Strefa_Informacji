package pl.strefainformacji.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
public class UserLoginRequest {
    @Length(min = 5)
    private String email;

    @Length(min = 5)
    private String password;
}
