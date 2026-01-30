package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final MessageService messageService;
    private final CacheManager cacheManager;

    @Async
    public void sendEmail(String email) {
        throwIfEmailIsInvalid(email);
    }

    private String generateActiveCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10000, 99999));
    }

    private void throwIfEmailIsInvalid(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.EMAIL_IS_INVALID, email));
        }
    }
}
