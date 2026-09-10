package pl.strefainformacji.dto.request;

import org.hibernate.validator.constraints.Length;

public class UserRegisterRequest {

    @Length(min = 3)
    private String firstName;

    @Length(min = 3)
    private String lastName;

    @Length(min = 5)
    private String email;

    @Length(min = 5)
    private String password;

    @Length(min = 5)
    private String repeatPassword;
}
