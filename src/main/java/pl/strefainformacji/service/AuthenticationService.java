package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.strefainformacji.dto.request.UserLoginRequest;
import pl.strefainformacji.dto.response.UserLoginResponse;
import pl.strefainformacji.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserLoginResponse authenticate(UserLoginRequest request) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));
        String token = jwtUtil.generateToken(authenticate.getName());

        return new UserLoginResponse(token);
    }
}
