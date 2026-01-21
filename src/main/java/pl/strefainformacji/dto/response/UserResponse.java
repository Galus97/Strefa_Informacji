package pl.strefainformacji.dto.response;

import pl.strefainformacji.entity.User;

public record UserResponse(Long userId, String firstName, String lastName, String email,
                           boolean enabled, String emailCode) {

    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.isEnabled(),
                user.getEmailCode()
        );
    }
}
