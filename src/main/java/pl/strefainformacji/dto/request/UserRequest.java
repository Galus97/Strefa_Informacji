package pl.strefainformacji.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRequest {
    private Long userId;
    @Size(min = 3)
    private String firstName;
    @Size(min = 3)
    private String lastName;
    @Size(min = 5)
    private String email;
    @Size(min = 5)
    private String password;
    private boolean isSubscriber;
}
