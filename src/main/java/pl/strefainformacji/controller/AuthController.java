package pl.strefainformacji.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    /*
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserRequest userRequest) {
        try {
            UserResponse savedUser = userService.saveNewUser(userRequest);
            return ResponseEntity.created(URI.create("/user/" + savedUser.userId()))
                    .body(savedUser);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getValidationsErrors());
        }
    }
     */
}
