package pl.strefainformacji.mapper;

import org.springframework.stereotype.Component;
import pl.strefainformacji.dto.request.UserRequest;
import pl.strefainformacji.dto.response.UserResponse;
import pl.strefainformacji.model.User;

@Component
public class UserMapper {

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.isEnabled(),
                user.isSubscriber()
        );
    }

    public static User toUserModel(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .isSubscriber(userRequest.isSubscriber())
                .build();
    }
}
