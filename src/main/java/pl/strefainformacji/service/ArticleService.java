package pl.strefainformacji.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
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
        articleRepository.delete(getArticleOrThrowIfNotExist(articleId));
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

    public List<ArticleResponse> getAllArticles() {
        List<ArticleResponse>  articleResponses = new ArrayList<>();

        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> articleResponses.add(ArticleResponse.fromEntity(article)));

        return articleResponses;
    }

    private Article buildArticle(ArticleRequest articleRequest) {
        throwIfRequestIsNull(articleRequest);
        return Article.builder()
                .articleId(articleRequest.getArticleId())
                .title(articleRequest.getTitle())
                .shortDescription(articleRequest.getShortDescription())
                .description(articleRequest.getDescription())
                .build();
    }

    private void throwIfIdIsInvalid(Long articleId) {
        if (articleId == null || articleId <= 0) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.INVALID_ARTICLE_ID, articleId));
        }
    }

    private Article getArticleOrThrowIfNotExist(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException(messageService.getMessage(ErrorMessages.ARTICLE_NOT_FOUND, articleId)));
    }

    private void throwIfRequestIsNull(ArticleRequest articleRequest) {
        if (articleRequest == null) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.ARTICLE_REQUEST_IS_NULL));
        }
    }
}
