package pl.strefainformacji.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.strefainformacji.dto.response.UserDataResponse;
import pl.strefainformacji.service.UserDataService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userdata")
public class UserDataController {
    private final UserDataService userDataService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDataResponse> showUserData(@PathVariable Long id) {
        return ResponseEntity.ok(userDataService.getUserData(id));
    }
}
