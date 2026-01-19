package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.strefainformacji.component.CurrentUser;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.entity.User;
import pl.strefainformacji.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MessageService messageService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        throwIfEmailIsInvalid(email);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new CurrentUser(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), user);
        }
        throw new UsernameNotFoundException(messageService.getMessage(ErrorMessages.USER_NOT_FOUND, email));
    }

    private void throwIfEmailIsInvalid(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.EMAIL_IS_INVALID, email));
        }
    }
}
