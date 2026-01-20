package pl.strefainformacji.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRequest {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
