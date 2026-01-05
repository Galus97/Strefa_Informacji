package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.entity.Article;
import pl.strefainformacji.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MessageService messageService;

    private void throwIfIdIsInvalid (Long id, String message) {
        if(id == null || id < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    private Article getArticleOrThrowIfNotExist (Long id, String message) {
        return articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(messageService.getMessage(message)));
    }
}
