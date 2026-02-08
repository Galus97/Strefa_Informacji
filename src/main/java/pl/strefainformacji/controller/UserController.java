package pl.strefainformacji.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.strefainformacji.dto.request.UserRequest;
import pl.strefainformacji.dto.response.UserResponse;
import pl.strefainformacji.exception.ValidationException;
import pl.strefainformacji.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> showUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserResponse(id));
    }

//    @PostMapping
//    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
//        try {
//
//        } catch (ValidationException e) {
//            return ResponseEntity.badRequest().body(e.getValidationsErrors());
//        }
//    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }
}
