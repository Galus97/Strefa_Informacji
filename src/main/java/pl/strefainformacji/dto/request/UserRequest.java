package pl.strefainformacji.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
public class UserRequest {
    private Long userId;
    @Length(min = 3)
    private String firstName;
    @Length(min = 3)
    private String lastName;
    @Length(min = 5)
    private String email;
    @Length(min = 5)
    private String password;
    private boolean isSubscriber;
}
