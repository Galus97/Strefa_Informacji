package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        throwIfIdIsInvalid(articleId);
        return ArticleResponse.fromEntity(getArticleOrThrowIfNotExist(articleId));
    }

    public ArticleResponse saveArticle(ArticleRequest articleRequest) {
        return ArticleResponse.fromEntity(buildArticle(articleRequest));
    }

    public void deleteArticle(Long articleId) {
        throwIfIdIsInvalid(articleId);
        articleRepository.deleteById(articleId);
    }

    @Transactional
    public ArticleResponse updateArticle(ArticleRequest articleRequest) {
        throwIfRequestIsNull(articleRequest);
        throwIfIdIsInvalid(articleRequest.getArticleId());

        Article article = getArticleOrThrowIfNotExist(articleRequest.getArticleId());
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        article.setShortDescription(articleRequest.getShortDescription());

        return ArticleResponse.fromEntity(articleRepository.save(article));
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

    private void throwIfIdIsInvalid (Long id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_ARTICLE_ID);
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
