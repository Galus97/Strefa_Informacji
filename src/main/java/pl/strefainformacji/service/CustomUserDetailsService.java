package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MessageService messageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    private void throwIfEmailIsInvalid(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.EMAIL_IS_INVALID, email));
        }
    }
}
