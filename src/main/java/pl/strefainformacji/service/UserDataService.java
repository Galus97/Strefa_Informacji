package pl.strefainformacji.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.strefainformacji.repository.UserDataRepository;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserDataRepository userDataRepository;
}