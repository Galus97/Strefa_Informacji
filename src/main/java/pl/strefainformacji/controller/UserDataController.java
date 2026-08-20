package pl.strefainformacji.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.strefainformacji.dto.request.UserDataRequest;
import pl.strefainformacji.dto.response.UserDataResponse;
import pl.strefainformacji.service.UserDataService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userdata")
public class UserDataController {
    private final UserDataService userDataService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDataResponse> showUserData(@PathVariable Long id) {
        return ResponseEntity.ok(userDataService.getUserData(id));
    }

    @PostMapping
    public ResponseEntity<UserDataResponse> saveUserData(@RequestBody UserDataRequest userDataRequest) {
        UserDataResponse savedUserData = userDataService.saveUserData(userDataRequest);
        return ResponseEntity.created(URI.create("/userdata/" + savedUserData.UserDataId()))
                .body(savedUserData);
    }

    @PutMapping
    public ResponseEntity<UserDataResponse> updateUserData(@RequestBody UserDataRequest userDataRequest) {
        return ResponseEntity.ok(userDataService.updateUserData(userDataRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserData(@PathVariable Long id) {
        userDataService.deleteUserData(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDataResponse> getUserDataByUser(@PathVariable Long id) {
        return ResponseEntity.ok(userDataService.getUserDataByUser(id));
    }
}
