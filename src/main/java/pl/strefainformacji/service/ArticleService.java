package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.strefainformacji.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void throwIfIdIsInvalid (Long id, String message) {
        if(id == null || id < 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
