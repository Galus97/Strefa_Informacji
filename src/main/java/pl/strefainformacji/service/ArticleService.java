package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.request.ArticleRequest;
import pl.strefainformacji.dto.response.ArticleResponse;
import pl.strefainformacji.entity.Article;
import pl.strefainformacji.exception.ArticleNotFoundException;
import pl.strefainformacji.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MessageService messageService;

    public ArticleResponse getArticleResponse(Long articleId) {
        throwIfIdIsInvalid(articleId, ErrorMessages.INVALID_ARTICLE_ID);
        return ArticleResponse.fromEntity(getArticleOrThrowIfNotExist(articleId));
    }



    private Article buildArticle(ArticleRequest articleRequest) {
        throwIfRequestIsNull(articleRequest);
        return  Article.builder()
                .articleId(articleRequest.getArticleId())
                .title(articleRequest.getTitle())
                .shortDescription(articleRequest.getShortDescription())
                .description(articleRequest.getDescription())
                .build();
    }

    private void throwIfIdIsInvalid (Long id, String message) {
        if(id == null || id < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    private Article getArticleOrThrowIfNotExist (Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new ArticleNotFoundException(messageService.getMessage(ErrorMessages.ARTICLE_NOT_FOUND, id)));
    }

    private void throwIfRequestIsNull (ArticleRequest articleRequest) {
        if (articleRequest == null) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.ARTICLE_REQUEST_IS_NULL));
        }
    }
}
