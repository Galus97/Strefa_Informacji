package pl.strefainformacji.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.strefainformacji.dto.request.UserRegisterRequest;
import pl.strefainformacji.dto.response.UserResponse;
import pl.strefainformacji.exception.ValidationException;
import pl.strefainformacji.service.UserService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserRegisterRequest request) {
        try {
            UserResponse savedUser = userService.saveNewUser(request);
            return ResponseEntity.created(URI.create("/user/" + savedUser.userId()))
                    .body(savedUser);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getValidationsErrors());
        }
    }
}
