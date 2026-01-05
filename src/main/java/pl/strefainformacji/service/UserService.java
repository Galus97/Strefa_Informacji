package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.strefainformacji.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
