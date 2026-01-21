package pl.strefainformacji.dto.response;

public record UserResponse(Long userId, String firstName, String lastName, String email,boolean enabled, String emailCode) {
}
