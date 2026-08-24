package pl.strefainformacji.dto.response;

public record UserResponse(
        String firstName,
        String lastName,
        String email,
        boolean enabled,
        boolean isSubscriber) {
}
