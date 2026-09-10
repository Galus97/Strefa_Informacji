package pl.strefainformacji.dto.request;

import org.hibernate.validator.constraints.Length;

public class UserLoginRequest {
    @Length(min = 5)
    private String email;
    @Length(min = 5)
    private String password;
}
