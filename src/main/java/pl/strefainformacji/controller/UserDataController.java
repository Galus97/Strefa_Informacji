package pl.strefainformacji.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.strefainformacji.dto.request.UserDataRequest;
import pl.strefainformacji.dto.response.UserDataResponse;
import pl.strefainformacji.model.User;
import pl.strefainformacji.service.UserDataService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userdata")
public class UserDataController {
    private final UserDataService userDataService;

    @GetMapping
    public ResponseEntity<UserDataResponse> getUserDataByUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userDataService.getUserDataByUser(user));
    }

    @PostMapping
    public ResponseEntity<UserDataResponse> saveUserData(
            @RequestBody @Valid UserDataRequest userDataRequest,
            @AuthenticationPrincipal User user) {
        UserDataResponse savedUserData = userDataService.saveUserData(userDataRequest, user);

        return ResponseEntity.created(URI.create("/userdata"))
                .body(savedUserData);
    }

    @PutMapping
    public ResponseEntity<UserDataResponse> updateUserData(
            @RequestBody @Valid UserDataRequest userDataRequest,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userDataService.updateUserData(userDataRequest, user));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserData(@AuthenticationPrincipal User user) {
        userDataService.deleteUserData(user);
        return ResponseEntity.noContent().build();
    }
}
